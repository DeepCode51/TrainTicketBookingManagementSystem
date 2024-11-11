package cn.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javafx.print.Collation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Checi;
import cn.entity.Gonggao;
import cn.entity.Orders;
import cn.entity.Seat;
import cn.entity.Site;
import cn.entity.Users;
import cn.service.CheciService;
import cn.service.GonggaoService;
import cn.service.OrdersService;
import cn.service.SeatService;
import cn.service.SiteService;
import cn.service.UsersService;
import cn.test.Dijkstra;
import cn.test.Node;
import cn.util.Const;
import cn.util.PageBean;
import cn.util.ServerResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.StringUtils;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nnn
 * @since 2019-12-07
 */
@Controller
public class IndexController {
	@Autowired
	UsersService usersService;
	@Autowired
	GonggaoService gonggaoService;
	@Autowired
	OrdersService ordersService;
	@Autowired
	CheciService checiService;
	@Autowired
	SiteService siteService;
	@Autowired
	SeatService seatService;

	@RequestMapping("/index")
	public String index(Model model, String bsite,String esite,HttpSession session) {
		QueryWrapper<Gonggao> gwrapper = new QueryWrapper<Gonggao>();
		gwrapper.orderByDesc("id");
		List<Gonggao> glist = gonggaoService.list(gwrapper);
		session.setAttribute("glist", glist);
		
		boolean flag=true;
		
		if(bsite!=null && esite!=null){
			List<Checi> list=new ArrayList<Checi>();
			//1、始发-终点,如果刚好是始发和终点
			QueryWrapper<Checi> wrapper=new QueryWrapper<Checi>();
			wrapper.eq("bsite", bsite);
			wrapper.eq("esite", esite);
			wrapper.eq("isdel", 0);
			list=checiService.list(wrapper);
			if(list.size()==0){
				List<Integer> cidMap=checiService.getCid2(bsite, esite);
				//2、同一车次直达
				if(cidMap!=null && cidMap.size()>0){
					wrapper=new QueryWrapper<Checi>();
					wrapper.in("id", cidMap);
					wrapper.eq("isdel", 0);
					list=checiService.list(wrapper);
//					int n=list.size();
//					//同一车次判断顺序
//					for(int i=0;i<n;i++){
//						Checi c=list.get(i);
//						//出发
//						QueryWrapper<Site> siteWrapper1=new QueryWrapper<Site>();
//						siteWrapper1.eq("cid", c.getId());
//						siteWrapper1.eq("sname",bsite);
//						Site site1=siteService.getOne(siteWrapper1);
//						//到站
//						QueryWrapper<Site> siteWrapper2=new QueryWrapper<Site>();
//						siteWrapper2.eq("cid", c.getId());
//						siteWrapper2.eq("sname",esite);
//						Site site2=siteService.getOne(siteWrapper2);
//						//如果顺序不正确就删除
//						if(site1!=null && site2!=null){
//							if(site1.getStime()>site2.getStime()){
//								list.remove(c);
//								i--;//移除后少一个
//							}
//						}
//					}
					
				}else{
					flag=false;
					//3、多车次中转,Dijkstra算法选择中转点
					//包含起点的所有站，包含终点的所有站
					List<Integer> bcids=checiService.getCid3(bsite);
					List<Integer> ecids=checiService.getCid3(esite);
					ArrayList<Node> nodes=new ArrayList<Node>();
					//起点所有节点
					if(bcids!=null && bcids.size()>0){
						for(Integer cid : bcids){
							QueryWrapper<Site> siteWrapper1=new QueryWrapper<Site>();
							siteWrapper1.eq("cid",cid);
							List<Site> bsites=siteService.list(siteWrapper1);
							for(int i=0;i<bsites.size()-1;i++){
								Site b=bsites.get(i);
								Site e=bsites.get(i+1);
								Node node=new Node();
								node.setCheci(cid);
								node.setBsite(b.getSname());
								node.setEsite(e.getSname());
								node.setTime(e.getStime()-b.getStime());
								nodes.add(node);
							}
						}
					}
					//终点所有节点
					if(ecids!=null && ecids.size()>0){
						for(Integer cid : ecids){
							QueryWrapper<Site> siteWrapper1=new QueryWrapper<Site>();
							siteWrapper1.eq("cid",cid);
							List<Site> bsites=siteService.list(siteWrapper1);
							for(int i=0;i<bsites.size()-1;i++){
								Site b=bsites.get(i);
								Site e=bsites.get(i+1);
								Node node=new Node();
								node.setCheci(cid);
								node.setBsite(b.getSname());
								node.setEsite(e.getSname());
								node.setTime(e.getStime()-b.getStime());
								nodes.add(node);
							}
						}
					}
					
					//调用算法
					Dijkstra dijkstra=new Dijkstra();
					
					dijkstra.addNodes(nodes);
					
					ArrayList<Node> result=dijkstra.wrap(bsite,esite);
					
					if(result.size()>0){
						//找到了中转路线
						List<Integer> cids=new ArrayList<Integer>();
						for(int i=0;i<result.size();i++){
							Node node=result.get(i);
							if(!cids.contains(node.getCheci())){
								cids.add(node.getCheci());
							}
						}
						for(Integer cid:cids){
							//将能中转的车次查出
							QueryWrapper<Checi> cwrapper=new QueryWrapper<Checi>();
							cwrapper.eq("id", cid);
							cwrapper.eq("isdel", 0);
							Checi c=checiService.getOne(cwrapper);
							c.setSeats("需要中转，请分开预订!");
							
							if(c!=null){
								list.add(c);
							}
						}
					}	
					
				}
			}
			
			
				//站点和票价
				for(Checi c:list){
					//查询站点
					QueryWrapper<Site> siteWrapper=new QueryWrapper<Site>();
					siteWrapper.eq("cid",c.getId());
					List<Site> siteList=siteService.list(siteWrapper);
					StringBuffer sb=new StringBuffer();
					int bsid=0;
					int esid=0;
					for(Site s:siteList){
						if(s.getSname().equals(bsite)){
							bsid=s.getId();
							sb.append("<div><span style='color:#F00'>"+s.getSname()+"</span>&nbsp;&nbsp;达到时间"+s.getAtime()+"&nbsp;&nbsp;历时"+s.getStime()+"分</div>");
						}else if(s.getSname().equals(esite)){
							esid=s.getId();
							sb.append("<div><span style='color:#F00'>"+s.getSname()+"</span>&nbsp;&nbsp;达到时间"+s.getAtime()+"&nbsp;&nbsp;历时"+s.getStime()+"分</div>");
							break;
						}else{
							sb.append("<div><span>"+s.getSname()+"</span>&nbsp;&nbsp;达到时间"+s.getAtime()+"&nbsp;&nbsp;历时"+s.getStime()+"分</div>");
						}
					}
					c.setSites(sb.toString());
					if(flag){//无需中转的直接出价格
						sb=new StringBuffer();
						//查询票价
						QueryWrapper<Seat> seatWrapper=new QueryWrapper<Seat>();
						seatWrapper.eq("cid",c.getId());
						List<Seat> seatList=seatService.list(seatWrapper);
						for(Seat s:seatList){
	//						if(s.getSid()==bsid){
	//							sb.append("<div>"+s.getName()+"&nbsp;&nbsp;<span style='color:#F00'>价格"+s.getPrice()+"元</span>&nbsp;&nbsp;余票"+s.getNum()+"</div>");
	//						}else 
							if(s.getSid()==esid){
								sb.append("<div><input type='radio' name='sid' value='"+s.getId()+"'/>"+s.getName()+"&nbsp;&nbsp;<span style='color:#F00'>价格<span class='p"+s.getId()+"'>"+s.getPrice()+"</span>元</span>&nbsp;&nbsp;余票"+s.getNum()+"</div>");
							}
	//						else{
	//							sb.append("<div>"+s.getName()+"&nbsp;&nbsp;<span>价格"+s.getPrice()+"元</span>&nbsp;&nbsp;余票"+s.getNum()+"</div>");
	//						}
						}
						c.setSeats(sb.toString());
					}
				}
			
			
			model.addAttribute("list",list);
			model.addAttribute("bsite", bsite);
			model.addAttribute("esite", esite);
		}
		
		return "index";
	}

	

	@RequestMapping("/login")
	@ResponseBody
	public String login(String username, String password, Integer role,
			HttpSession session) {
		session.setAttribute("role", role);
		QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
		wrapper.eq("role", role);
		wrapper.eq("isdel", 0);
		wrapper.eq("username", username);
		wrapper.eq("password", password);
		List<Users> list = usersService.list(wrapper);
		if (list != null && list.size() > 0) {
			Users user = list.get(0);
			session.setAttribute("users", user);
			QueryWrapper<Gonggao> gwrapper = new QueryWrapper<Gonggao>();
			gwrapper.orderByDesc("id");
			List<Gonggao> glist = gonggaoService.list(gwrapper);
			session.setAttribute("glist", glist);
			return "ok";
		}
		return "error";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping("reg")
	@ResponseBody
	public ServerResponse<Users> reg(Users user) {
		QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
		wrapper.eq("username", user.getUsername());
		Users u = usersService.getOne(wrapper);
		if (u != null) {
			return new ServerResponse<Users>("1", "该用户名已经存在!");
		} else {
			user.setRole(1);
			user.setIsdel(0);
			usersService.save(user);
			return new ServerResponse<Users>("0", "注册成功!");
		}
	}

	@RequestMapping("/uppwd")
	@ResponseBody
	public ServerResponse<String> uppwd(String password, HttpSession session) {
		Users a = (Users) session.getAttribute("users");
		a.setPassword(password);
		boolean flag = usersService.updateById(a);
		if (flag) {
			session.invalidate();
			return new ServerResponse<String>("0", "修改成功，请重新登录");
		}
		return new ServerResponse<String>("1", "修改失败");
	}

}

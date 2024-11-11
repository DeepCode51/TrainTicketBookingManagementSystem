package cn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
import cn.util.Const;
import cn.util.ServerResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nnn
 * @since 2019-12-07
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	UsersService usersService;
	@Autowired
	OrdersService ordersService;
	@Autowired
	CheciService checiService;
	@Autowired
	SiteService siteService;
	@Autowired
	SeatService seatService;
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Orders> add(Integer cid,Integer sid,Double price,String bsite,String esite,HttpSession session) {
		Users users=(Users)session.getAttribute("users");
		Orders orders=new Orders();
		orders.setUid(users.getId());
		orders.setCid(cid);
		orders.setSid(sid);
		orders.setPrice(price);
		orders.setBsite(bsite);
		orders.setEsite(esite);
		//发出时间
		QueryWrapper<Site> siteWrapper1=new QueryWrapper<Site>();
		siteWrapper1.eq("cid", cid);
		siteWrapper1.eq("sname",bsite);
		Site site1=siteService.getOne(siteWrapper1);
		orders.setBtime(site1.getAtime());
		
		QueryWrapper<Site> siteWrapper2=new QueryWrapper<Site>();
		siteWrapper2.eq("cid", cid);
		siteWrapper2.eq("sname",esite);
		Site site2=siteService.getOne(siteWrapper2);
		orders.setEtime(site2.getAtime());
		
		orders.setOptime(Const.getFullTime());
		orders.setStatus("未取票");
		boolean flag =ordersService.save(orders);
		if (flag) {
			return new ServerResponse<Orders>("0", "订票成功!");
		} else {
			return new ServerResponse<Orders>("1", "订票失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Orders> update(Orders orders) {
		Orders o=ordersService.getById(orders.getId());
		if("已取票".equals(orders.getStatus())){
			Seat seat=seatService.getById(o.getSid());
			seat.setNum(seat.getNum()-1);
			seat.updateById();
		}else if("已退票".equals(orders.getStatus())){
			Seat seat=seatService.getById(o.getSid());
			seat.setNum(seat.getNum()+1);
			seat.updateById();
		}
			
		boolean flag = ordersService.updateById(orders);
		if (flag) {
			return new ServerResponse<Orders>("0", "操作成功!");
		} else {
			return new ServerResponse<Orders>("1", "操作失败!");
		}
	}
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		Orders orders = ordersService.getById(id);
		model.addAttribute("g", orders);
		return "ordersdetail";
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Orders> delete(Orders orders) {
		boolean flag = ordersService.removeById(orders);
		if (flag) {
			return new ServerResponse<Orders>("0", "删除成功!");
		} else {
			return new ServerResponse<Orders>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Orders> list(Integer page, Integer limit,String name) {
		QueryWrapper<Orders> wrapper = new QueryWrapper<Orders>();
		if(!StringUtils.isEmpty(name)){
			wrapper.like("bsite", name);
			wrapper.or();
			wrapper.like("esite", name);
		}
		wrapper.orderByDesc("id");
		IPage<Orders> page_orders = new Page<Orders>(page, limit);
		page_orders = ordersService.page(page_orders, wrapper);
		// 会自动查出总条数
		int count = (int) page_orders.getTotal();
		List<Orders> list=page_orders.getRecords();
		for(Orders o :list){
			o.setUser(usersService.getById(o.getUid()));
			o.setSeat(seatService.getById(o.getSid()));
			o.setCheci(checiService.getById(o.getCid()));
		}
		return new ServerResponse<Orders>("0", "", count,list);
	}
	
	@RequestMapping("index")
	public String index(Integer page, Integer limit,Integer id,Model model) {
		QueryWrapper<Orders> wrapper = new QueryWrapper<Orders>();
		wrapper.eq("uid", id);
		wrapper.orderByDesc("id");
		List<Orders> list=ordersService.list(wrapper);
		for(Orders o :list){
			o.setUser(usersService.getById(o.getUid()));
			o.setSeat(seatService.getById(o.getSid()));
			o.setCheci(checiService.getById(o.getCid()));
		}
		model.addAttribute("list", list);
		return "orders";
	}
}

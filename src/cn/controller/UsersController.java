package cn.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.entity.Users;
import cn.service.UsersService;
import cn.util.Const;
import cn.util.ServerResponse;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nnn
 * @since 2019-12-07
 */
@Controller
@RequestMapping("/user")
public class UsersController {
	@Autowired
	UsersService usersService;

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Users> list(Integer page,Integer limit,String name) {
		QueryWrapper<Users> wapper = new QueryWrapper<Users>();
		if (!StringUtils.isEmpty(name)) {
			wapper.like("name", name);
		}
		wapper.eq("role", 1);
		wapper.orderByAsc("id");
		IPage<Users> page_user=new Page<Users>(page,limit);
		page_user = usersService.page(page_user, wapper);
		//会自动查出总条数
		int count=(int)page_user.getTotal();
		return new ServerResponse<Users>("0","",count,page_user.getRecords());
	}
	@RequestMapping("jsonlist")
	@ResponseBody
	public List<Users> jsonlist() {
		QueryWrapper<Users> wapper = new QueryWrapper<Users>();
		List<Users> list=usersService.list(wapper);
		return list;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Users> add(Users user) {
		QueryWrapper<Users> wapper = new QueryWrapper<Users>();
		wapper.eq("username", user.getUsername());
		Users u=usersService.getOne(wapper);
		if(u!=null){
			return new ServerResponse<Users>("1","该用户名已经存在!");
		}else{
			user.setRole(1);
			user.setIsdel(0);
			usersService.save(user);
			return new ServerResponse<Users>("0","添加成功!");
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Users> update(Users user) {
		boolean flag=usersService.updateById(user);
		if(flag){
			return new ServerResponse<Users>("0","修改成功!");
		}else{
			return new ServerResponse<Users>("1","修改失败!");
		}
	}
	@RequestMapping("getById")
	@ResponseBody
	public Users getById(Integer id) {
		Users user=usersService.getById(id);
		return user;
	}
	
	@RequestMapping("toupdate")
	public String toupdate(Integer id,Model model) {
		Users user=usersService.getById(id);
		model.addAttribute("v", user);
		return "update_users";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Users> delete(Users user) {
		user.setIsdel(1);
		boolean flag=usersService.updateById(user);
		if(flag){
			return new ServerResponse<Users>("0","操作成功!");
		}else{
			return new ServerResponse<Users>("1","操作失败!");
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		Integer role = (Integer) session.getAttribute("role");
		session.invalidate();
		return "manage/login";
	}
}


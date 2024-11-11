package cn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.entity.Message;
import cn.entity.Reply;
import cn.entity.Users;
import cn.service.MessageService;
import cn.service.ReplyService;
import cn.service.UsersService;
import cn.util.Const;
import cn.util.PageBean;
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
@RequestMapping("/message")
public class MessageController {
	@Autowired
	MessageService messageService;
	@Autowired
	UsersService usersService;
	@Autowired
	ReplyService replyService;
	
	@RequestMapping("add")
	public String add(Message message,HttpSession session) {
		Users user=(Users)session.getAttribute("users");
		message.setUid(user.getId());
		message.setIsdel(0);
		message.setOptime(Const.getFullTime());
		messageService.save(message);
		return "redirect:/message/index";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Message> update(Message message,HttpSession session) {
		messageService.updateById(message);
		return new ServerResponse<Message>("0", "操作成功!");
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Message> delete(Message message) {
		message.setIsdel(1);
		boolean flag = messageService.updateById(message);
		if (flag) {
			return new ServerResponse<Message>("0", "删除成功!");
		} else {
			return new ServerResponse<Message>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Message> list(Integer page, Integer limit,HttpSession session) {
		QueryWrapper<Message> wrapper = new QueryWrapper<Message>();
		Integer role=(Integer)session.getAttribute("role");
		if(role!=null && (role==1 || role==2)){
			Users user=(Users)session.getAttribute("users");
			wrapper.eq("uid",user.getId());
		}
		wrapper.eq("isdel", 0);
		wrapper.orderByDesc("optime");
		IPage<Message> page_message = new Page<Message>(page, limit);
		page_message = messageService.page(page_message, wrapper);
		// 会自动查出总条数
		int count = (int) page_message.getTotal();
		// 关联对象
		List<Message> list = page_message.getRecords();
		for (Message m : list) {
			Users u = usersService.getById(m.getUid());
			m.setUser(u);
		}
		return new ServerResponse<Message>("0", "", count,list);
	}

	@RequestMapping("index")
	public String index(PageBean pageBean,Model model) {
		QueryWrapper<Message> wrapper = new QueryWrapper<Message>();
		wrapper.eq("isdel", 0);
		wrapper.orderByDesc("optime");
		IPage<Message> page_message = new Page<Message>(pageBean.getPageNo(),pageBean.getPageSize());
		page_message = messageService.page(page_message, wrapper);
		// 会自动查出总条数
		// 关联对象
		int count = (int) page_message.getTotal();
		pageBean.setTotalCount(count);
		List<Message> list = page_message.getRecords();
		for (Message m : list) {
			Users u = usersService.getById(m.getUid());
			m.setUser(u);
		}
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("list", list);
		return "message";
	}
	
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		Message m=messageService.getById(id);
		Users u = usersService.getById(m.getUid());
		m.setUser(u);
		QueryWrapper<Reply> wrapper = new QueryWrapper<Reply>();
		wrapper.eq("mid", id);
		wrapper.orderByDesc("id");
		List<Reply> list=replyService.list(wrapper);
		for (Reply r : list) {
			Users ru = usersService.getById(r.getUid());
			r.setUser(ru);
		}
		model.addAttribute("v", m);
		model.addAttribute("list", list);
		return "messagedetail";
	}
}

package cn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.entity.Message;
import cn.entity.Reply;
import cn.entity.Users;
import cn.service.MessageService;
import cn.service.ReplyService;
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
 * @since 2019-12-05
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	ReplyService replyService;
	@Autowired
	UsersService usersService;
	@Autowired
	MessageService messageService;

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Reply> list(Integer page, Integer mid, Model model) {
		if (page == null) {
			page = 1;
		}
		QueryWrapper<Reply> wrapper = new QueryWrapper<Reply>();
		wrapper.eq("mid", mid);
		wrapper.orderByDesc("id");
		List<Reply> list = replyService.list(wrapper);
		// 关联对象
		for (Reply m : list) {
			Users u = usersService.getById(m.getUid());
			m.setUser(u);
		}
		int count = list.size();
		return new ServerResponse<Reply>("0", "", count, list);
	}

	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Reply> add(Integer mid, Reply reply, HttpSession session) {
		Users users = (Users) session.getAttribute("users");
		reply.setUid(users.getId());
		reply.setMid(mid);
		reply.setOptime(Const.getFullTime());
		replyService.save(reply);
		return new ServerResponse<Reply>("0", "回复成功!");
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Reply> delete(Integer id) {
		boolean flag=replyService.removeById(id);
		if (flag) {
			return new ServerResponse<Reply>("0", "删除成功!");
		} else {
			return new ServerResponse<Reply>("1", "删除失败!");
		}
	}
}

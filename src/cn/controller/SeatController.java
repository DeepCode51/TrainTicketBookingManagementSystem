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

import cn.entity.Seat;
import cn.service.CheciService;
import cn.service.SeatService;
import cn.service.SiteService;
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
@RequestMapping("/seat")
public class SeatController {
	@Autowired
	SeatService seatService;
	@Autowired
	CheciService checiService;
	@Autowired
	SiteService siteService;
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Seat> add(Seat seat) {
		boolean flag = seatService.save(seat);
		if (flag) {
			return new ServerResponse<Seat>("0", "添加成功!");
		} else {
			return new ServerResponse<Seat>("1", "添加失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Seat> update(Seat seat) {
		boolean flag = seatService.updateById(seat);
		if (flag) {
			return new ServerResponse<Seat>("0", "修改成功!");
		} else {
			return new ServerResponse<Seat>("1", "修改失败!");
		}
	}
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		Seat seat = seatService.getById(id);
		model.addAttribute("g", seat);
		return "seatdetail";
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Seat> delete(Seat seat) {
		boolean flag = seatService.removeById(seat);
		if (flag) {
			return new ServerResponse<Seat>("0", "删除成功!");
		} else {
			return new ServerResponse<Seat>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Seat> list(Integer page, Integer limit,String name,Integer sid) {
		QueryWrapper<Seat> wrapper = new QueryWrapper<Seat>();
		wrapper.eq("sid", sid);
		if (!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		IPage<Seat> page_seat = new Page<Seat>(page, limit);
		page_seat = seatService.page(page_seat, wrapper);
		// 会自动查出总条数
		int count = (int) page_seat.getTotal();
		List<Seat> list=page_seat.getRecords();
		for(Seat s:list){
			s.setCheci(checiService.getById(s.getCid()));
			s.setSite(siteService.getById(s.getSid()));
		}
		return new ServerResponse<Seat>("0", "", count,list);
	}
}

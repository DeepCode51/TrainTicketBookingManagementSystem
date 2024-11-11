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

import cn.entity.Checi;
import cn.entity.Users;
import cn.service.CheciService;
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
@RequestMapping("/checi")
public class CheciController {
	@Autowired
	CheciService checiService;
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Checi> add(Checi checi) {
		checi.setIsdel(0);
		boolean flag = checiService.save(checi);
		if (flag) {
			return new ServerResponse<Checi>("0","添加成功");
		} else {
			return new ServerResponse<Checi>("1", "添加失败!");
		}
		
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Checi> update(Checi checi) {
		boolean flag = checiService.updateById(checi);
		if (flag) {
			return new ServerResponse<Checi>("0", "修改成功!");
		} else {
			return new ServerResponse<Checi>("1", "修改失败!");
		}
	}
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		Checi checi = checiService.getById(id);
		model.addAttribute("g", checi);
		return "checidetail";
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Checi> delete(Checi checi) {
		checi.setIsdel(1);
		boolean flag = checiService.updateById(checi);
		if (flag) {
			return new ServerResponse<Checi>("0", "删除成功!");
		} else {
			return new ServerResponse<Checi>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Checi> list(Integer page, Integer limit,String name) {
		QueryWrapper<Checi> wrapper = new QueryWrapper<Checi>();
		if (!StringUtils.isEmpty(name)) {
			wrapper.like("name", name);
		}
		wrapper.eq("isdel", 0);
		IPage<Checi> page_checi = new Page<Checi>(page, limit);
		page_checi = checiService.page(page_checi, wrapper);
		// 会自动查出总条数
		int count = (int) page_checi.getTotal();
		List<Checi> list=page_checi.getRecords();
		return new ServerResponse<Checi>("0", "", count,list);
	}
	
	@RequestMapping("jsonlist")
	@ResponseBody
	public List<Checi> jsonlist() {
		QueryWrapper<Checi> wrapper = new QueryWrapper<Checi>();
		wrapper.eq("isdel", 0);
		List<Checi> list=checiService.list(wrapper);
		return list;
	}
}

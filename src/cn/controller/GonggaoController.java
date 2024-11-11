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

import cn.entity.Gonggao;
import cn.service.GonggaoService;
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
@RequestMapping("/gonggao")
public class GonggaoController {
	@Autowired
	GonggaoService gonggaoService;
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Gonggao> add(Gonggao gonggao) {
		gonggao.setIsdel(0);
		gonggao.setOptime(Const.getFullTime());
		gonggaoService.save(gonggao);
		return new ServerResponse<Gonggao>("0");
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Gonggao> update(Gonggao gonggao) {
		boolean flag = gonggaoService.updateById(gonggao);
		if (flag) {
			return new ServerResponse<Gonggao>("0", "修改成功!");
		} else {
			return new ServerResponse<Gonggao>("1", "修改失败!");
		}
	}
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		Gonggao gonggao = gonggaoService.getById(id);
		model.addAttribute("g", gonggao);
		return "gonggaodetail";
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Gonggao> delete(Gonggao gonggao) {
		gonggao.setIsdel(1);
		boolean flag = gonggaoService.updateById(gonggao);
		if (flag) {
			return new ServerResponse<Gonggao>("0", "删除成功!");
		} else {
			return new ServerResponse<Gonggao>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Gonggao> list(Integer page, Integer limit,String title) {
		QueryWrapper<Gonggao> wrapper = new QueryWrapper<Gonggao>();
		if (!StringUtils.isEmpty(title)) {
			wrapper.like("title", title);
		}
		wrapper.eq("isdel", 0);
		wrapper.orderByDesc("optime");
		IPage<Gonggao> page_gonggao = new Page<Gonggao>(page, limit);
		page_gonggao = gonggaoService.page(page_gonggao, wrapper);
		// 会自动查出总条数
		int count = (int) page_gonggao.getTotal();
		List<Gonggao> list=page_gonggao.getRecords();
		return new ServerResponse<Gonggao>("0", "", count,list);
	}
}

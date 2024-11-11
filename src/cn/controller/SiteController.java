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

import cn.entity.Site;
import cn.service.CheciService;
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
@RequestMapping("/site")
public class SiteController {
	@Autowired
	SiteService siteService;
	@Autowired
	CheciService checiService;
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<Site> add(Site site) {
		boolean flag =siteService.save(site);
		if (flag) {
			return new ServerResponse<Site>("0", "添加成功!");
		} else {
			return new ServerResponse<Site>("1", "添加失败!");
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<Site> update(Site site) {
		boolean flag = siteService.updateById(site);
		if (flag) {
			return new ServerResponse<Site>("0", "修改成功!");
		} else {
			return new ServerResponse<Site>("1", "修改失败!");
		}
	}
	@RequestMapping("detail")
	public String detail(Integer id,Model model) {
		Site site = siteService.getById(id);
		model.addAttribute("g", site);
		return "sitedetail";
	}

	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<Site> delete(Site site) {
		boolean flag = siteService.removeById(site);
		if (flag) {
			return new ServerResponse<Site>("0", "删除成功!");
		} else {
			return new ServerResponse<Site>("1", "删除失败!");
		}
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse<Site> list(Integer page, Integer limit,String name,Integer cid) {
		QueryWrapper<Site> wrapper = new QueryWrapper<Site>();
		wrapper.eq("cid", cid);
		if (!StringUtils.isEmpty(name)) {
			wrapper.like("sname", name);
		}
		IPage<Site> page_site = new Page<Site>(page, limit);
		page_site = siteService.page(page_site, wrapper);
		// 会自动查出总条数
		int count = (int) page_site.getTotal();
		List<Site> list=page_site.getRecords();
		for(Site s:list){
			s.setCheci(checiService.getById(s.getCid()));
		}
		return new ServerResponse<Site>("0", "", count,list);
	}
}

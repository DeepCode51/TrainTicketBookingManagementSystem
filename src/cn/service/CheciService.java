package cn.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.entity.Checi;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nnn
 * @since 2020-05-28
 */
public interface CheciService extends IService<Checi> {
	public List<Integer> getCid1(String sname);
	
	public List<Integer> getCid2(String sname1,String sname2);
	
	public List<Integer> getCid3(String sname);
	
	public List<Map<String,Integer>> getPiaojia(Integer cid,Integer bsid,Integer esid);
}

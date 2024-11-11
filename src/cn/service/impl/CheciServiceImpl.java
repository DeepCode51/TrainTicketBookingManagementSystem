package cn.service.impl;

import java.util.List;
import java.util.Map;

import cn.entity.Checi;
import cn.mapper.CheciMapper;
import cn.service.CheciService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nnn
 * @since 2020-05-28
 */
@Service
public class CheciServiceImpl extends ServiceImpl<CheciMapper, Checi> implements CheciService {

	@Override
	public List<Integer> getCid1(String sname) {
		return this.baseMapper.getCid1(sname);
	}
	
	@Override
	public List<Integer> getCid2(String sname1,String sname2) {
		return this.baseMapper.getCid2(sname1,sname2);
	}

	@Override
	public List<Integer> getCid3(String sname) {
		return this.baseMapper.getCid3(sname);
	}

	@Override
	public List<Map<String, Integer>> getPiaojia(Integer cid, Integer bsid,
			Integer esid) {
		// TODO Auto-generated method stub
		return this.baseMapper.getPiaojia(cid, bsid, esid);
	}

}

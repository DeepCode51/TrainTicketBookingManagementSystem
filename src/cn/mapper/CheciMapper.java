package cn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.entity.Checi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nnn
 * @since 2020-05-28
 */
public interface CheciMapper extends BaseMapper<Checi> {
	@Select("select cid from site where sname=#{sname} group by cid")
	public List<Integer> getCid1(String sname);
	
	@Select("select A.cid from (select * from site) A,(select * from site) B where A.cid=B.cid and A.sname=#{sname1} and B.sname=#{sname2} and A.atime<B.atime")
	public List<Integer> getCid2(@Param("sname1") String sname1,@Param("sname2") String sname2);
	
	
	@Select("select s.cid from site s,checi c where s.cid=c.id and c.isdel=0 and s.sname=#{sname} group by s.cid")
	public List<Integer> getCid3(String sname);
	
	
	@Select("select A.name,B.price-A.price as price,B.num from (select * from seat where cid=#{cid} and sid=#{bsid}) A,(select * from seat where cid=#{cid} and sid=#{esid}) B where A.name=B.name")
	public List<Map<String,Integer>> getPiaojia(@Param("cid") Integer cid,@Param("bsid") Integer bsid,@Param("esid") Integer esid);
}

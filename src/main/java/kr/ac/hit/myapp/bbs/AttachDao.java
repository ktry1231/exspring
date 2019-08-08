package kr.ac.hit.myapp.bbs;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachDao {
	public int insert(AttachVo vo);
	public AttachVo select(int attNo);
}

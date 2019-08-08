package kr.ac.hit.myapp.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDao {
	public int insert(ReplyVo vo);
	public List<ReplyVo> selectList(int repBbsNo);
	public int delete(ReplyVo vo);
}

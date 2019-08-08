package kr.ac.hit.myapp.reply;

import java.util.List;

public interface ReplyService {
	public int insert(ReplyVo vo);
	public List<ReplyVo> selectList(int repBbsNo);
	public int delete(ReplyVo vo);
}

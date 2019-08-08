package kr.ac.hit.myapp.member;

import java.io.File;
import java.util.List;

public interface MemberService {
	public int insert(MemberVo vo);
	public List<MemberVo> selectList();
	public MemberVo select(String memId);
	public int update(MemberVo vo);
	public int delete(String memId);
	public MemberVo selectLoginUser(MemberVo vo);
	public File getImgFile(MemberVo vo);
}

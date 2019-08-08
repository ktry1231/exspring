package kr.ac.hit.myapp.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//mybatis-spring 연동 모듈이 이 인터페이스를 찾아서 구현체를 생성하도록 표시
@Mapper
public interface MemberDao {
	public int insert(MemberVo vo);
	public List<MemberVo> selectList();
	public MemberVo select(String memId);
	public int update(MemberVo vo);
	public int delete(String memId);
	public MemberVo selectLoginUser(MemberVo vo);
}

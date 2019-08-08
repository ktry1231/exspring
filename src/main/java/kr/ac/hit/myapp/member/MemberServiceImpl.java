package kr.ac.hit.myapp.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

//@Component @Controller @Service @Repository 중 하나를 붙여서 스프링에 등록
@Service
public class MemberServiceImpl implements MemberService {
	@Resource
	private MemberDao memberDao;
	
	private String uploadImgDir = "C:/Temp/profile";
	{
		new File(uploadImgDir).mkdirs(); //폴더가 없는 경우 생성
	}
	
	@Transactional
	@Override
	public int insert(MemberVo vo) {
		MultipartFile f = vo.getMemImgFile();
		if (f!=null && f.isEmpty()==false) { //파일을 받았다면,
			String orgName = f.getOriginalFilename();  //원래파일명 "xxx.xxxx.png"
			int idx = orgName.lastIndexOf('.'); //원래파일명에서 마지막 .의 위치
			String ext = idx<0 ? "" : orgName.substring( idx ); //마지막.이후의문자열 == 확장자
			String newName = vo.getMemId() + ext; //저장하는 파일명
			File saveFile = new File(uploadImgDir, newName);
			try {
				//f의 내용을 saveFile에 저장(복사)
				f.transferTo(saveFile); //MultipartFile 의 메서드를 사용
//				Files.copy(f.getInputStream(), Paths.get(saveFile.getAbsolutePath())); //JAVA API를 사용
//				FileCopyUtils.copy(f.getInputStream(), new FileOutputStream(saveFile)); //스프링 사용
				vo.setMemImg(newName); //하드디스크에 저장한 파일명
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException("프로필 이미지 파일 저장 실패", e);
			}
		}
		
		int num = memberDao.insert(vo);
		return num;
	}

	@Override
	public List<MemberVo> selectList() {
		List<MemberVo> list = memberDao.selectList();
		return list;
	}

	@Override
	public MemberVo select(String memId) {
		MemberVo vo = memberDao.select(memId);
		return vo;
	}

	@Override
	public int update(MemberVo vo) {
		int num = memberDao.update(vo);
		return num;
	}

	@Override
	public int delete(String memId) {
		return memberDao.delete(memId);
	}

	@Override
	public MemberVo selectLoginUser(MemberVo vo) {
		return memberDao.selectLoginUser(vo);
	}

	@Override
	public File getImgFile(MemberVo vo) {
		return new File(uploadImgDir, vo.getMemImg());
	}

}

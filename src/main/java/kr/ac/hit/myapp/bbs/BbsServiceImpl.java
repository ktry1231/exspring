package kr.ac.hit.myapp.bbs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hit.myapp.comm.PageInfo;
import kr.ac.hit.myapp.comm.SearchInfo;

//클래스에 @Transactional를 붙이면, 클래스 내의 모든 메서드에 @Transactional를 붙인 것과 동일  
//@Transactional
@Service
public class BbsServiceImpl implements BbsService {
	@Resource
	private BbsDao bbsDao;
	@Resource
	private AttachDao attachDao;
	
	//게시판 첨부파일을 저장할 디렉토리 경로
	private String uploadDir = "C:/Temp/upload";
	{
		//uploadDir에 지정된 디렉토리가 없으면 만들기
		new File(uploadDir).mkdirs();
	}
	
	
	//이 메서드를 하나의 트랜잭션으로 설정하여, 
	//실행 중 예외가 발생하면 데이터베이스를 메서드 실행 전 상태로 되돌리도록(rollback) 설정 
	@Transactional
	@Override
	public int insert(BbsVo vo) {
		//게시글 정보 저장
		int num = bbsDao.insert(vo);
		
		if (vo.getUploadList()==null) return num; //첨부파일 파라미터 자체를 받지 않은 경우
		
		List<MultipartFile> uploadList = vo.getUploadList();
		for (MultipartFile f : uploadList) {
//			int n = 10/0;
			
			//빈 파일인 경우 == 파일을 선택하지 않은 경우, 첨부파일 저장 작업을 하지 않음  
			if ( f.isEmpty() ) continue; // f.getSize()<=0
			
			System.out.println( f.getOriginalFilename() +":"+ f.getSize() );//파일명과 파일크기 출력
			//파일을 서버의 파일시스템(하드디스크)에 저장
			String newFileName = UUID.randomUUID().toString(); //거의 중복될 확률이 없는 문자열 생성
			File saveFile = new File(uploadDir, newFileName ); //저장될 파일 정보
			try {
				//f의 파일 내용을 읽어서, saveFile에 저장(복사)
				Files.copy(f.getInputStream(), Paths.get(saveFile.getAbsolutePath()));//JAVA
//				FileCopyUtils.copy(f.getInputStream(), new FileOutputStream(saveFile));//Spring
			} catch (IOException e) {
				e.printStackTrace();
			}
			//저장한 파일정보를 데이터베이스의 attach 테이블에 추가
			AttachVo avo = new AttachVo();
			avo.setAttOrgName( f.getOriginalFilename() ); //원래 파일명
			avo.setAttNewName( newFileName ); //서버에 저장한 파일명
			avo.setAttBbsNo( vo.getBbsNo() ); //현재 파일이 속한 게시물의 번호
			attachDao.insert(avo);
		}
		
		return num;
	}

	@Override
	public List<BbsVo> selectList(SearchInfo info) {
		return bbsDao.selectList(info);
	}

	@Override
	public BbsVo select(int bbsNo) {
		return bbsDao.select(bbsNo);
	}

	@Override
	public int update(BbsVo vo) {
		return bbsDao.update(vo);
	}

	@Override
	public int delete(BbsVo vo) {
		return bbsDao.delete(vo);
	}

	@Override
	public AttachVo selectAttach(int attNo) {
		return attachDao.select(attNo);
	}

	@Override
	public File getAttachFile(AttachVo vo) {
		return new File(uploadDir, vo.getAttNewName());
	}

	@Override
	public int selectCount(SearchInfo info) {
		return bbsDao.selectCount(info);
	}
}





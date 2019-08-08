package kr.ac.hit.myapp.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	//@Autowired @Inject @Resource 중 하나를 사용하여 자동 주입
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value="/member/add.do", method=RequestMethod.GET)
	public String addForm( MemberVo vo ) { 
		//스프링 폼 태그(form:form)의 modelAttribute 사용을 위해서 
		//빈 MemberVo 객체를 모델에 저장하기 위해서 인자로 설정
		return "member/memAdd";
	}
	
	@RequestMapping(value="/member/add.do", method=RequestMethod.POST)
	public String add(@Valid MemberVo vo, BindingResult result ) {
		//@Valid 를 붙이면 해당 객체 내부에 적어놓은 조건에 따라서
		//	객체의 변수(속성) 값들을 검사하고,
		//	그 결과를 다음 인자인 BindingResult 에 저장하여 전달
		if (result.hasErrors()) { //검증 결과 에러가 있는지 확인
			return "member/memAdd"; //회원가입 화면을 다시 출력
		}
		
		//파라미터로 넘어온 값들을 받아서 데이터베이스에 추가(insert)
		memberService.insert(vo);
		//JSP 파일로 이동하는 대신 redirect: 뒤에 지정한 주소로 이동하라는 응답을 전송 
		return "redirect:/member/list.do";
	}
	
	@RequestMapping("/member/list.do")
	public String list( Map modelMap) {
		//데이터베이스에서 회원목록을 조회하고,
		List<MemberVo> list = memberService.selectList();
		//조회한 회원목록 list를 JSP에서 ${memberList}로 사용할 수 있도록 모델에 저장
		modelMap.put("memberList", list);
		return "member/memList";
	}
	
	@RequestMapping(value="/member/edit.do", method=RequestMethod.GET)
	public String editForm( String memId, Map modelMap, HttpSession session ) {
		//로그인한 사용자와 상세정보를 조회하는 사용자(memId)와 일치하는지 확인
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser"); //로그인한 사용자
		if (loginUser.getMemId().equals(memId)==false) {//로그인한 사용자와 조회대상 사용자가 다르면
//			return "redirect:/member/list.do";
			throw new RuntimeException("권한이 없습니다."); //예외(에러) 발생
		}
		
		MemberVo vo = memberService.select(memId);
		modelMap.put("memberVo", vo);
		return "member/memEdit";
	}
	
	@RequestMapping(value="/member/edit.do", method=RequestMethod.POST)
	public String edit( MemberVo vo , HttpSession session) {
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser"); //로그인한 사용자
		if (loginUser.getMemId().equals(vo.getMemId())==false) {//로그인한 사용자와 조회대상 사용자가 다르면
			throw new RuntimeException("권한이 없습니다."); //예외(에러) 발생
		}
		
		int num = memberService.update(vo);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(value="/member/del.do")
	public String del( String memId, HttpSession session ) {
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser"); //로그인한 사용자
		if (loginUser.getMemId().equals(memId)==false) {//로그인한 사용자와 조회대상 사용자가 다르면
			throw new RuntimeException("권한이 없습니다."); //예외(에러) 발생
		}
		
		int num = memberService.delete(memId);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public String login(MemberVo vo, HttpSession session) { //스프링이 실행시 세션객체를 전달
		// 사용자가 입력한 아이디/비밀번호와 일치하는 회원 정보 조회
		MemberVo mvo = memberService.selectLoginUser(vo);
		if (mvo==null) { //일치하는 회원이 없는 경우 == 로그인 실패
			return "member/login"; //다시 로그인 화면 출력
		}
		// mvo!=null : 로그인 성공 
		// 로그인한 사용자 정보(mvo)를 세션에 "loginUser" 라는 이름으로 저장
		session.setAttribute("loginUser", mvo);
		return "redirect:/bbs/list.do"; //게시판(글목록)으로 이동
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session) {
		//로그아웃 == 세션에 저장된 로그인 정보를 삭제
//		session.setAttribute("loginUser", null);//세션에 "loginUser"라는 이름으로 null을 저장
//		session.removeAttribute("loginUser");//세션에서 "loginUser" 속성 자체를 제거
		session.invalidate(); //세션 객체 자체를 삭제 (하고 새로 생성)
		return "redirect:/member/login.do";
	}
	
	//회원아이디를 받아서 그 회원의 사진(이미지)파일을 응답으로 전송
	@RequestMapping("/member/down.do")
	public void down( String memId, HttpServletResponse resp ) throws IOException {
		MemberVo vo = memberService.select(memId);
		File f = memberService.getImgFile(vo);
		//파일 f의 내용을 응답 객체에 쓰기 (브라우저로 전송)
//		Files.copy(Paths.get(f.getAbsolutePath()), resp.getOutputStream());
		FileCopyUtils.copy(new FileInputStream(f), resp.getOutputStream()); 
	}
	
}










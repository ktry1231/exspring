package kr.ac.hit.ex;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //스프링 AOP 관련 설정 및 코드를 담고 있는 객체임을 표시
@Component //이 객체를 스프링에 등록
public class AppAdvice {
	
	// kr.ac.hit.ex 패키지의 모든 클래스의 모든 메서드(파라미터상관없음)의 실행 전에
	// 이 메서드를 실행하도록 설정
	@Before("execution(* kr.ac.hit.ex.*.*(..))")
	public void beforeMethod(JoinPoint joinPoint) { 
		//JoinPoint : 이 메서드가 끼어들어가서 실행되는 지점에 대한 정보를 인자로 받을 수 있다  
		System.out.println("메서드 시작 : " + joinPoint.getSignature().getName());//실행중인 메서드 이름
	}
	
	// kr.ac.hit.ex 패키지의 모든 클래스의 모든 메서드(파라미터상관없음)의 실행 전에
	// 이 메서드를 실행하도록 설정
	@After("execution(* kr.ac.hit.ex.*.*(..))")
	public void afterMethod(JoinPoint joinPoint) { 
		System.out.println("메서드 끝 : " + joinPoint.getSignature().getName());//실행중인 메서드 이름
	}
	
}






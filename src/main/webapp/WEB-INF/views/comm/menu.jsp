<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:if test="${loginUser==null}">
		<!-- 로그인하지 않은 경우 -->
		<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
		</c:if>
		<c:if test="${loginUser!=null}">
		<!-- 로그인한 경우 -->
		<a href="${pageContext.request.contextPath}/member/list.do">회원관리</a>
		| <a href="${pageContext.request.contextPath}/bbs/list.do">게시판</a>
		| <a href="${pageContext.request.contextPath}/product/list.do">상품관리</a>
<!-- 		pageScope(페이지컨텍스트객체의속성들),requsetScope(요청객체의속성들) -->
<!-- 		,sessionScope(세션객체의속성들),applicationScope(서블릿컨텍스트객체의속성들) -->
<%-- 		[ ${sessionScope.loginUser.memName } 님 --%>
<!-- 		xxxScope를 생략하면, pageScope>requsetScope>sessionScope>applicationScope 순서대로 찾는다 -->
		[ ${loginUser.memName } 님 
		| <a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a> ]
		</c:if>
	
	</div>
	<hr />
</body>
</html>






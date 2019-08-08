<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
	<!-- 다른 JSP 파일의 내용을 삽입 -->
	<!-- 1. %@ include 는 외부 JSP 파일의 내용을 현재 위치에 복사해서 넣은 후 하나의 JSP 파일로 실행 -->
<%-- 	<%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
	<!-- 2. jsp:include 는 별도의 독립된 JSP 파일로 요청을 보내서 출력 결과를 현재 위치에 포함 -->
	<%-- <jsp:include page="/WEB-INF/views/comm/menu.jsp"></jsp:include> --%>
	<!-- 3. c:import 는 다른 웹 사이트(서비스)의 자원도 포함 가능 -->
	<%-- <c:import url="/WEB-INF/views/comm/menu.jsp"></c:import> --%>
	<%-- <c:import url="https://www.daum.net" ></c:import> --%>
	
	<h1>회원목록</h1>
	
	<c:forEach items="${memberList }" var="vo">
		<c:if test="${loginUser.memId==vo.memId}">
		<a href="${pageContext.request.contextPath}/member/edit.do?memId=${vo.memId }">${vo.memId }</a>
		</c:if>		
		<c:if test="${loginUser.memId!=vo.memId}">
		${vo.memId }
		</c:if>		
		 : ${vo.memName } <br />
	</c:forEach>
	<hr />
	<a href="${pageContext.request.contextPath}/member/add.do">회원가입</a>
</body>


</html>







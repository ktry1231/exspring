<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 스프링이 제공하는 스프링 폼 태그(<form:xxx >) 사용을 위해서 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
<%-- 	<%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
	<h1>회원가입</h1>
	
	<form:form modelAttribute="memberVo" enctype="multipart/form-data" action="${pageContext.request.contextPath}/member/add.do" method="post">
<!-- 	form:input 태그를 사용하면, path 속성값과 동일하게 name 속성값을 설정하고, -->
<!-- 	form:form 태그의 modelAttribute에 지정된 모델 객체의  -->
<!-- 	path 속성값과 같은 이름의 변수의 값으로 value 속성값을 설정   -->
		아이디 : <form:input path="memId" /> <form:errors path="memId" /> <br />
<!-- 	form:error 태그를 사용하여 path에 지정한 변수(속성)에 해당하는 검증 오류 메시지들 출력 -->
		비밀번호 : <form:password path="memPass"/> <form:errors path="memPass" /> <br />
		이름 : <form:input path="memName" /> <form:errors path="memName" /> <br />
		포인트 : <form:input path="memPoint"/> <form:errors path="memPoint" /> <br />
		프로필 사진 : <input type="file" name="memImgFile" /> <br />
		<input type="submit" />
	</form:form>
	
	<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/member/add.do" method="post">
		아이디 : <input type="text" name="memId" value="${memberVo.memId }" /> <br />
		비밀번호 : <input type="password" name="memPass" value="${memberVo.memPass }" /> <br />
		이름 : <input type="text" name="memName" value="${memberVo.memName }" /> <br />
		포인트 : <input type="text" name="memPoint" value="${memberVo.memPoint }" /> <br />
		프로필 사진 : <input type="file" name="memImgFile" /> <br />
		<input type="submit" />
	</form>
	<hr />
	<a href="${pageContext.request.contextPath}/member/list.do">목록으로 이동</a>
</body>
</html>









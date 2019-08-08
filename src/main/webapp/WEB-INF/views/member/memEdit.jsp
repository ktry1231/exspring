<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<style type="text/css">
	img.profile {
		width: 100px;
		height: 100px;
	}
</style>
</head>
<body>
<%-- 	<%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
	<h1>회원정보수정</h1>
	
	<c:if test="${memberVo.memImg==null}">
		<img class="profile" alt="디폴트프로필이미지" src="${pageContext.request.contextPath}/resources/img/user.png" />
	</c:if>
	<c:if test="${memberVo.memImg!=null}">
		<img class="profile" alt="프로필이미지" src="${pageContext.request.contextPath}/member/down.do?memId=${memberVo.memId}" />
	</c:if>
	<form action="${pageContext.request.contextPath}/member/edit.do" method="post">
		아이디 : <input type="text" name="memId" value="${memberVo.memId}" readonly="readonly" /> <br />
		이름 : <input type="text" name="memName" value="${memberVo.memName}" /> <br />
		포인트 : <input type="text" name="memPoint" value="${memberVo.memPoint}" /> <br />
		<input type="submit" value="저장" />
		<a href="${pageContext.request.contextPath}/member/list.do"><input type="button" value="취소" /></a>
		<a href="${pageContext.request.contextPath}/member/del.do?memId=${memberVo.memId}"><input type="button" value="삭제" /></a>
	</form>
</body>
</html>






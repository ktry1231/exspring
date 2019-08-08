<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
</head>
<body>
<%-- 	<%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
	<h1>상품 상세정보</h1>
	<form action="${pageContext.request.contextPath}/product/edit.do" method="post" >
	<input type="hidden" name="prodNo" value="${prodVo.prodNo }"  />
	<table border="1">
		<tbody>
		<tr><td>상품명</td><td><input type="text" name="prodName" value="${prodVo.prodName }" /></td></tr>
		<tr><td>가격</td><td><input type="text" name="prodPrice"  value="${prodVo.prodPrice }" /></td></tr>
		</tbody>
	</table>
		<input type="submit" value="저장" />
		<a href="${pageContext.request.contextPath}/product/list.do"><input type="button" value="게시글목록" /></a>
		<a href="${pageContext.request.contextPath}/product/del.do?prodNo=${prodVo.prodNo }"><input type="button" value="삭제" /></a>
	</form>
	
</body>
</html>







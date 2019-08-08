<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<%-- 	<%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
	<h1>글쓰기</h1>
<!-- form이 전송할 데이터에 파일(<input type="file" />)이 포함된 경우 -->
<!-- form의 enctype 속성값을 "multipart/form-data"로 설정해야 한다 -->
	<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/bbs/add.do" method="post" >
	<table border="1">
		<tbody>
		<tr><td>제목</td><td><input type="text" name="bbsTitle"  /></td></tr>
		<tr><td>내용</td><td><textarea rows="20" cols="80" name="bbsContent"></textarea></td></tr>
<%-- <tr><td>작성자</td><td><input type="text" name="bbsWriter" value="${loginUser.memId }" /></td></tr> --%>
		<tr><td>첨부파일1</td><td><input type="file" name="uploadList"  /></td></tr>
		<tr><td>첨부파일2</td><td><input type="file" name="uploadList"  /></td></tr>
		</tbody>
	</table>
		<input type="submit" value="저장" />
		<a href="${pageContext.request.contextPath}/bbs/list.do"><input type="button" value="게시글목록" /></a>
	</form>
	
</body>
</html>







<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 날짜나 숫자 데이터의 파싱/포맷팅을 담당하는 JSTL 태그 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- EL (${}) 내에서 사용가능한 함수들을 제공해주는 JSTL 태그 라이브러리 사용  --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<%-- 	<%@ include file="/WEB-INF/views/comm/menu.jsp" %> --%>
	<h1>게시글 목록</h1>
	
	<form id="sform" action="${pageContext.request.contextPath }/bbs/list.do" >
		<select name="searchType" >
			<option value="title" >제목</option>
			<option value="content" >내용</option>
			<option value="total" >제목+내용</option>
		</select>
		<input type="text" name="searchWord" value="${searchInfo.searchWord}" />
		<input type="hidden" name="page" value="1" /> 
		<input type="submit" value="검색" />
	</form>
	<script>
		//searchType 셀렉트의 기본값을 설정하기 위해서
		if ('${searchInfo.searchType}') //searchType 값이 존재하는 경우에만 (자바스크립트에서 빈문자열은 false)
		document.querySelector('[name="searchType"]').value = '${searchInfo.searchType}';
	</script>
	
	<table border="1">
		<thead>
			<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${bbsList }" var="vo">
				<tr>
					<td>${vo.bbsNo }</td>
					<td>
<%-- <a href="${pageContext.request.contextPath }/bbs/edit.do?bbsNo=${vo.bbsNo }">${vo.bbsTitle }</a> --%>
			<a href="${pageContext.request.contextPath }/bbs/edit.do?bbsNo=${vo.bbsNo }">
				<c:out value="${vo.bbsTitle }" />
<%-- 				${fn:escapeXml(vo.bbsTitle) }  --%>
			</a>
<%-- <c:out value="${xxx}" /> 또는 ${fn:escapeXml(xxx) } 를 이용하여 출력하면,  --%>
<!-- 기본적으로 태그 괄호문자를 특수문자로 변환하여 출력해준다 -->
					</td>
					<td><c:out value="${vo.bbsWriter }" /></td>
					<td><fmt:formatDate value="${vo.bbsRegDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<!-- 날짜/시간 값을 원하는 형태의 문자열로 출력하고 싶을 때 fmt:formatDate 태그를 사용 -->
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	${searchInfo.pageHtml}
	<script>
// 	페이지 링크 클릭시 실행될 goPage()함수를 재정의할 필요가 있을 경우, 다시 선언
		function goPage(no) {
			//폼 내부에 페이지번호 파라미터를 설정하고
			document.querySelector('[name="page"]').value = no;
			//폼을 전송
			document.querySelector('#sform').submit();
		}
	</script>
	<hr />
	<a href="${pageContext.request.contextPath }/bbs/add.do" ><button>글쓰기</button></a>
</body>
</html>







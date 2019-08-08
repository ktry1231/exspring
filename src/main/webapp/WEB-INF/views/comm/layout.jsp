<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 타일즈 태그 사용을 위한 태그 라이브러리 -->
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING WEB</title>
</head>
<body>
	<!-- 타일즈 화면 정의에서 putAttribute로 삽입시키는 JSP 파일이 들어갈 곳을 -->
	<!-- insertAttribute 태그로 설정 -->
	<div><tiles:insertAttribute name="header" /></div>
	<div><tiles:insertAttribute name="body" /></div>
	<div><tiles:insertAttribute name="footer" /></div>
</body>
</html>






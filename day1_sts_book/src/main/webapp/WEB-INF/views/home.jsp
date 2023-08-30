<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isELIgnored="false"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	스프링 작심 1초
</h1>

<a  href="${contextPath}/list"><h1 style="text-align:center">학습 목록</h1></a>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta charset="UTF-8">
<title>학습 목록</title>
</head>
<body>
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>학습번호</b></td>
			<td><b>학습일자</b></td>
			<td><b>학습제목</b></td>
			<td><b>학습주제</b></td>
			<td><b>학습량</b></td>
		</tr>

		<c:forEach var="book" items="${books}">
			<tr align="center">
				<td>${book.bookNo}</td>
				<td>${book.studyDate}</td>
				<td><a href="${contextPath}/detail/${book.bookNo}">${book.title}</a></td>
				<td>${book.subject}</td>
				<td>${book.chapter}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<div align="center">
		<a href="${contextPath}/insert">학습내용 추가하기</a>
	</div>
</body>
</html>

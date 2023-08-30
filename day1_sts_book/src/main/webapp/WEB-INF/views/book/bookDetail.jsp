<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학습 내용</title>
<style>
.text_center {
	text-align: center;
}
</style>
</head>
<body>
	<div align="center">
		<h1>${bookVO.studyDate}:${bookVO.title}</h1>
	</div>
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>bookNo</b></td>
			<td><b>title</b></td>
			<td><b>subject</b></td>
			<td><b>content</b></td>
			<td><b>studyDate</b></td>
			<td><b>chapter</b></td>
		</tr>
		<tr>
			<td width="200"><p align="center">${bookVO.bookNo}</td>
			<td width="200"><p align="center">${bookVO.title}</td>
			<td width="200"><p align="center">${bookVO.subject}</td>
			<td width="200"><p align="center">${bookVO.content}</td>
			<td width="200"><p align="center">${bookVO.studyDate}</td>
			<td width="200"><p align="center">${bookVO.chapter}</td>
		</tr>
	</table>
	<br>
	<div align="center">
		<a href="${contextPath}/modify/${bookVO.bookNo}">수정</a> 
		<a href="${contextPath}/delete/${bookVO.bookNo}">삭제</a>
		<a href="${contextPath}/list">목록</a>
	</div>

</body>
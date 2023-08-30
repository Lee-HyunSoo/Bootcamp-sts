<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 입력</title>
</head>

<body>
	<form method="post" action="${contextPath}/insert">
		<table border="1" width="80%" align="center">
			<tr align="center">
				<td>bookNo</td>
				<td>title</td>
				<td>subject</td>
				<td>content</td>
				<td>studyDate</td>
				<td>chapter</td>
			</tr>
			<tr align="center">
				<td>${bookNo}</td>			
				<td><input type="text" name="title" value="" size="20"></td>
				<td><input type="text" name="subject" value="" size="20"></td>
				<td><input type="text" name="content" value="" size="20"></td>
				<td><input type="text" name="studyDate" value="" size="20"></td>
				<td><input type="text" name="chapter" value="" size="20"></td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="bookNo" value="${bookNo}"/>
		<div align="center">
			<input type="submit" value="제출"> 
			<input type="reset" value="다시입력">
		</div>
	</form>
</body>
</html>

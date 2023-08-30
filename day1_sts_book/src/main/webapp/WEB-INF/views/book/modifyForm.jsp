<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정창</title>
<style>
.text_center {
	text-align: center;
}
</style>
</head>
<body>
	<form method="post" action="${contextPath}/modify/${bookNo}">
		<h1 class="text_center">수정창</h1>
		<table align="center">
			<tr>
				<td width="200"><p align="right">title</td>
				<td width="400"><input type="text" name="title"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">subject</td>
				<td width="400"><input type="text" name="subject"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">content</td>
				<td width="400"><p>
						<input type="text" name="content"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">studyDate</td>
				<td width="400"><p>
						<input type="text" name="studyDate"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">chapter</td>
				<td width="400"><p><input type="text" name="chapter"></td>
			</tr>
			<tr>
				<td width="200"><p>&nbsp;</p></td>
				<td width="400"><input type="submit" value="수정하기">
				<input type="reset" value="다시입력"></td>
			</tr>
		</table>
		<input type="hidden" name="bookNo" value="${bookNo}">
	</form>
</body>
</html>
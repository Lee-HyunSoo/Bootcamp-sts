<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 업로드 하기</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	var cnt = 1;
	function fn_addFile() {
		/* id가 d_file인 태그를 select해, 선택한 태그의 시작과 끝 사이에 append */
		$("#d_file").append("<br>" + "<input type='file' name='file" + cnt + "'/>" );
		cnt++;
	}
</script>
</head>
<body>
	<h1>파일 업로드 하기</h1>
	<form action="${contextPath}/upload" method="post" enctype="multipart/form-data">
		<label>아이디:</label>
		<input type="text" name="id"/> <br>
		<label>이름:</label>
		<input type="text" name="name"/> <br>
		<input type="button" value="파일 추가" onclick="fn_addFile()"> <br>
		<div id="d_file"></div>
		<input type="submit" value="업로드">
	</form>
</body>
</html>
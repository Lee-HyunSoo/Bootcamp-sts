<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.io.*" pageEncoding="UTF-8" isELIgnored="false"%>
	
<%-- 1. --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">

<%-- 2. --%>
<title><spring:message code="site.title" text="Member Info" /></title>
</head>
<body>
	<%-- 3. --%>
	<a href="${pageContext.request.contextPath }/test/locale.do?locale=ko">한국어</a>
	
	<%-- 4. --%>
	<a href="${pageContext.request.contextPath }/test/locale.do?locale=en">ENGLISH</a>
	<h1>
		<spring:message code="site.title" text="Member Info" />
	</h1>
	<p>
		<spring:message code="site.name" text="no name" />   <%-- 5. --%>
		:
		<spring:message code="name" text="no name" />		 <%-- 6. --%>
	</p>
	<p>
		<spring:message code="site.job" text="no job" />
		:
		<spring:message code="job" text="no job" />
	</p>

	 <%-- 7. --%>
	<input type="button" value="<spring:message code='btn.send' />" /> 
	<input type="button" value="<spring:message code='btn.cancel' />" />
	<input type="button" value="<spring:message code='btn.finish' />" />

</body>
</html>

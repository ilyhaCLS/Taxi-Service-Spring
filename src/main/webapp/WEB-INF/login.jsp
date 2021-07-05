<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>

<%@include file="jspf/directive/taglib.jspf"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename = "com.taxi.resources"/>

<!DOCTYPE html>
<html>
<head>
	<%@include file="jspf/head.jspf"%>
</head>
<body>
<jsp:scriptlet><![CDATA[
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setHeader("Expires", "0"); //Proxies
 ]]></jsp:scriptlet>
 
	<%@include file="jspf/header.jspf"%>

<div class="text-center">

	<c:if test="${param['error'] != null }"> 
		<h4>
			<fmt:message key="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
		</h4>
	</c:if>
	
	<c:if test="${param['form'] != null }">
		<h4>
			<fmt:message key="wrong_login_form" />
		</h4>
	</c:if> 


	<div class="login-form">
		<form action="/login" method="post">
			<div class="form-group">
				<fmt:message key="login" />
				<input type="text" name="username" />
			</div>
			<div class="form-group">
				<fmt:message key="password" />
				<input type="password" name="password" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">
					<fmt:message key="log_in" />
				</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
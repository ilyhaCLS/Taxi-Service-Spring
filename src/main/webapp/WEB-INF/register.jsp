<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
	
<%@include file="jspf/directive/taglib.jspf"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename = "com.taxi.resources"/>
	
<!DOCTYPE html>
<html>
<head>
	<%@include file="jspf/head.jspf" %>
</head>
<body>
<jsp:scriptlet><![CDATA[
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setHeader("Expires", "0"); //Proxies
 ]]></jsp:scriptlet>
 
	<%@include file="jspf/header.jspf" %>
	
	<div class="text-center">
	<c:if test="${!empty pageContext.response.getHeader('message_info')}">
		<h4>
			<fmt:message key="${pageContext.response.getHeader('message_info')}" />
		</h4>
	</c:if>
	
	
	
	<div class="login-form">
		<form action="/register" method="post">
			<div class="form-group">
				<fmt:message key="login"/> <input type="text" name="login" />
			</div>
			<div class="form-group">
				<fmt:message key="password"/> <input type="text"name="password" />
			</div>
			<div class="form-group">
				<fmt:message key="account.first"/> <input type="text" name="first" />
			</div>
			<div class="form-group">
				<fmt:message key="account.last"/> <input type="text"name="last" />
			</div>
			<div class="form-group">
				<button class="btn btn-primary" type="submit" value="Регистрация"><fmt:message key="register"/></button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
	
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename = "com.taxi.resources"/>

<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<header id="header" class="header">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<img src="img/logo.png" class="log-adj animated fadeInLeft" />
				</div>

			</div>
		</div>
	</header>
<div class="col-lg-8 ml-auto justify-content-center">

	<c:if test="${!empty pageContext.response.getHeader('message_info')}">
		<h4>
			<fmt:message key="${pageContext.response.getHeader('message_info')}" />
		</h4>
	</c:if>
	
	<h1><fmt:message key="page.noPage"/></h1>
	<h1><a href="/"><fmt:message key="page.back_to_main"/></a></h1>
</div>
</body>
</html>
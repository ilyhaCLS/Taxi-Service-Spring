<%@page import="java.io.*"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@include file="WEB-INF/jspf/directive/taglib.jspf"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename = "com.taxi.resources"/>


<!DOCTYPE html>
<html>
<head>
	<%@include file="WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<%@include file="WEB-INF/jspf/header.jspf" %>
</body>
</html>
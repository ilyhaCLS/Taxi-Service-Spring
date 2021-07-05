<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.taxi.entity.Ride"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename = "com.taxi.resources"/>

<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
<c:choose>
	<c:when test="${cookie.lang.value != 'en'}">
		<a href="/changeLang?lang=en"> EN </a>
	</c:when>
	<c:when test="${cookie.lang.value != 'ru'}">
		<a href="/changeLang?lang=ru"> RU </a>
	</c:when>
</c:choose>

	<h1>
	<fmt:message key="adminPage.rides24h"/>: <c:out value="${ridesInADay}"/>
		<br />
	</h1>
	
	<h1>
	
	<fmt:message key="adminPage.income24h"/>: <c:out value="${sumInADay}"/>
		<br />
	</h1>

	<h3><fmt:message key="adminPage.allRides"/> :</h3>

	<h3>
		<a href="/showRides?q=all&order=desc&p=1"
			target="_blank"><fmt:message key="adminPage.newFirst"/></a>
	</h3>
	<h3>
		<a href="/showRides?q=all&order=asc&p=1" target="_blank">
		<fmt:message key="adminPage.oldFirst"/></a>
	</h3>
	<h3>
		<a href="/showRides?q=all&order=exp&p=1" target="_blank">
		<fmt:message key="adminPage.expFirst"/></a>
	</h3>
	<h3>
		<a href="/showRides?q=all&order=cheap&p=1"
			target="_blank"><fmt:message key="adminPage.cheapFirst"/></a>
	</h3>


	<div class="col s3">
		<form action="/showRides"
			method="get">
			<p>
				<b><fmt:message key="adminPage.madeFrom"/>:</b>
			</p>
			<input type="datetime-local" name="from" required="required">
			<p>
				<b><fmt:message key="adminPage.madeUntil"/></b>
			</p>
			<input type="datetime-local" name="until" required="required">
			
			
			<input type="hidden" name="q" value="date">
			<input type="hidden" name="p" value="1">
	
			<button type="submit">OK</button>
		</form>
	</div>
	
		<form action="/showRides" method="get">
			<p>
				<b><fmt:message key="adminPage.byId"/></b>
			</p>
			<input type="number" name="rides_user_id" required="required">
			
			<input type="hidden" name="q" value="us">
			<input type="hidden" name="p" value="1">


			<button type="submit">OK</button>
		</form>
</body>
</html>
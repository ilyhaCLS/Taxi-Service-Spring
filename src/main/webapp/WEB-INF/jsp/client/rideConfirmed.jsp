<%@page import="java.util.ArrayList"%>
<%@page import="com.taxi.entity.Car"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename = "com.taxi.resources"/>


<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf" %>

	<div class="col-lg-12 ml-auto justify-content-center">
		<h2 style="color: green;"><fmt:message key="rideDetails.confirmed"/></h2>
		<br>
		<h2 style="color: green;">
		<%
		
		for(Car c : (ArrayList<Car>)request.getAttribute("cars")){
			out.print(c.getLicPlate()+" "+c.getName()+" <br/>");
		}

		
		%>
	 	<fmt:message key="rideConfirmed.wish"/>
		</h2>
		<br>
		<h2>
			<a href="/account?p=1"><fmt:message key="rideConfirmed.history"/></a>
			<br/>
			<a href="/"><fmt:message key="page.back_to_main"/></a>
		</h2>
	</div>
</body>
</html>
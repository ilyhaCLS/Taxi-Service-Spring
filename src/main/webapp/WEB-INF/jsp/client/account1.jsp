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
<jsp:scriptlet><![CDATA[
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setHeader("Expires", "0"); //Proxies
 ]]></jsp:scriptlet>
	
	<%@include file="/WEB-INF/jspf/header.jspf" %>


	<h1><fmt:message key="account.base_info"/></h1>
	<br>
	<br>

	<h3> | <fmt:message key="account.first"/><c:out value = "${first}"/></h3>
	<h3> | <fmt:message key="account.last"/><c:out value = "${last}"/></h3>
	
	
	
	<h3> | <fmt:message key="account.totalSpent"/>
		<c:choose>
			<c:when test="${cookie.lang.value == 'en'}">
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${totalSpent / 27}"/></td>
			</c:when>
			<c:when test="${cookie.lang.value == 'ru'}">
				<td>${totalSpent}</td>
			</c:when>
		</c:choose>
	</h3>
	
			
	
	
	<h1><fmt:message key="account.rides_info"/></h1>


	<table class="table table-hover table-dark">
		<thead>
			<tr>
				<th scope="col">id</th>
				<th scope="col"><fmt:message key="account.rides.from"/></th>
				<th scope="col"><fmt:message key="account.rides.to"/></th>
				<th scope="col"><fmt:message key="account.rides.price"/></th>
				<th scope="col"><fmt:message key="account.rides.date"/></th>
				<th scope="col"><fmt:message key="account.rides.car"/></th>
			</tr>
		</thead>
		
		<tbody>		
		<c:set var = "langTag" scope = "session" value = "${cookie.lang.value}"/>
		<c:forEach items="${rides}" var="ride">
			<tr>
			<th scope="row">${ride.getId()}</th>
    		<td>${ride.getPosFrom()}</td>
    		<td>${ride.getPosTo()}</td>
 
 			<c:choose>
				<c:when test="${cookie.lang.value == 'en'}">
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${ride.getPrice() / 27}"/></td>
				</c:when>
				<c:when test="${cookie.lang.value == 'ru'}">
					<td>${ride.getPrice()}</td>
				</c:when>
			</c:choose>

			<fmt:parseDate value="${ride.getCreationTime()}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
			<td>
			<fmt:formatDate type = "both" 
        	 dateStyle = "medium" timeStyle = "medium" value="${parsedDateTime}"/></td>
        	 
    		<td>${ride.getCar().getLicPlate()} ${ride.getCar().getName()} ${ride.getCar().getCarClass()}</td>
    		</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<c:forEach begin="1" end="${numOfPages}" varStatus="loop">
    	<a href="/account?p=${loop.index}" class="btn btn-primary"> <c:out value = "${loop.index}"/> </a>
	</c:forEach>


</body>
</html>
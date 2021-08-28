<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Home</title>
	<link href="${path}/resources/js/test.css" rel="stylesheet"/>
</head>
<body>
<h1>
	Hello world!  
</h1>

	<button type="button" class="btn" onclick="mqtt_onLight()">LightOn</button>

<P>  The time on the server is ${serverTime}. </P>
${path }

<script src="${path}/resources/js/mqtthandler.js"></script>
</body>
</html>

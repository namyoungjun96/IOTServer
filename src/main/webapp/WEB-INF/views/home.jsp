<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

	<button type="button" class="btn" onclick="mqtt_onLight()">LightOn</button>

<P>  The time on the server is ${serverTime}. </P>

<script src='<c:url value="/resources/js/mqtthandler.js"/>'></script>
</body>
</html>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><tiles:getAsString name="title"/></title>
	<link rel="stylesheet" type="text/css" href="/_ui/css/main.css"/>
</head>

<body>
<%--holder for header and body--%>
<div class="contentBar">

	<%--header slot goes here--%>
	<header class="headerBar">
		<tiles:insertAttribute name="header"/>
	</header>


	<%--main content slot goes here--%>
	<div class="mainContent">
		<tiles:insertAttribute name="body"/>
	</div>
</div>

<%--footer goes here--%>
<footer class="footerBar">
	<tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>

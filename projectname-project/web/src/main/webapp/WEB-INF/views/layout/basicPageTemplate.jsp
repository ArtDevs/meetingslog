<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><tiles:getAsString name="title"/></title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700&subset=latin,cyrillic-ext' rel='stylesheet' type='text/css'>    <link href='http://fonts.googleapis.com/css?family=Lora:400,700&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:700&subset=latin,cyrillic-ext' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="/_ui/css/main.css"/>
</head>

<body>

<%--header slot goes here--%>
<header class="headerBar">
    <div class="container">
        <tiles:insertAttribute name="header"/>
    </div>
</header>
<%--holder body--%>
<div class="container">
	<%--main content slot goes here--%>
	<div class="mainContent">
		<tiles:insertAttribute name="body"/>
	</div>
</div>

<%--footer goes here--%>
<footer class="footerBar">
    <div class="container">
	    <tiles:insertAttribute name="footer"/>
    </div>
</footer>
</body>
</html>

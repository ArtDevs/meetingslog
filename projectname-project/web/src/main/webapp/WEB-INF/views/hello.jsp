<%--
  Created by IntelliJ IDEA.
  User: artlaber
  Date: 09.12.14
  Time: 3:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<title></title>
</head>
<body>
<h1>this is hello :)</h1>
<p>${customMessage}</p>
<p>
  <a href=${loginLink}>${loginHeader}</a>
</p>
<p>
  <a href=${signUpLink}>${signUpHeader}</a>
</p>

</body>
</html>

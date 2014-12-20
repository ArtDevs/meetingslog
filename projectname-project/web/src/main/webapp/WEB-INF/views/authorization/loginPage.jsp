<%--
  Created by IntelliJ IDEA.
  User: artlaber
  Date: 20.12.14
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login page</title>
  <style type="text/css">
    div.authData {
      display: table;
      width: auto;
      height: auto;
      margin: 0px auto;
    }

    div.label, div.input{
      display: inline-block;
      float: left;
      width: auto;
      height: auto;
      margin: 5px 20px;
    }

    div.label { text-align: right; min-width: 100px; }
    div.input { text-align: left; }

    br { clear: both; }
  </style>
</head>
<body style="vertical-align: middle;">

  <form:form action="/login" method="post" modelAttribute="loginForm">
    <div class="authData">
      <div class="label">
        Login:
      </div>
      <div class="input">
        <form:input path="login" tabindex="1" /> ${msgLogin}
      </div>
      <br />
      <div class="label">
        Password:
      </div>
      <div class="input">
        <form:password path="pass" tabindex="2" /> ${msgPass}
      </div>
      <br />
      <div class="label">
        <input type="submit" value="Go!" tabindex="3"/>
      </div>
    </div>
  </form:form>

</body>
</html>

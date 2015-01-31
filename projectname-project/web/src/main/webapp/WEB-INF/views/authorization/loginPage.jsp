<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Login Page For Security</title>
  <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>

<body>

<c:if test="${not empty error}">
  <div class="errorblock">
    Your login attempt was not successful, try again.
    Caused :
      ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
  </div>
</c:if>

<div class="contentBar">
  <header class="headerBar">
    <div class="headerContent">
      <div class="headerLeftCell">
        <b>LeftHeader</b>
      </div>
      <div class="headerRightCell">
        <b>HeaderRight</b>
      </div>
    </div>
  </header>
  <div class="mainContent">

    <c:url var="doLogin" value="/j_spring_security_check" />
    <form name='f' action="${doLogin}" method='POST'>
    <div style="display: inline; float: left; margin: 5px; width: 100px;">
      <label class="inputLable">Login</label>
    </div>
    <div style="display: inline; float: left; margin: 5px;">
      <input class="inputField" type="text" name="j_username" />
    </div>
    <br style="clear:both"/>
    <div style="display: inline; float: left; margin: 5px; width: 100px;">
      <label class="inputLable">Password</label>
    </div>
    <div style="display: inline; float: left; margin: 5px;">
      <input class="inputField" type="password" name="j_password" />
      <br style="clear:both"/><br style="clear:both"/>
      <input class="inputFormButton" type="submit" value="Log in"/>
    </div>

    </form>

  </div>

</div>

<footer class="footerBar">
  <div class="footerContent">
    <div class="footerLeftCell">
      <p>
        designed and developed by<br/>
        <sup>&copy;</sup>ArtDevs, 2015
      </p>
    </div>
    <div class="footerMidCell">
      FooterMid
    </div>
    <div class="footerRightCell">
      FooterRight
    </div>
  </div>
</footer>

</body>

</html>

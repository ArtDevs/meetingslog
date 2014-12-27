<%--
  Created by IntelliJ IDEA.
  User: artlaber
  Date: 20.12.14
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Welcome to registration page</h1>

<sf:form method="POST" modelAttribute="newUser" enctype="multipart/form-data">
  <sf:errors path="*" cssClass="error"/>

  <fieldset>
    <table cellspacing="1" cellpadding="1">
      <tr>
        <th align="right"><sf:label path="login" >Login:</sf:label></th>
        <td><sf:input path="login" size="25" maxlength="25"></sf:input>
          <sf:errors path="login" cssClass="error"/></td>
      </tr>

      <tr>
        <th align="right"><sf:label path="password">Password:</sf:label></th>
        <td><sf:password path="password" size="25" maxlength="25"></sf:password>
          <sf:errors path="password" cssClass="error"/></td>
      </tr>

      <tr>
        <th align="right"><sf:label path="firstName">First name:</sf:label></th>
        <td><sf:input path="firstName" size="50" maxlength="50"></sf:input>
          <sf:errors path="firstName" cssClass="error"/></td>
      </tr>

      <tr>
        <th align="right"><sf:label path="secondName">Second name:</sf:label></th>
        <td><sf:input path="secondName" size="50" maxlength="50"></sf:input>
          <sf:errors path="secondName" cssClass="error"/></td>
      </tr>

      <tr>
        <th align="right"><sf:label path="email">Email:</sf:label></th>
        <td><sf:input path="email" size="25" maxlength="255"></sf:input>
          <sf:errors path="email" cssClass="error"/></td>
      </tr>

      <tr>
        <th align="right"><sf:label path="comment">Comment:</sf:label></th>
        <td><sf:textarea path="comment" size="50" rows="5" maxlength="500"></sf:textarea>
          <sf:errors path="comment" cssClass="error"/></td>
      </tr>

      <tr>
        <td align="center" colspan="2"><input name="commit" type="submit" value="Try to create account."/></td>
      </tr>

    </table>
  </fieldset>

</sf:form>
</body>
</html>
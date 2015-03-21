<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form method="POST" modelAttribute="newUserForm">
  <%--<sf:errors path="*" cssClass="error"/>--%>

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
        <th align="right"><sf:label path="address">Postal address:</sf:label></th>
        <td><sf:input path="address" size="25" maxlength="255"></sf:input>
          <sf:errors path="address" cssClass="error"/></td>
      </tr>

      <tr>
        <th align="right"><sf:label path="phoneNumber1">Phone number1:</sf:label></th>
        <td><sf:input path="phoneNumber1" size="25" maxlength="25"></sf:input>
          <sf:errors path="phoneNumber1" cssClass="error"/></td>
      </tr>

      <tr>
        <th align="right"><sf:label path="phoneNumber2">Phone number2:</sf:label></th>
        <td><sf:input path="phoneNumber2" size="25" maxlength="25"></sf:input>
          <sf:errors path="phoneNumber2" cssClass="error"/></td>
      </tr>

      <tr>
        <th align="right"><sf:label path="comment">Comment:</sf:label></th>
        <td><sf:textarea path="comment" size="50" rows="5" maxlength="500"></sf:textarea>
          <sf:errors path="comment" cssClass="error"/></td>
      </tr>

      <tr>
        <td align="center" colspan="2"><input name="commit" type="submit" value="Try to create account."/></td>
      </tr>

      <tr>
        <td align="left" colspan="2">${message}</td>
      </tr>

    </table>
  </fieldset>

</sf:form>

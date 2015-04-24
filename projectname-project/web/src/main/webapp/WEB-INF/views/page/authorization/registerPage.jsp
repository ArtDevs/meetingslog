<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<section class="registration-form">
<sf:form method="POST" modelAttribute="newUserForm">
  <%--<sf:errors path="*" cssClass="error"/>--%>


     <sf:label path="login">Login:</sf:label>
        <sf:input path="login" size="25" maxlength="25"/>
          <sf:errors path="login" cssClass="error"/>



        <sf:label path="password">Password:</sf:label>
        <sf:password path="password" size="25" maxlength="25"/>
          <sf:errors path="password" cssClass="error"/>



         <sf:label path="firstName">First name:</sf:label>
        <sf:input type="text" path="firstName" size="50" maxlength="50"/>
          <sf:errors path="firstName" cssClass="error"/>



        <sf:label path="secondName">Second name:</sf:label>
        <sf:input path="secondName" size="50" maxlength="50"/>
          <sf:errors path="secondName" cssClass="error"/>



         <sf:label path="email">Email:</sf:label>
        <sf:input path="email" size="25" maxlength="255"/>
          <sf:errors path="email" cssClass="error"/>



        <sf:label path="address">Postal address:</sf:label>
        <sf:input path="address" size="25" maxlength="255"/>
          <sf:errors path="address" cssClass="error"/>



        <sf:label path="phoneNumber1">Phone number1:</sf:label>
        <sf:input path="phoneNumber1" size="25" maxlength="25"/>
          <sf:errors path="phoneNumber1" cssClass="error"/>



         <sf:label path="phoneNumber2">Phone number2:</sf:label>
        <sf:input path="phoneNumber2" size="25" maxlength="25"/>
          <sf:errors path="phoneNumber2" cssClass="error"/>


      <sf:label path="comment">Comment:</sf:label>
        <sf:textarea path="comment" size="50" rows="2" maxlength="500"/>
          <sf:errors path="comment" cssClass="error"/>

        <div class="cf">
        <input class="button" name="commit" type="submit" value="Try to create account."/>
        </div>


    <div class="error">
        ${message}
    </div>
</sf:form>
</section>

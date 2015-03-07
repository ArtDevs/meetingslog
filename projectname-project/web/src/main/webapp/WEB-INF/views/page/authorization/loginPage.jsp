<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--defining the url our form should use as an action--%>
<c:url var="doLogin" value="/j_spring_security_check"/>
<section class="login-form">
<%--start of the spring form--%>
<form name='f' action="${doLogin}" method='POST'>

	<%--login lable and input field--%>
    <label for="j_username">Login</label>
    <input type="text" id="j_username" name="j_username" placeholder="Username" required="" />

	<%--password lable and input field--%>
	<label for="j_password">Password</label>
	<input type="password" id="j_password" name="j_password" placeholder="Password" required=""/>

    <%--in case of login error we'll have this variable and an error message we want to display--%>
    <c:if test="${not empty error}">
        <div class="error">
            Your login attempt was not successful, try again.Caused :
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    <div class="cf">
        <%--buttons that submits our form for processing--%>
        <input class="button" type="submit" value="Log in"/>

        <c:url var="registrationUrl" value="/register"/>
        <a href="${registrationUrl}" class="register-link">Register here</a>
    </div>
</form>
</section>

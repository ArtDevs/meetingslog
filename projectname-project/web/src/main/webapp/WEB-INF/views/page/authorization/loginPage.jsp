<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--defining the url our form should use as an action--%>
<c:url var="doLogin" value="/j_spring_security_check"/>

<%--start of the spring form--%>
<form name='f' action="${doLogin}" method='POST'>

	<%--login lable and input field--%>
	<div style="display: inline; float: left; margin: 5px; width: 100px;">
		<label class="inputLable">Login</label>
	</div>
	<div style="display: inline; float: left; margin: 5px;">
		<input class="inputField" type="text" name="j_username"/>
	</div>

	<%--clearing floats to make a line break--%>
	<br style="clear:both"/>

	<%--password lable and input field--%>
	<div style="display: inline; float: left; margin: 5px; width: 100px;">
		<label class="inputLable">Password</label>
	</div>
	<div style="display: inline; float: left; margin: 5px;">
		<input class="inputField" type="password" name="j_password"/>
		<br style="clear:both"/>

		<%--in case of login error we'll have this variable and an error message we want to display--%>
		<c:if test="${not empty error}">
			<div class="loginError">
				Your login attempt was not successful, try again.Caused :
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>

		<br style="clear:both"/>

		<%--buttons that submits our form for processing--%>
		<input class="inputFormButton" type="submit" value="Log in"/>

		<c:url var="registrationUrl" value="/register"/>
		<a href="${registrationUrl}">Register here</a>
	</div>
</form>

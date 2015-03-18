<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artlaber
  Date: 20.12.14
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages of user.</title>
</head>
<body>
  <c:if test="${not empty messages}">

    <table>
      <tbody>
      <c:forEach items="${messages}" var="msg">
        <tr>
          <td>

          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

  </c:if>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%--<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
  <title>Time-Tracker</title>
</head>

<body>
<div align="center">
  <form name="loginForm" method="get" action="MainServlet">
    <table align="center" width="25%" border="0">
      <tr>
        <td align="center">
          Login<br> <input type="text" name="nameInput" required><br>
          Password<br> <input type="password" name="passInput" required><br>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="center">
          <input type="submit" value="Login">
          <input type="hidden" name="command" value="register">
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>

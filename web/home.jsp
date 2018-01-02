<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@page import="*" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<c:choose>
    <table border="1">

        <c:forEach var="activity" items="${activities}">
            <tr>
                <td><c:out value="${activity.getActID()}"/></td>
                <td><c:out value="${activity.actName()}"/></td>
                <td><c:out value="${activity.actDuration()}"/></td>
                <td><c:out value="${activity.userName()}"/></td>
                <c:when test="${not empty user && user.isAdmin}">
                    <td>ADMIN</td>
                </c:when>
                <c:when test="${not empty user && !user.isAdmin}">
                    <td>USER</td>
                </c:when>
                    <%--<td><c:if test="${product.stock > 0}">--%>
                    <%--<form method="post" action="/?command=bucket">--%>
                    <%--<input type="hidden" name="addToBucket" value="${product.series}">--%>
                    <%--<input type="submit" value="buy">--%>
                    <%--<input name="quantity" min="1" max ="${product.stock}" value="1" type="number"/>--%>
                    <%--</form></td>--%>
                    <%--<br/>--%>
                    <%--</c:if>--%>
            </tr>

        </c:forEach>
    </table>

</c:choose>
</body>
</html>

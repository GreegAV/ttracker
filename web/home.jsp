<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<c:choose>
        <div class="list">
            <table border="1">

                <c:forEach var="activity" items="${DAO.getActListFromDB()}">
                    <tr>
                        <td><c:out value="${activity.getActID()}"/></td>
                        <td> <c:out value="${activity.actName()}"/></td>
                        <td> <c:out value="${activity.actDuration()}"/></td>
                        <td> <c:out value="${activity.userName()}"/></td>
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
        </div>


</c:choose>
</body>
</html>

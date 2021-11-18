<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 16.11.2021
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.lysenko.payments.model.user.UserStatus" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<jsp:include page="${pageContext.request.contextPath}/header_admin.jsp"/>
<body>
<table id="table-accounts" class="table">
    <thead>
    <tr>
        <th scope="col">User</th>
        <th scope="col">Phone number</th>
        <th scope="col">Email</th>
        <th scope="col">Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <a href="/customer?id=${user.getUserId()}">${user.getName()} ${user.getSurname()}</a>
            </td>


            <td># ${user.getPhone()}</td>
            <td>${user.getEmail()}</td>
            <td>
                <c:choose>
                <c:when test="${user.getStatus() == UserStatus.UNBLOCKED }">
                    <a href="${pageContext.request.contextPath}/customer/block?userId=${user.getUserId()}">Block</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/customer/unblock?userId=${user.getUserId()}">Unblock</a>
                </c:otherwise>
            </c:choose></td>

        </tr>
    </c:forEach>

    </tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${numberOfPages}" step="1">
            <li class="page-item <c:if test="${i == pageContext.request.getParameter(\"page\")}">active</c:if>">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/admin?page=${i}">${i}</a>
            </li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>

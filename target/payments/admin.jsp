
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.lysenko.payments.model.entity.user.UserStatus" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${param.lang}"/>
<f:setBundle basename="locale"/>
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
        <th scope="col"><f:message key="user"/></th>
        <th scope="col"><f:message key="phoneNum"/></th>
        <th scope="col"><f:message key="email"/></th>
        <th scope="col"><f:message key="status"/></th>
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
                    <a href="${pageContext.request.contextPath}/customer/block?userId=${user.getUserId()}"><f:message key="block"/></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/customer/unblock?userId=${user.getUserId()}"><f:message key="unblock"/></a>
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

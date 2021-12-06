<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.lysenko.payments.model.entity.user.UserStatus" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.lang}"/>
<f:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<jsp:include page="${pageContext.request.contextPath}/header_admin.jsp"/>
<body>
<h4><f:message key="user_requests_to_unblock_an_account"/></h4>
<table id="table-request-unblock" class="table" style="width:50%">
    <thead>
    <tr>
        <th scope="col"><f:message key="status"/></th>
        <th scope="col"><f:message key="accountId"/></th>
        <th scope="col"><f:message key="unblock"/> <f:message key="account"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requests}" var="request_unblock">
        <tr>
            <td>${request_unblock.getStatusRequest()}</td>
            <td>${request_unblock.getAccountId()}</td>
            <td>
                <a href="${pageContext.request.contextPath}/request_unblock_account?id=${request_unblock.getAccountId()}">
                    <f:message key="unblock"/>
                </a></td>

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


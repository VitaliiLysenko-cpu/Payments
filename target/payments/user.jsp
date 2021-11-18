
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.lysenko.payments.model.account.Status" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/header.jsp"/>

<table id="table-accounts" class="table">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Number</th>
        <th scope="col">Amount</th>
        <th scope="col">Status</th>
        <th scope="col">Change Status</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>
                <a href="/account?id=${account.getId()}&page=1">${account.getName()}</a>
            </td>
            <td>${account.getNumber()}</td>
            <td>$${account.getBalance()}</td>
            <td>${account.getStatus()}</td>
            <td>
                <c:choose>
                    <c:when test="${account.getStatus() == Status.OPEN }">
                        <a href="/block?id=${account.getId()}">Block</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/sent-request?id=${account.getId()}">Unblock</a>
                    </c:otherwise>
                </c:choose>
            </td>

        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>

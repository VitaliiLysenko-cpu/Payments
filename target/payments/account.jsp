
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>AccountInfo</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/header.jsp"/>
<div class="container-sm"><f:message key="balance"/> ${balance}</div>
<form method="post" action="${pageContext.request.contextPath}/top_up" novalidate>
    <div class="mb-3 form-group col-lg-2">
        <label for="total" class="form-label"><f:message key="total"/>*</label>
        <input id="total" type="number" min="0" step="0.01" class="form-control" name="total" required>
        <div class="invalid-feedback">${requestScope.error}</div>
    </div>
    <input type="hidden" name="accountId" id="id2" value="${pageContext.request.getParameter("id")}">
    <input type="submit" value="<f:message key="topUp"/>" class="btn btn-primary">
</form>
<table id="table-accounts" class="table">
    <thead>
    <tr>
        <th scope="col"><f:message key="cardNumber"/></th>
        <th scope="col"><f:message key="expiration"/></th>
        <th scope="col"><f:message key="cvc"/></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cards}" var="card">
        <tr>
            <td>${card.getNumber()}</td>
            <td>${card.getExpiration()}</td>
            <td>${card.getCvc()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<table id="table-payments" class="table">
    <thead>
    <tr>
        <th scope="col"><f:message key="amount"/></th>
        <th scope="col"><f:message key="status"/></th>
        <th scope="col"><f:message key="dataCreated"/></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${payments}" var="payment">
        <tr>
            <td>${payment.getAmount()}</td>
            <td>${payment.getStatus()}</td>
            <td>${payment.getDateCreated()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${numberOfPages}" step="1">
            <li class="page-item <c:if test="${i == pageContext.request.getParameter(\"page\")}">active</c:if>">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/account?id=${pageContext.request.getParameter("id")}&page=${i}">${i}</a>
            </li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>

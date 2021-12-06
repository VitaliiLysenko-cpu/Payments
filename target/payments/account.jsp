<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>AccountInfo</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/header.jsp"/>
<div class="container-sm"><f:message key="balance"/> ${balance}</div>
<form method="post" action="${pageContext.request.contextPath}/top_up" style="width:40%">
    <div class="mb-3 form-group col-lg-2">
        <label for="total" class="form-label"><f:message key="total"/>*</label>
        <input id="total" type="number" min="0" step="0.01" class="form-control" name="total" required>
        <div class="invalid-feedback">${requestScope.error}</div>
    </div>
    <input type="hidden" name="accountId" id="id2" value="${pageContext.request.getParameter("id")}">
    <input type="submit" value="<f:message key="topUp"/>" class="btn btn-primary">
</form>
<br>
<h4><f:message key="card_information"/></h4>
<table id="table-accounts" class="table" style="width: 50%">
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
<br>
<h4><f:message key="payments_information"/></h4>
<table id="table-payments" class="table" style="width: 50%">
    <thead>
    <tr>
        <th scope="col">
            <c:choose>
                <c:when test="${sortOrder.equalsIgnoreCase(\"DESC\")}">
                    <a href="${pageContext.request.contextPath}/account?sortBy=id&page=1&id=${pageContext.request.getParameter("id")}&sortOrder=ASC">
                        <f:message key="number"/></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/account?sortBy=id&page=1&id=${pageContext.request.getParameter("id")}&sortOrder=DESC">
                        <f:message key="number"/></a>
                </c:otherwise>
            </c:choose>
        </th>
        <th scope="col"><f:message key="amount"/></th>
        <th scope="col"><f:message key="status"/></th>
        <th scope="col">
            <c:choose>
                <c:when test="${sortOrder.equalsIgnoreCase(\"DESC\")}">
                    <a href="${pageContext.request.contextPath}/account?sortBy=date&page=1&id=${pageContext.request.getParameter("id")}&sortOrder=ASC">
                        <f:message key="dateCreated"/></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/account?sortBy=date&page=1&id=${pageContext.request.getParameter("id")}&sortOrder=DESC">
                        <f:message key="dateCreated"/></a>
                </c:otherwise>
            </c:choose></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${payments}" var="payment">
        <tr>
            <td>${payment.getId()}</td>
            <td>${payment.getAmount()}</td>
            <td>${payment.getStatus()}</td>
            <td>${payment.getDateCreated()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${numberOfPages}" step="1">
            <li class="page-item <c:if test="${i == page}">active</c:if>">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/account?sortBy=${sortBy}&sortOrder=${sortOrder}&id=${pageContext.request.getParameter("id")}&page=${i}">${i}</a>
            </li>
        </c:forEach>
    </ul>
</nav>
</body>
</html>

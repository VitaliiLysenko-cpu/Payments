
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title>New payment</title>
</head>
<body>
<form class="form-horizontal needs-validation" id="create" role="form" method="post"
      action="${pageContext.request.contextPath}/payment/create" novalidate>
    <div class="mb-3 form-group col-lg-2">
        <label for="total" class="form-label">Total*</label>
        <input id="total" type="number" min="0" step="0.01" class="form-control" name="total" required>
        <div class="invalid-feedback">${requestScope.error}</div>
    </div>

    <select name="accountId" id="accountId" class="form-select" aria-label="Default select example">
        <option selected>Open this select menu</option>
        <c:forEach items="${accounts}" var="account">
            <option value="${account.getId()}">${account.getId()}, ${account.getName()}, ${account.getNumber()}, ${account.getBalance()}</option>
        </c:forEach>
    </select>
    <input type="submit" value="pay" class="btn btn-primary">
</form>


</body>
</html>

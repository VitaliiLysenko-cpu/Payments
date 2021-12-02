<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<f:setLocale value="${sessionScope.lang}"/>
<f:setBundle basename="locale"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/header_login_page.jsp"/>
<c:if test="${pageContext.request.getParameter('error') == 'errorLogin'}">
    <div class="alert alert-danger" role="alert" style="width:50%">
        <f:message key="the_account_was_not_found"/>
    </div>
</c:if>
<div class="container">
    <form class="form-horizontal needs-validation" id="loginform" role="form" method="post" style="width:100%"
          action="${pageContext.request.contextPath}/login">
        <div class="mb-3 form-group col-lg-2">
            <label for="login-username" class="form-label"><f:message key="email"/>*</label>
            <input id="login-username" type="email" class="form-control" name="email" required>
            <div class="invalid-feedback">${requestScope.error}</div>
        </div>

        <div class="mb-3 form-group col-lg-2">
            <label for="login-password" class="form-label"><f:message key="password"/>*</label>
            <input id="login-password" type="password" class="form-control" name="password" minlength="8" required>
            <div class="invalid-feedback">${requestScope.error}</div>
        </div>


        <input type="submit" value="<f:message key="login"/>" class="btn btn-primary">
        <a href="${pageContext.request.contextPath}/registration"><f:message key="notHaveAccount"/></a>
    </form>
</div>
</body>
</html>

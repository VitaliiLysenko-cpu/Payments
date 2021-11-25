<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<div class="container">
    <form class="form-horizontal needs-validation" id="registration" class="form-horizontal" role="form"
          method="post" action="${pageContext.request.contextPath}/create_user">

        <div id="signupalert" style="display:none" class="alert alert-danger">
            <p>Error:</p>
            <span></span>
        </div>


        <div class="form-group">
            <label  class="col-md-3 control-label">
            <f:message key="email"/> *</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                <small id="emailHelp" class="form-text text-muted"><f:message key="we_will_never_share_your_email_with_anyone_else"/></small>
        </div>

        <div class="form-group">
            <label  class="col-md-3 control-label">
            <f:message key="firstName"/> *
        </label>
            <div class="col-md-9">
                <input type="text" class="form-control" name="firstname"  required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">
                <f:message key="lastName"/> *
            </label>
            <div class="col-md-9">
                <input type="text" class="form-control" name="lastname"  required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">
                <f:message key="phoneNum"/>
            </label>
            <div class="col-md-9">
                <input type="text" class="form-control" name="phone"
                       minlength="10" maxlength="10"required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">
                <f:message key="password"/> *
            </label>
            <div class="col-md-9">
                <input type="password" class="form-control" name="password"
                       minlength="3" maxlength="16"required>
            </div>
        </div>

            <!-- Button -->
        <input type="submit" value="<f:message key="submit"/>" class="btn btn-primary">
    </form>
</div>
</html>

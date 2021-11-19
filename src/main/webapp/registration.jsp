<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container">
    <form class="form-horizontal needs-validation" id="registration" class="form-horizontal" role="form"
          method="post" action="${pageContext.request.contextPath}/create_user">

        <div id="signupalert" style="display:none" class="alert alert-danger">
            <p>Error:</p>
            <span></span>
        </div>


        <div class="form-group">
            <%--@declare id="email"--%><label for="email" class="col-md-3 control-label">
            <f:message key="email"/> *
        </label>
            <div class="col-md-9">
                <input type="text" class="form-control" name="email" placeholder="Email Address" required>
            </div>
        </div>

        <div class="form-group">
            <%--@declare id="firstname"--%><label for="firstname" class="col-md-3 control-label">
            <f:message key="firstName"/> *
        </label>
            <div class="col-md-9">
                <input type="text" class="form-control" name="firstname" placeholder="First Name" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">
                <f:message key="lastName"/> *
            </label>
            <div class="col-md-9">
                <input type="text" class="form-control" name="lastname" placeholder="Last Name" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-3 control-label">
                <f:message key="phoneNum"/>
            </label>
            <div class="col-md-9">
                <input type="text" class="form-control" name="phone" placeholder="phone number" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">
                <f:message key="cardNumber"/> *
            </label>
            <div class="col-md-9">
                <input type="password" class="form-control" name="card_number" placeholder="Card number"
                       minlength="16" maxlength="16" required>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">
                <f:message key="cvc"/> *
            </label>
            <div class="col-md-9">
                <input type="password" class="form-control" name="cvc" placeholder="CVC"
                       minlength="3" maxlength="3" required>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">
                    <f:message key="expiration"/> *
                </label>
                <div class="col-md-9">
                    <input type="password" class="form-control" name="expiration" placeholder="Expiration"
                           minlength="4" maxlength="4" required>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">
                        <f:message key="pinCode"/> *
                    </label>
                    <div class="col-md-9">
                        <input type="password" class="form-control" name="pin" placeholder="pin"
                               minlength="4" maxlength="4" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">
                        <f:message key="password"/> *
                    </label>
                    <div class="col-md-9">
                        <input type="password" class="form-control" name="password" placeholder="Password"
                               minlength="3" maxlength="16">
                    </div>
                </div>


                <div class="form-group">
                    <!-- Button -->
                    <div class="col-md-offset-3 col-md-9">
                        <input type="submit" value="<f:message key="submit"/>" class="btn btn-primary">
                    </div>
                </div>

            </div>
        </div>
    </form>
</div>
</html>

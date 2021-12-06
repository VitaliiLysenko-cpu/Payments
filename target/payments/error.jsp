<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.lang}"/>
<f:setBundle basename="locale"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <title>Error</title>
</head>
<body>
<H1><f:message key="error"/>!!!</H1>
</body>
</html>

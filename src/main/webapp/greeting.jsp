<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 05.12.2021
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="tld/greetingDescriptor.tld" prefix="m" %>
<f:setLocale value="${sessionScope.lang}"/>
<f:setBundle basename="locale"/>

<html>
<head>
    <title>Title</title>
</head>
<body>

<f:message key="hello"/>  <m:hello/>
</body>
</html>

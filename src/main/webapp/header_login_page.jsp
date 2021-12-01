<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<f:setLocale value="${sessionScope.lang}"/>
<f:setBundle basename="locale"/>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <ul class="list-group list-group-horizontal">
                    <c:forEach items="${locales}" var="locale">
                        <li class="list-group-item">
                            <a href="?lang=${locale}&${pageContext.request.queryString}">${locale}</a>
                        </li>
                    </c:forEach>
                </ul>

            </ul>
        </div>
    </div>

</header>
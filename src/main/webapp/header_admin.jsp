<%--
  Created by IntelliJ IDEA.
  User: vitaliy
  Date: 17.11.2021
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

                <li><a href="${pageContext.request.contextPath}/admin" class="nav-link px-2 link-dark">Home</a></li>

            </ul>

            <div class="dropdown text-end">

                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/images/account.png" alt="mdo" width="32" height="32"
                         class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">

                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/sign_out">Sign out</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

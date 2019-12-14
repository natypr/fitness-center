<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.login" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <p class="text-center"><fmt:message key="text.login.login" bundle="${var}"/></p>

    <div class="row justify-content-md-center">
        <div class="col col-lg-5">
            <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="login"/>

                <div class="form-group">
                    <input type="text" name="login" class="form-control"
                           placeholder="<fmt:message key="text.login.login" bundle="${var}"/>"
                           aria-describedby="emailHelp">
                    <small id="emailHelp" class="form-text text-muted"><fmt:message key="text.login.helptext"
                                                                                    bundle="${var}"/></small>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control"
                           placeholder="<fmt:message key="text.login.password" bundle="${var}"/>" id="idPassword1">
                </div>

                <c:if test="${not empty sessionScope.errorLoginPassMessage}">
                    <div class="alert alert-danger" role="alert">
                            ${sessionScope.errorLoginPassMessage}
                    </div>
                </c:if>

                <div class="text-center">
                    <button type="submit" class="btn btn-outline-success"><fmt:message key="bt.login.login"
                                                                                       bundle="${var}"/></button>
                </div>

                <div class="form-group">
                    <p class="card-text bg-light text-secondary" style="transform: rotate(0);">
                        <a href="${pageContext.request.contextPath}/jsp/registration.jsp"
                           class="text-warning stretched-link"><fmt:message key="href.login.reg" bundle="${var}"/></a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
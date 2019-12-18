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
    <h1><fmt:message key="text.login.login" bundle="${var}"/></h1>

    <div class="row justify-content-md-center">
        <div class="col col-lg-5">
            <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="login"/>

                <div class="form-group">
                    <input type="text" name="login" class="form-control"
                           placeholder="<fmt:message key="text.login.login" bundle="${var}"/>"
                           aria-describedby="emailHelp" maxlength="40">
                    <small id="emailHelp" class="form-text text-muted"><fmt:message key="text.login.helptext"
                                                                                    bundle="${var}"/></small>
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" maxlength="40"
                           placeholder="<fmt:message key="text.login.password" bundle="${var}"/>" id="idPassword1">
                </div>

                <c:if test="${not empty errorLoginPassMessage}">
                    <div class="alert alert-danger" role="alert">
                            ${errorLoginPassMessage}
                    </div>
                </c:if>

                <div class="text-center">
                    <button type="submit" class="btn btn-outline-dark">
                        <fmt:message key="bt.login.login" bundle="${var}"/></button>
                </div>

                <a href="${pageContext.request.contextPath}/jsp/registration.jsp">
                    <fmt:message key="href.login.reg" bundle="${var}"/></a>
            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
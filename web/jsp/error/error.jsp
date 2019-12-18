<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.error" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <h1><fmt:message key="text.error.sorry" bundle="${var}"/></h1>

    <div class="row justify-content-md-center">
        <div class="col col-lg-8">

            <div class="form-group">
                <div class="row">
                    <div class="col-4">
                        <p class="font-weight-bolder"><fmt:message key="text.error.requestfrom" bundle="${var}"/></p>
                    </div>
                    <div class="col">
                        <p>${pageContext.errorData.requestURI}
                            <fmt:message key="text.error.isfailed" bundle="${var}"/></p>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-4">
                        <p class="font-weight-bolder"><fmt:message key="text.error.servletnameortype"
                                                                   bundle="${var}"/></p>
                    </div>
                    <div class="col">
                        <p>${pageContext.errorData.servletName}</p>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-4">
                        <p class="font-weight-bolder"><fmt:message key="text.error.statuscode" bundle="${var}"/></p>
                    </div>
                    <div class="col">
                        <p>${pageContext.errorData.statusCode}</p>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-4">
                        <p class="font-weight-bolder"><fmt:message key="text.error.exception" bundle="${var}"/></p>
                    </div>
                    <div class="col">
                        <p>${pageContext.errorData.throwable}</p>
                    </div>
                </div>
            </div>

            <c:if test="${not empty accessClosed}">
                <div class="alert alert-danger" role="alert">
                        ${accessClosed}
                </div>
            </c:if>

            <a href="${pageContext.request.contextPath}/index.jsp">
                <fmt:message key="bt.main.return" bundle="${var}"/></a>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
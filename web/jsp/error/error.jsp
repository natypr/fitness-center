<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.error" bundle="${var}"/></title>
</head>
<br>
    <h1><fmt:message key="text.error.pagenotfound" bundle="${var}"/></h1>
    <br/>

    <strong><fmt:message key="text.error.requestfrom" bundle="${var}"/></strong>
    ${pageContext.errorData.requestURI}
    <fmt:message key="text.error.isfailed" bundle="${var}"/>
    <br/>
    <strong><fmt:message key="text.error.servletnameortype" bundle="${var}"/></strong>
    ${pageContext.errorData.servletName}
    <br/>
    <strong><fmt:message key="text.error.statuscode" bundle="${var}"/></strong>
    ${pageContext.errorData.statusCode}
    <br/>
    <strong><fmt:message key="text.error.exception" bundle="${var}"/></strong>
    ${pageContext.errorData.throwable}

    </br></br>
    <a href="${pageContext.request.contextPath}/jsp/login.jsp">
        <fmt:message key = "bt.login.return" bundle="${var}"/></a>

    <c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
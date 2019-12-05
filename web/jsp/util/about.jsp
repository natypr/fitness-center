<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.about" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>
<div>
    <h1><fmt:message key="text.about.fitnesscenter" bundle="${var}"/></h1>
    <p>
        <fmt:message key="text.about.mail" bundle="${var}"/>
    </p>
    <p>
        <fmt:message key="text.about.info" bundle="${var}"/>
    </p>
    <p>
        <fmt:message key="text.about.createdby" bundle="${var}"/>
    </p>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
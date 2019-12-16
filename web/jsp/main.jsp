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
    <h1><fmt:message key="text.main.fitnesscenter" bundle="${var}"/></h1>

    <div class="row justify-content-md-center">
        <div class="col col-lg-10">

            <p><fmt:message key="text.main.description1" bundle="${var}"/>
                <fmt:message key="text.main.description2" bundle="${var}"/></p>
            <p><fmt:message key="text.main.description3" bundle="${var}"/></p>

            <h3><fmt:message key="text.main.advantages" bundle="${var}"/></h3>
            <ul>
                <li><fmt:message key="adv1" bundle="${var}"/></li>
                <li><fmt:message key="adv2" bundle="${var}"/></li>
                <li><fmt:message key="adv3" bundle="${var}"/></li>
                <li><fmt:message key="adv4" bundle="${var}"/></li>
                <li><fmt:message key="adv5" bundle="${var}"/></li>
                <li><fmt:message key="adv6" bundle="${var}"/></li>
                <li><fmt:message key="adv7" bundle="${var}"/></li>
                <li><fmt:message key="adv8" bundle="${var}"/></li>
            </ul>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>

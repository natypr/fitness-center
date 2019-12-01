<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.allinfo" bundle="${var}"/></title>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<h3><fmt:message key="text.allinfo.clientlist" bundle="${var}"/>: </h3>
<table border="1" width="60%" cellpadding="5">
    <tr>
        <th><fmt:message key="text.allinfo.id" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.name" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.surname" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.gender" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.yearold" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.email" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.blocked" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.discount" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${sessionScope.clients}" var="client">
        <tr>
            <td>${client.id}</td>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.gender}</td>
            <td>${client.yearOld}</td>
            <td>${client.email}</td>
            <td>${client.blocked}</td>
            <td>${client.discount}</td>
        </tr>
    </c:forEach>
</table>


<h3><fmt:message key="text.allinfo.trainerlist" bundle="${var}"/>: </h3>
<table border="1" width="60%" cellpadding="5">
    <tr>
        <th><fmt:message key="text.allinfo.id" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.name" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.surname" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.gender" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.yearold" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.email" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.blocked" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.education" bundle="${var}"/></th>
        <th><fmt:message key="text.allinfo.costperoneworkout" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${sessionScope.trainers}" var="trainer">
        <tr>
            <td>${trainer.id}</td>
            <td>${trainer.name}</td>
            <td>${trainer.surname}</td>
            <td>${trainer.gender}</td>
            <td>${trainer.yearOld}</td>
            <td>${trainer.email}</td>
            <td>${trainer.blocked}</td>
            <td>${trainer.education}</td>
            <td>${trainer.costPerOneWorkout}</td>
        </tr>
    </c:forEach>
</table>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
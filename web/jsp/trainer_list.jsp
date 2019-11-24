<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.trainerlist" bundle="${var}"/></title>
    <c:import url="/jsp/util/header.jsp"/>
</head>
<body>
<h3><fmt:message key="text.trainerlist.trainerlist" bundle="${var}"/>: </h3>

<table border="1" width="60%" cellpadding="5">
    <tr>
        <th><fmt:message key="text.trainerlist.name" bundle="${var}"/></th>
        <th><fmt:message key="text.trainerlist.surname" bundle="${var}"/></th>
        <th><fmt:message key="text.trainerlist.gender" bundle="${var}"/></th>
        <th><fmt:message key="text.trainerlist.yearold" bundle="${var}"/></th>
        <th><fmt:message key="text.trainerlist.education" bundle="${var}"/></th>
        <th><fmt:message key="text.trainerlist.costperoneworkout" bundle="${var}"/></th>
    </tr>
    <c:forEach items="${trainers}" var="trainers">
        <tr>
            <td>${trainers.name}</td>
            <td>${trainers.surname}</td>
            <td>${trainers.gender}</td>
            <td>${trainers.yearOld}</td>
            <td>${trainers.education}</td>
            <td>${trainers.costPerOneWorkout}</td>
        </tr>
    </c:forEach>
</table>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
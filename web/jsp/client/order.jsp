<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.order" bundle="${var}"/></title>
    <c:import url="/jsp/util/header.jsp"/>
</head>
<body>
<h1><fmt:message key="text.order.orderpage" bundle="${var}"/></h1>
<br/>

<form name="orderPage" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="order"/>

    <h3><fmt:message key="text.order.typeofworkout" bundle="${var}"/></h3>
    <select name="select_type_of_workout">
        <option value="aerobic" selected><fmt:message key="text.order.typeofworkout.aerobic" bundle="${var}"/></option>
        <option value="cardio"><fmt:message key="text.order.typeofworkout.cardio" bundle="${var}"/></option>
        <option value="power"><fmt:message key="text.order.typeofworkout.power" bundle="${var}"/></option>
    </select>
    <br/><br/>

    <strong><fmt:message key="text.order.numberofworkout" bundle="${var}"/></strong><br/>
    <input type="number" value="1" name="number_of_workout">


    <h3><fmt:message key="text.order.trainerlist" bundle="${var}"/></h3>
    <table border="1" width="60%" cellpadding="5">
        <tr>
            <th><fmt:message key="text.order.select" bundle="${var}"/></th>
            <th><fmt:message key="text.order.name" bundle="${var}"/></th>
            <th><fmt:message key="text.order.surname" bundle="${var}"/></th>
            <th><fmt:message key="text.order.gender" bundle="${var}"/></th>
            <th><fmt:message key="text.order.yearold" bundle="${var}"/></th>
            <th><fmt:message key="text.order.email" bundle="${var}"/></th>
            <th><fmt:message key="text.order.education" bundle="${var}"/></th>
            <th><fmt:message key="text.order.costperoneworkout" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${trainers}" var="trainers">
            <tr>
                <td><input type="radio" name="select_trainer" value="${trainers.email}" id="id_trainer"/></td>
                <td>${trainers.name}</td>
                <td>${trainers.surname}</td>
                <td>${trainers.gender}</td>
                <td>${trainers.yearOld}</td>
                <td>${trainers.email}</td>
                <td>${trainers.education}</td>
                <td>${trainers.costPerOneWorkout}</td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <input type="submit" value=<fmt:message key="bt.order.makeorder" bundle="${var}"/> name="make_order">

    <br/> ${successfulOrder} <br/>
</form>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
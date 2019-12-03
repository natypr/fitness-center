<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.trainercabinet" bundle="${var}"/></title>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>
<h1><fmt:message key="text.trainercabinet.cabinet" bundle="${var}"/></h1>
<br/>

<a href="${pageContext.request.contextPath}/jsp/trainer/update_profile.jsp">
    <fmt:message key="href.trainercabinet.updateprofile" bundle="${var}"/></a>
<div>
    <strong><fmt:message key="text.trainercabinet.name" bundle="${var}"/></strong>
    ${sessionScope.trainer.name} </br>

    <strong><fmt:message key="text.trainercabinet.surname" bundle="${var}"/></strong>
    ${sessionScope.trainer.surname} </br>

    <strong><fmt:message key="text.trainercabinet.gender" bundle="${var}"/></strong>
    ${sessionScope.trainer.gender} </br>

    <strong><fmt:message key="text.trainercabinet.yearold" bundle="${var}"/></strong>
    ${sessionScope.trainer.yearOld} </br>

    <strong><fmt:message key="text.trainercabinet.email" bundle="${var}"/></strong>
    ${sessionScope.trainer.email} </br></br></br></br>
</div>

<form name="trainerCabinet" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="trainer_cabinet"/>

    <%--    <table border="1" width="60%" cellpadding="5">
            <tr>
                <th><fmt:message key="text.trainercabinet.select" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.name" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.surname" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.gender" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.yearold" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.email" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.discount" bundle="${var}"/></th>

                <th><fmt:message key="text.trainercabinet.typeofworkout" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.numberofworkout" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.nameofworkout" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.equipment" bundle="${var}"/></th>
                <th><fmt:message key="text.trainercabinet.description" bundle="${var}"/></th>
            </tr>

            <c:forEach items="${sessionScope.clientInfo}" var="clientInfo" varStatus="loop">
                <tr>
                    <td><input type="checkbox" name="select_client" value="${clientInfo.client.email}" id=""/></td>
                    <td>${clientInfo.client.name}</td>
                    <td>${clientInfo.client.surname}</td>
                    <td>${clientInfo.client.gender}</td>
                    <td>${clientInfo.client.yearOld}</td>
                    <td>${clientInfo.client.email}</td>
                    <td>${clientInfo.client.discount}</td>
                </tr>
                <c:forEach items="${clientInfo.workoutList}" var="workouts" varStatus="loop">
                    <tr>
                        <td>${workouts.typeWorkout}</td>
                        <td>${workouts.numberOfVisit}</td>
                        <td>${workouts.nameOfWorkout}</td>
                        <td>${workouts.equipment}</td>
                        <td>${workouts.description}</td>
                    </tr>
                </c:forEach>
            </c:forEach>

        </table>--%>


    <table border="1" width="60%" cellpadding="5">
        <tr>
            <th><fmt:message key="text.trainercabinet.select" bundle="${var}"/></th>
            <th><fmt:message key="table.client.typeofworkout" bundle="${var}"/></th>
            <th><fmt:message key="table.client.numberofworkout" bundle="${var}"/></th>
            <th><fmt:message key="table.client.equipment" bundle="${var}"/></th>
            <th><fmt:message key="table.client.description" bundle="${var}"/></th>
            <th><fmt:message key="table.client.ispaid" bundle="${var}"/></th>

            <th><fmt:message key="text.trainercabinet.name" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.surname" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.gender" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.yearold" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.email" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.discount" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${sessionScope.orders}" var="order">
            <tr>
                <td><input type="radio" name="select_order" value="${order.id}" id=""/></td>
                <td>${order.typeOfWorkout}</td>
                <td>${order.numberOfWorkout}</td>
                <td>${order.equipment}</td>
                <td>${order.description}</td>
                <td>${order.isPaid}</td>
            </tr>
        </c:forEach>

        <c:forEach items="${sessionScope.clients}" var="client">
            <tr>
                <td>${client.name}</td>
                <td>${client.surname}</td>
                <td>${client.gender}</td>
                <td>${client.yearOld}</td>
                <td>${client.email}</td>
                <td>${client.discount}</td>
            </tr>
        </c:forEach>
    </table>


    </br></br></br></br>
    <strong><fmt:message key="text.trainercabinet.equipment" bundle="${var}"/></strong>
    <input type="text" name="equipment" value=""/></br>

    <strong><fmt:message key="text.trainercabinet.description" bundle="${var}"/></strong>
    <input type="text" name="description" value=""/></br></br>


    <input type="submit" name="action_workout"
           value="<fmt:message key = "bt.trainercabinet.updateworkout" bundle="${var}"/>">
</form>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>

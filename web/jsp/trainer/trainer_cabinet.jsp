<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.trainercabinet" bundle="${var}"/></title>
    <c:import url="/jsp/util/header.jsp"/>
</head>
<body>
<form name="trainerCabinet" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="trainer_cabinet"/>

    <h1><fmt:message key="text.trainercabinet.cabinet" bundle="${var}"/></h1>
    <br/>

    <div>
        <strong><fmt:message key="text.trainercabinet.name" bundle="${var}"/></strong>
        ${trainer.name} </br>

        <strong><fmt:message key="text.trainercabinet.surname" bundle="${var}"/></strong>
        ${trainer.surname} </br>

        <strong><fmt:message key="text.trainercabinet.gender" bundle="${var}"/></strong>
        ${trainer.gender} </br>

        <strong><fmt:message key="text.trainercabinet.yearold" bundle="${var}"/></strong>
        ${trainer.yearOld} </br>

        <strong><fmt:message key="text.trainercabinet.email" bundle="${var}"/></strong>
        ${trainer.email}</br></br></br></br>
    </div>

    <table border="1" width="60%" cellpadding="5">
        <tr>
            <th><fmt:message key="text.trainercabinet.select" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.name" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.surname" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.gender" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.yearold" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.email" bundle="${var}"/></th>

            <th><fmt:message key="text.trainercabinet.typeWorkout" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.nameOfWorkout" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.equipment" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.description" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.costPerOneWorkout" bundle="${var}"/></th>
            <th><fmt:message key="text.trainercabinet.numberOfVisit" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${clientInfo}" var="clientInfo" varStatus="loop">
            <tr>
                <td><input type="checkbox" name="select_client" value="${clientInfo.client.email}" id=""/></td>
                <td>${clientInfo.client.name}</td>
                <td>${clientInfo.client.surname}</td>
                <td>${clientInfo.client.gender}</td>
                <td>${clientInfo.client.yearOld}</td>
                <td>${clientInfo.client.email}</td>
            </tr>
            <c:forEach items="${clientInfo.workoutList}" var="workouts" varStatus="loop">
                <tr>
                    <td>${workouts.typeWorkout}</td>
                    <td>${workouts.nameOfWorkout}</td>
                    <td>${workouts.equipment}</td>
                    <td>${workouts.description}</td>
                    <td>${workouts.costPerOneWorkout}</td>
                    <td>${workouts.numberOfVisit}</td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>


    </br></br></br></br>
    <strong> <fmt:message key="text.trainercabinet.typeWorkout" bundle="${var}"/></strong>
    <input type="text" name="type_workout" value=""/></br>


    <strong><fmt:message key="text.trainercabinet.nameOfWorkout" bundle="${var}"/></strong>
    <input type="text" name="name_of_workout" value=""/></br>


    <strong><fmt:message key="text.trainercabinet.equipment" bundle="${var}"/></strong>
    <input type="text" name="equipment" value=""/></br>

    <strong><fmt:message key="text.trainercabinet.description" bundle="${var}"/></strong>
    <input type="text" name="description" value=""/></br>

    <strong><fmt:message key="text.trainercabinet.costPerOneWorkout" bundle="${var}"/></strong>
    <input type="text" name="cost_per_one_workout" value=""/></br>

    <strong><fmt:message key="text.trainercabinet.numberOfVisit" bundle="${var}"/></strong>
    <input type="text" name="number_of_visit" value=""/></br></br>


    <input type="submit" name="action_workout" value="Add workout">
    <input type="submit" name="action_workout" value="Delete workout">
    <input type="submit" name="action_workout" value="Update workout">
</form>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>

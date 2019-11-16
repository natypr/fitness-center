<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.clientcabinet" bundle="${var}"/></title>
</head>
<body>
<h1><fmt:message key="text.clientcabinet.cabinet" bundle="${var}"/></h1>
<br/>

<div>
    <div>
        <strong><fmt:message key="text.clientcabinet.name" bundle="${var}"/></strong>
        ${client.name}
        </br>

        <strong><fmt:message key="text.clientcabinet.surname" bundle="${var}"/></strong>>
        ${client.surname}
        </br>

        <strong><fmt:message key="text.clientcabinet.gender" bundle="${var}"/></strong>
        ${client.gender}
        </br>

        <strong><fmt:message key="text.clientcabinet.yearold" bundle="${var}"/></strong>
        ${client.yearOld}
        </br>

        <strong><fmt:message key="text.clientcabinet.email" bundle="${var}"/></strong>
        ${client.email}
        </br>
    </div>


    <form name="clientCabinet" method="POST" action = "${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="client_cabinet"/>

        <table>
            <tr>
                <th><fmt:message key="table.client.nameOfWorkout" bundle="${var}"/></th>
                <th><fmt:message key="table.client.typeWorkout" bundle="${var}"/></th>
                <th><fmt:message key="table.client.equipment" bundle="${var}"/></th>
                <th><fmt:message key="table.client.description" bundle="${var}"/></th>
                <th><fmt:message key="table.client.numberOfVisit" bundle="${var}"/></th>
                <th><fmt:message key="table.client.button.refuse" bundle="${var}"/></th>
            </tr>

            <c:forEach items="${workout}" var="workout_n" begin="${index}" end="${index}" >
                <tr>
                    <td>${workout_n.nameOfWorkout}</td>
                    <td>${workout_n.typeWorkout}</td>
                    <td>${workout_n.equipment}</td>
                    <td>${workout_n.description}</td>
                    <td>${workout_n.numberOfVisit}</td>
                    <td><input type="submit"
                               value= "<fmt:message key="table.client.button.refuse" bundle="${var}"/>"
                               name="Refuse"></td>
                </tr>
            </c:forEach>
        </table>
    </form>

</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
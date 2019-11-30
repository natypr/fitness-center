<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.clientcabinet" bundle="${var}"/></title>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>
<h1><fmt:message key="text.clientcabinet.cabinet" bundle="${var}"/></h1>
<br/>

<a href="${pageContext.request.contextPath}/jsp/client/order.jsp">
    <fmt:message key="href.clientcabinet.makeorder" bundle="${var}"/></a>
<a href="${pageContext.request.contextPath}/jsp/client/update_profile.jsp">
    <fmt:message key="href.clientcabinet.updateprofile" bundle="${var}"/></a>
<br/>
<div>
    <div>
        <strong><fmt:message key="text.clientcabinet.name" bundle="${var}"/></strong>
        ${client.name} </br>

        <strong><fmt:message key="text.clientcabinet.surname" bundle="${var}"/></strong>
        ${client.surname} </br>

        <strong><fmt:message key="text.clientcabinet.gender" bundle="${var}"/></strong>
        ${client.gender} </br>

        <strong><fmt:message key="text.clientcabinet.yearold" bundle="${var}"/></strong>
        ${client.yearOld} </br>

        <strong><fmt:message key="text.clientcabinet.email" bundle="${var}"/></strong>
        ${client.email} </br>

        <strong><fmt:message key="text.clientcabinet.discount" bundle="${var}"/></strong>
        ${client.discount} </br></br></br></br>
    </div>


    <form name="clientCabinet" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="client_cabinet"/>

        <table border="1" width="60%" cellpadding="5">
            <tr>
                <th><fmt:message key="table.client.typeofworkout" bundle="${var}"/></th>
                <th><fmt:message key="table.client.numberofworkout" bundle="${var}"/></th>
                <th><fmt:message key="table.client.nameofworkout" bundle="${var}"/></th>
                <th><fmt:message key="table.client.equipment" bundle="${var}"/></th>
                <th><fmt:message key="table.client.description" bundle="${var}"/></th>
                <th><fmt:message key="table.client.button.refuse" bundle="${var}"/></th>
                <th><fmt:message key="table.client.button.pay" bundle="${var}"/></th>
            </tr>

            <c:forEach items="${sessionScope.orders}" var="workout_n" begin="${index}" end="${index}">
                <tr>
                    <td>${workout_n.nameOfWorkout}</td>
                    <td>${workout_n.typeWorkout}</td>
                    <td>${workout_n.equipment}</td>
                    <td>${workout_n.description}</td>
                    <td>${workout_n.numberOfVisit}</td>
                    <td><input type="submit"
                               value="<fmt:message key="table.client.button.refuse" bundle="${var}"/>"
                               name="refuse"></td>
                    <td><input type="submit"
                               value="<fmt:message key="table.client.button.pay" bundle="${var}"/>"
                               name="pay"></td>
                </tr>
            </c:forEach>
        </table>
    </form>

</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
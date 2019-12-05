<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.allinfo" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <div class="row justify-content-md-center">
        <div class="col col-lg-10">

            <p class="text-center"><fmt:message key="text.allinfo.clientlist" bundle="${var}"/></p>
            <table class="table table-hover">
                <caption><fmt:message key="text.allinfo.clientlist" bundle="${var}"/></caption>
                <thead class="thead-dark">
                <tr>
                    <th scope="col"><fmt:message key="text.allinfo.id" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.name" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.surname" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.gender" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.yearold" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.email" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.blocked" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.discount" bundle="${var}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.clients}" var="client">
                    <tr>
                        <th scope="row">${client.id}</th>
                        <td>${client.name}</td>
                        <td>${client.surname}</td>
                        <td>${client.gender}</td>
                        <td>${client.yearOld}</td>
                        <td>${client.email}</td>
                        <td>${client.blocked}</td>
                        <td>${client.discount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <p class="text-center"><fmt:message key="text.allinfo.trainerlist" bundle="${var}"/></p>
            <table class="table table-hover">
                <caption><fmt:message key="text.allinfo.trainerlist" bundle="${var}"/></caption>
                <thead class="thead-dark">
                <tr>
                    <th scope="col"><fmt:message key="text.allinfo.id" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.name" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.surname" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.gender" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.yearold" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.email" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.blocked" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.education" bundle="${var}"/></th>
                    <th scope="col"><fmt:message key="text.allinfo.costperoneworkout" bundle="${var}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.trainers}" var="trainer">
                    <tr>
                        <th scope="row">${trainer.id}</th>
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
                </tbody>
            </table>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
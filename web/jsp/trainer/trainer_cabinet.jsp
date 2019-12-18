<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.trainercabinet" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <h1><fmt:message key="text.trainercabinet.cabinet" bundle="${var}"/></h1>

    <div class="row justify-content-md-center">
        <div class="col col-lg-11">

            <div class="form-group">
                <div class="row">
                    <div class="col-2">
                        <p class="font-weight-bolder"><fmt:message key="text.trainercabinet.name" bundle="${var}"/></p>
                    </div>
                    <div class="col-3">
                        <p>${trainer.name}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-2">
                        <p class="font-weight-bolder">
                            <fmt:message key="text.trainercabinet.surname" bundle="${var}"/></p>
                    </div>
                    <div class="col-3">
                        <p>${trainer.surname}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-2">
                        <p class="font-weight-bolder">
                            <fmt:message key="text.trainercabinet.gender" bundle="${var}"/></p>
                    </div>
                    <div class="col-3">
                        <p>${trainer.gender}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-2">
                        <p class="font-weight-bolder">
                            <fmt:message key="text.trainercabinet.yearold" bundle="${var}"/></p>
                    </div>
                    <div class="col-3">
                        <p>${trainer.yearOld}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-2">
                        <p class="font-weight-bolder"><fmt:message key="text.trainercabinet.email" bundle="${var}"/></p>
                    </div>
                    <div class="col-3">
                        <p>${trainer.email}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-2">
                        <p class="font-weight-bolder">
                            <fmt:message key="text.trainercabinet.education" bundle="${var}"/></p>
                    </div>
                    <div class="col-3">
                        <p>${trainer.education}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-2">
                        <p class="font-weight-bolder">
                            <fmt:message key="text.trainercabinet.costperoneworkout" bundle="${var}"/></p>
                    </div>
                    <div class="col-3">
                        <p>${trainer.costPerOneWorkout}</p>
                    </div>
                </div>
            </div>

            <a href="${pageContext.request.contextPath}/jsp/trainer/update_profile.jsp">
                <fmt:message key="href.trainercabinet.updateprofile" bundle="${var}"/></a>

            <form name="trainerCabinet" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="trainer_cabinet"/>

                <table class="table table-hover">
                    <caption><fmt:message key="text.trainercabinet.listofclientswithorders" bundle="${var}"/></caption>
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="text.trainercabinet.name" bundle="${var}"/></th>
                        <%--<th scope="col"><fmt:message key="text.trainercabinet.surname" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.trainercabinet.gender" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.trainercabinet.yearold" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.trainercabinet.email" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.trainercabinet.discount" bundle="${var}"/></th>--%>

                        <th scope="col"><fmt:message key="text.trainercabinet.select" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.trainercabinet.idorder" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.trainercabinet.idtrainer" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.typeofworkout" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.numberofworkout" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.equipment" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.description" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.ispaid" bundle="${var}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.clients}" var="client">
                        <tr>
                            <td>${client.name}</td>
                            <td>${client.surname}</td>
                            <td>${client.gender}</td>
                            <td>${client.yearOld}</td>
                            <td>${client.email}</td>
                            <td>${client.discount}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <c:forEach items="${client.orderList}" var="order">
                            <tr>
                                <td></td>
                                <td><input type="radio" name="select_order" value="${order.id}" id="id"/></td>
                                <td>${order.id}</td>
                                <td>${order.idTrainer}</td>
                                <td>${order.typeOfWorkout}</td>
                                <td>${order.numberOfWorkout}</td>
                                <td>${order.equipment}</td>
                                <td>${order.description}</td>
                                <td>${order.paid}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="col col-lg text-center">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-2">
                                <label for="idEquipment1">
                                    <fmt:message key="text.trainercabinet.equipment" bundle="${var}"/></label>
                            </div>
                            <div class="col">
                                <input type="text" class="form-control" id="idEquipment1" name="equipment" value=""
                                       required maxlength="40" pattern="^([A-Za-z ,-_]{3,40})|([А-Яа-я ,-_]{3,40})$"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-2">
                                <label for="idDescription1">
                                    <fmt:message key="text.trainercabinet.description" bundle="${var}"/></label>
                            </div>
                            <div class="col">
                                <input type="text" class="form-control" id="idDescription1" name="description"
                                       value="" required
                                       maxlength="100" pattern="^([A-Za-z ,-_]{3,100})|([А-Яа-я ,-_]{3,100})$"/>
                            </div>
                        </div>
                    </div>

                    <c:if test="${not empty dataIsNotCorrect}">
                        <div class="alert alert-danger" role="alert">
                                ${dataIsNotCorrect}
                        </div>
                    </c:if>

                    <c:if test="${not empty selectWorkoutRadio}">
                        <div class="alert alert-warning" role="alert">
                                ${selectWorkoutRadio}
                        </div>
                    </c:if>

                    <c:if test="${not empty cannotChangePaidOrder}">
                        <div class="alert alert-danger" role="alert">
                                ${cannotChangePaidOrder}
                        </div>
                    </c:if>

                    <c:if test="${not empty cannotChangeOrderOfAnotherTrainer}">
                        <div class="alert alert-danger" role="alert">
                                ${cannotChangeOrderOfAnotherTrainer}
                        </div>
                    </c:if>

                    <div class="text-center">
                        <button type="submit" class="btn btn btn-outline-dark" name="update_order">
                            <fmt:message key="bt.trainercabinet.updateworkout" bundle="${var}"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>

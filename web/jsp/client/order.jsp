<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.order" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <p class="text-center"><fmt:message key="text.order.orderpage" bundle="${var}"/></p>

    <div class="row justify-content-md-center">
        <div class="col col-lg-10">

            <form name="orderPage" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="order"/>

                <div class="col col-lg-3 justify-content-md-left">
                    <div class="form-group">
                        <select class="custom-select" name="select_type_of_workout" required>
                            <option value=""><fmt:message key="text.order.typeofworkout" bundle="${var}"/></option>
                            <option value="aerobic"><fmt:message key="text.order.typeofworkout.aerobic"
                                                                 bundle="${var}"/></option>
                            <option value="cardio"><fmt:message key="text.order.typeofworkout.cardio"
                                                                bundle="${var}"/></option>
                            <option value="power"><fmt:message key="text.order.typeofworkout.power"
                                                               bundle="${var}"/></option>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="number" class="form-control"
                               placeholder="<fmt:message key="text.order.numberofworkout" bundle="${var}"/>"
                               name="number_of_workout"
                               value="" required pattern="^([0-9]{1,3})$"/>
                    </div>
                </div>

                <p class="text-center"><fmt:message key="text.order.trainerlist" bundle="${var}"/></p>
                <table class="table table-hover">
                    <caption><fmt:message key="text.order.trainerlist" bundle="${var}"/></caption>
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="text.order.select" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.order.name" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.order.surname" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.order.gender" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.order.yearold" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.order.email" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.order.education" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.order.costperoneworkout" bundle="${var}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.trainers}" var="trainer">
                        <tr>
                            <td><input type="radio" name="select_trainer" value="${trainer.email}" id="id_trainer"/>
                            </td>
                            <td>${trainer.name}</td>
                            <td>${trainer.surname}</td>
                            <td>${trainer.gender}</td>
                            <td>${trainer.yearOld}</td>
                            <td>${trainer.email}</td>
                            <td>${trainer.education}</td>
                            <td>${trainer.costPerOneWorkout}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${not empty sessionScope.successfulOrder}">
                    <div class="alert alert-success" role="alert">
                            ${sessionScope.successfulOrder}
                    </div>
                </c:if>


                <div class="text-center">
                    <button type="submit" class="btn btn btn-outline-success" name="make_order">
                        <fmt:message key="bt.order.makeorder" bundle="${var}"/></button>
                </div>

                <div class="form-group">
                    <p class="card-text bg-light text-secondary" style="transform: rotate(0);">
                        <a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp"
                           class="text-warning stretched-link">
                            <fmt:message key="href.clientcabinet" bundle="${var}"/></a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
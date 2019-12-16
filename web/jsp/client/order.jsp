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
    <h1><fmt:message key="text.order.orderpage" bundle="${var}"/></h1>

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
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="text.order.numberofworkout" bundle="${var}"/>"
                               name="number_of_workout"
                               value="" required pattern="^([1-9][0-9]{1,2})$"/>
                    </div>
                </div>

                <h3><fmt:message key="text.order.trainerlist" bundle="${var}"/></h3>
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

                <c:if test="${not empty selectTrainerRadio}">
                    <div class="alert alert-warning" role="alert">
                            ${selectTrainerRadio}
                    </div>
                </c:if>

                <c:if test="${not empty successfulOrder}">
                    <div class="alert alert-success" role="alert">
                            ${successfulOrder}
                    </div>
                </c:if>


                <div class="text-center">
                    <button type="submit" class="btn btn btn-outline-dark" name="make_order">
                        <fmt:message key="bt.order.makeorder" bundle="${var}"/></button>
                </div>

                <a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">
                    <fmt:message key="href.clientcabinet" bundle="${var}"/></a>

            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
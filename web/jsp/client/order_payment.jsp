<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.orderpayment" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <h1><fmt:message key="text.orderpayment.orderpayment" bundle="${var}"/></h1>

    <div class="row justify-content-md-center">
        <div class="col col-lg-10">

            <form name="orderPayment" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="order_payment"/>

                <p><fmt:message key="table.client.text.help.pay" bundle="${var}"/></p>

                <table class="table table-hover">
                    <caption><fmt:message key="text.client.orderlist" bundle="${var}"/></caption>
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="table.client.select" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.typeofworkout" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.numberofworkout" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.idtrainer" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.equipment" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.description" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="table.client.ispaid" bundle="${var}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.unpaidOrders}" var="order">
                        <tr>
                            <td><input type="submit" name="select_order" class="btn btn btn-outline-success"
                                       value="${order.id}" id="id_order"/></td>
                            <td>${order.typeOfWorkout}</td>
                            <td>${order.numberOfWorkout}</td>
                            <td>${order.idTrainer}</td>
                            <td>${order.equipment}</td>
                            <td>${order.description}</td>
                            <td>${order.paid}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


                <div class="container row">
                    <div class="col"></div>
                    <div class="col-6">
                        <p><fmt:message key="text.orderpayment.helpdiscount" bundle="${var}"/></p>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-9">
                                    <p class="font-weight-bolder">
                                        <fmt:message key="text.orderpayment.price" bundle="${var}"/></p>
                                </div>
                                <div class="col-1">
                                    <p>${price}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-9">
                                    <p class="font-weight-bolder">
                                        <fmt:message key="text.orderpayment.discount" bundle="${var}"/></p>
                                </div>
                                <div class="col-1">
                                    <p>${newDiscount}%</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-9">
                                    <p class="font-weight-bolder"><fmt:message key="text.orderpayment.totalprice"
                                                                               bundle="${var}"/></p>
                                </div>
                                <div class="col-1">
                                    <p>${totalPrice}</p>
                                </div>
                            </div>
                        </div>


                        <p><fmt:message key="text.orderpayment.paymentservice" bundle="${var}"/></p>

                        <c:if test="${not empty selectOrderRadio}">
                            <div class="alert alert-warning" role="alert">
                                    ${selectOrderRadio}
                            </div>
                        </c:if>

                        <c:if test="${not empty orderSuccessfullyPaid}">
                            <div class="alert alert-success" role="alert">
                                    ${orderSuccessfullyPaid}
                            </div>
                        </c:if>

                        <div class="text-center">
                            <button type="submit" class="btn btn btn-outline-success" name="pay">
                                <fmt:message key="table.client.button.pay" bundle="${var}"/></button>
                        </div>

                        <a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">
                            <fmt:message key="href.clientcabinet" bundle="${var}"/></a>
                    </div>
                    <div class="col"></div>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
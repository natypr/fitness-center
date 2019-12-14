<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.reg" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <p class="text-center"><fmt:message key="text.reg.registration" bundle="${var}"/></p>

    <div class="row justify-content-md-center">
        <div class="col col-lg-5">
            <form name="signForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="registration"/>

                <div class="form-group">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="radio_role" value="Client" id="idClient1"
                               checked>
                        <label class="form-check-label" for="idClient1"><fmt:message key="radio.reg.client"
                                                                                     bundle="${var}"/></label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="radio_role" value="Trainer" id="idTrainer1">
                        <label class="form-check-label" for="idTrainer1"><fmt:message key="radio.reg.trainer"
                                                                                      bundle="${var}"/></label>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col">
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="text.reg.firstname" bundle="${var}"/>" name="name"
                               value=""
                               id="idName1" required
                               pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/>
                    </div>
                    <div class="col">
                        <input type="text" class="form-control"
                               placeholder="<fmt:message key="text.reg.lastname" bundle="${var}"/>" name="surname"
                               value="" id="idSurname1" required
                               pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="radio_gender" value="M" checked
                                   id="idRadioM">
                            <label class="form-check-label" for="idRadioM"><fmt:message key="radio.reg.genderM"
                                                                                        bundle="${var}"/></label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="radio_gender" value="F" id="idRadioF">
                            <label class="form-check-label" for="idRadioF"><fmt:message key="radio.reg.genderF"
                                                                                        bundle="${var}"/></label>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <input type="number" class="form-control"
                                   placeholder="<fmt:message key="text.reg.yearold" bundle="${var}"/>" name="year_old"
                                   value="" id="idYearOld1" required
                                   pattern="^([0-9]{1,2})$"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control"
                           placeholder="<fmt:message key="text.reg.email" bundle="${var}"/>" name="email" value=""
                           id="idEmail1" required
                           pattern="^([a-z0-9_.-]+)@([a-z0-9_.-]+)\.([a-z]{2,5})$"/>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control"
                           placeholder="<fmt:message key="text.reg.password" bundle="${var}"/>" name="password" value=""
                           required aria-describedby="passwordHelpBlock"
                           pattern="^([A-Za-z0-9_-]{8,40})$"/>
                    <small id="passwordHelpBlock" class="form-text text-muted">
                        <fmt:message key="text.reg.passwordhelp" bundle="${var}"/>
                    </small>
                </div>

                <c:if test="${not empty sessionScope.errorLoginPassMessage}">
                    <div class="alert alert-danger" role="alert">
                            ${sessionScope.errorLoginPassMessage}
                    </div>
                </c:if>

                <div class="text-center">
                    <button type="submit" class="btn btn-outline-success">
                        <fmt:message key="bt.reg.signup" bundle="${var}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>

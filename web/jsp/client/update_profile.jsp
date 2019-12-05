<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.updateprofile" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <p class="text-center"><fmt:message key="text.updateprofile.updateprofile" bundle="${var}"/></p>

    <div class="row justify-content-md-center">
        <div class="col col-lg-5">

            <form name="clientUpdate" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="client_update"/>

                <div class="form-group">
                    <div class="row">
                        <div class="col-3">
                            <label for="idName1"><fmt:message key="text.updateprofile.name" bundle="${var}"/></label>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" id="idName1" name="name" value="" required
                                   pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-3">
                            <label for="idSurname1"><fmt:message key="text.updateprofile.surname"
                                                                 bundle="${var}"/></label>
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" id="idSurname1" name="surname" value="" required
                                   pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-3">
                            <label for="idYearOld1"><fmt:message key="text.updateprofile.yearold"
                                                                 bundle="${var}"/></label>
                        </div>
                        <div class="col">
                            <input type="number" class="form-control" id="idYearOld1" name="year_old" value=""
                                   required/>
                        </div>
                    </div>
                </div>

                <c:if test="${not empty sessionScope.successfullyUpdated}">
                    <div class="alert alert-success" role="alert">
                            ${sessionScope.successfullyUpdated}
                    </div>
                </c:if>

                <button type="submit" class="btn btn-primary" name="update_profile"><fmt:message key="bt.updateprofile"
                                                                                                 bundle="${var}"/></button>

                <a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">
                    <fmt:message key="href.clientcabinet" bundle="${var}"/></a>
            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
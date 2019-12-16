<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.admincabinet" bundle="${var}"/></title>
    <c:import url="/jsp/util/head_link.jsp"/>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>

<div class="container-fluid">
    <h1><fmt:message key="text.admincabinet.cabinet" bundle="${var}"/></h1>

    <div class="row justify-content-md-center">
        <div class="col col-lg-10">

            <a href="${pageContext.request.contextPath}/controller?command=admin_show_info">
                <fmt:message key="href.admincabinet.showallinfo" bundle="${var}"/></a>

            <form name="adminCabinetClients" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="admin_block_user"/>

                <h3><fmt:message key="text.admincabinet.listofuser" bundle="${var}"/></h3>
                <table class="table table-hover">
                    <caption><fmt:message key="text.admincabinet.listofuser" bundle="${var}"/></caption>
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="text.admincabinet.select" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.admincabinet.id" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.admincabinet.role" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.admincabinet.name" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.admincabinet.surname" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.admincabinet.email" bundle="${var}"/></th>
                        <th scope="col"><fmt:message key="text.admincabinet.blocked" bundle="${var}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.users}" var="user">
                        <tr>
                            <td><input type="checkbox" name="select_user" value="${user.id}"/></td>
                            <th scope="row">${user.id}</th>
                            <td>${user.role}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.email}</td>
                            <td>${user.blocked}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${not empty selectUserCheckbox}">
                    <div class="alert alert-warning" role="alert">
                            ${selectUserCheckbox}
                    </div>
                </c:if>

                <c:if test="${not empty adminCannotBeBlocked}">
                    <div class="alert alert-danger" role="alert">
                            ${adminCannotBeBlocked}
                    </div>
                </c:if>


                <div class="text-center">
                    <button type="submit" class="btn btn btn-outline-success" name="button_admin_unblock_user">
                        <fmt:message key="bt.admin.unblock.user" bundle="${var}"/></button>
                    <button type="submit" class="btn btn-outline-danger" name="button_admin_block_user">
                        <fmt:message key="bt.admin.block.user" bundle="${var}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
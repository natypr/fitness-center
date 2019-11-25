<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.admincabinet" bundle="${var}"/></title>
    <c:import url="/jsp/util/header.jsp"/>
</head>
<body>
<h1><fmt:message key="text.admincabinet.cabinet" bundle="${var}"/></h1>
<br/>

<form name="adminCabinetClients" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="admin_block_client"/>

    <h3><fmt:message key="text.admincabinet.listofclient" bundle="${var}"/></h3>
    <table border="1" width="60%" cellpadding="5">
        <tr>
            <th><fmt:message key="text.admincabinet.select" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.idclient" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.name" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.surname" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.gender" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.yearold" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.email" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.discount" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.blocked" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${clients}" var="clients">
            <tr>
                <td>
                    <div><input type="checkbox" name="select_client" value="${clients.email}" id="id_client"/></div>
                </td>
                <td>${clients.id}</td>
                <td>${clients.name}</td>
                <td>${clients.surname}</td>
                <td>${clients.gender}</td>
                <td>${clients.yearOld}</td>
                <td>${clients.email}</td>
                <td>${clients.discount}</td>
                <td>${clients.blocked}</td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <input type="submit" value=
    <fmt:message key="bt.admin.block.client" bundle="${var}"/> name="button_admin_block_client">

    <input type="submit" value=
    <fmt:message key="bt.admin.unblock.client" bundle="${var}"/> name="button_admin_unblock_client">
    <br/><br/>
</form>


<form name="adminCabinetTrainers" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="admin_block_trainer"/>

    <h3><fmt:message key="text.admincabinet.listoftrainers" bundle="${var}"/></h3>
    <table border="1" width="60%" cellpadding="5">
        <tr>
            <th><fmt:message key="text.admincabinet.select" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.idtrainer" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.name" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.surname" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.gender" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.yearold" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.email" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.education" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.costperoneworkout" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.blocked" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${trainers}" var="trainers">
            <tr>
                <td>
                    <div><input type="checkbox" name="select_trainer" value="${trainers.email}" id="id_trainer"/></div>
                </td>
                <td>${trainers.id}</td>
                <td>${trainers.name}</td>
                <td>${trainers.surname}</td>
                <td>${trainers.gender}</td>
                <td>${trainers.yearOld}</td>
                <td>${trainers.email}</td>
                <td>${trainers.blocked}</td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <input type="submit" value=
    <fmt:message key="bt.admin.block.trainer" bundle="${var}"/> name="button_admin_block_trainer">

    <input type="submit" value=
    <fmt:message key="bt.admin.unblock.trainer" bundle="${var}"/> name="button_admin_unblock_trainer">
    <br/><br/>
</form>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
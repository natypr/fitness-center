<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.admincabinet" bundle="${var}"/></title>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>
<h1><fmt:message key="text.admincabinet.cabinet" bundle="${var}"/></h1>
<br/>

<a href="${pageContext.request.contextPath}/jsp/admin/all_info.jsp">
    <fmt:message key="href.admincabinet.showallinfo" bundle="${var}"/></a>

<form name="adminCabinetClients" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="admin_block_user"/>

    <h3><fmt:message key="text.admincabinet.listofuser" bundle="${var}"/></h3>
    <table border="1" width="60%" cellpadding="5">
        <tr>
            <th><fmt:message key="text.admincabinet.select" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.id" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.role" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.name" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.surname" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.email" bundle="${var}"/></th>
            <th><fmt:message key="text.admincabinet.blocked" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${sessionScope.users}" var="user">
            <tr>
                <td><input type="checkbox" name="select_user" value="${user.id}" /></td>
                <td>${user.id}</td>
                <td>${user.role}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>${user.blocked}</td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <input type="submit" value=
    <fmt:message key="bt.admin.block.user" bundle="${var}"/> name="button_admin_block_user">

    <input type="submit" value=
    <fmt:message key="bt.admin.unblock.user" bundle="${var}"/> name="button_admin_unblock_user">
    <br/><br/>
</form>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
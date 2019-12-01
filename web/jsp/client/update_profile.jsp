<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.updateprofile" bundle="${var}"/></title>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>
<h1><fmt:message key="text.updateprofile.updateprofile" bundle="${var}"/></h1>
<br/>

<form name="clientUpdate" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="client_update"/>

    <div>
        <strong><fmt:message key="text.updateprofile.name" bundle="${var}"/></strong>
        <input type="text" name="name" value="" required pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/></br>

        <strong><fmt:message key="text.updateprofile.surname" bundle="${var}"/></strong>
        <input type="text" name="surname" value="" required pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/></br>

        <strong><fmt:message key="text.updateprofile.yearold" bundle="${var}"/></strong>
        <input type="number" value="0" name="year_old" required/></br>

        <strong><fmt:message key="text.updateprofile.password" bundle="${var}"/></strong>
        <input type="text" name="password" value="" required pattern="^([A-Za-z0-9_-]{8,})$"/></br></br></br>

        <br/>
        <input type="submit" value=
        <fmt:message key="bt.updateprofile" bundle="${var}"/> name="update_profile"/>
    </div>

    <br/> ${sessionScope.successfullyUpdated} <br/>

    <br/><br/><br/>
    <a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">
        <fmt:message key="href.clientcabinet" bundle="${var}"/></a>
</form>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
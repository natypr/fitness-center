<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.profile" bundle="${var}"/></title>
</head>
<body>
<c:import url="/jsp/util/header.jsp"/>
<h3><fmt:message key="text.profile.welcome" bundle="${var}"/></h3>
<br/>
<fmt:message key="text.profile.hellouser" bundle="${var}"/>
${user}
<br/><br/>

<div>
    <h1><fmt:message key="text.profile.myprofile" bundle="${var}"/></h1>

    <strong><fmt:message key="text.profile.name" bundle="${var}"/></strong>
    ${user.name} </br>

    <strong><fmt:message key="text.profile.surname" bundle="${var}"/></strong>
    ${user.surname} </br>

    <strong><fmt:message key="text.profile.gender" bundle="${var}"/></strong>
    ${user.gender} </br>

    <strong><fmt:message key="text.profile.yearold" bundle="${var}"/></strong>
    ${user.yearOld} </br>

    <strong><fmt:message key="text.profile.email" bundle="${var}"/></strong>
    ${user.email}

    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="logout"/><br/>
        <input type="submit" value="<fmt:message key = "bt.profile.logout" bundle="${var}"/>">
    </form>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
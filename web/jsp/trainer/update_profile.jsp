<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.updateprofile" bundle="${var}"/></title>
    <c:import url="/jsp/util/header.jsp"/>
</head>
<body>
<h1><fmt:message key="text.updateprofile.updateprofile" bundle="${var}"/></h1>
<br/>

<form name="trainerUpdate" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="trainer_update"/>

    <div>
        <strong><fmt:message key="text.updateprofile.name" bundle="${var}"/></strong>
        <input type="text" name="name" value=""/>

        <strong><fmt:message key="text.updateprofile.surname" bundle="${var}"/></strong>
        <input type="text" name="surname" value=""/>

        <strong><fmt:message key="text.updateprofile.gender" bundle="${var}"/></strong>
        <input type="text" name="gender" value=""/>

        <strong><fmt:message key="text.updateprofile.yearold" bundle="${var}"/></strong>
        <input type="text" name="year_old" value=""/>

        <strong><fmt:message key="text.updateprofile.password" bundle="${var}"/></strong>
        <input type="text" name="password" value=""/>

        <strong><fmt:message key="text.updateprofile.education" bundle="${var}"/></strong>
        <input type="text" name="education" value=""/>

        <strong><fmt:message key="text.updateprofile.costperoneworkout" bundle="${var}"/></strong>
        <input type="text" name="cost_per_one_workout" value=""/>

        <br/>
        <input type="submit" value=
        <fmt:message key="bt.updateprofile" bundle="${var}"/> name="update_profile"/>
    </div>

    <br/> ${successfullyUpdated} <br/>

    <br/><br/><br/>
    <a href="${pageContext.request.contextPath}/jsp/trainer/trainer_cabinet.jsp">
        <fmt:message key="href.trainercabinet" bundle="${var}"/></a>
</form>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
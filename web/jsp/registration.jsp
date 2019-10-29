<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="messages" var="var" />
<html>
<head>
    <title><fmt:message key="title.reg" bundle="${ var }" /></title>
</head>
<body>
<form name="signForm" method="POST" action = "">
    <h3><b><fmt:message key="text.reg.registration" bundle="${ var } "/></b></h3>
    <input type="hidden" name="command" value="sign_up"/>

    <fmt:message key="text.reg.firstname" bundle="${ var }" /><br/>
    <input type="text" name="first_name" value="" required pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/>

    <br/> <fmt:message key="text.reg.lastname" bundle="${ var }"/><br/>
    <input type="text" name="last_name" value="" required pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/>

    <br/><fmt:message key="text.reg.yearsold" bundle="${ var }"/><br/>
    <input type="text" name="years_old" value="" />

    <br/><fmt:message key="text.reg.sex" bundle="${ var }"/><br/>
    <input type="radio" name="radio-sex" value="M" checked>
        <fmt:message key="radio.reg.sexM" bundle="${ var }"/>
    <input type="radio" name="radio-sex" value="F">
        <fmt:message key="radio.reg.sexF" bundle="${ var }"/>

    <br/><fmt:message key="text.reg.email" bundle="${ var }"/><br/>
    <input type="text" name="email" value="" required pattern="^([a-z0-9_.-]+)@([a-z0-9_.-]+)\.([a-z]{2,5})$"/>

    <br/><fmt:message key="text.reg.password" bundle="${ var }"/><br/>
    <input type="password" name="password" value="" required pattern="^([A-Za-z0-9_-]{8,})$"/>

    <br/>
    <input type="radio" name="radio-role" value="Client" checked>
        <fmt:message key="radio.reg.client" bundle="${ var }"/>
    <input type="radio" name="radio-role" value="Trainer">
        <fmt:message key="radio.reg.trainer" bundle="${ var }"/>
    <input type="radio" name="radio-role" value="Administrator">
        <fmt:message key="radio.reg.administrator" bundle="${ var }"/>Administrator

    <br/>
    <input type="submit" value="<fmt:message key = "bt.reg.signup" bundle="${ var }"/>">
    <br/>
    ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
<c:import url="/jsp/util/footer.jsp" />
</body>
</html>

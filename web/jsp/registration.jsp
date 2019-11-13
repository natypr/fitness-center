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
    <fmt:message key="text.reg.registration" bundle="${ var }"/>
    <br/>
    <input type="hidden" name="command" value="registration"/>

    <input type="radio" name="radio-role" value="Client" checked>
    <fmt:message key="radio.reg.client" bundle="${ var }"/>
    <input type="radio" name="radio-role" value="Trainer">
    <fmt:message key="radio.reg.trainer" bundle="${ var }"/>
    <input type="radio" name="radio-role" value="Administrator">
    <fmt:message key="radio.reg.administrator" bundle="${ var }"/>
    <br/>

    <fmt:message key="text.reg.firstname" bundle="${ var }" /><br/>
    <input type="text" name="name" value="" required pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/>

    <br/> <fmt:message key="text.reg.lastname" bundle="${ var }"/><br/>
    <input type="text" name="surname" value="" required pattern="^([A-Z][a-z]{1,19})|([А-Я][а-я]{1,19})$"/>


    <br/><fmt:message key="text.reg.gender" bundle="${ var }"/><br/>
    <input type="radio" name="radio-gender" value="M" checked>
    <fmt:message key="radio.reg.genderM" bundle="${ var }"/>
    <input type="radio" name="radio-gender" value="F">
    <fmt:message key="radio.reg.genderF" bundle="${ var }"/>

    <br/><fmt:message key="text.reg.yearold" bundle="${ var }"/><br/>
    <input type="text" name="year_old" value="" />


    <br/><fmt:message key="text.reg.email" bundle="${ var }"/><br/>
    <input type="text" name="email" value="" required pattern="^([a-z0-9_.-]+)@([a-z0-9_.-]+)\.([a-z]{2,5})$"/>

    <br/><fmt:message key="text.reg.password" bundle="${ var }"/><br/>
    <input type="password" name="password" value="" required pattern="^([A-Za-z0-9_-]{8,})$"/>

    <br/><br/>
    <input type="submit" value="<fmt:message key = "bt.reg.signup" bundle="${ var }"/>">
    <br/>
    ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
<c:import url="/jsp/util/footer.jsp" />
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.login" bundle="${var}"/></title>
    <c:import url="/jsp/util/header.jsp"/>
</head>
<body>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login"/>

    <fmt:message key="text.login.login" bundle="${var}"/><br/>
    <input type="text" name="login" value=""/><br/>

    <fmt:message key = "text.login.password" bundle="${var}"/><br/>
    <input type="password" name="password" value=""/>

    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="<fmt:message key="bt.login.login" bundle="${var}"/>"/>

    </br>
    <a href="${pageContext.request.contextPath}/jsp/registration.jsp">
        <fmt:message key = "href.login.reg" bundle="${var}"/></a>
    </br>
    <a href="${pageContext.request.contextPath}/jsp/profile.jsp">
        <fmt:message key = "href.login.main" bundle="${var}"/></a>
</form>
<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
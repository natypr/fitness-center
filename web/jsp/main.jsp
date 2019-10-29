<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="messages" var="var" />
<html>
<head>
    <title><fmt:message key="title.main" bundle="${ var }" /></title>
</head>
<body>
    <h3><fmt:message key="text.main.welcome" bundle="${ var }" /></h3>
    <br/>
    <fmt:message key="text.main.hellouser" bundle="${ var }" />
    ${user}
    <br/><br/>
    <a href="controller?command=logout"><fmt:message key="text.main.logout" bundle="${ var }" /></a>
    <c:import url="/jsp/util/footer.jsp" />
</body>
</html>
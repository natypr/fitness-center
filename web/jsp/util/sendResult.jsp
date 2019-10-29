<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="messages" var="var" />
<html>
<head>
    <title><fmt:message key="title.sendresult" bundle="${ var }" /></title>
</head>
<body>
    <p><fmt:message key="text.sendresult.senging" bundle="${ var }" /></p>
    <a href="mail.jsp"><fmt:message key="text.sendresult.return" bundle="${ var }" /></a>
    <c:import url="/jsp/util/footer.jsp" />
</body>
</html>
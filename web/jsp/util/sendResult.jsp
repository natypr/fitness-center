<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
    <title><fmt:message key="title.sendresult" bundle="${var}"/></title>
    <c:import url="/jsp/util/header.jsp"/>
</head>
<body>
<div>
    <p><fmt:message key="text.sendresult.senging" bundle="${var}"/></p>
    <a href="mail.jsp"><fmt:message key="text.sendresult.return" bundle="${var}"/></a>
</div>

<c:import url="/jsp/util/footer.jsp"/>
</body>
</html>
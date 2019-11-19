<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<html>
<head>
</head>
<body>
<footer>
    <hr/>
    <hr/>
    <fmt:message key="footer.nameproject" bundle="${ var }" />
    <br>
    <fmt:message key="footer.copyright" bundle="${ var }" />
    <fmt:message key="footer.year" bundle="${ var }" />
    <fmt:message key="footer.authorname" bundle="${ var }" />
</footer>
</body>
</html>
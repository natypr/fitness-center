<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>

<footer class="footer_part">
<%--    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value="/css/footer.css"/>">--%>

    <div class="footer_icons">
        <a href="#" class="social-icon facebook"></a>
        <a href="#" class="social-icon twitter"></a>
        <a href="#" class="social-icon google+"></a>
        <a href="#" class="social-icon instagram"></a>
        <a href="#" class="social-icon youtube"></a>
        <a href="#" class="social-icon vk"></a>
        <a href="#" class="social-icon github"></a>
    </div>
    <div class="footer_text">
        <p><fmt:message key="footer.nameproject" bundle="${var}"/></p>
        <p><fmt:message key="footer.copyright" bundle="${var}"/></p>
    </div>
</footer>
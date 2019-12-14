<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>

<footer class="footer_part">
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

    <div class="footer_icons">
        <a href="#" class="social-icon facebook"></a>
        <a href="#" class="social-icon twitter"></a>
        <a href="#" class="social-icon google+"></a>
        <a href="#" class="social-icon instagram"></a>
        <a href="#" class="social-icon youtube"></a>
        <a href="#" class="social-icon vk"></a>
        <a href="#" class="social-icon github"></a>
    </div>--%>

    <div class="footer_text">
        <p class="text-center"><fmt:message key="footer.nameproject" bundle="${var}"/></p>
        <p class="text-center"><fmt:message key="footer.copyright" bundle="${var}"/></p>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</footer>
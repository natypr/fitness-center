<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<%@ taglib prefix="ctg" uri="customtags" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/main.jsp">
        <fmt:message key="text.header.fitnesscenter" bundle="${var}"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=show_trainer_list">
                    <fmt:message key="text.header.trainerlist" bundle="${var}"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/jsp/contacts.jsp"><fmt:message
                        key="text.header.contacts" bundle="${var}"/></a>
            </li>
            <li class="nav-item">
                <c:choose>
                    <c:when test="${sessionScope.user.role=='client'}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">
                            <fmt:message key="text.header.mycabinet" bundle="${var}"/></a>
                    </c:when>
                    <c:when test="${sessionScope.user.role=='trainer'}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/trainer/trainer_cabinet.jsp">
                            <fmt:message key="text.header.mycabinet" bundle="${var}"/></a>
                    </c:when>
                    <c:when test="${sessionScope.user.role=='admin'}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/admin/admin_cabinet.jsp">
                            <fmt:message key="text.header.mycabinet" bundle="${var}"/></a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/login.jsp">
                            <fmt:message key="text.header.login" bundle="${var}"/></a>
                    </c:otherwise>
                </c:choose>
            </li>


            <c:if test="${not empty sessionScope.user}">
                <%--                <ctg:role user="${sessionScope.user}"/>--%>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout">
                        <fmt:message key="text.header.logout" bundle="${var}"/></a>
                </li>
            </c:if>

            <form name="localeForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="page_path" value="${pageContext.request.requestURL}"/>
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="new_locale" value="ru_RU"/>
                <input type="image"
                       src="<fmt:message key="path.to.locale.picture.flag.ru" bundle="${var}"/>"
                       height="30" width="40"
                       alt="<fmt:message key="button.locale.language.ru" bundle="${var}"/>">
            </form>
            <form name="localeForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="page_path" value="${pageContext.request.requestURL}"/>
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="new_locale" value="en_US"/>
                <input type="image"
                       src="<fmt:message key="path.to.locale.picture.flag.us" bundle="${var}"/>"
                       height="30" width="40"
                       alt="<fmt:message key="button.locale.language.us" bundle="${var}"/>">
            </form>


            <li class="nav-item">
                <a class="nav-link" href="#"><ctg:info-time-tag/></a>
            </li>

        </ul>
    </div>
</nav>
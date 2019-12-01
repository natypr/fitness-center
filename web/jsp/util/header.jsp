<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<%@ taglib prefix="ctg" uri="customtags" %>

<nav>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/header.css"/>">
    <div class="body-bar">

            <ul class="menu-bar">
                <li class="button-bar"><a href="${pageContext.request.contextPath}/jsp/trainer_list.jsp">
                    <fmt:message key="text.header.trainerlist" bundle="${var}"/></a></li>

                <li class="button-bar"><a href="${pageContext.request.contextPath}/jsp/util/mail.jsp">
                    <fmt:message key="text.header.contacts" bundle="${var}"/></a></li>

                <li class="button-bar"><a href="${pageContext.request.contextPath}/jsp/util/about.jsp">
                    <fmt:message key="text.header.about" bundle="${var}"/></a></li>

                <li class="button-bar">
                    <c:choose>
                        <c:when test="${sessionScope.user.role=='client'}">
                            <a href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp">
                                <fmt:message key="text.header.mycabinet" bundle="${var}"/></a>
                        </c:when>
                        <c:when test="${sessionScope.user.role=='trainer'}">
                            <a href="${pageContext.request.contextPath}/jsp/trainer/trainer_cabinet.jsp">
                                <fmt:message key="text.header.mycabinet" bundle="${var}"/></a>
                        </c:when>
                        <c:when test="${sessionScope.user.role=='admin'}">
                            <a href="${pageContext.request.contextPath}/jsp/admin/admin_cabinet.jsp">
                                <fmt:message key="text.header.mycabinet" bundle="${var}"/></a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/jsp/login.jsp">
                                <fmt:message key="text.header.login" bundle="${var}"/></a>
                        </c:otherwise>
                    </c:choose>
                </li>


                <%--                <c:if test="${not empty sessionScope.user}">
                                    <ctg:role user="${sessionScope.user}"/>

                                    <form name="localeFormOut" method="POST" action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="command" id="logout"/>
                                        <li class="button-bar"><a href="${pageContext.request.contextPath}/controller?command=logout"></a>
                                            <fmt:message key="text.header.logout" bundle="${var}"/></li>
                                    </form>
                                </c:if>--%>

                <li class="button-bar">
                    <table>
                        <tr>
                            <form name="localeForm" method="POST"
                                  action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="page_path" value="${pageContext.request.requestURL}"/>
                                <input type="hidden" name="command" value="locale"/>
                                <input type="hidden" name="new_locale" value="ru_RU"/>
                                <td><input type="image"
                                           src="<fmt:message key="path.to.locale.picture.flag.ru" bundle="${var}"/>"
                                           height="30" width="40"
                                           alt="<fmt:message key="button.locale.language.ru" bundle="${var}"/>">
                                </td>
                            </form>
                            <form name="localeForm" method="POST"
                                  action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="page_path" value="${pageContext.request.requestURL}"/>
                                <input type="hidden" name="command" value="locale"/>
                                <input type="hidden" name="new_locale" value="en_US"/>
                                <td><input type="image"
                                           src="<fmt:message key="path.to.locale.picture.flag.us" bundle="${var}"/>"
                                           height="30" width="40"
                                           alt="<fmt:message key="button.locale.language.us" bundle="${var}"/>">
                                </td>
                            </form>
                        </tr>
                        <tr>
                            <td><ctg:info-time-tag/></td>
                        </tr>
                    </table>
                </li>
            </ul>

    </div>
</nav>
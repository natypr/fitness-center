<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="messages" var="var"/>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
</head>
<body>
<nav>
    <div>
        <table>
            <tr>
                <form name="localeForm" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="pagePath" value="${pageContext.request.requestURL}" />
                    <input type="hidden" name="command" value="locale"/>
                    <input type="hidden" name="newLocale" value="ru_RU"/>
                    <td><input type="image"
                               src="<fmt:message key="path.to.locale.picture.flag.ru" bundle="${var}"/>"
                               height="30" width="40"
                               alt="<fmt:message key="button.locale.language.ru" bundle="${var}"/>">
                    </td>
                </form>
                <form name="localeForm" method="POST" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="pagePath" value="${pageContext.request.requestURL}" />
                    <input type="hidden" name="command" value="locale"/>
                    <input type="hidden" name="newLocale" value="en_US"/>
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
    </div>
</nav>
</body>
</html>
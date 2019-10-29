<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="messages" var="var" />
<html>
<head>
    <title><fmt:message key="title.mail" bundle="${ var }" /></title>
</head>
<body>
<form action="MailServlet" method="POST">
    <table>
        <tr>
            <td><fmt:message key="text.mail.sendto" bundle="${ var }" /></td>
            <td><input type="text" name="to"/></td>
        </tr>
        <tr>
            <td><fmt:message key="text.mail.subject" bundle="${ var }" /></td>
            <td><input type="text" name="subject"/></td>
        </tr>
    </table>
    <br/>
    <textarea type="text" name="body" rows="5" cols="30">
        <fmt:message key="text.mail.messagetext" bundle="${ var }" />
    </textarea>
    <br/>
    <input type="submit" value="Send message!"/>
    <c:import url="/jsp/util/footer.jsp" />
</form>
</body>
</html>
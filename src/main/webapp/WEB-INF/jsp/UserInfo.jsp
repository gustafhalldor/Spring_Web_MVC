<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>User pages</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
    </head>
    <body>

    <%-- Gústi: This is a relic from the spring boot application. We would probably like to rename the page to "MyEvents"
        or something like that, so it is more like a MyProfile kind of deal. The endpoint /users doesn't even exist
        anymore as I deleted it! I'm just crazy like that. --%>

    <h1><a href="/users">User pages</a></h1>

    <sf:form method="POST" commandName="userName" action="/users">

        <table>
            <tr>
                <td>Name:</td>
                <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="name" type="text" placeholder="Enter name"/></td>
            </tr>
            <tr>
                <td>Info:</td>
                <td><sf:textarea path="note" type="text" placeholder="Some text here"/></td>
            </tr>
        </table>

        <input type="submit" VALUE="Post It!"/>

    </sf:form>


    <c:choose>
        <c:when test="${not empty userNames}">

        </c:when>

        <%--If all tests are false, then do this--%>
        <c:otherwise>
            <h3>No notes!</h3>
        </c:otherwise>
    </c:choose>

    <script src="<c:url value="/js/main.js" />"></script>
    </body>
</html>

<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="is" >

    <head>
        <title>Event info</title>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/postitnote.css"/>"/>
    </head>
    <body>

    <%-- Model = info --%>

    <h2>Event information</h2>

    <table>
        <tr>
            <td>Name:</td>
            <td>${info.name}</td>
        </tr>
        <tr>
            <td>Description:</td>
            <td>${info.description}</td>
        </tr>
        <tr>
            <td>Minimum age:</td>
            <td>${info.ageMin}</td>
        </tr>
        <tr>
            <td>Maximum age:</td>
            <td>${info.ageMax}</td>
        </tr>
        <tr>
            <td>Gender restrict event?</td>
            <td>${info.genderRestriction}</td>
        </tr>
    </table>

    <sf:form method="GET" action="/event">
        <input type="submit" VALUE="Create new event"/>
    </sf:form>

    <h2>Attendees:</h2>
    <%--Choose what code to generate based on tests that we implement--%>
    <c:choose>

        <%--If the model has an attribute with the name `listOfAttendees`--%>
        <c:when test="${not empty listOfAttendees}">
            <%--Create a table for the Postit Notes--%>
            <table>

                    <%--For each postit note, that is in the list that was passed in the model--%>
                    <%--generate a row in the table--%>
                    <%--Here we set `postit` as a singular item out of the list `postitNotes`--%>
                <c:forEach var="attendee" items="${listOfAttendees}">
                    <tr>
                            <%--We can reference attributes of the Entity by just entering the name we gave--%>
                            <%--it in the singular item var, and then just a dot followed by the attribute name--%>

                            <%--Create a link based on the name attribute value--%>
                        <td>${attendee.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>

        <%--If all tests are false, then do this--%>
        <c:otherwise>
            <h3>No one is going yet!</h3>
        </c:otherwise>
    </c:choose>

    </body>
</html>

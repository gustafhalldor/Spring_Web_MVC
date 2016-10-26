<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="is" >

    <head>
        <title>Create event</title>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/postitnote.css"/>"/>
    </head>
    <body>

    <%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
    <%--that is added to the model that is passed to the view.--%>
    <%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
    <sf:form method="POST" commandName="eventDetails" action="/eventinfo">

        <table>
            <tr>
                <td>Name:</td>
                <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
                <td><sf:input path="name" type="text" placeholder="Enter name of event"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><sf:textarea path="description" type="text" placeholder="Description of event"/></td>
            </tr>
            <tr>
                <td>Minimum age:</td>
                <td><sf:input path="ageMin" type="number" min="18" value="18"/></td>
            </tr>
            <tr>
                <td>Maximum age:</td>
                <td><sf:input path="ageMax" type="number" min="18" value="18"/></td>
            </tr>
            <tr>
                <td>Gender restrict event?</td>
                <td><form:checkbox path="genderRestriction"/>Yes</td>
            </tr>
            <tr>
                <%--hiding this input because we want to get the data through javascript--%>
                <td><sf:input path="lgt" type="hidden"/></td>
            </tr>
            <tr>
                <%--hiding this input because we want to get the data through javascript--%>
                <td><sf:input path="lat" type="hidden"/></td>
            </tr>

<%--            <tr>
                <td>Type of event:</td>
                <td><form:checkbox path="type" value="Fotbolti"/>Fotbolti</td>
                <td><form:checkbox path="type" value="Kubb"/>Kubb</td>
                <td><form:checkbox path="type" value="Bordspil"/>Bordspil</td>
            </tr>
            <tr>
                <td><sf:input path="creatorId" type="hidden"/></td>
            </tr>--%>
        </table>

        <input type="submit" VALUE="CREATE!"/>

    </sf:form>

    <%--** STUFF BELOW SHOULD BE IN viewEvent.jsp PROBABLY **
    <h2>Attendees:</h2>
    &lt;%&ndash;Choose what code to generate based on tests that we implement&ndash;%&gt;
    <c:choose>

        &lt;%&ndash;If the model has an attribute with the name `listOfAttendees`&ndash;%&gt;
        <c:when test="${not empty listOfAttendees}">
            &lt;%&ndash;Create a table for the Postit Notes&ndash;%&gt;
            <table class="notes">

                    &lt;%&ndash;For each postit note, that is in the list that was passed in the model&ndash;%&gt;
                    &lt;%&ndash;generate a row in the table&ndash;%&gt;
                    &lt;%&ndash;Here we set `postit` as a singular item out of the list `postitNotes`&ndash;%&gt;
                <c:forEach var="attendee" items="${listOfAttendees}">
                    <tr>
                            &lt;%&ndash;We can reference attributes of the Entity by just entering the name we gave&ndash;%&gt;
                            &lt;%&ndash;it in the singular item var, and then just a dot followed by the attribute name&ndash;%&gt;

                            &lt;%&ndash;Create a link based on the name attribute value&ndash;%&gt;
                        <td>${attendee.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>

        &lt;%&ndash;If all tests are false, then do this&ndash;%&gt;
        <c:otherwise>
            <h3>No one is going yet!</h3>
        </c:otherwise>
    </c:choose>--%>

    </body>
</html>

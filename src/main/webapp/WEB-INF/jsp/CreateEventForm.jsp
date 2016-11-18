<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
    <%--that is added to the model that is passed to the view.--%>
    <%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
    <sf:form method="POST" commandName="eventDetails" action="/saveEvent">

                <H3>Name:</H3>
                <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
                <p><sf:input path="name" type="text" placeholder="Enter name of event"/></p>

                <H3>Description:</H3>
                <p><sf:textarea path="description" type="text" placeholder="Description of event"/></p>

                <H3>Minimum age:</H3>
                <p><sf:input path="ageMin" type="number" min="18" value="18"/></p>

                <H3>Maximum age:</H3>
                <p><sf:input path="ageMax" type="number" min="18" value="18"/></p>

                <H3>Gender restrict event?</H3>
                <p><form:checkbox path="genderRestriction"/>Yes</p>

                <%--hiding this input because we want to get the data through javascript--%>
                <sf:input path="lat" type="float" class="lat"/>

                <%--hiding this input because we want to get the data through javascript--%>
                <sf:input path="lgt" type="float" class="lgt"/>

<%--            <tr>
                <td>Type of event:</td>
                <td><form:checkbox path="type" value="Fotbolti"/>Fotbolti</td>
                <td><form:checkbox path="type" value="Kubb"/>Kubb</td>
                <td><form:checkbox path="type" value="Bordspil"/>Bordspil</td>
            </tr>
            <tr>
                &lt;%&ndash; hiding this input because we want to get the data through javascript&ndash;%&gt;
                <td><sf:input path="location" type="hidden"/></td>
            </tr>
            <tr>
                <td><sf:input path="creatorId" type="hidden"/></td>
            </tr>--%>
        </table>
        <%--<div id="map"></div>--%>
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
    <%--</c:choose>&ndash;%&gt;--%>
      <%--<script src="https://code.jquery.com/jquery-3.1.1.js"   integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="   crossorigin="anonymous"></script>--%>

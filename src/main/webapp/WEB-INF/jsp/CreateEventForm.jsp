<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>

    </head>
    <body>
        <%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
        <%--that is added to the model that is passed to the view.--%>
        <%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
        <sf:form class="createEventForm" method="POST" commandName="eventDetails" action="/saveEvent">

            <h3>Name<span>*</span></h3>
            <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
            <sf:input path="name" type="text" cssClass="createEventForm_Name" placeholder="Enter name of event"/>

            <h3>Description<span>*</span></h3>
            <sf:textarea path="description" type="text" cssClass="createEventForm_Description" placeholder="Description of event"/>

            <h3>Start Date<span>*</span></h3>
            <sf:input path="startDate" type="text" class="datePicker" placeholder="Date"/>

            <h3>End Date<span>*</span></h3>
            <sf:input path="endDate" type="text" class="datePicker" placeholder="Date"/>

            <h3>Minimum age<span>*</span></h3>
            <sf:input path="ageMin" type="number" min="18" value="18"/>

            <h3>Maximum age<span>*</span></h3>
            <sf:input path="ageMax" type="number" min="18" value="18"/>

            <h3>Gender restrict event?(optional)</h3>
            <h3><sf:checkbox path="genderRestriction"/> Yes</h3>

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
            <%--<div id="map"></div>--%>
            <button type="submit" VALUE="Create"/>Create</button>

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
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script type="text/javascript" src="<c:url value="/js/createFormValidation.js" />"></script>

    </body>
</html>



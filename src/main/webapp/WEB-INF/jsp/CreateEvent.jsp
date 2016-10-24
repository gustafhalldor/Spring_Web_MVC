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

    <h1><a href="/event">Postit Notes</a></h1>

    <%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
    <%--that is added to the model that is passed to the view.--%>
    <%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
    <sf:form method="POST" commandName="eventInfo" action="/event">

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
                <td><form:checkbox path="genderRestriction" />Yes</td>
            </tr>
            <tr>
                <td>Type of event:</td>
                <td><form:checkbox path="type" value="Fotbolti"/>Fotbolti</td>
                <td><form:checkbox path="type" value="Kubb"/>Kubb</td>
                <td><form:checkbox path="type" value="Bordspil"/>Bordspil</td>
            </tr>
        </table>

        <input type="submit" VALUE="CREATE!"/>

    </sf:form>


    </body>
</html>

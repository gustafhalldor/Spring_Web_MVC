
<!DOCTYPE html>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>The Event Finder</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery-ui-timepicker-addon.css"/>"/>
    </head>
  <body>
  <div class="wrapper">
      <nav class="navigationBar">
          <div class="leftSide">
              <a class="btn" href="/user/1">Back</a>
              <a class="btn" href="/">Home Page</a>
          </div>
          <h3>The Event Finder</h3>
          <div class="rightSide">
              <img id="profilePic" src="">
              <span></span>
              <div class="btnContainer">
                  <img class="divImg" src="../images/fb_icon.png">
                  <button class="exbtn" type="button" id="logout">
                      <span>Logout</span>
                  </button>
              </div>
          </div>
      </nav>

      <div class="main">


            <div class="mapContainer">
                <div id="map"></div>
            </div>

            <div class="eventInfoSideBar">
                 <sf:form class="viewEventForm" commandName="info">
                <h3>Name:</h3>
                <p class="viewEventInfo_name">${info.name}</p>

                <h3>Description:</h3>
                <p class="viewEventInfo_description">${info.description}</p>

                <h3>Minimum age:</h3>
                <p class="viewEventInfo_ageMin">${info.ageMin}</p>

                <h3>Maximum age:</h3>
                <p class="viewEventInfo_ageMax">${info.ageMax}</p>

                <h3>Gender restrict event?</h3>
                <p class="viewEventInfo_genderRestriction">${info.genderRestriction}</p>

                <h3>Attendees</h3>
                <div id="attendees">
                    <c:choose>
                        <c:when test="${not empty attendeeNames}">
                            <c:forEach items="${attendeeNames}" var="attendee">
                             <%--<img src="http://graph.facebook.com/${attendeeFbId}/picture" alt="profile picture">--%>
                                <p>${attendee}</p>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h2>No one is going yet</h2>
                        </c:otherwise>
                    </c:choose>
                </div>
                </sf:form>
            </div>

          <script src="https://code.jquery.com/jquery-3.1.1.js"   integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="   crossorigin="anonymous"></script>
          <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
          <script src="<c:url value="/js/jquery-ui-timepicker-addon.js" />"></script>
          <script src="<c:url value="/js/main.js" />"></script>
            <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDw_tl_1b0d4i3KviPUzVHvM7sFbmAz-RE&callback=initFocusEventMap&libraries=places">
            </script>
        </div>
    </div>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script
          src="http://code.jquery.com/ui/1.12.0/jquery-ui.min.js"
          integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E="
          crossorigin="anonymous"></script>
    <script src="<c:url value="/js/jquery-ui-timepicker-addon.js" />"></script>
    <script src="<c:url value="/js/main.js" />"></script>

  </body>
</html>

<!DOCTYPE html>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>The Event Finder</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
    </head>
  <body>
  <div class="overlay"></div>
  <nav class="navigationBar">
      <button type="button" class="toggle_createEvent_sideBar_btn">Create Event</button>
      <h3>The Event Finder</h3>
      <div class="loginDiv">
          <button type="button" id="login"></button>
      </div>
      <button type="button" id="logout">logout</button>
  </nav>
  <!--<div class="fb-login-button" data-max-rows="1" data-size="medium" data-show-faces="false" data-auto-logout-link="true"></div>-->


  <div class="main">
        <div class="createEventSideBar"><jsp:include page="${request.contextPath}/event"></jsp:include></div>
        <div class="mapContainer">
            <input id="mapSearchBox" class="controls" type="text" placeholder="Search Box">
            <div id="map"></div>
        </div>
        <script   src="https://code.jquery.com/jquery-3.1.1.js"   integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="   crossorigin="anonymous"></script>
        <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDw_tl_1b0d4i3KviPUzVHvM7sFbmAz-RE&callback=initMap&libraries=places">
        </script>

        <!--<h3><a href="/event">Create event</a></h3>-->
    </div>
    <script src="<c:url value="/js/main.js" />"></script>
  </body>
</html>

<!DOCTYPE html>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>The Event Finder</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/postitnote.css"/>"/>
    </head>
  <body>
    <h3>The Event Finder</h3>
    <div id="map"></div>
    <script>
      function initMap() {
        var rvkLOC = {lat: 64.1265, lng: 21.044};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: rvkLOC
        });
        var marker = new google.maps.Marker({
          position: rvkLOC,
          map: map
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDw_tl_1b0d4i3KviPUzVHvM7sFbmAz-RE&callback=initMap">
    </script>
  </body>
</html>
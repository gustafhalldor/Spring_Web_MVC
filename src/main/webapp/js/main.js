//Index initMap
var sideBarOn = false;
var eventInfoSideBarOn = false;
function initMap() {

    if(!sideBarOn) {
        var currInfoWindow;
        var rvkLOC = {lat: 64.138705, lng: -21.955501};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: rvkLOC,
            mapTypeId: 'roadmap'

        });
        // Create the search box and link it to the UI element.
        var input = document.getElementById('mapSearchBox');
        //console.log(input);
        //var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
        console.log("villa?");
        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function () {
           // searchBox.setBounds(map.getBounds());
        });

        var markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
         /*
        searchBox.addListener('places_changed', function () {
            var places = searchBox.getPlaces();
            console.log(places);
            if (places.length == 0) {
                return;
            }

            // Clear out the old markers.
            markers.forEach(function (marker) {
                marker.setMap(null);
            });
            markers = [];
            console.log("erroR?");

            // For each place, get the icon, name and location.
            var bounds = new google.maps.LatLngBounds();
            places.forEach(function (place) {
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                var icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };

                // Create a marker for each place.
                markers.push(new google.maps.Marker({
                    map: map,
                    icon: icon,
                    title: place.name,
                    position: place.geometry.location
                }));

                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            });
            map.fitBounds(bounds);
        });
        */

        $.getJSON("/js/data.json", function (Events) {
            $.each(Events, function (key, data) {
                var location = {lat: data["lat"], lng: data["lgt"]};
                var marker = new google.maps.Marker({
                    position: location,
                    map: map
                });
                var imgSrc = "" + data["imgSrc"];
                var infowindow = new google.maps.InfoWindow({
                    content: '' + data["name"] + '<br>' + data["description"] + '<br>' +
                    '<br> <Button>Sign Up!</Button>'
                });
                marker.addListener('click', function () {
                    if (typeof(currInfoWindow) !== "undefined")currInfoWindow.close();
                    currInfoWindow = infowindow;

                    infowindow.open(map, marker);
                    eventInfoSideBar(data["name"], data["description"], data["ageMin"], data["ageMax"], data["genderRestriction"]);
                });
            });
        });
    }else{

        var currInfoWindow;
        var rvkLOC = {lat: 64.138705, lng: -21.955501};
        var radius;
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: rvkLOC,
            mapTypeId: 'roadmap'
        });

        //Add listener
        google.maps.event.addListener(map, "click", function (event) {
            var latitude = event.latLng.lat();
            var longitude = event.latLng.lng();
            fillLocationTextBox(latitude, longitude);
            if(radius) radius.setMap(null);
            radius = new google.maps.Circle({map: map,
                radius: 20,
                center: event.latLng,
                fillColor: '#777',
                fillOpacity: 0.1,
                strokeColor: '#AA0000',
                strokeOpacity: 0.8,
                strokeWeight: 4,
                draggable: true,    // Dragable
                editable: false      // Resizable
            });
            google.maps.event.addListener(radius, "mouseup", function(event){
                var latitude = event.latLng.lat();
                var longitude = event.latLng.lng();
                fillLocationTextBox(latitude, longitude);
            })

            // Center of map
            map.panTo(new google.maps.LatLng(latitude,longitude));
            function fillLocationTextBox(lat, lgt){
                var lgtBox = document.getElementsByClassName("lgt");
                var latBox = document.getElementsByClassName("lat");
                lgtBox[0].value = lgt;
                latBox[0].value = lat;
            }
        });
    }
}

function init() {
    $( '.createEventSideBar' ).hide();
    $( '.eventInfoSideBar' ).hide();
    $( '.toggle_createEvent_sideBar_btn' ).on('click', function(){
        toggleMap()
    });
}


function toggleMap() {
    $( '.eventInfoSideBar' ).hide(500);
    $( '.createEventSideBar' ).toggle(500);
    sideBarOn = !sideBarOn;
    initMap();
}

function eventInfoSideBar(name, description, minAge, maxAge, genRestriction) {
    fillEventInfo(name, description, minAge, maxAge, genRestriction)
    $( '.createEventSideBar' ).hide(500);
    $( '.eventInfoSideBar' ).show(500);
    sideBarOn = false;
    initMap();
}

function fillEventInfo(name, description, minAge, maxAge, genRestriction){

 $('.viewEventInfo_name').html(name);
 $('.viewEventInfo_description').html(description);
 $('.viewEventInfo_ageMin').html(minAge);
 $('.viewEventInfo_ageMax').html(maxAge);
 $('.viewEventInfo_genderRestriction').html(genRestriction);
}

function attendEvent(eventId, userId){
    $.ajax({
        'url': 'http://localhost:8080/attend',
        'type': 'POST',
        'contentType': 'application/json',
        'dateType': 'json',
        'data': {"userId": userId, "eventId": eventId},
        'success': function (data) {
            console.log('Attending Event!');
        }
    });
}


//Create event initMap()
function initPlaceMarkerMap(){
    var currInfoWindow;
    var rvkLOC = {lat: 64.138705, lng: -21.955501};
    var radius;
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 14,
      center: rvkLOC,
      mapTypeId: 'roadmap'
    });

    //Add listener
    google.maps.event.addListener(map, "click", function (event) {
    var latitude = event.latLng.lat();
    var longitude = event.latLng.lng();
    fillLocationTextBox(latitude, longitude);
    if(radius) radius.setMap(null);
    radius = new google.maps.Circle({map: map,
        radius: 20,
        center: event.latLng,
        fillColor: '#777',
        fillOpacity: 0.1,
        strokeColor: '#AA0000',
        strokeOpacity: 0.8,
        strokeWeight: 4,
        draggable: true,    // Dragable
        editable: false      // Resizable
    });
    google.maps.event.addListener(radius, "mouseup", function(event){
        var latitude = event.latLng.lat();
        var longitude = event.latLng.lng();
        fillLocationTextBox(latitude, longitude);
    })

    // Center of map
    map.panTo(new google.maps.LatLng(latitude,longitude));
    function fillLocationTextBox(lat, lgt){
        var lgtBox = document.getElementsByClassName("lgt");
        var latBox = document.getElementsByClassName("lat");
        lgtBox[0].value = lgt;
        latBox[0].value = lat;
    }
}); //end addListener

}

/*
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}
*/


window.fbAsyncInit = function() {
    FB.init({
        appId      : '549623305236918',
        auth       : true,
        status     : true,
        cookie     : true,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.8' // use graph api version 2.5
    });

    function userExists(userId) {
        if(false) {
            $.ajax({
                'url': 'http://localhost:8080/user/check',
                'type': 'GET',
                'contentType': 'application/json; charset=utf-8',
                'dateType': 'json',
                'data': {"fdId": userId},
                'success': function (data) {
                    console.log(data);
                    return true;
                }
            });
        }
        alert("nothing happens");
    }

    $('#login').click(function(event) {
        event.preventDefault();
        FB.login(function(response) {
            var getInfo = $('#test');
            if(response.authResponse) {
                //this response return expiresIn, userID, accessToken and signedRequest
                var accessToken = response.authResponse.accessToken;

                var userId = response.authResponse.userID;

                // TODO implement userExists(userID) function
                if(!userExists(userId)) {
                    // user doesn't exist so we need to create one in the database
                    FB.api('/me?fields=id,name,email,birthday,permissions', function(response) {

                        var name = response.name;
                        var email = response.email;

                        $.ajax({
                            'url': 'http://localhost:8080/user/create',
                            'type': 'POST',
                            'contentType': "application/json; charset=utf-8",
                            'dataType': 'json',
                            'data': JSON.stringify({
                                name: name,
                                email: email,
                                fbId: parseInt(userId)
                            }),
                            success: function(response)
                            {
                                console.log("IT WORKS!");
                                console.log(response);
                                alert(response);
                            },
                            error: function ()
                            {

                            }
                        });

                    });
                };
            } else {
                window.alert("failed");
            }
        }, {
            scope: 'email,user_birthday'
        });
    });

    $('#logout').click(function(event) {
        event.preventDefault();
        FB.logout(function(response) {
            if(response.status !== 'connected') {
                console.log('goodbye');
            }
            $('.overlay').show();
            $('.loginDiv').show();
            $('#logout').hide();
        });
    });
};

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

init();


function initMap() {
    var currInfoWindow;
    var rvkLOC = {lat: 64.138705, lng: -21.955501};
    //var imgSrc = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSb0Ry8iIoe-4I_uaA0seqyGsaOoT7RZzG7ESMbU5G5L0EPKebJvjesosw";

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 14,
      center: rvkLOC,
      mapTypeId: 'roadmap'

    });

    // Create the search box and link it to the UI element.
    var input = document.getElementById('mapSearchBox');
    console.log(input);
    var searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function() {
        searchBox.setBounds(map.getBounds());
    });

    var markers = [];
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function() {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear out the old markers.
        markers.forEach(function(marker) {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
        var bounds = new google.maps.LatLngBounds();
        places.forEach(function(place) {
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

   $.getJSON("/js/data.json", function(Events) {
     $.each(Events, function(key, data) {
        var location = {lat: data["lat"], lng:data["lgt"]};
        var marker = new google.maps.Marker({
           position: location,
           map: map
        });
        var imgSrc = ""+data["imgSrc"];
        var infowindow = new google.maps.InfoWindow({
          content: ''+data["eventName"]+'<br>' + data["eventDesc"] + '<br>' +
                   '<br> <Button>Sign Up!</Button>'
         });
         marker.addListener('click', function() {
               if(typeof(currInfoWindow) !== "undefined")currInfoWindow.close();
               currInfoWindow = infowindow;
               infowindow.open(map, marker);
         });
      });
   });


   /* var marker = new google.maps.Marker({
      position: rvkLOC,
      text: "Event",
      map: map
    });
    //The window that pops up when you click a marker.
    var infowindow = new google.maps.InfoWindow({
      content: 'Event hjá Vr2! <br> Klukkan: 16:00 <br><img src='+imgSrc+'>  <br> <button>Sign Up!</button>'
    });
     marker.addListener('click', function() {
      infowindow.open(map, marker);
    });*/
}

function statusChangeCallBack(response) {
    console.log('statusChangeCallBack');
    console.log(response);

    if(response.status === 'connected') {
        testAPI();
    } else if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.
        document.getElementById('status').innerHTML = 'Please log ' +
            'into this app.';
    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.
        document.getElementById('status').innerHTML = 'Please log ' +
            'into Facebook.';
    }
}

function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : '549623305236918',
        cookie     : true,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.5' // use graph api version 2.5
    });

    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });

};

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
        console.log('Successful login for: ' + response.name);
        document.getElementById('status').innerHTML =
            'Thanks for logging in, ' + response.name + '!';
    });
}

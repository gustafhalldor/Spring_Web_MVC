
function initMap() {
    var currInfoWindow;
    var rvkLOC = {lat: 64.138705, lng: -21.955501};
    //var imgSrc = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSb0Ry8iIoe-4I_uaA0seqyGsaOoT7RZzG7ESMbU5G5L0EPKebJvjesosw";


    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 14,
      center: rvkLOC
    });

   $.getJSON("/js/eventData.json", function(Events) {
     $.each(Events["Events"], function(key, data) {
        console.log(data["coordinates"][0])
        var location = {lat: data["coordinates"][1], lng:data["coordinates"][0]};
        var marker = new google.maps.Marker({
           position: location,
           map: map
        });
        var imgSrc = ""+data["imgSrc"];
        var infowindow = new google.maps.InfoWindow({
          content: ''+data["eventName"]+'<br>' + data["eventDesc"] + '<br>' +
                   '<img src='+imgSrc+'> <br> <Button>Sign Up!</Button>'
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

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('.map').each(function(item, Element) {
        var coords = $(Element).text().split(",");
        if (coords.length != 2) {
            $(this).display = "none";
            return;
        }
        var latlng = new google.maps.LatLng(parseFloat(coords[0]), parseFloat(coords[1]));
        var myOptions = {
            zoom: 10,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            disableDefaultUI: false,
            mapTypeControl: true,
            zoomControl: true,
            zoomControlOptions: {
                style: google.maps.ZoomControlStyle.SMALL
            }
        };
        var map = new google.maps.Map(Element, myOptions);

        var marker = new google.maps.Marker({
            position: latlng,
            map: map
        });
    });
});

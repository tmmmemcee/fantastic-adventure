<%-- 
    Document   : superheros
    Created on : Dec 10, 2019, 11:06:12 AM
    Author     : tmmmemcee
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <title>Super Heros & Super Villains</title>
    </head>
    <body>
        
        <div class="container">
            <h1>Super Heros & Super Villains Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/supers">Superheros</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/powers">Superpowers</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                </ul>    
            </div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <div class="row">
                
                <div class="col-md-8" id="sighting-div">
                    <h2>Super Heros & Super Villains Sightings</h2>
                    <c:forEach items="${sightings}" var="currentSighting" >
                        
                        <div class="container-fluid" style="text-align: center; min-height: 250px; border: solid;" >
                            <div class="row" >
                                <div class="col-xs-3">
                                    <h4>${currentSighting.superHero.superHeroName}</h4>
                                </div>
                                <div class="col-xs-6">
                                    <h4>${currentSighting.location.name}</h4>
                                </div>
                                <div class="col-xs-3">
                                    <h4>Seen on:</h4>
                                </div>
                                <div class="col-xs-3">
                                    <img src="${pageContext.request.contextPath}/${currentSighting.superHero.picture.filename}" alt="${currentSighting.superHero.picture.title}" class="img-responsive" />
                                </div>
                                <div class="col-xs-6 map" style="height:250px" id="map-${currentSighting.sightingId}" lat="${currentSighting.location.latitude}" lng="${currentSighting.location.longitude}">

                                </div>

                                <div class="col-xs-3" style="text-align:center">
                                    <h4>${currentSighting.date}</h4>
                                </div>
                            
                            </div>
                            <div class="row" style="margin:5px">
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                        Edit
                                    </a> | 
                                    <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                        Delete
                                    </a>
                                </sec:authorize>
                                    
                            </div>
                        </div>
            \        </c:forEach>
                </div> 
                <!-- End col div -->
                <div class="col-sm-4 add-div">
                    <h2>Add Sighting</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSighting">
                        <div class="form-group">
                            <div class="col-xs-6 col-sm-6">
                                <label for="add-super" style="display : block" >Super:</label>
                                <select name="super" style=" min-width: 40%">
                                    <c:forEach var="currentSuper" items="${superList}">
                                        <option value ="${currentSuper.superHeroId}">
                                            ${currentSuper.superHeroName}
                                        </option>
                                    </c:forEach>
                                </select>
                                    
                            </div>
                            <div class="col-xs-6 col-sm-6">
                                <label for="add-location" style="display: block">Location:</label>
                            
                                <select name="location" style="min-width: 40%">
                                    <c:forEach var="currentLocation" items="${locationList}">
                                        <option value="${currentLocation.locationId}"> ${currentLocation.name} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4">
                                <label style="display: block">Date:</label>
                            
                                <input name="date" type="date" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </form>

                </div> <!-- End col div -->
                
            </div> <!-- End row div -->
        </div>

        <script>
            var maps = [];
            var markers = [];
            function initMap() {
                var $maps = $('.map');
                $.each($maps, function (i, value) {
                    var uluru = { lat: parseFloat($(value).attr('lat')), lng: parseFloat($(value).attr('lng')) };

                    var mapDivId = $(value).attr('id');

                    maps[mapDivId] = new google.maps.Map(document.getElementById(mapDivId), {
                        zoom: 17,
                        center: uluru
                    });

                    markers[mapDivId] = new google.maps.Marker({
                        position: uluru,
                        map: maps[mapDivId]
                    });
                })
            }
        </script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPMJkTx6ILtgF6yv1brHyj3rRWuiVJDMI&callback=initMap">
        </script>                                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

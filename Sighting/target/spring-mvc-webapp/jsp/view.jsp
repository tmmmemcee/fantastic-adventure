<%-- 
    Document   : super
    Created on : Dec 11, 2019, 7:27:45 PM
    Author     : tmmmemcee
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <title>View </title>
    </head>
    <body>
        <div class="container">
            <h1>
                <c:out value="${hero}" />
                <c:out value="${organization}"/>
                <c:out value="${power}" />
                <c:out value="${location}"/>
            </h1>
            <hr />
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/supers">Superheros</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/organizations">Organizations</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/powers">Superpowers</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/locations">Locations</a>
                    </li>
                </ul>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <c:if test="${hero!=null}">
                        <img src="${pageContext.request.contextPath}/${hero.picture.filename}" alt="${hero.picture.title}" class="responsive" style="max-width: 400px;"/>
                    </c:if>
                    <c:if test="${sighting!=null}">
                        <img src="${pageContext.request.contextPath}/${sighting.superHero.picture.filename}" alt="${sighting.superHero.picture.title}" />
                    </c:if>
                    <c:if test="${location!=null}">
                        <div>
                            <div id="map" style="width:100%;height:400px"></div>
                        </div>
                    </c:if>
                    <c:if test="${power!=null}">
                        <h1>Affiliated Supers:</h1>
                        <table class='table table-hover'>
                            <tr>
                                <td>
                                    <ul>
                                    <c:forEach var="currentSuper" items="${superList}"  >
                                        <li><h3><c:out value="${currentSuper.superHeroName}"/></h3></li>
                                    </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                    <c:if test="${organization!=null}">
                        <h1>Affiliated Supers:</h1>
                        <table class='table table-hover'>
                            <tr>
                                <td>
                                    <ul>
                                    <c:forEach var="currentSuper" items="${superList}"  >
                                        <li><h3><c:out value="${currentSuper.superHeroName}"/></h3></li>
                                    </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </div>
                <div class="col col-sm-8">
                    <h1>
                        <c:out value="${organization.name}"/>
                        <c:out value="${hero.superHeroName}"/>
                        <c:out value="${power.powerName}"/>
                        <c:out value="${location.name}" />
                    </h1>
                    <table class='table table-hover' id='show-super'>
                        <c:if test="${hero!=null}">
                        <tr>
                            <td>
                                Super Powers:
                            </td>
                            <td>
                                <c:forEach items="${hero.superPowers}" var="superPower">
                                    ${superPower.powerName} 
                                </c:forEach>
                            </td>    
                        </tr>
                        <tr>
                            <td>
                                Organizations:
                            </td>
                            <td>
                                <c:forEach items="${hero.organizations}" var="organization">
                                    ${organization.name} 
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Hero or Villain:
                            </td>
                            <td>
                                <c:if test="${hero.usePowerForGood == true}">
                                    Hero
                                </c:if>
                                <c:if test="${hero.usePowerForGood == false}">
                                    Villain
                                </c:if>
                            </td>
                        </tr>
                        </c:if>
                        <c:if test="${organization!=null}">
                            <tr>
                                <td>
                                    Location:   
                                </td>
                                <td>
                                    <c:out value="${organization.location.name}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Phone:
                                </td>
                                <td>
                                    <c:out value="${organization.phone}" />
                                </td>
                            </tr>
                            
                        </c:if>
                        <c:if test="${location!=null}">
                        
                            <tr>
                                <td>
                                    Address:   
                                </td>
                                <td>
                                    <c:out value="${location.address}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Longitude:   
                                </td>
                                <td>
                                    <c:out value="${location.longitude}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Latitude:   
                                </td>
                                <td>
                                    <c:out value="${location.latitude}"/>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${sighting==null}">
                            <tr>
                            <td>
                                Description:
                            </td>
                            <td>
                                <c:out value="${hero.description}"/>
                                <c:out value="${power.description}"/>
                                <c:out value="${location.description}"/>
                                <c:out value="${organization.description}" />
                            </td>
                        </tr>
                        </c:if>
                        <c:if test="${sighting!=null}">
                            <tr>
                                <td>
                                    Super's Name: 
                                </td>
                                <td>
                                    <c:out value="${sighting.superHero.superHeroName}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Location: 
                                </td>
                                <td>
                                    <c:out value="${sighting.location.name}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Date: 
                                </td>
                                <td>
                                    <c:out value="${sighting.date}"/>
                                </td>
                            </tr>
                        <div>
                            <div id="map" style="width:100%;height:400px"></div>
                        </div>
                    
                        </c:if>
                        
                    </table>
                    
                    <button class='btn btn-default' 
                            onclick='window.location.href = "${pageContext.request.contextPath}/${hero}${power}${organization}${location}";'>
                        Back
                    </button>
                </div>
            </div>
            <script>
                function initMap() {
                    // The location of Uluru
                    var uluru = {lat: ${sighting.location.latitude} ${location.latitude}, lng: ${sighting.location.longitude} ${location.longitude}};
                    // The map, centered at Uluru
                    var map = new google.maps.Map(
                        document.getElementById('map'), {zoom: 10, center: uluru});
                    // The marker, positioned at Uluru
                    var marker = new google.maps.Marker({position: uluru, map: map});
                  }
            </script>
            <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPMJkTx6ILtgF6yv1brHyj3rRWuiVJDMI&callback=initMap">
            </script>
            
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>

<%-- 
    Document   : super
    Created on : Dec 11, 2019, 7:27:45 PM
    Author     : tmmmemcee
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
                <div class="col-md-6" id='edit-super-div'>
                    <h2>
                        Edit 
                        <c:out value="${hero}" />
                        <c:out value="${organization}"/>
                        <c:out value="${power}" />
                        <c:out value="${location}"/>
                        <c:out value="${sighting}"/>
                    </h2>
                    <c:if test="${sighting!=null}">
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="editSighting">
                            <div class="form-group">
                                <div class="col-xs-6 col-sm-offset-4 col-sm-4">
                                    <label for="add-super" style="display : block" >Super:</label>
                                    <select name="super" style=" min-width: 40%">
                                        <c:forEach var="currentSuper" items="${superList}">
                                            <c:if test="${currentSuper!=sighting.superHero}">
                                            <option value ="${currentSuper.superHeroId}">
                                                ${currentSuper.superHeroName}
                                            </option>
                                            </c:if>
                                            <c:if test="${currentSuper.superHeroId==sighting.superHero.superHeroId}">
                                            <option value ="${currentSuper.superHeroId}" selected>
                                                ${currentSuper.superHeroName}
                                            </option>    
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-xs-6 col-sm-4">
                                    <label for="add-location" style="display: block">Location:</label>
                                    <select name="location" style="min-width: 40%">
                                        <c:forEach var="currentLocation" items="${locationList}">
                                            <c:if test="${sighting.location==currentLocation}">
                                                <option value="${currentLocation.locationId}" selected> ${currentLocation.name} </option>
                                            </c:if>
                                            <c:if test="${sighting.location!=currentLocation}">
                                                <option value="${currentLocation.locationId}"> ${currentLocation.name} </option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col">
                                <label class="col-md-4 control-label">Date:</label>
                                <div class="col-md-8">
                                    <input name="date" type="date" required/>
                                    <input name="sightingId" value="${sighting.sightingId}" hidden/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Edit Super"/>
                                    <button type="button" class="btn btn-default" onclick='window.location.href = "${pageContext.request.contextPath}/index";'>
                                        Cancel
                                    </button>

                                </div>
                            </div>
                        </form>
                    </c:if>
                    <c:if test="${power!=null}">
                        <sf:form class="form-horizontal" 
                              role="form" method="POST" 
                              action="editPower" 
                              modelAttribute="power">
                            <div class="form-group">
                                <label for="edit-power-name" class="col-md-4 control-label">Super Power Name:</label>
                                <div class="col-md-8">
                                  <sf:input type="text" class="form-control" path="powerName" placeholder="${power.powerName}"/>
                                  <sf:hidden path="powerId" />
                                </div>
                            </div>
                                <div class="form-group">
                                <label for="edit-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <sf:textarea class="form-control" path="description" placeholder="${power.description}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Edit Power"/>
                                    <button type="button" class="btn btn-default" onclick='window.location.href = "${pageContext.request.contextPath}/powers";'>
                                        Cancel
                                    </button>
                                </div>
                            </div>
                        </sf:form>
                    </c:if>
                    <c:if test="${hero!=null}">
                        <form class="form-horizontal" 
                                role="form" method="POST" 
                                action="editSuper" 
                                enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="edit-name" class="col-sm-4 control-label">Name:</label>
                                <div class="col-md-8">
                                  <input type="text" class="form-control" name="superHeroName" value="${hero.superHeroName}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6 col-sm-offset-4 col-sm-4">
                                    <label for="edit-organization" style="display : block" >Organization:</label>
                                    <select name="organization" style=" min-width: 40%" size="4" multiple >
                                        <c:forEach var="currentOrganization" items="${organizationList}">
                                            <option value="${currentOrganization.organizationId}">
                                                ${currentOrganization.name}
                                            </option>
                                        </c:forEach>
                                            
                                    </select>

                                </div>
                                <div class="col-xs-6 col-sm-4">
                                    <label for="edit-power" style="display: block">Powers:</label>

                                    <select name="power" multiple style=" min-width: 40%">
                                        <c:forEach var="currentPower" items="${powerList}">
                                            <option value="${currentPower.powerId}"> ${currentPower.powerName} </option>
                                        </c:forEach>
                                        
                                    </select>
                                </div>
                            </div>
                            <div></div>
                            <div class="form-group col">
                                <label class="col-md-4 control-label">Hero or Villain</label>
                                <div class="col-md-8">
                                    <select name="usePowerForGood">
                                        <c:if test="${hero.usePowerForGood == true}">
                                            <option value="true" selected>Hero</option>
                                            <option value="false">Villain</option>
                                        </c:if>
                                        <c:if test="${hero.usePowerForGood == false}">
                                            <option value="true">Hero</option>
                                            <option value="false" selected>Villain</option>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" name="description">${hero.description}</textarea>
                                    <input name="superHeroId" value="${hero.superHeroId}" hidden />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="picture" class="col-md-4 control-label">Upload Picture of Super:</label> 
                                <div class="col-md-8">    
                                    <input type="file" 
                                           id="picture" 
                                           name="picture"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Edit Super"/>
                                    <button type="button" class="btn btn-default" onclick='window.location.href = "${pageContext.request.contextPath}/supers";'>
                                        Cancel
                                    </button>
                                </div>
                            </div>
                        </form>
                    </c:if>

                    <c:if test="${organization!=null}">
                        <form class="form-horizontal" 
                                    role="form" method="POST" 
                                    action="editOrganization">

                            <div class="form-group">
                                <label class="col-md-4 control-label">Organization Name:</label>
                                <div class="col-md-8">
                                    <input name="name" type="text" class="form-control" value="${organization.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" >Location:</label>
                                <div class="col-md-8">
                                    <select name="location">
                                        <option style="display: none" selected>Select Location</option>
                                        <c:forEach var="currentLocation" items="${locationList}">
                                            <c:if test="${currentLocation==organization.location}">
                                                <option value ="${currentLocation.locationId}" selected>${currentLocation.name}</option>    
                                            </c:if>
                                            <c:if test="${currentLocation!=organization.location}">
                                                <option value ="${currentLocation.locationId}">${currentLocation.name}</option>    
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4 control-label" >Phone Number:</label>
                                <div class="col-md-8">
                                    <input type="tel" class="form-control" name="phone" value="${organization.phone}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" name="description"> ${organization.description}</textarea>
                                    <input hidden value="${organization.organizationId}" name="organizationId">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Edit Organization"/>
                                    <button type="button" class="btn btn-default" onclick='window.location.href = "${pageContext.request.contextPath}/organizations";'>
                                        Cancel
                                    </button>
                                </div>
                            </div>                                
                        </form>
                    </c:if>
                    <c:if test="${location!=null}">
                        <sf:form class="form-horizontal" 
                              role="form" method="POST" 
                              action="editLocation" modelAttribute="location">
                            <div class="form-group">
                                <label for="edit-location-name" class="col-md-4 control-label">Location Name:</label>
                                <div class="col-md-8">
                                  <sf:input type="text" class="form-control" path="name" value="${location.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-address" class="col-md-4 control-label">Address:</label>
                                <div class="col-md-8">
                                    <sf:input type="text" class="form-control" path="address" value="${location.address}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-latitude" class="col-md-4 control-label">Latitude:</label>
                                <div class="col-md-8">
                                    <sf:input type='number' step="0.0000001" min="-90" max="90" class="form-control" 
                                              path="latitude" value="${location.latitude}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-longitude" class="col-md-4 control-label">Longitude:</label>
                                <div class="col-md-8">
                                    <sf:input type="number" step="0.0000001" min="-180" max="180" class="form-control" 
                                              path="longitude" value="${location.longitude}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <sf:textarea class="form-control" path="description" value="${location.description}" />
                                    <sf:hidden path="locationId" value="${location.locationId}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="button" class="btn btn-default" onclick='window.location.href = "${pageContext.request.contextPath}/locations";'>
                                        Cancel
                                    </button>
                                    <input type="submit" class="btn btn-default" value="Edit Location"/>
                                </div>

                            </div>
                        </sf:form>
                    </c:if>
                </div> <!-- End col div -->


                </div> <!-- End col div -->

            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>

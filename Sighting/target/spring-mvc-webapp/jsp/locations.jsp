<%-- 
    Document   : locations
    Created on : Dec 10, 2019, 11:06:50 AM
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
        <title>Location Page</title>
    </head>
    <body>
        <div class="container">
            <h1>SuperHeros/Villian Sightings</h1>
            <hr/>
            
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/supers">Superheros</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/powers">Superpowers</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                </ul>    
            </div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            
            <!-- 
                Add a row to our container - this will hold the summary table and the new
                contact form.
            -->
            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Locations</h2>
                    <table id="locationTable" class="table table-hover">
                        <tr>
                            <th width="40%">Name</th>
                            <th width="30%">Address</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentLocation" items="${locationList}">
                            <tr>
                                <td>
                                    <a href="location/${currentLocation.locationId}">
                                      <c:out value="${currentLocation.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.address}"/>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="displayEditLocationForm?locationId=${currentLocation.locationId}">
                                        Edit
                                        </a>
                                    </sec:authorize>
                                        
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteLocation?locationId=${currentLocation.locationId}">
                                            Delete
                                        </a>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the new contact form - have it take up the other 
                    half of the row
                -->
                <div class="col-md-6 add-div">
                    <h2>Add New Location</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createLocation">
                        <div class="form-group">
                            <label for="add-location-name" class="col-md-4 control-label">Location Name:</label>
                            <div class="col-md-8">
                              <input type="text" class="form-control" name="locationName" placeholder="Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-address" class="col-md-4 control-label">Address:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="address" placeholder="Address" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                            <div class="col-md-8">
                                <input type='number' step="0.0000001" min="-90" max="90" class="form-control" name="latitude" placeholder="Latitude"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                            <div class="col-md-8">
                                <input type="number" step="0.0000001" min="-180" max="180" class="form-control" name="longitude" placeholder="Longitude"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <textarea class="form-control" name="description" placeholder="Description of location"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Location"/>
                            </div>
                        </div>
                    </form>

                </div> <!-- End col div -->
                
            </div> <!-- End row div -->
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>

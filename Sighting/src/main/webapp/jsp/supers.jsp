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
            <h1>Super Heros/Villains </h1>
            <hr/>
            
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/supers">Superheros</a></li>
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
            <h2>${errorMsg}</h2>                
            <div class="row">
                
                <div class='col-md-8' id='supers-div' >
                    <c:forEach var="currentSuper" items="${superList}">
                        <div class="col-sm-5 display-box" style="min-height: 250px; text-align: center; border: solid; margin: 5px;" >
                            <h4>
                                <a href="${pageContext.request.contextPath}/super/${currentSuper.superHeroId}">
                                <c:out value="${currentSuper.superHeroName}"/>
                                </a>    
                            </h4>
                                
                            <div class="col-xs-5" style="padding: 0px 5px">
                                <img src="${pageContext.request.contextPath}/${currentSuper.picture.filename}"
                                      class="img-thumbnail" alt="${currentSuper.superHeroName}" />
                            </div>
                            <div class="col-xs-7" style="text-align: left; padding: 0px 5px">
                                <div style="display: block">
                                    <c:choose>
                                        <c:when test="${currentSuper.usePowerForGood==true}">
                                            Hero
                                        </c:when>
                                        <c:otherwise>Villain</c:otherwise>
                                    </c:choose>
                                </div>
                                <div style="display: block">
                                    Known Organizations:
                                    <ul>
                                        <c:forEach items ="${currentSuper.organizations}" var="currentOrganization">
                                            <a href="organization/${currentOrganization.organizationId}"><li>
                                                <c:out value="${currentOrganization.name}" /></li>
                                            </a>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div style="display: block">
                                    Known Super Powers:
                                    <ul>
                                        <c:forEach var="currentPower" items="${currentSuper.superPowers}">
                                            <li> <c:out value="${currentPower.powerName}"/> </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="displayEditSuperForm?superId=${currentSuper.superHeroId}">
                                    Edit
                                </a> | 
                                <a href="deleteSuper?superId=${currentSuper.superHeroId}">
                                    Delete
                                </a>
                            </sec:authorize>
                        </div>
                    </c:forEach>
                </div>
                <!-- 
                    Add col to hold the new contact form - have it take up the other 
                    half of the row
                -->
                <div class="col-sm-8 col-md-4 add-div">
                    <h2>Add New Super Hero/Villain</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSuper" 
                          enctype="multipart/form-data">
                        <div class="form-group" >
                            <div class="col-sm-12">
                                <label for="superHeroName" style="display: block"> Name:</label>
                                <input type="text" class="form-control" name="superHeroName" id="superHeroName" placeholder="Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            
                            <div class="col-sm-6">
                                <label for="add-organization" style="display : block" >Organization:</label>
                                <select name="organization" style=" min-width: 40%" size="4" multiple >
                                    
                                    <c:forEach var="currentOrganization" items="${organizationList}">
                                        <option value ="${currentOrganization.organizationId}">
                                            ${currentOrganization.name}
                                        </option>
                                    </c:forEach>
                                </select>
                                    
                            </div>
                            <div class="col-sm-6">
                                <label for="add-power" style="display: block">Powers:</label>
                            
                                <select name="power" multiple style=" min-width: 40%">
                                    <c:forEach var="currentPower" items="${powerList}">
                                        <option value="${currentPower.powerId}"> ${currentPower.powerName} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col">
                            <div class="col-md-8">
                                 <label style="display: block">Hero or Villain</label>
                            
                                <select name="usePowerForGood">
                                    <option style="display: none" selected>Select Hero/Villain</option>
                                    <option value="true">Hero</option>
                                    <option value="false">Villain</option>
                                </select>
                            </div>
                            
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">    
                            <label for="add-description" style="display: block">Description:</label>
                                <textarea class="form-control" name="description" placeholder="Description of Super Hero/Villian"></textarea>
                                
                            </div>
                        </div>      
                        <div class="form-group">
                            <div class="col-md-8">    
                                <label for="picture">Upload Picture of Super:</label> 
                                <input type="file" 
                                       id="picture" 
                                       name="picture"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Super"/>
                            </div>
                        </div>
                    </form>

                </div> <!-- End col div -->
            </div> <!-- End row div -->
        </div>
                                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

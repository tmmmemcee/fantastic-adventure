<%-- 
    Document   : powers
    Created on : Dec 10, 2019, 11:06:38 AM
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
        <title>Super Powers</title>
    </head>
    <body>
        
        <div class="container">
            <h1>Super Powers</h1>
            <hr/>
            
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/supers">Superheros</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/powers">Superpowers</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                </ul>    
            </div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <!-- 
                Row container - this will hold the summary table and the new
                super form.
            -->
            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Super Powers</h2>
                    <table id="powerTable" class="table table-hover">
                        <tr>
                            <th width="40%">Name</th>
                            <th width="30%"></th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentPower" items="${powerList}">
                            <tr>
                                <td>
                                    <a href="power/${currentPower.powerId}">
                                    <c:out value="${currentPower.powerName}"/>
                                    </a>
                                </td>
                                <td>
                                    
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="displayEditPowerForm?powerId=${currentPower.powerId}">
                                        Edit
                                        </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deletePower?powerId=${currentPower.powerId}">
                                            Delete
                                        </a>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 
                <div class="col-md-6 add-div">
>
                    <h2>Add Super Power</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createPower">
                        <div class="form-group">
                            <label for="add-power-name" class="col-md-4 control-label">Super Power Name:</label>
                            <div class="col-md-8">
                              <input type="text" class="form-control" name="superPowerName" placeholder="Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <textarea class="form-control" name="description" placeholder="Description of Super Power"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Power"/>
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

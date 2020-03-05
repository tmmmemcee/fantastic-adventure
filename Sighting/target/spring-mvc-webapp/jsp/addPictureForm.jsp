<%-- 
    Document   : superheros
    Created on : Dec 10, 2019, 11:06:12 AM
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
        <title>Super Heros & Super Villains</title>
    </head>
    <body>
        
        <div class="container">
            <h1>Add Photo for Super Heros & Super Villains</h1>
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
            <h2>${errorMsg}</h2>                
            <div class="row">
                
                <div class='col-sm-8 col-sm-offset-2' id='supers-div' >
                 
                    <h2>Home Page</h2>
                    <h2>${errorMsg}</h2>
                    <p>Please upload an image:</p>
                    <form role="form" method="POST" 
                          action="addPicture" 
                          enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="displayTitle">Display Title:</label>
                            <input type="text" 
                                   id="displayTitle" 
                                   name="displayTitle"/>
                        </div>
                        <div class="form-group">
                            <label for="picture">Upload File:</label> 
                            <input type="file" 
                                   id="picture" 
                                   name="picture"/>
                        </div>
                        <input type="submit" value="Upload Picture"/>
                    </form>
                </div> <!-- End col div -->
                
            </div> <!-- End row div -->
        </div>
                                        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

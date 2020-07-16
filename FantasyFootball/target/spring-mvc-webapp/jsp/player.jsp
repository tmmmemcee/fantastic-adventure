<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Players</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Spring MVC Application from Archetype</h1>
            <hr/>
            <div class="navbar">
                 <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/players">Players</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/seasons">Seasons</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/fantasyRank">Fantasy</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <h2>${player.name}</h2>
                    <table id="playerStats" class="table table-hover">
                        <tr>
                            <th>
                                Summary
                            </th>
                            <th>
                                Yds
                            </th>
                            <th>
                                Tds
                            </th>
                            <th>
                                Int
                            </th>
                        </tr>
                        <c:forEach var="currentSeason" items="${player.stats}">
                            <tr>
                                <td>
                                    <c:out value="${currentSeason.year}"/>             
                                </td>
                                <td>
                                    <c:out value="${currentSeason.passYards}" />
                                </td>
                                <td>
                                    <c:out value="${currentSeason.passTds}" />
                                </td>
                                <td>
                                    <c:out value="${currentSeason.inters}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 
            </div> <!-- End row div -->
        </div>
            
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


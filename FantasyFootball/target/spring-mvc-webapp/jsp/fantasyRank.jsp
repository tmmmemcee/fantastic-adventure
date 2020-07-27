<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello Controller Page</title>
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
            <h2>Fantasy Rank</h2>
        </div>
        <div class="row">
            <div class="col-md-6">
                <c:forEach var="currentScore" items="${scoring}">
                    <h2>
                        <a href="${pageContext.request.contextPath}/${currentScore.scoreId}">
                            <c:out value="${currentScore.scoreId}"></c:out>
                        </a>
                    </h2>
                </c:forEach>
                <table id="powerTable" class="table table-hover">
                    <tr>
                        <th width="30%">Name</th>
                        <th width="4%">Pos</th>
                        <th width="4%">Points</th>
                        <th width="4%">Ps Yds</th>
                        <th width="4%">TDs</th>
                        <th width="4%">Ints</th>
                        <th width="4%">Rs Yds</th>
                        <th width="4%">Tds</th>
                        <th width="4%">Fmbl</th>
                        <th width="4%">Rc Yds</th>
                        <th width="4%">Rec</th>
                        <th width="4%">Tds</th>
                        <th width="4%">Rank</th>
                        <th width="4%">TFR</th>
                    </tr>
                    <c:forEach var="currentPlayer" items="${playerList}">
                        <tr>
                            <td>
                                <!--<a href="power/${currentPlayer.playerId}">-->
                                <c:out value="${currentPlayer.name}"/>
                                <!--</a>-->
                            </td>
                            <td>
                                <c:out value="${currentPlayer.positionId}" />
                            </td>
                            <c:forEach items="${currentPlayer.stats}" var="season">
                                <c:choose >
                                    <c:when test="${season.year >= 2019}">
                                        <td>
                                            ${season.fantasyTotal}
                                        </td>
                                        <td>
                                            ${season.passYards}
                                        </td>
                                        <td>
                                            ${season.passTds}
                                        </td>
                                        <td>
                                            ${season.inters}
                                        </td>
                                        <td>
                                            ${season.rushYards}
                                        </td>
                                        <td>
                                            ${season.rushTds}
                                        </td>
                                        <td>
                                            ${season.fumbles}
                                        </td>
                                        <td>
                                            ${season.recYards}
                                        </td>
                                        <td>
                                            ${season.receptions}
                                        </td>
                                        <td>
                                            ${season.recTds}
                                        </td>
                                        <td>

                                        </td>
                                        <td>

                                        </td>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <td>
                                
                            </td>
                        </tr>
                    </c:forEach>
                </table>                    
            </div> 
            <div class="col-md-6 add-div">
                <h2>Fantasy League scoring</h2>
                <c:forEach items="${scoring}" var="score">
                    <a href="displayFantasyScoring?scoreId=${score.scoreId}">${score.scoreId}</a>
                    <br>
                    <hr>
                </c:forEach>
                           
                <form class="form-horizontal" 
                      role="form" method="POST" 
                      action="createScoringMatrix">
                    <div class="form-group">
                        <label for="NumberOfTeams" class="col-md-4 control-label">Number Of Teams:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="NumberOfTeams" min="0" max="16" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="NumberOfQB" class="col-md-4 control-label">Number Of QBs:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="NumberOfQB" min="0" max="3" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="NumberOfRB" class="col-md-4 control-label">Number Of RBs:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="NumberOfRBs" min="0" max="3" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="NumberOfWR" class="col-md-4 control-label">Number Of WRs:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="NumberOfWRs" min="0" max="5" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="NumberOfTE" class="col-md-4 control-label">Number Of TEs:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="NumberOfTEs" min="0" max="3" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="NumberOfFlexRWT" class="col-md-4 control-label">Number Of Flex (RWT):</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="NumberOfFlexRWT" min="0" max="3" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="NumberOfFlexRW" class="col-md-4 control-label">Number Of Flex (RW):</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="NumberOfFlexRW" min="0" max="3" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="NumberOfFlexWT" class="col-md-4 control-label">Number Of Flex (WT):</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="NumberOfFlexWT" min="0" max="3" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="PassingYardPerPoint" class="col-md-4 control-label">PassYard/Point:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="PassingYardPerPoint" min="1" max="100" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="PointPerPassingTD" class="col-md-4 control-label">Point/PassingTD:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="PointPerPassingTD" min="0" max="100" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="PointPerInterception" class="col-md-4 control-label">Point/Interception:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="PointPerInterception" min="-10" max="10" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="RushingYardPerPoint" class="col-md-4 control-label">RushYard/Point:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="RushingYardPerPoint" min="1" max="100" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="PointPerRushingTD" class="col-md-4 control-label">Point/RushingTD:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="PointPerRushingTD" min="0" max="100" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="PointPerFumble" class="col-md-4 control-label">Point/Fumble:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="PointPerFumble" min="-10" max="10" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ReceivingYardPerPoint" class="col-md-4 control-label">ReceivingYard/Point:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="ReceivingYardPerPoint" min="1" max="100" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="PointPerReceivingTD" class="col-md-4 control-label">Point/ReceivingTD:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="PointPerReceivingTD" min="0" max="100" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="PointPerReception" class="col-md-4 control-label">Point/Rec:</label>
                        <div class="col-md-8">
                          <input type="number" class="form-control" name="PointPerReception" min="0" max="10" required/>
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
                            <input type="submit" class="btn btn-default" value="Create Fantasy Scoring"/>
                        </div>
                    </div>
                </form>

            </div> <!-- End col div -->
        </div> <!-- End row div -->
        
                
                
                
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>


<%
    String path = request.getContextPath();
    String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>

 
        <script src="js/d3/d3.v3_4_2.min.js" type="text/javascript"></script>
        <link href="css/arttimer.css" rel="stylesheet" type="text/css"/>
        <script src="js/arttimer/imageCollection.js" type="text/javascript"></script>
        <script src="js/arttimer/timer.js" type="text/javascript"></script>
        <script src="js/arttimer/timer_setup.js" type="text/javascript"></script>

        <script>
            $(document).ready(function () {
                timer_init();
                // http://bl.ocks.org/tomgp/6475678<br>
                // http://bl.ocks.org/mbostock/1098617


            });
        </script>
     

        <div class="row span12">

            
            <div  class="form-inline">




                <label for="imageSelect">Image</label> 
                <select class="input-medium" onchange="goToImage(this)" id="imageSelector">

                </select>



                <label for="maxTimeSelect">Delay</label> <select  class="input-small"  
                    onchange="goToTime(this)" id="maxTimeSelect">
                    <option value="0">1 min</option>
                    <option value="1">2 min</option>
                    <option value="2">5 min</option>


                </select> 

                <label >&nbsp;</label>
                <button id="timerButton" class="btn btn-primary" 
                        onclick="toggleTimer(this)">Start Timer</button> 

            </div>
        </div>
        <!--  end control row -->

        <div class="row span12">
            <div class="span5">
                <div id="imageCollection">
                    <img src="simages/a1.jpg" alt=""/>
                    <img src="simages/a2.jpg" alt=""/>
                    <img src="simages/a3.jpg" alt=""/>
                    <img src="simages/a4.jpg" alt=""/>
                    <img src="simages/a5.jpg" alt=""/>
                    <img src="simages/a6.jpg" alt=""/>
                </div>
            </div>
            <div class="span4">
                <div  id="clockTimer"></div>
            </div>
        </div>




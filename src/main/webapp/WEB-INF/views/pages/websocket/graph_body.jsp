<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="baseURL" value="/app/" />




<div class="row">
    <div style="height: 350px" class="well offset1 col-lg-7" id="randomDataChart"></div>
</div>
<script>
    var baseURL = '${baseURL}';
</script>
 
<script type="text/javascript"  src="js/websocket/stomp.js"></script>
<script type="text/javascript"  src="js/websocket/sockjs-0.3.4.min.js"></script>
<script src="js/websocket/highcharts.js" type="text/javascript"></script>
<script src="js/websocket/graph_update.js" type="text/javascript"></script>
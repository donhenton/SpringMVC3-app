<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="baseURL" value="/app/" />



<div style="margin: 10px" class="col-lg-10">




    <div class="row well well-sm">
        <div>
            <button style="margin-left: 20px" class="btn btn-primary" onclick="doSendAppCode()">Send To App Code</button>
            <span style="margin-left: 10px" class="col2">Bonzo</span> 
            <span style="margin-left: 10px" id="renderBonzo" class="col5"></span>
            <span style="margin-left: 10px" class="col2">Frodo</span> 
            <span style="margin-left: 10px" id="renderFrodo" class="col5"></span>
        </div>
    </div>


    <div class="row well well-sm">

        <div>
            <button  style="margin-left: 20px" class="btn btn-primary" onclick="doSendUser()">Send To User Code</button>
            <span style="margin-left: 10px" class="col2">User</span> 
            <span style="margin-left: 10px" id="renderUser" class="col5"></span>
        </div>

    </div>

   

       

  

    <script>
        var baseURL = '${baseURL}';
    </script>
    <script type="text/javascript"  src="js/websocket/stomp.js"></script>
    <script type="text/javascript"  src="js/websocket/sockjs-0.3.4.min.js"></script>
    <script src="js/websocket/chat.js" type="text/javascript"></script>
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

    <div class="row well well-sm">

        <p>This code demonstrates public and private (user) channels.</p>
        <h3> Send to App Code </h3>
        <p>This code talks on public channels that are visible to all users. With two browsers open, users
            will see updates any time the button is pressed.</p>

        <h3> Send to User Code </h3>
        <p>This code talks on a private channel that is  visible only to the current user. With two browsers open, users
            will <em>ONLY</em> see their own updates any time the button is pressed.</p>

        <p>The controller code is in ChatController. The private channel works by getting user information from the
            HTTP session, which is normally filled out by Spring authentication in the webapp. In this case, a user
            name is created for the anonymous user for his session to allow for identification. This is done in
            the class TestHandshakeHandler. See 
            <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html">
                http://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html
            </a>. Search for <span class="badge">determineUser</span>

    </div>

    <script>
        var baseURL = '${baseURL}';
    </script>
    <script type="text/javascript"  src="js/websocket/stomp.js"></script>
    <script type="text/javascript"  src="js/websocket/sockjs-0.3.4.min.js"></script>
    <script src="js/websocket/chat.js" type="text/javascript"></script>
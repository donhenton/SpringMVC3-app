<div>
    This code demonstrates features that would be useful in a websocket based chat
</div>

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
    </a>. Search for <span class="label"> determineUser</span>




<div>
    The corresponding java code that handles these actions can be found in
    <code>com.dhenton9000.spring.mvc.controllers.websocket ChatController</code>
    This is using the Spring in memory websocket stomp broker in the spring-servlet.xml file.
</div> 

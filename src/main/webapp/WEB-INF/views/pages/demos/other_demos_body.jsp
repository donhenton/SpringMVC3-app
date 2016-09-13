<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="baseURL" value="/app/" />
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/app" ;
    String fullPath = basePath+"/ajax/getFile";
    
 



%>

<div class="columnLeft">
    <h2>ANGULAR</h2>
    <blockquote>
         <h3>MOUNTEBANK-UI</h3>
         <div>
                <blockquote>
                    <ul>
                        <li><b>Description: </b>UI for the Mountebank Imposter Server</li>
                        <li><a href="https://github.com/donhenton/mountebank-UI">GitHub Source</a></li>
                        <li><a href="http://donhenton.github.io/mountebank-UI/public_html/index.html">Demonstration Site</a></li>
                        <li><a href="https://www.youtube.com/watch?v=69usGV3uScI&feature=youtu.be">You Tube Presentation</a></li>
                    </ul>
                </blockquote>

         <h3>MORGFILE</h3>
                <blockquote>
                    <ul>
                        <li><b>Description: </b>Image Tracking Application</li>
                        <li><a href="https://github.com/donhenton/MorgFile">GitHub Source</a></li>
                        <li><a href="https://donhenton.github.io/MorgFile/public_html/index.html">Demonstration Site</a></li>
                    </ul>
                </blockquote>




         <h3>ERROR INDICATORS</h3>
            <blockquote>
                    <ul>
                        <li><b>Description: </b>Directives and Animations</li>
                        <li><a href="https://github.com/donhenton/angular-sandbox">GitHub Source</a> <em>(wait-demo branch)</em></li>
                        <li><a href="https://donhenton.github.io/angular-sandbox/public_html/index.html">Demonstration Site</a></li>
                    </ul>
            </blockquote>

    

    </blockquote>
</div>
<div class="columnRight">
        <h2>REACT</h2>
        <blockquote>
                 
                 <h3>LIST DEMO (ES6)</h3>
                  <blockquote>
                    <ul>
                        <li><b>Description: </b>List Selection and Components</li>
                        <li><a href="https://github.com/donhenton/react-lists">GitHub Source</a> </li>
                        <li><a href="https://donhenton.github.io/react-lists/public_html/">Demonstration Site</a></li>
                    </ul>
                   </blockquote>
                 
                  <h3>REDUX DEMONSTRATION</h3>
                  <blockquote>
                    <ul>
                        <li><b>Description: </b>Redux and React-Redux</li>
                        <li><a href="https://github.com/donhenton/redux-sandbox">GitHub Source</a> <em>(flux-sandbox branch)</em></li>
                        <li><a href="https://donhenton.github.io/redux-sandbox/public_html/index.html">Demonstration Site</a></li>
                    </ul>
                   </blockquote>
 

        </blockquote>
</div>
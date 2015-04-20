<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>


<c:url var="baseURL" value="/app/" />


<div class="navbar navbar-fixed-top navbar-inverse">

    <div class="navbar-inner">
        <div class="container-fluid">

            <a class="brand" href="${baseURL}home.html">Spring MVC3 Demonstration</a>

            <div class="nav-collapse">
                <!-- credits -->
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Credits <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:out value="${baseURL}home.html"/>">Home</a></li>
                            <li><a href="<c:out value="${baseURL}credits"/>">Credits</a></li>
                        </ul>
                    </li>
                </ul> 


                <ul class="nav pull-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Spring MVC 3 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:out value="${baseURL}jsondemo.html"/>">JSON Demo</a></li>
                            <li><a href="<c:out value="${baseURL}getpostdemo/home.html"/>">Get Post Demo</a></li>
                            <li><a href="<c:out value="${baseURL}data/standard/home.html"/>">Standard Arguments</a></li>
                            <li><a href="<c:out value="${baseURL}messageconverters/home.html"/>">Message Converters</a></li>
                            <li><a href="<c:out value="${baseURL}views/html.html" />">Simple Html via JSP</a></li>
                            <li><a href="<c:out value="${baseURL}tags/demo/home" />">Tag Demo</a></li>
                            <li><a href="<c:out value="${baseURL}views/nontile.html" />">Non Tile Demos</a></li>
                            <li><a href="<c:out value="${baseURL}complex/forms/home" />">Complex Forms</a></li>
                            <li><a href="<c:out value="${baseURL}servlet/functions/home" />">Basic Servlet Functions</a></li>
                          
                            <li><a href="<c:out value="${baseURL}image/generator/home" />">Image Generator</a></li>
                             
                            <li><a href="<c:out value="${baseURL}errors/demo/home" />">Error Handling</a></li>
                            <li><a href="<c:out value="${baseURL}security/demo/home" />">Security Demo</a></li>      

                            <li class="divider"></li><li class="dropdown-header">Spring Context</li>
                            <li><a href="<c:out value="${baseURL}context/bookMaker.html" />">Bean Loaded in Context</a></li>

                        </ul>
                    </li>
                </ul> 

                <ul class="nav pull-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">JQuery Demos <b class="caret"></b></a>
                        <ul class="dropdown-menu">


                            <li><a href="<c:out value="${baseURL}jquery/demos/linkedLists" />">Linked Lists</a></li>
                            <li><a href="<c:out value="${baseURL}jquery/demos/jsonData" />">JSON Data</a></li>
                            <li><a href="<c:out value="${baseURL}jquery/demos/positioning" />">Positioning</a></li>

                            <li class="divider"></li><li class="dropdown-header">Forms</li> 
                            <li><a href="<c:out value="${baseURL}jquery/demos/periodicUpdater" />">Periodic Updater</a></li>
                            <li><a href="<c:out value="${baseURL}jquery/demos/formSamples" />">Form Samples</a></li>
                            <li><a href="<c:out value="${baseURL}jquery/formSubmission/home" />">Ajax Form Submission</a></li>

                            <li class="divider"></li><li class="dropdown-header">Image Displays</li>        
                            <li><a href="<c:out value="${baseURL}jquery/demos/pixDisplay" />">JQuery Lightbox</a></li>
                            <li><a href="<c:out value="${baseURL}jquery/demos/arttimer" />">Art Timer</a></li>


                        </ul>
                    </li>
                </ul> 


                <ul class="nav pull-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Websocket Demos <b class="caret"></b></a>
                        <ul class="dropdown-menu">

                            <li><a href="<c:out value="${baseURL}websocket/graph" />">Graph Update</a></li>
                            <li><a href="<c:out value="${baseURL}websocket/chat" />">Websocket Chat</a></li>
                        </ul>
                    </li>
                </ul> 


                <ul class="nav pull-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Javascript Framework Demos<b class="caret"></b></a>
                        <ul class="dropdown-menu">

                            <li><a href="<c:out value="${baseURL}backbone/demos/js/model" />">Javascript Model/View</a></li>
                            <li><a href="<c:out value="${baseURL}angular/restaurant" />">Angular Restaurant App</a></li> 	

                            <li><a href="<c:out value="${baseURL}backbone/demos/localstorage" />">Backbone Local Storage</a></li>
                            <li><a href="<c:out value="${baseURL}backbone/demos/restaurant" />">Backbone Restaurant App</a></li>
                            
                            
                            <li class="divider"></li><li class="dropdown-header">D3 JS Demos</li>
                            
                            
                            <li><a href="<c:out value="${baseURL}d3/treeDemo" />">Tree Demo</a></li>
                            <li><a href="<c:out value="${baseURL}d3/dynamicGraph" />">Dynamic Graph</a></li>
                            <li><a href="<c:out value="${baseURL}d3/fade" />">Fading Graph Transition</a></li>
                            <li class="divider"></li>
                            <!--
                            <li><a href="<c:out value="${baseURL}backbone/demos/restservice/docs" />">Restaurant REST Service Docs</a></li>
                            -->
                           <li><a href="<c:out value="${baseURL}swagger/mainPage" />">Swaggers Restaurant REST Service Docs</a></li>
                            <li><a href="<c:out value="${baseURL}jerseyclient.html" />">Jersey REST Client</a></li>
                        </ul>
                    </li>
                </ul> 


            </div>
        </div>
    </div>
</div>





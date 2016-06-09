<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<c:url var="baseURL" value="/app/" />

 <!-- begin insert -->   
 
 <script>
            var $dialog = null;

            $(document).ready(function() {

                ActionCode.init();
                TreeCode.createGraph();
            });



        </script>
 
 
                        <div class="row grouping">
                        <div  id="tree-container" class="columnLeft well"></div>
                        <div id="action-container" class="columnRight">
                            <div class="row">
                            <button class="btn btn-small btn-primary btn-large" onclick="ActionCode.zoomIn()">
                                <i class="fi-zoom-in icon icon-large"></i> Zoom In</button>
                            <button class="btn btn-small btn-primary btn-large" onclick="ActionCode.zoomOut()">
                                 <i class="fi-zoom-out icon icon-large"></i> Zoom Out</button>
                            </div>
                            <div class="row">
                               
                            <div id="outputBox" style="margin-top:10px"></div>
                            </div>
                        </div>
                        </div>
                        <div id="my_custom_menu" style="display:none;">


                            <ul> 
                                <li id="nodeId" />
                                <li><button class="btn btn-small btn-primary" onclick="MESSAGE_PUMP.raiseEvent('1', ActionCode.actions.menuClicked)"><i class="icon-tasks icon-white"></i> Menu 1</button></li>
                                <li><button class="btn btn-small btn-primary" onclick="MESSAGE_PUMP.raiseEvent('2', ActionCode.actions.menuClicked)"><i class="icon-tasks icon-white"></i> Menu 2</button></li>    
                            </ul>

                        </div>

                        <!-- end insert --> 



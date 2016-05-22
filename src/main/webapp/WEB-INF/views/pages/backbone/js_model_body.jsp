<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" href="css/backbone/jsdemo.css" media="screen">

<script>
	$(document).ready(function() {
		XTree.init({
			"attachmentPoint" : "tree",
			"transformBase" : "transforms/jsdemo/",
			"urlBase" : "<%=basePath%>app/rest/categories"
				});
				XTree.getLevel1DataForGroup(3);
				XTREE_LISTENERS.transformBase = "transforms/jsdemo/";
				XTREE_LISTENERS.init();
				MESSAGE_PUMP.subscribe(XTREE_LISTENERS.xml_block_refresh,
						XTREE_LISTENERS.ON_REFRESH_EVENT);
				MESSAGE_PUMP.subscribe(XTREE_LISTENERS.selected_list_refresh,
						XTREE_LISTENERS.ON_REFRESH_EVENT);
				

			});
</script>


<div class="columnLeft">
    
     <div class="tabs">
                        <ul>
                            <li><a href="#tabs1">Tree</a></li>
                            <li><a href="#tabs2">XML</a></li>

                        </ul>
                        <div id="tabs1"> 
                        <div id="tree" class="tree" style="overflow-y: auto; height: 250px"></div>
                        </div>
                        <div id="tabs2"> 
                        <textarea id="xml_block" rows="15" style="width: 300px"></textarea>
                        </div>
     </div>
</div>


<div class="columnRight">
    
    
    
    
</div>
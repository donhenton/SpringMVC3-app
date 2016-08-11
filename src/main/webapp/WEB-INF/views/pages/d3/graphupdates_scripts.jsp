<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

  <script>
   absPath = '<%= basePath %>';
  
  </script>
    
    <script src="js/d3/graphupdates/graph_updates.js"></script>
    
   <!-- <link rel="stylesheet" href="css/d3/dynamic_graph.css" /> -->
     
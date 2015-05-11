<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<link href="css/d3/fade/caliper.css" rel="stylesheet" type="text/css"/>
<link href="css/d3/fade/fade.css" rel="stylesheet" type="text/css">
<link href="css/d3/fade/menubar.css" rel="stylesheet" type="text/css"/>
<script>
    absPath = '<%= basePath%>';

</script>


<script src="js/d3/fade/calipler1.js" type="text/javascript"></script>
<script src="js/d3/fade/menubar.js" type="text/javascript"></script>
<script src="js/d3/fade/rectHandler.js" type="text/javascript"></script>
<script src="js/d3/fade/fadeAPI.js" type="text/javascript"></script>
<script src="js/d3/fade/fadeInit.js" type="text/javascript"></script>
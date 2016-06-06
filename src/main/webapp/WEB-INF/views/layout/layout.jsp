<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"  %> 
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"  %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
    
    Date d = new  Date();
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
    String yearValue = sdf.format(d) ;
    

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>

<!DOCTYPE html>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true" />
        </title>

        <base href="<%= basePath%>" />



        <link href="css/main/jquery-ui-theme.css" rel="stylesheet" type="text/css"/>  
        <link href="css/main/foundation-icons.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="css/main/main.css" media="screen"/>



        <tiles:insertAttribute name="jsscripts" />
        <tiles:insertAttribute name="jsscripts_app_specific" />
        <script src="js/ui-init.js" type="text/javascript"></script>

    </head>
    <body>
        <section id="pageContainer">
            <header>

                <figure class="logo"><a href="/">Spring MVC Demonstration</a><br>
                <tiles:getAsString name="subTitle" /></figure> 
                <tiles:insertAttribute name="menu" />
            </header>
            
            
            <section class="grouping" id="main">
                <button onclick="showExplainDialog()" class="pull-right btn btn-primary btn-large">
                    <i class="icon icon-large fi-comment"></i>
                    Explain</button>
                <div class="row">
                <tiles:insertAttribute name="body" />
                </div>
            </section>
            
            
            
             <footer>
                &copy; <%= yearValue %> Don Henton 
            </footer>  
        </section>
            
            <div id="explainDialog" title="Explain">
                <tiles:insertAttribute name="explainPanel"/>
            </div>
            
            
            
    </body>

</html>

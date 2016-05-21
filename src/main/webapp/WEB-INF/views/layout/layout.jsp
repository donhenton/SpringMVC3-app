<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"  %> 
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"  %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%



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


        <link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css"  media="screen">

 

        <!-- <script src="http://code.jquery.com/jquery.js"></script>-->
        
        <tiles:insertAttribute name="jsscripts" />
        <tiles:insertAttribute name="jsscripts_app_specific" />
            
        
        
        <script>
            var $dialog = null;

            $(document).ready(function() {


            });



        </script>


    </head>
    <body>

        <div  class='container'>
            <div class="content">


                <div class="page-header">
                    <div class='row-fluid'>
                        <div class='pull-left'>
                            <h1>
                                <small> 
                                    <tiles:getAsString name="subTitle" />
                                </small>
                            </h1>
                        </div>

                        <div class='pull-right'>


                            <a href="#explainModal" role="button" class="btn large btn-info" 
                               data-toggle="modal">Explain</a>


                        </div>
                    </div>               


                </div>

                <div id="menu">
                    <tiles:insertAttribute name="menu" />
                </div>

                <div id='content' class='row-fluid'>
                    <div class='span12 main'>
                        <tiles:insertAttribute name="body" />
                    </div>
                </div>

                <div id="explainModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="explainModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="explainModalLabel">Explanation:</h3>
                    </div>
                    <div style="max-height: 200px" class="modal-body">

                        <tiles:insertAttribute name="explainPanel"/>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" data-dismiss="modal" aria-hidden="false">Close</button>

                    </div>
                </div>


                <tiles:insertAttribute name="footer" />


            </div><!-- end content -->
        </div> 
 

    </body>

</html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<c:url var="baseURL" value="/app/" />

<!-- begin insert -->   

<script>
    

    $(document).ready(function () {
        

    });



</script>
       
        <div class="row">
            <p>&nbsp;</p>
            Demonstration code illustrating D3 transitions for basic graph types. Source code is available
            at the <a href="https://github.com/donhenton/d3-library">d3-library</a> github project.
            The javascript here is a ES6 browserified, babelfied bundle.
        </div>
             

        <div class="row">
                <div class="tabs">
                    <ul>
                        
                        <li><a href="#pieChart">Pie Chart</a></li>
                        <li><a href="#lineChart">Line Chart</a></li>
                        <li><a href="#hchart">Horizontal Chart</a></li>

                    </ul>

                    
                     <div id="pieChart"> 





                        <div class="row">
                            <h3>Pie</h3>  
                        </div>
                        <div class="row">
                            <table   class="table">
                                <tr>
                                    <td><div id="pieMain"></div></td>
                                    <td><button id="updatePieData" class="pull-right btn btn-primary">Update Data</button></td>
                                </tr>
                            </table>
                        </div>







                    </div><!-- end pie -->

                    

                    <div id="lineChart"> 





                        <div class="row">
                            <h3>Line</h3>  
                        </div>
                        <div class="row well">
                             
                                   <div id="lineChartMain"></div> 
                              
                        </div>







                    </div><!-- end line -->



                   
                    <div id="hchart"> 
                        <div class="well">


                            <div class="row">
                                <h3>Chart</h3>  
                            </div>
                            <div class="row">
                                <table border="1" class="table">
                                    <tr>
                                        <td><div id="horizontalMain"></div></td>
                                        <td><button id="updateHorizontalData" class="pull-right btn btn-primary">Update Data</button></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

        </div>




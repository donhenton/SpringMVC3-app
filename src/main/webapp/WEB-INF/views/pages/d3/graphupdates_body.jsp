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

        <style>

            div.tooltip {					/* set the CSS for tooltip divs */
                position: absolute;			/* reference for measurement */
                text-align: center;			/* align the text to the center */
                width: 60px;					/* set the width of the rectangle */
                height: 35px;					/* set the height of the rectangle */
                padding: 2px;	
                vertical-align: central;
                font: 12px sans-serif;		/* set the font type for the tooltips */
                background: lightsteelblue;	/* set the colour of the rectangle */
                border: 0px;					/* turn off the border (0px) */
                border-radius: 8px;			/* set how rounded the edges of the rectangle is */
                pointer-events: none;			/* 'none' tells the mouse to ignore the rectangle */
            }


        </style>

        <div class="row">
            Demonstration code illustrating D3 transitions for basic graph types. Source code is available
            at the <a href="https://github.com/donhenton/d3-library">d3-library</a> github project.
            The javascript here is a ES6 browserified, babelfied bundle.
        </div>
             

        <div class="row">
                <div class="tabs">
                    <ul>
                        <li><a href="#lineChart">Line Chart</a></li>
                        <li><a href="#pieChart">Pie Chart</a></li>
                        <li><a href="#hchart">Horizontal Chart</a></li>

                    </ul>


                    <div id="lineChart"> 





                        <div class="row">
                            <h3>Line</h3>  
                        </div>
                        <div class="row well">
                             
                                   <div id="lineChartMain"></div> 
                              
                        </div>







                    </div><!-- end pie -->



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




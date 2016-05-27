<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<c:url var="baseURL" value="/app/" />

<!-- begin insert -->   


<script>
    $(document).ready(function () {

        createGraph();


    });



    function   createGraph()
    {
        var svgGroup = null;
        var svg = null;
        var margin = {top: 0, right: 0, bottom: 0, left: 0};
        var width = 650;
        var height = 250;
        var svg = d3.select("#label-demo").append("svg")
                .attr("width", width)
                .on("mousedown", function (d, i) {
                    D3Labels.doMouseDownForSVG(this)
                })
                .on("mouseup", function (d, i) {
                    D3Labels.doMouseUpForSVG(this)
                })
                .on("mousemove", function (d, i) {
                    D3Labels.doMouseMoveForSVG(d, i, this)
                })
                .attr("height", height);
        svg.append("rect").attr("width", width).attr("height", height)
                .attr("fill", "#6677cc")


        var svgGroup = svg.append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")")

        var sample1 = {pointerTargetPoint: {x: 0, y: 140},
            "text": "Alpha", "id": 100, "top": 25, "left": 75};
        var sample2 = {"text": "Beta", "id": 101, "top": 50, "left": 210};
        var sample3 = {"text": "Gamma", "id": 104, "top": 70, "left": 190};
        params = {};
        D3Labels.init(svgGroup, $("#label-demo"), margin, params);
        D3Labels.addLabel(sample1);
        D3Labels.addLabel(sample2);
       // D3Labels.addLabel(sample3);

    }





</script>





 
    
<div class="row"><b>See the Explain button for options</b></div>
    <div class="row"  id="label-demo"></div>


 







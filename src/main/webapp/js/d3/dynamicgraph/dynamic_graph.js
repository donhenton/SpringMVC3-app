
var graph;
var line;
var margin = {top: 30, right: 20, bottom: 30, left: 50};
var width = 650 - margin.left - margin.right;
var height = 400 - margin.top - margin.bottom;
var x = d3.time.scale().range([0, width]);
var y = d3.scale.linear().range([height, 0]);
var svg = null;
var MAX_POINTS = 20;
var xAxis = null;
var yAxis = null;
var delay = 500;
var div = null;

$(document).ready(
        function () {
            
  

 div = d3.select("body").append("div")	// declare the properties for the div used for the tooltips
        .attr("class", "tooltip")				// apply the 'tooltip' class
        .style("opacity", 0);
 });

// Parse the date / time 2015-02-13

var formatTime = d3.time.format("%_m/%_d");
var dateFormatter = d3.time.format("%Y-%m-%d")
var parseDate = dateFormatter.parse;
var key = function (d) {
    //if (d != null && typeof d != undefined)
    return  d.date;
    //else
    // return null;
};

var valueline = d3.svg.line()
        .x(function (d) {
            return x(d.date);
        })
        .y(function (d) {
            return y(d.data);
        });



var data = getData();

function setUp()
{
    if (svg == null)
    {
        svg = initializeSVG();
    }
    assembleAxes();
    drawAllDraw();

}




function reDraw() {



    data = getData();
    x.domain(d3.extent(data, function (d) {
        return d.date;
    }));
    y.domain([0, d3.max(data, function (d) {
            return d.data;
        })]);

    var svg2 = svg.transition();
    //console.log( svg2.select(".line").duration(750))

    svg2.select(".line")   // change the line
            .duration(delay)
            .attr("d", valueline(data));
    svg2.select(".x.axis") // change the x axis
            .duration(delay)
            .call(xAxis);
    svg2.select(".y.axis") // change the y axis
            .duration(delay)
            .call(yAxis);


    doDots();
}
;







function drawAllDraw()
{


    svg.append("path")
            .attr("class", "line")
            .attr("d", valueline(data));

    doDots();

}


function doDots()
{

    var dots = svg.selectAll(".dot").data(data, key);
    dots.attr("fill", "blue");
    dots.enter().append("circle")
            .attr("fill", "green")
            .attr("r", 5)
            .attr("class", "dot")
            .attr("cx", function (d) {
                return x(d.date);
            })
            .attr("cy", function (d) {
                return y(d.data);
            })			// remove semicolon	
            // Tooltip stuff after this
            .on("mouseover", function (d) {
                div.transition()
                        .duration(delay)
                        .style("opacity", .9);
                div.html(formatTime(d.date) + "<br/>" + d.data)
                        .style("left", (d3.event.pageX) + "px")
                        .style("top", (d3.event.pageY - 28) + "px");
            })
            .on("mouseout", function (d, x, y) {
                div.transition()
                        .duration(delay)
                        .style("opacity", 0);                                                   // and go all the way to an opacity of nil
                // console.log(d + " " + x + " " + y)

            });
    // delete
    dots.exit().transition().duration(delay).attr("fill", "red").remove();
    //update
    dots.transition().duration(delay)
            .attr("r", 5)
            .attr("cx", function (d) {
                return x(d.date);
            })
            .attr("cy", function (d) {
                return y(d.data);
            })

}

function initializeSVG()
{
    return  d3.select("#graph")
            .append("svg")
            .attr("height", height + margin.top + margin.bottom).attr("width", width + margin.left + margin.right)

            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
}


function assembleAxes()
{
    x.domain(d3.extent(data, function (d) {

        return d.date;
    }));
    y.domain([0, d3.max(data, function (d) {
            return d.data;
        })]);

    xAxis =
            d3.svg.axis()
            .scale(x).tickPadding(15)
            .ticks(8)
            .tickFormat(function (d) {
                //return d3.time.format("%Y-%m-%d")
                return formatTime(d)
            })
            .innerTickSize([4])
            .outerTickSize([20])
            .orient("bottom");

    yAxis = d3.svg.axis().scale(y)
            .orient("left").ticks(5)

    svg.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis);

    // Add the Y Axis
    svg.append("g")
            .attr("class", "y axis")
            .call(yAxis);
}

//{"date": "2015-01-19","data": 4400} 
function getData() {
    var arr = [];
    var myDate = new Date("06/15/2011");
    myDate.setDate(myDate.getDate() + rand(20));
    for (var x = 0; x < MAX_POINTS; x++) {
        myDate.setDate(myDate.getDate() + 1);
        var d = d3.time.format("%Y-%m-%d")(myDate);
        //   console.log(formatTime)
        var t = rand(20000)
        var item = {"date": parseDate(d), "data": t}
        arr.push(item);
    }
    return arr;
}

function rand(max) {
    return Math.floor(Math.random() * (max + 1))
}




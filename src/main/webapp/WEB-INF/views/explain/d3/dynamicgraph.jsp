<h3>D3 Dynamic Graph</h3>
 
<div>
    This code illustrates how d3 handles adding, updating or removing data
    points from a graph. The <b>Update Date</b> button adds new random data,
    and adjust the axes. The definition of a 'new point' is programmatic, 
    and in this case is based on the date. Thus if a point in the old and 
    new data set have the same date, it will be considered an update.
</div>
<div>
	Hovering over a point will display a tooltip with the data values
        for that point.
</div>
<div>
	The corresponding java code that handles these actions can be found in
	<code>com.dhenton9000.spring.mvc.controllers.d3.</code>
	<code>D3Controller</code>
</div>

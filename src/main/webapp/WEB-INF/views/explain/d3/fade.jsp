<h3>D3 Fading Graph Transition</h3>
 
<div>
  This code demonstrates transitions between reloads by fading the entire
  graph. In addition, a mouseover event positions a bar from the x-axis to 
  the graph point, by finding the nearest point on the graph. The code for 
  this behavior was adapted from 
  <a target="_blank" href="http://www.d3noob.org/2014/07/my-favourite-tooltip-method-for-line.html">
      D3 Noob's favorite tooltip method
  </a>
  
  <h3>Functions Available</h3>
  <ul>
      <li>sliding menu and graph resizing--click the orange half circle</li>
      <li>tooltips</li>
      <li>brush selection of points slide the rectangles beneath the graph</li>
      <li>loading display on data reload</li>
      
      
  </ul>
  
  
  
  
</div>
<div>
    In addition, the javascript code illustrates the use of exporting a public
    API for the caliper, menu and graph components. The internal aspects of 
    the code are generally
    unavailable. Specific methods are exposed for use. Communiction is via 
    the d3 event system..
    
</div>

<div>
	The corresponding java code that handles these actions can be found in
	<code>com.dhenton9000.spring.mvc.controllers.d3.</code>
	<code>D3Controller</code>
</div>

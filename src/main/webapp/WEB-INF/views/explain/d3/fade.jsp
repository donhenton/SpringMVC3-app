<h3>D3 Fading Graph Transition</h3>
 
<div>
  This code demonstrates transitions between reloads by fading the entire
  graph. In addition, a mouseover event positions a bar from the x-axis to 
  the graph point, by finding the nearest point on the graph. The code for 
  this behavior was adapted from 
  <a target="_blank" href="http://www.d3noob.org/2014/07/my-favourite-tooltip-method-for-line.html">
      D3 Noob's favorite tooltip method
  </a>
</div>
<div>
    In addition, the javascript code illustrates the use of exporting a public
    API for the graph object. The internal aspects of the code are generally
    unavailable, the only public methods are the contstructor,  the redraw 
    method, and a hide method.
    
</div>

<div>
	The corresponding java code that handles these actions can be found in
	<code>com.dhenton9000.spring.mvc.controllers.d3.</code>
	<code>D3Controller</code>
</div>

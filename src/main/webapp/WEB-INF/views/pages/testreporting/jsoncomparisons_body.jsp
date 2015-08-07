<div>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
    
<p>
    <a target="_blank" href="<%=basePath%>testing_demos/service_public_html/index.html">Image based testing system</a>
</p>   
<div>
    
    Source code that produced this comparison is available at 
    <a target="_blank" href="https://github.com/donhenton/test-reporting-sandbox">
        https://github.com/donhenton/test-reporting-sandbox</a>.
        
        
    In this demonstration, code contacts a JSON based web service, 
    then compares the result to a previous
    JSON response generated by the same code. The comparison uses the Wikipedia 
    <a href="https://en.wikipedia.org/wiki/User:Cacycle/diff">WikiEd Comparator</a>
      In this code
    base the test that is responsible for this comparision is:
    <code>com.dhenton9000.restaurants.RestaurantTests</code>.
    <p>
    Other features are the ability to copy the current result to the second tab
    where it can be cut and pasteed unformatted into the gold files for updating.
    </p>
    
</div>
    
</div>
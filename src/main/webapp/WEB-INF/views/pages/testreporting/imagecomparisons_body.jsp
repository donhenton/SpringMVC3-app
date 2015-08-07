<div>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
    
<p>
    <a target="_blank" href="<%=basePath%>testing_demos/public_html/index.html">Image based testing system</a>
</p>   
<div>
    
    Source code that produced this comparison is available at 
    <a target="_blank" href="https://github.com/donhenton/test-reporting-sandbox">
        https://github.com/donhenton/test-reporting-sandbox</a>.
        
        
    In this demonstration, code composes a screenshot of the system, 
    then compares it to a previous
    image taken with the same code. The comparison is an overlay on the original
    image of pixel differences and highlights those differences. In this code
    base the test that is responsible for this comparision is:
    <code>com.dhenton9000.screenshot.demo.ScreenShotComparisonTest</code>.
    
    
</div>
    
</div>
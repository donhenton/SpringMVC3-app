<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>


<c:url var="baseURL" value="/app/backbone/demos/js/model" />

<!-- backbone restaurant scripts -->
 
 
    <!--  
    <script src="tree_tests/jquery.mockjax.js"></script>
   -->
   
    <script>
        // generate using jsp tags
       _main_url = "<%= basePath %>/app/backbone/restaurant/";
       restaurantData = ${jsonRestaurants}
        //_main_url = "http://donhenton.appspot.com/app/backbone/restaurant"
        // generate using jsp tags
        
    
     
    </script>
        <!-- 
    <script src="tree_tests/RestaurantMockData.js"></script>
        <script>
        // generate using jsp tags
 
        restaurantData = mockRestaurantData;
    </script>
    -->
 
    <script src="js/backbone/underscore.js"></script>
    <script src="js/backbone/backbone.js"></script>
  
    <script id="reviewTemplate" type="text/template">
        <section class="row">
        {{reviewListing}}({{starRating}}<span class='icon  fi-star'></span>s) 
        
        <span class='label label-primary editRatingClass'><i class='icon fi-pencil'></i></span>
        <span class='label label-important deleteRatingClass'><i class='icon fi-x-circle'></i></span></li>
        </section>
    </script>
    <script id="reviewEditTemplate" type="text/template">
        
        <span class='form form-inline'>
        <input type='text'  id='r_reviewListing'  name='r_reviewListing' value='{{reviewListing}}' size="65">

        <select id='s_starRating' name='s_starRating'>
        $$star_select_content$$
        </select>
        <div style="row">
        <input type='button' value='Save'  class="saveRatingClass btn btn-small btn-primary" />
        <input type='button' value='Cancel'   class="cancelRatingClass btn btn-small btn-red" />
        <span class="text-error" style="display:none" id="error_message"></span>
        </div>
        </span>
        
    </script>



    <script id="formTemplate" type="text/template">

        <div class=form form-inline>
         
         <table class="table">
         <tr>
        <td>Name</td><td>
        <input id="name" name="name" size=60 placeholder="Name"  value="{{name}}" type="text" required="">
        </td></tr>
        <tr><td>City</td><td>
        <input id="city" name="city" size=60 placeholder="City"  value="{{city}}"  type="text" required="">
        </td></tr>
        <tr><td>State</td><td>
        <input id="state" name="city" class="input-small" placeholder="State"   value="{{state}}" type="text" required="">
        </td></tr>
        <td>Zip Code</td><td>
        <input id="zipCode" name="zipCode" class="input-small" placeholder="Zip Code"  value="{{zipCode}}"  type="text" required="">
        </td></tr>
        <tr><td>Version</td><td>
        <input id="version" name="version" class="input-small" placeholder="Version"   value="{{version}}" type="text" required="">
        </td>
        </tr></table>
        </div>

    </script>  

    <script id="rowTemplate" type="text/template">
        <td class="nameItem editMarker" data-id="{{id}}">{{name}}</td>
        <td class="cityItem editMarker">{{city}}</td>
        <td class="stateItem editMarker">{{state}}</td>
        <td class="zipCodeItem editMarker">{{zipCode}}</td>
        <td class="versionItem editMarker">{{version}}</td>
        <td class="actionItems"><button class="btn   btn-primary editMarker"> <i class="icon   fi-pencil"></i> Edit</button></td>
        <td class="actionItems"><button class="btn  btn-red  deleteMarker"> <i class="icon  fi-x-circle"></i> Delete</button></td>
    </script>
  

    
    
    
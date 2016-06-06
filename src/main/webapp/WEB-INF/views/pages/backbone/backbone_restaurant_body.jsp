<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<p>&nbsp;</p>
<div class="tHeadContainer">
    <table>
        <tbody>
        <tr>
	<th class="nameItem">Name</th>
	<th class="cityItem">City</th>
	<th class="stateItem">State</th>
	<th class="zipCodeItem">Zip Code</th>
	<th class="versionItem">Version</th>
        <th class="actionItems">&nbsp;</th>
        <th class="actionItems">&nbsp;</th>
    </tr>
        </tbody>
    </table>

</div>
<div  class="tBodyContainer">
	<table id="listPoint" >
		<tbody></tbody>
	</table>
</div>
<div>
	<div class="row">
		<div id="editArea" class="well columnLeft">

			<div id="restaurantFormViewItems"></div>
			<div class="row">

				<input type="button" id="saveEdits" style="display: none"
					class="btn btn-primary"
					onclick="window.myRestaurantFormView.saveEdits()"
					value="Save Changes" /> <input type="button" id="addRestaurant"
					style="margin: 2px" class="btn btn-primary"
					onclick="window.myRestaurantFormView.addRestaurant()"
					value="Add New Restaurant" />

			</div>
			<div>
				<ul id="errorItems">

				</ul>
			</div>
		</div>
		<div id="ratingsArea" class="columnRight">
			<h4 style="display: inline-block">Reviews</h4>
			<button id="addReviewButton"
				onclick="window.myRatingsListView.showAddReviewDialog();"
				style="display: none" class='btn btn-small btn-primary'>
				<i class="icon fi-pencil"></i> Add Review
			</button>
			<ul id="ratingsLocation"></ul>
		</div>
	</div>
</div>


<div id="addReviewModal">
	 
		<h3>Add Review</h3>
	 
	<div>

		<div class="form form-inline row">
			 
				<label class="row">Review</label> <input id="a_reviewListing"
					name="reviewListing" size="50" placeholder="Review"
					value="" type="text">
			 
				<label class="row">Rating</label> <select  
					id="a_starRating" name="starRating">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
					<option>6</option>
					<option>7</option>
					<option>8</option>
					<option>9</option>
					<option>10</option>
					<option>11</option>
					<option>12</option>
					<option>13</option>
					<option>14</option>
					<option>15</option>
				</select>
			</div>

		</div>

            <div class="text-error row" style="display:none" id="error_message_for_addReview"></div>


	
	<div class="row">
		<button class="btn btn-red btn-primary" onclick="$('#addReviewModal').dialog('close');">Cancel</button>
		<button class="btn btn-primary"
			onclick="window.myRatingsListView.addReview();">Save changes</button>
	</div>
 </div>

<script>
    console.log("zzz")
     $('#addReviewModal').dialog(
                    {
                        'autoOpen':false,
                        'title':"Explain",
                        'resizable':false,
                        'modal':true,
                        'dialogClass': 'addReviewModalClass',
                        'width':500,
                        'height':350,
                        'minHeight':250,
                        'draggable': false,
                        'buttons':
                                {
                                    Close: function(){ $(this).dialog('close');}
                                }
                    });
   
        
 </script>       
        
      

<script src="js/backbone/restaurant/restaurant-app.js"></script>







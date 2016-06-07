<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath%>" />
<script>

    g_restaurantUrlBase = "<%= basePath%>" + "app/backbone/restaurant/";


    g_restaurantData = ${jsonRestaurants}



</script>
<script src="js/angular/restaurant/libs/angular.js/angular.js" type="text/javascript"></script>
<link href="js/angular/restaurant/libs/angular.js/angular-csp.css" rel="stylesheet" type="text/css"/>

<div ng-app="restaurantApp">
    <div ng-cloak>
        

        <div ng-controller="listRestaurantController">
            &nbsp;<br/>
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

            <div class="tBodyContainer">

                <table class="table">
                    <tbody>
                    <tr class="restaurantRow" ng-class= "{true: 'highLighted', false: ''}[restaurant.is_current]"  ng-repeat="restaurant in restaurantList">

                        <td  data-ng-click="changeRestaurant($event, restaurant)"   class="nameItem" data-id="{{restaurant.id}}">{{restaurant.name}}</td>
                        <td  data-ng-click="changeRestaurant($event, restaurant)"   class="cityItem">{{restaurant.city}}</td>
                        <td  data-ng-click="changeRestaurant($event, restaurant)"   class="stateItem">{{restaurant.state}}</td>
                        <td  data-ng-click="changeRestaurant($event, restaurant)"   class="zipCodeItem">{{restaurant.zipCode}}</td>
                        <td  data-ng-click="changeRestaurant($event, restaurant)"   class="versionItem">{{restaurant.version}}</td>                            
                        <td class="actionItems">
                            <span data-ng-click="changeRestaurant($event, restaurant)"   class="btn btn-primary"> <i class="icon fi-pencil"></i> Edit</span></td>
                        <td class="actionItems">
                            <span data-ng-click="deleteRestaurant(restaurant)"           class="btn btn-red"> <i class="icon fi-x-circle"></i> Delete</span></td>


                    </tr>    
                    </tbody>
                </table>
            </div>

            <div class="row red-content">
                &nbsp; {{errorMessage}}
            </div>
        </div>
        <!-- edit and review container -->
        <!-- https://docs.angularjs.org/api/ng/directive/input -->

        <div>
            
                <div  id="editRestaurantController"  ng-controller="editRestaurantController" class="well columnLeft">
                    <h4 style="display: inline-block">Restaurants</h4>
                    

                        <button  ng-show="canAdd" ng-click="addNewRestaurant()"   class="btn btn-small btn-primary"><i class="icon-pencil icon-white"></i> Add New Restaurant</button>

                  


                    <form class="form form-inline" name="editRestaurantForm"  role="form" novalidate ng-show="recordPresent">
                        <table class="table table-bordered">
                        <tr>
                              <td><label for="name">Name</label></td>
                              <td><input required  id="name" name="name"  placeholder="Name" size="60"  ng-model="currentRestaurant.name" type="text"></td> 
                        </tr>    
                        <tr>
                              <td><label for="name">City</label></td>
                              <td><input  required  id="city" name="city"  placeholder="City" size="60" ng-model="currentRestaurant.city"  type="text"></td> 
                        </tr>      
                        <tr>
                              <td><label for="name">State</label></td>
                              <td><input required ng-pattern="/[A-Z]{2}/" minlength="2" maxlength="2" id="state" name="state"  placeholder="State"   ng-model="currentRestaurant.state" type="text"></td> 
                        </tr>         
                        <tr>
                              <td><label for="name">Zip Code</label></td>
                              <td><input required ng-pattern="/[0-9]/" minlength="5" maxlength="5" id="zipCode" name="zipCode"   placeholder="Zip Code"  ng-model="currentRestaurant.zipCode"  type="text"></td> 
                        </tr>
                        <tr>
                              <td><label for="name">Version</label></td>
                              <td><input required  ng-pattern="/[0-9]/"   id="version" name="version" size placeholder="Version"   ng-model="currentRestaurant.version" type="text"></td> 
                        </tr>
                </table>
                             
                         
                        <div  style="margin-top:5px" ng-show="recordPresent">
                            <button  ng-click="saveClick()" class="btn btn-small btn-primary"> <i class="icon-edit  icon-white"></i>  Save</button>
                            <button  ng-click="cancelClick()" class="btn btn-small  btn-red"> <i class="icon-refresh  icon-white"></i>  Restore</button>
                        </div>
                        
                    </form>

                </div><!-- end editRestaurantController -->

                <div id="reviewController" class="columnRight" ng-controller="reviewController">

                    <h4 style="display: inline">Ratings</h4> 

                    <span class="restaurantErrorPanel" ng-show="addReviewForm.reviewListing.$error.required">Review Cannot be blank.</span>
                    <div ng-switch="isAdding">
                        <!-- begin adding new review -->
                        <form name="addReviewForm" role="form" novalidate>
                            <div ng-switch-when="true" style="margin-bottom:5px">

                                <input required style="margin-right:3px"  ng-model="addNewReviewBuffer.reviewListing" name="reviewListing" id="reviewListing"  size="60" type="text">  


                                <select  style="margin-right:2px" ng-model="addNewReviewBuffer.starRating"  class="input-mini">  
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

                                </select>    <span class="icon-star"></span> s 
                                <div>
                                    <button  ng-click="saveNewReview()" class="btn btn-small btn-primary"> 
                                        <i class="icon fi-pencil"></i> Save 
                                    </button>
                                    <button ng-click="cancelNewReview()" class="btn btn-small btn-red">
                                        <i class="icon fi-x-circle"></i> Cancel 
                                    </button>
                                </div>

                            </div>
                        </form>   
                        <form name="editReviewForm" role="form" class="form" novalidate>
                            <div ng-switch-when="false">
                                <div   ng-show="hasCurrentRestaurant()"  style="margin-bottom:5px">
                                    <button  id="addReviewButton" ng-click="addNewReview()" class='btn btn-small btn-primary'>
                                        <i class="icon fi-pencil"></i> Add Review</button>
                                </div>

                                <!-- end adding new review -->

                                <div ng-repeat="review in currentReviews">
                                    <div ng-switch="review.isEditing">
                                        <div ng-switch-when="true" class="reviewRow" ng-class= "{true: 'editingReview', false: ''}[review.isEditing]">

                                            <input style="margin-right:3px" required size="60" ng-model="review.reviewListing" type="text">    


                                            <select  style="margin-right:2px" required  ng-model="review.starRating" type="text">  
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

                                            </select>    <span class="icons fi-star"></span> s
                                            <div>
                                                <button  ng-click="saveReviewEdit(review)" class="btn btn-small btn-primary"> 
                                                    <i class="icon fi-pencil icon-white"></i> Save
                                                </button>
                                                <button ng-click="cancelReviewEdit(review)" class="btn btn-small btn-red">
                                                    <i class="icon fi-x-circle"></i> Cancel  
                                                </button>
                                            </div>

                                        </div>
                                        <div ng-switch-default  class="reviewRow">
                                            {{review.reviewListing}} ({{review.starRating}} 
                                            <span class="icon fi-star"></span> s) 


                                            <div style="margin:5px">
                                                <button  ng-click="editReview(review)" class="btn btn-small btn-primary"> 
                                                    <i class="icon fi-pencil"></i> Edit
                                                </button>
                                                <button ng-click="deleteReview(review)" class="btn btn-small btn-red">
                                                    <i class="icon fi-x-circle"></i> Delete
                                                </button>

                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </form>
                    </div>


                </div><!-- end reviewController -->


                  
        </div>


    </div>    

</div>      
<script src="js/angular/restaurant/libs/angular.js/angular-resource.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/app.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/messagePump.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/services/restaurantDAOService.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/services/reviewDAOService.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/services/messageFactory.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/services/reviewFactory.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/services/restaurantFactory.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/controllers/listRestaurantController.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/controllers/editRestaurantController.js" type="text/javascript"></script>
<script src="js/angular/restaurant/app/controllers/reviewController.js" type="text/javascript"></script>       
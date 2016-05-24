
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
 
<script>


    function postCoffee()
    {

        //var bean = $('#coffeeForm').serializeObject();
        // alert($.param(bean));
        $("#postJSONReply").empty();
        var url = "<%= basePath%>/app/json/postCoffee";
        //create the coffee object
        var jsonMap = new Object();
        
        jsonMap['brand'] = $("#brandPost").val();
        var datastring = JSON.stringify(jsonMap, null);
       

        var request = $.ajax({
            type: 'POST',
            url: url,
            data: datastring,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
 
        });

        request.done(function (coffee) {
             var brandInfo = coffee.brand +" has qty of "+coffee.quantity;
             $("#postJSONReply").text(brandInfo);
        }
        );
        request.error(function (jqXHR, textStatus, errorThrown) {
             $("#postJSONReply").text('error ' + textStatus + ' errorthrown ' + errorThrown);
        }
        );




    }

    function getJSON() {
        $("#getJSONReply").empty();
        $.getJSON('app/json/getbook.json', function (book) {
            $("#getJSONReply").text(book.author);
        });

    }


    function getBrand()
    {
        var url = "app/json/coffee.json?brand=";
        var brand = $('#brandGet').val();
        url = url + brand;
        $.getJSON(url, function (coffee) {
             $("#getBrandReply").text(coffee.brand +" ("+coffee.quantity+")");
        });

    }


</script>


<h3>Processing Jason on the Server via jQuery and JSON.js</h3>

<div class="row">
    <h4>Get To Json Service</h4> 
</div>


<div class="row">
    
        
        <span class="col1">&nbsp;</span>   
        <button class="btn btn-primary" onclick="getJSON();">Get Json</button>
        <span class="col1">&nbsp;</span>
        <span id="getJSONReply"> </span> 
        
   
</div>
<div class="row">
    <h4>GET to service with Brand Name as String Payload</h4> 
</div>
<div   class="row">
    <div class="form form-inline">   
         
        <button class="btn btn-primary" onclick="getBrand();">Get Brand</button>
        <label>Brand:</label><input type="text" id="brandGet" name="brand" value="Nescafe"   size="20"/>
        
        <span>&nbsp;</span>  
         <span id="getBrandReply"> </span> 
        
    </div>
</div> 
<div class="row">
    <h4>POST to service with Coffee JSON as Post Body</h4> 
</div>
<div class="row">
    <div class="form form-inline">

        <button class="btn btn-primary"  onclick="postCoffee()">Post Coffee</button>
        <label>Brand:</label> <input type="text" id="brandPost"  value="Folgers" name="brand" size="20"/>
        
        <span>&nbsp;</span>   
        <span id="postJSONReply"> </span> 
    </div>
</div> 



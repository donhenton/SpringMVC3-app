<script type="text/javascript"  src="js/linkedlistsjson.js"></script>
<script type="text/javascript"  src="js/linkedlistsviaCall.js"></script>

 

<div class="row">
    <div class="columnLeft">
        <h3>Using JSON Object</h3>
        <table class="table table-striped">

            <tr>
                <th>Car Maker</th>
                <td><select id="maker"></select></td>
            </tr>
            <tr>
                <th>Model</th>
                <td><select id="model"></select></td>
            </tr>
            <tr>
                <th>Feature</th>
                <td><select id="feature"></select></td>
            </tr>
            <tr>
                <td colspan="2">

                    <button onclick="showSelectgionDialog()"   class="btn btn-primary">Show Selection</button>
                </td>
            </tr>
        </table>
    </div>
    <div  class="columnRight">

        <h3>Using MVC Call</h3>
        <table class="table table-striped">
            <tr><th>Categories</th><td> <select style="width:250px" id="categories"></select></td><td rowspan="2">  <img id="loaderImage"  width="40" height="40" src="images/loader.gif" /></td></tr>
            <tr><th>SubCategories</th><td> <select  style="width:250px"  id="subCategories"></select></td></tr>
        </table>
    </div>

</div>



<!--  ///////////////////// dialog ///////////////////////////// -->

<div id="selectionInformation"  >
     
         
        <h3 id="selectionInformationLabel">You Selected:</h3>
     
    <div style="max-height: 200px">
       <table class="table">

            <tr>
                <th>Car Maker</th>
                <td id="makerResult"></td>
            </tr>
            <tr>
                <th>Model</th>
                <td id="modelResult"></td>
            </tr>
            <tr>
                <th>Feature</th>
                <td id="featureResult"></td>
            </tr>
        </table>
    </div>
   
</div>

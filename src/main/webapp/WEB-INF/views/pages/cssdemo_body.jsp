<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<c:url var="baseURL" value="/app/" />



<div class="row">
    <div class="columnLeft">
        <h3>Typography</h3>
        <hr>
        Body Text
        <h1> H1 Text</h1>
        <h2> H2 Text</h2>
        <h3> H3 Text</h3>
        <h4> H4 Text</h4>
    </div>
    <div class="columnRight">
        <h3>Lists</h3>
        <hr/>
        <ul>
            <li><em>emphasis</em></li>

            <li><b>bold text</b></li>

            <li><em><b>bold text with emphasis</b></em></li>

            <li>normal text</li>
        </ul>
        <p>You can't get much better than this. This is <em>sample emphasized</em> and <b>bolded</b> text</p>
        
        <div class="row">
        <h3>List Without Image</h3><hr/>
        <ul class="noImage">
            <li><em>emphasis</em></li>

            <li><b>bold text</b></li>

            <li><em><b>bold text with emphasis</b></em></li>

            <li>normal text</li>
        </ul>
        </div>
        
        
    </div>
</div>
<div class="row">
    <h3>Icons</h3>
    <i class="icon icon-large fi-x-circle"></i> Large Cross with Circle
    <i class="icon icon-small fi-x-circle"></i> Small Cross with Circle
    <i class="icon icon-large fi-x-circle red-content"></i> Large Cross with Circle
    <i class="icon icon-small fi-x-circle blue-content"></i> Small Cross with Circle
</div>

<div class="row">
    <h3>Explain Dialog</h3>
    <button onclick="showExplainDialog()" class="btn btn-primary btn-large">Explain</button>
</div>

<div class="row">
    <h3>Buttons</h3>

    <button class="btn btn-primary btn-small">Small Primary Button</button>
    <button class="btn btn-primary">Primary Button</button>
    <button class="btn btn-primary btn-large">Large Primary Button</button>
    <button class="btn btn-red btn-large">Red Button</button>
    <button class="btn btn-inverse">Inverse Button</button>
    <button class="btn  btn-primary btn-small"><i class="icon icon-small fi-check"></i> Save</button>
    <button class="btn btn-red btn-small"><i class="icon icon-small fi-x-circle"></i> Delete</button>

</div>
<div class="row">

    <h3>Tabs</h3>

    <div class="tabs">
        <ul>
            <li><a href="#tabs-1">Striped Table</a></li>
            <li><a href="#tabs-2">Plain Table</a></li>

        </ul>
        <div id="tabs-1"> 
            <table class="table table-striped"> 
                <caption>Striped Table</caption> 
                <thead> 
                    <tr> <th>#</th> <th>First Name</th> <th>Last Name</th> <th>Username</th> </tr> </thead>
                <tbody>
                    <tr> <th scope="row">1</th> <td>Mark</td> <td>Otto</td> <td>@mdo</td> </tr> 
                    <tr> <th scope="row">2</th> <td>Jacob</td> <td>Thornton</td> <td>@fat</td> </tr> 
                    <tr> <th scope="row">3</th> <td>Larry</td> <td>the Bird</td> <td>@twitter</td> </tr> 
                </tbody> 
            </table>
        </div>
        <div id="tabs-2"> 
            <table class="table"> 
                <caption>Basic Table</caption> 
                <thead> 
                    <tr> <th>#</th> <th>First Name</th> <th>Last Name</th> <th>Username</th>  <th>Color Samples</th> </tr> </thead>
                <tbody>
                    <tr> <th scope="row">1</th> <td>Mark</td> <td>Otto</td> <td>@mdo</td> <td class="green-color">Green Color</td></tr> 
                    <tr> <th scope="row">2</th> <td>Jacob</td> <td>Thornton</td> <td>@fat</td> <td class="red-color">Red Color</td> </tr> 
                    <tr> <th scope="row">3</th> <td>Larry</td> <td>the Bird</td> <td>@twitter</td> <td class="reverse-green-color">Reverse Green Color</td></tr> 
                    <tr> <th scope="row">4</th> <td>Mark</td><td>Mark</td><td>Mark</td>  <td class="reverse-red-color">Reverse Red Color</td></tr>
                </tbody> 
            </table>


        </div>
    </div>
</div>


<div class="row">

    <h3>Columns</h3>
    <div class="columnLeft">
        stuff on the left
    </div>
    <div class="columnRight">
        stuff on the right
    </div>
</div>

<div class="row">

    <h3>Well </h3>
    <div class="columnLeft well">
        <h4>Stuff</h4>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        <p class="well">In a well<br>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>

    </div>
    <div class="columnRight well">
        stuff on the right
    </div>
</div>
<div class="row">

    <h3>Forms</h3>
    <div class="columnLeft well">
        <h4>In Line Form</h4>
        <form class="form inline-form">
            <label for="name">Name:</label>
            <input type="text" size="35" id="name">
            <label for="age">Age:</label>
            <input type="text" size="5" id="age">
            <label for="party">Party</label>
            <select id="party">
                <option>Communist</option>
                <option>Socialist</option>
                <option>Democrat</option>
                <option>Republican</option>

            </select>
            <label for="magazine">Magazine</label>
            <select id="magazine">
                <option>Harpers</option>
                <option>New Yorker</option>
                <option>Rolling Stone</option>
                <option>Mad</option>

            </select>
            <fieldset class="radiogroup"> 
                <label for="gender">Gender</label>

                <input type="radio" id="gender-male" name="gender" value="male" checked> <label for="gender-male">Male</label> 
                <input type="radio" id="gender-female" name="gender" value="female"> <label for="gender-female">Female</label> 
                <input type="radio" id="gender-other" name="gender" value="other"> <label for="gender-other">Other</label> 
            </fieldset>
            <input class="btn btn-large btn-primary" value="Input Submit" type="submit"> 
            <button class="btn btn-small btn-red">Button</button> 

        </form>
    </div>
    <div class="columnRight">
        <h4>Stacked Form (using table)</h4>
        <form class="form well">
            <table class="table ">
                <tr><th><label for="name2">Name:</label></th><td><input type="text" size="35" id="name2"></td></tr>
                <tr><th><label for="age2">Age:</label></th><td><input type="text" size="5" id="age2"></td></tr>
                <tr>
                    <th><label for="party2">Party</label></th>
                    <td>
                        <select id="party2">
                            <option>Communist</option>
                            <option>Socialist</option>
                            <option>Democrat</option>
                            <option>Republican</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="magazine2">Magazine</label></th>
                    <td>
                        <select id="magazine2">
                            <option>Harpers</option>
                            <option>New Yorker</option>
                            <option>Rolling Stone</option>
                            <option>Mad</option>
                        </select>
                    </td>
                </tr>


                <tr><th>

                        <label for="gender">Gender</label>
                    </th>
                    <td>   
                        <input type="radio" id="gender-male" name="gender" value="male" checked> <label for="gender-male">Male</label> 
                        <input type="radio" id="gender-female" name="gender" value="female"> <label for="gender-female">Female</label> 
                        <input type="radio" id="gender-other" name="gender" value="other"> <label for="gender-other">Other</label> 
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="btn btn-small btn-red">Button</button> 
                    </td>

                    <td>

                        <input class="btn btn-large btn-primary" value="Input Submit" type="submit"> 
                    </td>
                </tr>





            </table>
        </form>




    </div>
</div>


 
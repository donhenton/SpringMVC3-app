<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="baseURL" value="/app/backbone/demos/" />

<script src="js/backbone/backbone.localStorage.js"></script>
<script src="js/backbone/localstorage/models.js"></script>
<script src="js/backbone/localstorage/views.js"></script>
<script src="js/backbone/localstorage/router.js"></script>



<script type="text/template" id="listTemplate">
    <h4 class="red-content">Tested only in Chrome</h4>
    <div class="row">
    <table class="table">
    <thead>
    <tr>
    <th>First name</th>
    <th>Second name</th>
    <th></th>
    </tr>
    </thead>	
    <tbody>
    </tbody>		
    </table>
    <div class="row">
    <a class="btn btn-primary" backboneActive="true" href="#add">Add a contact</a>
    </div>	
    </div>

</script>
<!--
<c:out value="${baseURL}localstorage.html#app" />
-->
<script type="text/template" id="addTemplate">
    <form  class="form form-inline" method="post" action="#" id= 'addForm' onsubmit="return false">
    <label for="firstname">First name</label>
    <input id="firstname" name='firstname' type="text" /><br />
    <label for="lastname">Last name</label>
    <input id="lastname" name='lastname' type="text" /><br />
    <input class="btn   btn-primary" type="submit" value='Add'/>
    <input id="cancel" class="btn  btn-primary btn-red" type="button" value="Cancel" />
    </form>
</script>

<script type="text/template" id="contactTemplate">
    <td><@= firstname@></td>
    <td><@= lastname@></td>
    <td><button class="btn btn-small btn-red delete">delete</button></td>
</script>

 

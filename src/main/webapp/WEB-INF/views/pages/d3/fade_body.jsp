<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<c:url var="baseURL" value="/app/" />

<!-- begin insert -->   

<script>
    var $dialog = null;

    $(document).ready(function () {
        rundemo();

    });



</script>

<div class="container">

    <div class="top-buffer">

        <h4>See the Explain Panel for more information</h4>
        
        <div id="info" class="well">
           Information will be reported here.


        </div>

    </div>
    <div class="top-buffer">

        <div class="graph" id="graph"> </div>

    </div>


</div>







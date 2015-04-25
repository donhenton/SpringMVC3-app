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
    <div class="container top-buffer">
        <div class="row top-buffer">
            <div class="offset1 span5">
                Mouse over the graph to see the indicator line
                <span>&nbsp;</span>
                <button onclick="reLoad()" class="top-buffer btn btn-lg btn-primary">ReLoad</button>  
            </div>

            <div id="info" class="span5 well">
                Move the mouse across the graph to see data
                <br/>
                Reload to see animation.


            </div>

        </div>
        <div class="row top-buffer">
            <div class="offset1">
                <div class="graph" id="graph"> </div>
            </div>
        </div>





    </div>
</div>

</div>






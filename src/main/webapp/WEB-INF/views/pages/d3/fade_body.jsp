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
        <div class="row">
            <div class="span6">
                <div id="graph"> </div>
            </div>
            <div class="offset2 span4">
                <div id="info" class="well">
                    Move the mouse across the graph to see data
                </div>
                <div>
                    <button onclick="reDrawGraph()" class="btn btn-lg btn-primary">Redraw</button>  
                </div>

                <div class="top-buffer">Mouse over the graph to see the indicator line</div>
            </div>
        </div>
    </div>

</div>




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
        setUp();

    });



</script>

<div class="container">
    <div id="graph"></div>
    <div class="row  top-buffer">
        
        <div class="offset1 span4" style="font-size: 20px">
            <ul>
                <li><span style="color:blue">Updated Points</span></li>
                <li><span style="color:green">Added Points</span></li>
                <li><span style="color:red">Deleted Points</span></li>
            </ul>
        </div>


        <div class="span6">
            <button class="btn btn-primary btn-large" onclick="reDraw()">Update Data</button>
        </div>
    </div>
</div>




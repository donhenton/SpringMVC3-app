<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<c:url var="baseURL" value="/app/" />


<c:if test="${not empty message}">
    <div class="alert-info">${message}</div>

</c:if>


<div class="row">
    This is from a get and/or the home page, note that the action for the form
    below cannot be reached by a GET
</div>
<div class="row">
    <div class="row">
        <h4>This should succeed</h4>
        <form method="post" class="form form-inline"  action="${baseURL}getpostdemo/post">
            <input class="btn btn-large btn-primary" type="submit" value="Submit this form via post" />
        </form>
    </div>
    <div class="row">
        <h4>This should fail</h4>
        <form method="get" class="form form-inline"   action="${baseURL}getpostdemo/post">
            <input class="btn btn-large btn-primary" type="submit" value="Submit this form via get" />
        </form>
    </div>
</div>


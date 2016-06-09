<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<c:url var="baseURL" value="/app/data/standard/" />

<h4>
    This is a collection of demos of standard argument access in
    controllers
</h4>
 
    <div class="row">
        <div class="row">
            <a class="btn large btn-primary"  href="<c:out value="${baseURL}request.html"/>">Go</a>
            <b>Request Variables</b>

        </div>


        <div class="row">

            <form class = "form form-inline" action="<c:out value="${baseURL}request/reader.html"/>" method="POST" >
                <input class="btn large btn-primary" type="submit" value="Go" />

                <b>Request Reader</b>
            </form>
        </div>
    </div>
    <div class="row">


        <form action='<c:out value="${baseURL}request/is.html"  />' method="POST" >
            <input class="btn large btn-primary"  type="submit" value="Go" />

            <b>Request InputStream</b></form>
    </div>
    <div class="row">

        <a class="btn large btn-primary"  href="<c:out value="${baseURL}response.html"/>">Go</a> <b>Response</b>
    </div>

    <div class="row">


        <a class="btn large btn-primary" href="<c:out value="${baseURL}session.html"/>">Go</a>
        <b>Session</b>
    </div>
    <div class="row well"> 
        <c:choose>
            <c:when test="${not empty results}">
                <div class="row offset1">
                    <div class="blue">
                        <h5>${results.description}</h5>
                        <div>${results.result}</div>
                    </div>

                </div>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>
    </div>
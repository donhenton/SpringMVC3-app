<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/spring-form.tld" prefix="form"%>
<c:url var="baseURL" value="/app/views/" />



<c:url var="stringPostVar" value="/app/messageconverters/stringPOST.html" />
<div class="offset1 row">
    <p>Message Conversion or request/form conversion/processing demonstrations</p>
</div>

<div class="offset1 row">
    <div class="span6">

        <div class="row">

            <form id="readString" class="form-inline"
                  action="<c:out value="${stringPostVar}" />" method="post">
                <input type="hidden" name="personalValue" value="astounding " />
                <input type="hidden" name="future" value="rosy " />
                <input class="btn large btn-primary" type="submit" value="Read a Post as String" />
                <input class="input-medium" type="text" size="20" name="input" value="boundless" />
            </form>
        </div>



        <div class="row">
            <form id="readXml" class="form-inline" action="<c:url value="/app/messageconverters/xml.xml" />"
                  method="get">
                <input class="btn large btn-primary" id="readXmlSubmit" type="submit" value="Read XML" />
            </form>
        </div>
        <div class="row">

            <p>  <button class="btn large btn-primary" onclick="getJSON();">Get JSON via JQuery</button></p> 

        </div>

        <div class="row">       

            <p><button  class="btn large btn-primary" onclick="getXML();">Get XML via jQuery</button></p> 

        </div>

    </div>

    <div id="displayArea" class="span6">
        <c:choose>
            <c:when test="${not empty results}">
                <div class="row offset1">
                    <div class="blue">

                        <div>${results}</div>
                    </div>

                </div>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>       
    </div>   

</div>








<script>

    function alertDisplay(text)
    {
        $('#displayArea').text(text);
    }

    function getJSON() {


        $.getJSON('<c:url value="/app/messageconverters/xml.json" />', function (item) {
            alertDisplay(JSON.stringify("item has a value of "+item.foo, null));
        });


    }

    function xmlToString(xmlData) {
        var xmlString;
        //IE     
        if (window.ActiveXObject)
        {
            xmlString = xmlData.xml;
        }
        // code for Mozilla, Firefox, Opera, etc.     
        else
        {
            xmlString = (new XMLSerializer()).serializeToString(xmlData);
        }

        return xmlString;
    }


    function getXML()
    {
        $.ajax({
            type: "GET",
            url: "<c:url value="/app/messageconverters/xml.xml" />",
            dataType: "xml",
            success: function (xml) {
                alertDisplay(xmlToString(xml));
            }
        });
    }


</script>





<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="baseURL" value="/app/" />
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/app" ;
    String fullPath = basePath+"/ajax/getFile";
    
 



%>

<h3>AJAX Download</h3>

<script>
 function doDownloadXHR(requestId)
{

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '<%= fullPath %>', true); //to the download servlet
    if (!requestId)
    {
        requestId = 0;
    }
    var dataToSend = JSON.stringify({"version": requestId});
    $('#loader').css({"visibility":"visible"})
    xhr.responseType = 'arraybuffer';
    xhr.addEventListener("loadend", function()
    {
         $('#loader').css({"visibility":"hidden"})
    })
    xhr.addEventListener("onerror", function(event)
    {
        window.alert(JSON.stringify(event));
    })
    xhr.onerror = function(ev)
    {
        window.alert(JSON.stringify(ev));
    }
    xhr.onload = function () {
        if (this.status === 200) {
            var filename = "";
            var disposition = xhr.getResponseHeader('Content-Disposition');
            if (disposition && disposition.indexOf('attachment') !== -1) {
                var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                var matches = filenameRegex.exec(disposition);
                if (matches != null && matches[1])
                    filename = matches[1].replace(/['"]/g, '');
            }
            var type = xhr.getResponseHeader('Content-Type');
            var a  = null;
            var blob = new Blob([this.response], {type: type});
            if (typeof window.navigator.msSaveBlob !== 'undefined') {
                // IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
                window.navigator.msSaveBlob(blob, filename);
            } else {
                var URL = window.URL || window.webkitURL;
                var downloadUrl = URL.createObjectURL(blob);

                if (filename) {
                    // use HTML5 a[download] attribute to specify filename
                    a = document.createElement("a");
                    a.setAttribute("id","downLoadLink")
                    // safari doesn't support this yet
                    if (typeof a.download === 'undefined') {
                        window.location = downloadUrl;
                    } else {
                        a.href = downloadUrl;
                        a.download = filename;
                        document.body.appendChild(a);
                        a.click();
                    }
                } else {
                    window.location = downloadUrl;
                }

                setTimeout(function () {
                    URL.revokeObjectURL(downloadUrl);
                    $('a#downLoadLink').remove();
                    console.log("timeout clean up called");
                }, 100); // cleanup
            }
        }
        else
        {
            window.alert(" error status "+this.status);
        }
    };
   // xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(dataToSend);




}
   
    
</script>

  <p><button onClick="doDownloadXHR()">Do AJAX Download No parameter</button> <span id="loader" class="loading"></span>    </p>
  <p><button onClick="doDownloadXHR(1)">Do AJAX Download Parameter 1</button> <span id="loader" class="loading"></span>    </p>	 
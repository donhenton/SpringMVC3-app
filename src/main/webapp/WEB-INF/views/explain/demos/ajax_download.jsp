<h3>AJAX Download</h3>
<div>
    <p>
        A demonstration of a wait indicator for a download. AJAX downloads aren't
        official, and this demonstration illustrates one way to do it unoffically.
        This method is not supported on Safari at this time.
    </p>
    <p>
        The servlet streams an xls file and javascript in the web page uses native
        XHR to download the stream and place it in a hidden link using Blobs and
        createObjectUrl.
    </p>
    <p>
        The differing parameters illustrate passing information to the servlet for
        the download process.
    </p>

</div>

<div>
    The corresponding java code that handles these actions can be found in
    <code>com.dhenton9000.spring.mvc.controllers.ajax.download.</code> <code>AjaxDownloadController</code>
</div>

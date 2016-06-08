package com.dhenton9000.spring.mvc.controllers.ajax.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.*;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The controller for the ajax downloader both the download and the navigation
 * method
 *
 * @author Don
 *
 */
@Controller
@RequestMapping("ajax/*")
public class AjaxDownloadController {

    private static Logger log = LogManager.getLogger(AjaxDownloadController.class);

    @RequestMapping("download")
    public ModelAndView ajaxDownloadPage() {

        return new ModelAndView("tiles.ajax.download");
    }

    @RequestMapping(value = "getFile", method = RequestMethod.POST)
    public void getFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String fullPath = context.getRealPath("/com/dhenton9000/spring/mvc/controllers/ajax/download/TestData.xls");
        //log.info("starting "+fullPath);
        String contentType = request.getHeader("Content-Type");
        //contentType == null is a simple GET click

        if (contentType == null || contentType.equals("application/json")) {

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String data = buffer.toString();
            log.debug("data is " + data);
            String fileExt = "alpha";
            if (data.indexOf("1") > 0) {
                fileExt = "beta";
            }
            InputStream in = null;
            OutputStream outstream = null;
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                in = classLoader.getResourceAsStream("TestData.xls");

                response.reset();
                // file = new File(fullPath);
                // in = new FileInputStream(file);
                response.setContentType("application/vnd.ms-excel");
                response.addHeader("content-disposition", "attachment; filename=data" + fileExt + ".xls");
                outstream = response.getOutputStream();
                IOUtils.copyLarge(in, outstream);
                Thread.sleep(1500);
                
                response.flushBuffer();
                log.debug("finished");
            } catch (Exception e) {
                log.error("Unable to download file " + e.getMessage());
            } finally {
                IOUtils.closeQuietly(outstream);
                IOUtils.closeQuietly(in);

            }
        } else {

            throw new ServletException("Content must be 'application/json'");

        }
    }

}

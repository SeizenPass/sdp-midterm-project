package com.team.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

public class RequestLoggingFilter implements Filter {
    private FilterConfig config = null;
    private FileOutputStream fos;
    public final String LOGGING_FILE = "C:\\usr\\tomcat\\sdp-server";
    public static String currentFilePath;
    public void init(FilterConfig _config)
            throws ServletException
    {
        this.config = _config;
        try {
            /* Timestamp log file */
            String pathName = LOGGING_FILE
                    + new Date().getTime() + ".log";
            File file = new File(pathName);
            fos = new FileOutputStream(file);
            currentFilePath = pathName;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error opening log file.");
            System.out.println(e.toString());
        }
    }
    public void doFilter(ServletRequest _req, ServletResponse _res,
                         FilterChain _chain) throws IOException, ServletException
    {
        /* Log HTTP form parameters */
        if (_req instanceof HttpServletRequest) {
            String log = getParams((HttpServletRequest)_req);
            if (fos != null) fos.write(log.getBytes());
        }
        /* Continue with filter chain */
        _chain.doFilter(_req, _res);
    }
    public void destroy()
    {
        config = null;
        try {
            fos.close();
        }
        catch (IOException e) {
            System.out.println("Error closing log file.");
            System.out.println(e.toString());
        }
    }
    private String getParams(HttpServletRequest _req)
            throws ServletException
    {
        StringBuffer log = new StringBuffer();
        /* Get Http Parms */
        log.append("HTTP Request: ");
        log.append(new Date());
        log.append(":\n");
        log.append("Remote Address: " + _req.getRemoteAddr() + "\n");
        log.append("Remote Host: " + _req.getRemoteHost() + "\n\n");
        Enumeration paramNames = _req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String key = (String)paramNames.nextElement();
            String[] values = _req.getParameterValues(key);
            log.append(key + " = ");
            for(int i = 0; i < values.length; i++) {
                log.append(values[i] + " ");
            }
            log.append("\r\n");
        }
        return log.toString();
    }
}



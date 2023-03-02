package com.appsterlight.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, initParams = {
            @WebInitParam(name = "encoding",
                    value = "UTF-8",
                    description = "Encoding parameter")})
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String actual = request.getCharacterEncoding();
        if (actual.equalsIgnoreCase(encoding)) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }
}

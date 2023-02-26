package com.appsterlight.utils;

import jakarta.servlet.http.HttpServletRequest;

public class ConnectionUtils {
    public boolean isGetMethod(HttpServletRequest req) {
        return req.getMethod().equalsIgnoreCase("GET");

    }

}

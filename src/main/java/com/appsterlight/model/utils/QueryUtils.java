package com.appsterlight.model.utils;

public class QueryUtils {
    public static String QueryBuilder(String... params) {
        StringBuilder query = new StringBuilder();
        for (String param : params) {
            query.append(param);
        }

        return query.toString();
    }
}

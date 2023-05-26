package com.appsterlight.model.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryUtils {
    public static String QueryBuilder(String... params) {
        StringBuilder query = new StringBuilder();
        for (String param : params) {
            query.append(param);
        }

        return query.toString();
    }
}

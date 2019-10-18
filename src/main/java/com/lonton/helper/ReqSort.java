package com.lonton.helper;

import lombok.Data;

@Data
public class ReqSort {

    private static final String SQL_ASC = "asc";

    private static final String SQL_DESC = "desc";

    private String field;

    private String order;

    public String getSortBy() {
        String sqlOrder = SQL_ASC;
        if (SQL_DESC.equals(order)) {
            sqlOrder = SQL_DESC;
        }

        return String.format("%s %s", field, sqlOrder);
    }
}

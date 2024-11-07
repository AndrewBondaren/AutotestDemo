package ru.beeline.common.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class SqlTimeUtil {

    private SqlTimeUtil() {

    }

    public static Date stringToSqlDate(final String date, final SimpleDateFormat format) {
        try {
            return new Date(format.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Illegal date argument \"%s\".Stacktrace: %s ", date, e));
        }
    }

}

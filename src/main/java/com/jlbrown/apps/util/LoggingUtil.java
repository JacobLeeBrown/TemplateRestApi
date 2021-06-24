package com.jlbrown.apps.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class LoggingUtil {

    private LoggingUtil() {
    }

    public static String flattenStackTrace(Exception e) {
        return ExceptionUtils.getStackTrace(e).replaceAll("[\r\n\t]+", " | ") + " <EOM>";
    }

}
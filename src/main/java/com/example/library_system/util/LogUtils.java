package com.example.library_system.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final Logger AUDIT_LOG = LogManager.getLogger("AuditLog");
    private static final Logger ERROR_LOG = LogManager.getLogger("ErrorLog");
    private static final Logger INFO_LOG = LogManager.getLogger("InfoLog");

    public static Logger getAuditLogger() {
        return AUDIT_LOG;
    }

    public static Logger getErrorLogger() {
        return ERROR_LOG;
    }


    public static Logger getInfoLogger() {
        return INFO_LOG;
    }

    private LogUtils() {
    }

}

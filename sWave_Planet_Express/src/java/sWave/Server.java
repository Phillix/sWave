package sWave;

import java.util.Properties;

/**
 *
 * @author Brian Millar
 */
public class Server {

    public static final boolean debugging = true;
    public static final boolean testing   = false;

    private static final boolean hasSSL = false;

    public static final String domain        = "localhost";
    public static final String folder        = "../webapps/ROOT/";
    public static final String MySQLUsername = "root";
    public static final String MySQLPassword = "";
    public static final String dbName        = testing ? "SWAVE_TEST" : "SWAVE";
    public static final int    MySQLPort     = 3306;
    public static final int    TomcatPort    = 8084;
    public static final String protocol      = hasSSL ? "https://" : "http://";
    public static final String dbProtocol    = "jdbc:mysql://";
    public static final String dbDriver      = "com.mysql.jdbc.Driver";

    public static Properties   sysProp       = System.getProperties();
    public static int          CPUs          = Runtime.getRuntime().availableProcessors();
    public static long         RAM           = Runtime.getRuntime().totalMemory();
    public static long         freeRAM       = Runtime.getRuntime().freeMemory();


    public static void shutdown() {
        sWave.Logger.writeLine("System Halt Requested");
        Runtime.getRuntime().halt(0);
    }

    public static void garbageCollect() {
        Runtime.getRuntime().gc();
    }
}

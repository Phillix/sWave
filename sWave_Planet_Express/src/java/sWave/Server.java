package sWave;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brian Millar
 */
public class Server {

    public static boolean DEBUGGING = true;
    public static boolean TESTING   = false;

    private static final boolean HAS_SSL  = false;

    public static final String DOMAIN         = "localhost";
    public static final String FOLDER         = "../webapps/ROOT/";
    public static final String MYSQL_USERNAME = "root";
    public static final String MYSQL_PASSWORD = "";
    public static final String DATABASE_NAME  = "SWAVE";
    public static final String TEST_DB_NAME   = "SWAVE_TEST";
    public static final String DB_NAME        = TESTING ? TEST_DB_NAME : DATABASE_NAME;
    public static final int    MYSQL_PORT     = 3306;
    public static final int    TOMCAT_PORT    = 8084;
    public static final String PROTOCOL       = HAS_SSL ? "https://" : "http://";
    public static final String DB_PROTOCOL    = "jdbc:mysql://";
    public static final String DB_DRIVER      = "com.mysql.jdbc.Driver";
    public static final int    MAX_QUEUE      = 2000; //refuse connections after 2000
    public static final int    SOCKET_PORT    = 0; //zero will cause auto-allocation of the port number
    public static ServerSocket SERVER_SOCKET  = null;

    public static Properties sysProp = System.getProperties();
    public static int        CPUs    = Runtime.getRuntime().availableProcessors();
    public static long       RAM     = Runtime.getRuntime().totalMemory();
    public static long       freeRAM = Runtime.getRuntime().freeMemory();


    public static void shutdown() {
        sWave.Logger.writeLine("System Halt Requested");
        Runtime.getRuntime().halt(0);
    }

    public static void garbageCollect() {
        Runtime.getRuntime().gc();
    }
    
    public static void createSocket() {
        try {
            SERVER_SOCKET = new ServerSocket(MAX_QUEUE, SOCKET_PORT);
        } catch (IOException ex) {
            if (DEBUGGING)
                ex.printStackTrace();
        }
    }
}

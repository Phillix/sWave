package sWave;

import java.util.Properties;

/**
 *
 * @author Brian Millar
 */
public class Server {
    public static Properties   sysProp = System.getProperties();;
    public static int          CPUs    = Runtime.getRuntime().availableProcessors();
    public static long         RAM     = Runtime.getRuntime().totalMemory();
    public static long         freeRAM = Runtime.getRuntime().freeMemory();
    public static final String domain  = "http://localhost:8084/";
    public static final String folder  = "../webapps/ROOT/";

    public static void shutdown() {
        sWave.Logger.writeLine("System Halt Requested");
        Runtime.getRuntime().halt(0);
    }

    public static void garbageCollect() {
        Runtime.getRuntime().gc();
    }
}

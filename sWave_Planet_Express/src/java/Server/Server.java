package Server;

import java.util.Properties;

/**
 *
 * @author Brian Millar
 */
public class Server {
    private Properties sysProp;
    private int        CPUs;
    private long       RAM;
    private long       freeRAM;
    private  static final String domain = "http://localhost:8084/";

    public Server() {
        sysProp = System.getProperties();
        CPUs    = Runtime.getRuntime().availableProcessors();
        RAM     = Runtime.getRuntime().totalMemory();
        freeRAM = Runtime.getRuntime().freeMemory();
    }
    
    public static void shutdown() {
        Logging.Logger.writeLine("System Halt Requested");
        Runtime.getRuntime().halt(0);
    }
    
    public static void garbageCollect() {
        Runtime.getRuntime().gc();
    }
    
    public static String getDomain() {
        return domain;
    }
}

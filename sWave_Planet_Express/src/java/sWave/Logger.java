package sWave;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author Brian Millar
 */
public class Logger {

    private static final boolean DEBUG = sWave.Server.debugging;

    public static void writeLine(String text) {
        File file = new File(Server.folder + "sWave.log");
        System.out.println(file.getAbsolutePath());
        FileWriter output = null;
        try {
            output = new FileWriter(Server.folder + "sWave.log");
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        try {
            output.append(System.currentTimeMillis() + " | " + text + "\n");
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
    }
}

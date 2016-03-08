package sWaveEngine;

import Daos.LockDao;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author Brian Millar
 */

public class SongLocking {

    private static final boolean DEBUG = Debugging.Debug.debug;

    public static void clean() {
        /*
            Gets a stream of file paths in current directory,
            loops through them and runs a lamda expression to check
            if no locks remain on the file and if not, remove it.
        */

        LockDao locks = new LockDao();

        Stream<Path> fileList = null;
        try {
            fileList = Files.list(Paths.get("../webapps/ROOT/"));
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        fileList.forEach((Path x) -> {
            if (x.toAbsolutePath().toString().contains(".mp3")) {
                File current = new File(x.toAbsolutePath().toString());
                String filename = current.getName();
                filename = filename.substring(0, filename.length() - 4);
                /*try {
                    if (locks.get(Integer.parseInt(filename)) == 0)
                        current.delete();
                } catch (NumberFormatException e) {
                    Logging.Logger.writeLine("Not an sWave Song File");
                    if (DEBUG)
                        e.printStackTrace();
                }*/
            }
        });
    }
}

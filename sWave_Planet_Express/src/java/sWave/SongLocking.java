package sWave;

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

    private static final boolean DEBUG = sWave.Server.debugging;

    public static void clean() {
        /*
            Gets a stream of file paths in current directory,
            loops through them and runs a lamda expression to check
            if no locks remain on the file and if not, remove it.
        */

        LockDao locks = new LockDao();

        Stream<Path> fileList = null;
        try {
            fileList = Files.list(Paths.get(Server.folder));
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        fileList.forEach((Path x) -> {
            String filename = x.getFileName().toString();
            if (filename.contains(".mp3")) {
                filename = filename.substring(0, filename.length() - 4);
                if (locks.getNumLocks(Integer.parseInt(filename)) == 0) {
                    File file = new File(Server.folder + filename + ".mp3");
                    file.delete();
                }
                /* Lock timeouts requires yet to be written SQL code
                for (Lock l : locks.getSongLocks(Integer.parseInt(filename))) {
                    if ((System.currentTimeMillis() - l.getLockTime()) > 1800000) {
                        File file = new File(Server.folder + filename + ".mp3");
                        file.delete();
                    }
                }
                */
            }
        });
    }
}

package sWaveEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Brian Millar
 */

/*
    This class gives us a way to manage the songs copied to the folder from the
    database. It keeps track of how many users are accessing a file in an
    arraylist called 'locks' and when the number of locks for a stored file is
    zero it will be deleted from the filesystem. Its actually more of a
    garbage collector than a locking system but while a user is accessing a
    we say there is a 'lock' on it anyway even though its fine for another user
    to use the file.
*/
public class SongLocking {

    private static final boolean DEBUG = Debugging.Debug.debug;

    public static void addLock(int x) {
        ArrayList<Integer> locks = new ArrayList<>();
        File outputFile = new File("../webapps/ROOT/lockfile.txt");
        try {
            if (!outputFile.exists())
                outputFile.createNewFile();
            Scanner scan = new Scanner(outputFile);
            if (scan.hasNextLine()) {
                String input    = scan.nextLine();
                String values[] = input.split(", ");
                for (String s : values)
                    if (!s.isEmpty())
                        locks.add(Integer.parseInt(s));
                scan.close();
            }
            locks.add(x);
            FileWriter output = new FileWriter(outputFile);
            for (Integer i : locks)
                output.write(i + ", ");
            output.close();
        } catch (Exception ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
    }

    public static int getLocks(int x) {
        ArrayList<Integer> locks = new ArrayList<>();
        File inputFile = new File("../webapps/ROOT/lockfile.txt");
        try {
            Scanner scan    = new Scanner(inputFile);
            if (scan.hasNextLine()) {
                String input    = scan.nextLine();
                String values[] = input.split(", ");
                for (String s : values)
                    if (!s.isEmpty())
                        locks.add(Integer.parseInt(s));
                scan.close();
            }
            return locks.get(x);
        } catch (FileNotFoundException | NumberFormatException ex) {
            if (DEBUG)
                ex.printStackTrace();
            return -1;
        }
    }

    public static void setLock(int pos, int x) {
        ArrayList<Integer> locks = new ArrayList<>();
        File outputFile = new File("../webapps/ROOT/lockfile.txt");
        try {
            if (!outputFile.exists())
                outputFile.createNewFile();
            Scanner scan    = new Scanner(outputFile);
            if (scan.hasNextLine()) {
                String input    = scan.nextLine();
                String values[] = input.split(", ");
                for (String s : values)
                    if (!s.isEmpty())
                        locks.add(Integer.parseInt(s));
                scan.close();
            }
            locks.set(pos, x);
            FileWriter output = new FileWriter(outputFile);
            for (Integer i : locks)
                output.write(i + ", ");
            output.close();
        } catch (Exception ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
    }

    public static void clearAll() {
        File output = new File("../webapps/ROOT/lockfile.txt");
        output.delete();
    }

    public static void clean() {
        ArrayList<Integer> locks = new ArrayList<>();
        Scanner scan = null;
        try {
            scan = new Scanner(new File("../webapps/ROOT/lockfile.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SongLocking.class.getName()).log(Level.SEVERE, null, ex);
        }
        String content = scan.nextLine();
        String values[] = content.split(", ");
        for (String s : values)
            locks.add(Integer.parseInt(s));
        /*
            Gets a stream of file paths in current directory,
            loops through them and runs a lamda expression to check
            if no locks remain on the file and if not, remove it.
        */
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
                try {
                    if (locks.get(Integer.parseInt(filename)) == 0)
                        current.delete();
                } catch (NumberFormatException e) {
                    Logging.Logger.writeLine("Not an sWave Song File");
                    if (DEBUG)
                        e.printStackTrace();
                }
            }
        });
    }
}

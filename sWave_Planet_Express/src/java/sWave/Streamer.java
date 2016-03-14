package sWave;

import Daos.SongDao;
import Dtos.Song;
import java.net.Socket;

/**
 *
 * @author Brian Millar
 */
public class Streamer {
    public static void stream(int songid) {

        SongDao sDao  = new SongDao();
        Song theSong  = sDao.getSongById(songid);
        byte buffer[] = theSong.getSongdata();
        Socket socket;
    }
}

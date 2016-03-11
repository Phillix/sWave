package sWave;

import Daos.SongDao;
import Dtos.Song;

/**
 *
 * @author Brian Millar
 */
public class Streamer {
    public static void stream(int songid) {

        //The new plan here is to use Datagram Sockets over SSL to deliver data
        //chucks in any order to the client and use DRM protected 
        //MediaSourceExtensions to handle the passing of file data to Web Audio

        SongDao sDao  = new SongDao();
        Song theSong  = sDao.getSongById(songid);
        byte buffer[] = theSong.getSongdata();
    }
}

package Dtos;

/**
 *
 * @author Brian Millar
 */
public class Lock {

    private int songid;
    private int userid;

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Lock{" + "songid=" + songid + ", userid=" + userid + '}';
    }

}

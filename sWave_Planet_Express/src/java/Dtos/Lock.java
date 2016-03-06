package Dtos;

/**
 *
 * @author Brian Millar
 */
public class Lock {

    private int  userid;
    private int  songid;
    private long lockTime;

    public Lock(int userid, int songid) {
        this.userid   = userid;
        this.songid   = songid;
        this.lockTime = System.currentTimeMillis();
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public long getLockTime() {
        return lockTime;
    }

    public void setLockTime(long lockTime) {
        this.lockTime = lockTime;
    }

    @Override
    public String toString() {
        return "Lock{" + "userid=" + userid + ", songid=" + songid + ", lockTime=" + lockTime + '}';
    }
}

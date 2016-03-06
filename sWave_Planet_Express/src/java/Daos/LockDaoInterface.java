package Daos;

/**
 *
 * @author Brian Millar
 */
public interface LockDaoInterface {
    public void addLock(int userId, int songId);
    public void clearLocks(int userId);
    public void clearSongLocks(int songId);
    public void clearAllLocks();
}

package Daos;

import Dtos.Lock;

/**
 *
 * @author Brian Millar
 */
public interface LockDaoInterface {
    public Lock addLock(Lock x);
    public void releaseUserLocks(int userId);
    public void releaseSongLocks(int songId);
    public void releaseAllLocks();
}

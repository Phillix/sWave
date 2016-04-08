
package Dtos;

import java.util.Objects;

/**
 *
 * @author Phillix
 */
public class Friend {
    
    private int userId;
    private int friendId;
    private String friendshipDate;
    private char status; // p = pending c = confirmed
    
    public Friend() {
        userId = -1;
        friendId = -2;
        friendshipDate = null;
        status = 'p';
    }

    public Friend(int userId, int friendId, String friendshipDate, char status) {
        this.userId = userId;
        this.friendId = friendId;
        this.friendshipDate = friendshipDate;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getFriendshipDate() {
        return friendshipDate;
    }

    public void setFriendshipDate(String friendshipDate) {
        this.friendshipDate = friendshipDate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.userId;
        hash = 37 * hash + this.friendId;
        hash = 37 * hash + Objects.hashCode(this.friendshipDate);
        hash = 37 * hash + this.status;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Friend other = (Friend) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.friendId != other.friendId) {
            return false;
        }
        if (!Objects.equals(this.friendshipDate, other.friendshipDate)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Friend{" + "userId=" + userId + ", friendId=" + friendId + ", friendshipDate=" + friendshipDate + ", status=" + status + '}';
    }
    
    
}

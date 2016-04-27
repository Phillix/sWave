package Dtos;

import java.util.Objects;

/**
 * This class is used for creating Message Objects
 * @author Phillix
 */
public class Message {
    private int    msgId;
    private int    sender;
    private int    receiver;
    private String date;
    private String content;
    private boolean    status;
    
    /**
     * Default Constructor for Messages
     */
    public Message() {
        msgId    = -1;
        sender   = -1;
        receiver = -2;
        date     = null;
        content  = "about them tests...";
        status   = false;
    }

    /**
     * Overloaded constructor for Messages
     * @param sender The UserId of the User sending the message
     * @param receiver The UserId of the User receiving the message
     * @param content The content of the message
     */
    public Message(int sender, int receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.date = null;
        this.content = content;
        this.status = false;
    }

    /**
     * Getting the Message id
     * @return the Message id
     */
    public int getMsgId() {
        return msgId;
    }

    /**
     * Method for setting the Message id
     * @param msgId The id you want to change the Message to
     */
    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    /**
     * Method for getting the UserId of the sender
     * @return the UserId of the sender
     */
    public int getSender() {
        return sender;
    }

    /**
     * Method for setting the UserId of the sender
     * @param sender The new id of the sender
     */
    public void setSender(int sender) {
        this.sender = sender;
    }

    /**
     * Getting the UserId of the receiver
     * @return the UserId of the receiver
     */
    public int getReceiver() {
        return receiver;
    }

    /**
     * Setting the UserId of the receiver
     * @param receiver the new UserId of the receiver
     */
    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    /**
     * Getting the date the Message was sent at
     * @return the date the Message was sent at
     */
    public String getDate() {
        return date;
    }

    /**
     * Setting the date that the Message was sent at
     * @param date the new date the Message was sent at
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getting the content of the Message
     * @return the content of the Message
     */
    public String getContent() {
        return content;
    }

    /**
     * Setting the content of the Message
     * @param content the new content of the message
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getting the status of the message
     * @return a boolean value indicating if the message is seen or not
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Setting the status of the message
     * @param status the new status of the message
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Method for hashing the message 
     * @return an int value representing the HashCode of the message
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.msgId;
        hash = 53 * hash + Objects.hashCode(this.sender);
        hash = 53 * hash + Objects.hashCode(this.receiver);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.content);
        return hash;
    }

    /**
     * Equals method for checking if this Message is equal to another object
     * @param obj the object you want to compare this Message to
     * @return a boolean indicating if this object is equal to the argument
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (this.msgId != other.msgId) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    /**
     * A toString method for cleaner printing of the Message as a whole
     * @return a String with all of the Message data inside
     */
    @Override
    public String toString() {
        return "Message{" + "msgId=" + msgId + ", sender=" + sender + ", receiver=" + receiver + ", date=" + date + ", content=" + content + ", status=" + status + '}';
    }
}

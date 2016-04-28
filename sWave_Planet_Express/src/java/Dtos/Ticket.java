package Dtos;

import java.util.Objects;

/**
 * This class is used for Creating and Managing Tickets
 * @author Phillix
 */
public class Ticket {

    private int     ticketId;
    private int     userId;
    private String  issue;
    private String  dateRaised;
    private boolean resolved;

    /**
     * Default Constructor for Tickets
     */
    public Ticket() {
        ticketId   = 0;
        userId     = 0;
        issue      = "Insert issue";
        dateRaised = "Insert date";
        resolved   = false;
    }

    /**
     * Overloaded constructor for Tickets
     * @param userId users id
     * @param issue what their problem is
     * @param resolved is it resolved
     */
    public Ticket(int userId, String issue, boolean resolved) {
        this.userId   = userId;
        this.issue    = issue;
        this.resolved = resolved;
    }

    /**
     * Getting the id of the ticket
     * @return the id of the ticket
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Setting the id of the ticket
     * @param ticketId the new id of the ticket
     */
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    
    /**
     * Getting the id of the user who made the ticket
     * @return the id of the user who made the ticket
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setting the id of the user who made the ticket
     * @param userId the new user id of the ticket
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getting the issue for the ticket
     * @return the issue for the ticket
     */
    public String getIssue() {
        return issue;
    }

    /**
     * Setting the issue for the ticket
     * @param issue the new issue for the ticket
     */
    public void setIssue(String issue) {
        this.issue = issue;
    }

    /**
     * Getting the date the ticket was made
     * @return the date the ticket was made
     */
    public String getDateRaised() {
        return dateRaised;
    }

    /**
     * Setting the date the ticket was raised
     * @param dateRaised the new date for the ticket being raised
     */
    public void setDateRaised(String dateRaised) {
        this.dateRaised = dateRaised;
    }

    /**
     * Getting if the ticket is resolved or not
     * @return if the ticket is resolved or not
     */
    public boolean isResolved() {
        return resolved;
    }

    /**
     * Setting if the ticket is resolved or not
     * @param resolved the new status of the ticket
     */
    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    /**
     * The toString method is for representing the details of the Ticket as a String
     * @return a String with the contents of this Ticket
     */
    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + ", userId=" + userId + ", issue=" + issue + ", dateRaised=" + dateRaised + ", resolved=" + resolved + '}';
    }

    /**
     * A method for hashing each ticket
     * @return an int value containing the hash of this Ticket
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.ticketId;
        hash = 97 * hash + this.userId;
        hash = 97 * hash + Objects.hashCode(this.issue);
        hash = 97 * hash + Objects.hashCode(this.dateRaised);
        hash = 97 * hash + (this.resolved ? 1 : 0);
        return hash;
    }

    /**
     * Equals method for checking if this Ticket is equal to an Object in the args
     * @param obj The object to compare this ticket to
     * @return a boolean for if this ticket is equal to the object
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        final Ticket other = (Ticket) obj;
        if (getClass() != obj.getClass())
            return false;
        if (this.ticketId != other.ticketId)
            return false;
        if (this.userId != other.userId)
            return false;
        if (this.resolved != other.resolved)
            return false;
        if (!Objects.equals(this.issue, other.issue))
            return false;
        return Objects.equals(this.dateRaised, other.dateRaised);
    }
}

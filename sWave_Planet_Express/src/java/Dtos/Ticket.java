package Dtos;

import java.util.Objects;

/**
 *
 * @author Phillix
 */
public class Ticket {

    private int     ticketId;
    private int     userId;
    private String  issue;
    private String  dateRaised;
    private boolean resolved;

    public Ticket() {
        ticketId   = 0;
        userId     = 0;
        issue      = "Insert issue";
        dateRaised = "Insert date";
        resolved   = false;
    }

    /**
     *
     * @param userId users id
     * @param issue what their problem is
     * @param resolved is it resolved
     */
    public Ticket(int userId, String issue, boolean resolved) {
        this.userId   = userId;
        this.issue    = issue;
        this.resolved = resolved;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDateRaised() {
        return dateRaised;
    }

    public void setDateRaised(String dateRaised) {
        this.dateRaised = dateRaised;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + ", userId=" + userId + ", issue=" + issue + ", dateRaised=" + dateRaised + ", resolved=" + resolved + '}';
    }

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

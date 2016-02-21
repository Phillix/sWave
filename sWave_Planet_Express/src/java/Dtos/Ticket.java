/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

/**
 *
 * @author d00159732
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

    public Ticket(int userId, String issue, boolean resolved) {
        this.userId     = userId;
        this.issue      = issue;
        this.resolved   = resolved;
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
    
    
    
}

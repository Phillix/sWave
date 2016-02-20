/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Ticket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Phillix
 */
public class TicketDao extends Dao {
    
     private final boolean DEBUG = false;
    
     private final String TABLE_NAME = "TICKETS";
     private final String ID = "TICKETID";
     private final String USERID = "USERID";
     private final String ISSUE = "ISSUE";
     private final String DATE = "DATERAISED";
     private final String RESOLVED = "RESOLVED";
    
     public int createTicket(Ticket t) {
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            
            con = getConnection();
                
            ps = con.prepareStatement("INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?)");
            //create date object and get current time
            java.util.Date utilDate = new java.util.Date();
            //pass into sql date object
            Date current = new Date(utilDate.getTime());
            
            ps.setInt(1,0);
            ps.setInt(2, t.getUserId());
            ps.setString(3, t.getIssue());
            ps.setDate(4, current);
            ps.setBoolean(5, t.isResolved());
            
            ps.executeUpdate();
            return SUCCESS;
            
        }
        catch (ClassNotFoundException e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return CLASSNOTFOUND;
        }
        catch(SQLException e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return SQLEX;
        }
        finally {
            
            try {
                
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
            }
            catch(SQLException e) {
                return CONNCLOSEFAIL;
            }
        }
    }
}

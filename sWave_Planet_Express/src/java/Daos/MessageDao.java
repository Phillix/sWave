/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

/**
 *
 * @author Phillix
 */
public class MessageDao extends Dao implements MessageDaoInterface {
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME  = "MESSAGE";
    private final String ID          = "MSGID";
    private final String SENDER      = "SENDER";
    private final String RECEIVER    = "RECEIVER";
    private final String DATE        = "DATE";
    private final String CONTENT     = "CONTENT";
    private final String STATUS      = "STATUS";
    
    
}

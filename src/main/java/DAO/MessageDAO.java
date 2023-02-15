package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    public Message createNewMessage( Message message){
        Connection conn = ConnectionUtil.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("insert into message (posted_by, message_text, time_posted_epoch) values(?,?,?)");
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            
            ps.executeUpdate();
           /* ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int generated_message_id = (int) rs.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(),
                 message.getTime_posted_epoch());
            }
            */
                 
            
        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }
    public List<Message> getAllMessages(){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from message");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message getMessageById(int message_id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from message where message_id = ?");
            ps.setInt(1, message_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                 rs.getInt("posted_by"), 
                 rs.getString("message_text"), 
                 rs.getLong("time_posted_epoch"));                       
                return message;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Message deleteMessageById(int message_id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("delete from message where message_id = ?");
            ps.setInt(1, message_id );
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Message updateMessageById(int message_id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update message where message_id=?");
            ps.setInt(1, message_id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Message getAllMessagesById(int account_id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from message (account_id ) values (?)");
            ps.setInt(1, account_id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
       
    }
    
   
    
    
}


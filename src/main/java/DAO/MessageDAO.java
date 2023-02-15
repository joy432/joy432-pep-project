package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    
    public Message createNewMessage( Message message){
        Connection conn = ConnectionUtil.getConnection();
        String sql ="insert into message (posted_by, message_text, time_posted_epoch) values(?,?,?)";
        //"insert into message ( message_id, posted_by,  message_text,  time_posted_epoch) values (?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           // ps.setInt(1, message.getMessage_id());
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int generated_message_id = (int) rs.getLong(1);
                //String generated_message_text = (String) rs.getString("hello message");
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(),
                 message.getTime_posted_epoch());
            }                
            
        }catch(SQLException e){
            System.out.println(e.getMessage());

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
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public void deleteMessageById( int message_id){
        Connection conn = ConnectionUtil.getConnection();
        String sql ="DELETE FROM message WHERE message_id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, message_id );            
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
       
    }
    public Message updateMessageById(int message_id, Message messages){
        Connection conn = ConnectionUtil.getConnection();
        String sql="update message set message_text = ? where message_id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);            
            ps.setString(1, messages.getMessage_text());
            ps.setInt(2, message_id);
            ps.executeUpdate(); 

            return getMessageById(message_id);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getAllMessagesById(int account_id){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();    
        try{
            PreparedStatement ps = conn.prepareStatement("select * from message where posted_by =  ?");
            ps.setInt(1, account_id);
           
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"), 
                rs.getString("message_text"), 
                rs.getLong("time_posted_epoch"));
                messages.add(message);                
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
           return messages;
       
    }
}

   
    
    



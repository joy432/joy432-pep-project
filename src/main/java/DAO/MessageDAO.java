package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDAO {
    Connection conn;
    
    public MessageDAO(){
        try{
            conn = DriverManager.getConnection("com.h2database");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void createMessage(){
        try{
            PreparedStatement ps = conn.prepareStatement("");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String getAllMessages(){
        try{
            PreparedStatement ps = conn.prepareStatement("");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String getMessageById(){
        try{
            PreparedStatement ps = conn.prepareStatement("");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void deleteMessageById(){
        try{
            PreparedStatement ps = conn.prepareStatement("");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateMessageById(){
        try{
            PreparedStatement ps = conn.prepareStatement("");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String getAllMessagesById(){
        try{
            PreparedStatement ps = conn.prepareStatement("");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
}

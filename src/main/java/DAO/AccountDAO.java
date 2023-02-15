package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {
    Connection conn;

    public AccountDAO(){
        try{
            conn = DriverManager.getConnection("com.h2database");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String register(){
        try{
            PreparedStatement ps = conn.prepareStatement("");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public int userLogin(){
        try{
            PreparedStatement ps = conn.prepareStatement("");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
}

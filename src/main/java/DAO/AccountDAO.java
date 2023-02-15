package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Util.ConnectionUtil;



public class AccountDAO {
   

    public Account createNewAccount(Account newAccount){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "insert into account (username, password) values (?,?)";
            PreparedStatement ps = conn.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newAccount.getUsername());
            ps.setString(2, newAccount.getPassword());
            ps.executeUpdate();           
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int generated_account_id = (int) rs.getLong(1);
                return new Account(generated_account_id , newAccount.getUsername(), newAccount.getPassword());
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
       return null;
    }

    
    public Account verifyUserLogin(String username, String password){
        Connection conn = ConnectionUtil.getConnection();
        
        try{
            PreparedStatement ps = conn.prepareStatement("select * from account where username = ? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
          
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
             Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
        
    }
   
}

package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;
    public String username;
    public int account_id;

    public AccountService(){
      this.accountDAO = new AccountDAO();
    }
    public Account createNewAccount(Account newAccount){
        if(newAccount.getUsername() != "" && newAccount.getPassword().length()>=4 ){
            return accountDAO.createNewAccount(newAccount);
        }else{
            return null;
        }        
    
}  
    public Account verifyUserLogin(Account account){

     return accountDAO.verifyUserLogin(account.getUsername(), account.getPassword());

   
    }  
   
}

package Service;

import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
      this.accountDAO = new AccountDAO();
    }
public String register(){
    return accountDAO.register();
}  
public int userLogin(){
    return accountDAO.userLogin();
}  
}

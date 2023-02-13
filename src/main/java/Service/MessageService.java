package Service;

import DAO.MessageDAO;

public class MessageService {
    MessageDAO messageDAO;
    public MessageService(){
       this.messageDAO = new MessageDAO();
    }
public void createMessage(){
     messageDAO.createMessage();
}   
public String getAllMessages(){
    return messageDAO.getAllMessages();
}
public String getMessageById(){
    return messageDAO.getMessageById();
}
public void deleteMessageById(){
    messageDAO.deleteMessageById();
}   
public void updateMessageById(){
    messageDAO.updateMessageById();
} 
public String getAllMessagesById(){
    return messageDAO.getAllMessagesById();
}
}

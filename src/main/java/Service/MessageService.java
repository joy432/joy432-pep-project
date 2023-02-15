package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;
    public MessageService(){
       this.messageDAO = new MessageDAO();
    }
public Message createNewMessage(Message message ){
    if(message.getMessage_text() == ""){
       return null;
  }else{
     return messageDAO.createNewMessage(message);
} 
} 

  
public List<Message> getAllMessages(){
    return messageDAO.getAllMessages();
}


public Message getMessageById(int message_id){
    return messageDAO.getMessageById(message_id);
}

public Message deleteMessageById(int message_id){
    return messageDAO.deleteMessageById(message_id);
}   
public Message updateMessageById(int message_id){
    return messageDAO.updateMessageById(message_id);

}

public Message getAllMessagesById(int account_id){
    return messageDAO.getAllMessagesById(account_id);
}

}


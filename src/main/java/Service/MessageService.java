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
    // messageDAO.deleteMessageById(message_id);
    if(messageDAO.getAllMessagesById(message_id) != null){
        Message message = messageDAO.getMessageById(message_id);
        messageDAO.deleteMessageById(message_id);
        return message;
    }
     return null;
}   
public Message updateMessageById( int message_id, Message message ){
    if(message.message_text != "" && message.message_text.length() <=255){
        return messageDAO.updateMessageById(message_id, message);
    }
    
    return null;
}

public List<Message> getAllMessagesById(int account_id){
    return messageDAO.getAllMessagesById(account_id);
}

}


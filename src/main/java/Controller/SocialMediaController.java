package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
     Javalin app;

    public Javalin startAPI() {
         app = Javalin.create();

        accountService = new AccountService();
       messageService = new MessageService();
       // app.get("example-endpoint", this::exampleHandler);

        app.post("/register", this::createNewAccountHandler);
        app.post("/login", this::verifyUserLoginHandler);
        app.post("/messages", this::createNewMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/message{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByIdHandler);
       
       return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    //private void exampleHandler(Context context) {
     //   context.json("sample text");
    //}

    private void createNewAccountHandler(Context ctx)throws JsonProcessingException{
       
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account newAccount = accountService.createNewAccount(account);
        if(newAccount != null){
            ctx.status(200);
            ctx.json(om.writeValueAsString(newAccount));
            
        }else {
            ctx.status(400);
        }
    }
    private void verifyUserLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account acc = accountService.verifyUserLogin(account);

        if(acc!= null){
            ctx.json(om.writeValueAsString(acc));
            ctx.status(200);
            
        }else{
            ctx.status(401);
        }

        }        
    
    private void createNewMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(ctx.body(), Message.class);
        Message msg = messageService.createNewMessage(message);
        if(msg != null){            
            ctx.status(200);
            ctx.json(om.writeValueAsString(msg));
        }else{
            ctx.status(400);
        }

    }
    
    private void getAllMessagesHandler(Context ctx){
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
        
    }
    
    private void getMessageByIdHandler(Context ctx){
     int message_input = Integer.parseInt(ctx.pathParam("message_id"));
     Message msg = messageService.getMessageById(message_input);
     ctx.json(msg); 

     
    } 
     

    private void deleteMessageByIdHandler(Context ctx){
        int message_delete = Integer.parseInt(ctx.pathParam("message_id"));
        Message msg = messageService.deleteMessageById(message_delete);
        if(msg != null){
          System.out.println(ctx.json(msg));   
        }
        if(msg == null){
          System.out.println(ctx.json(msg));
        }     
    }
    private void updateMessageByIdHandler(Context ctx) throws JsonProcessingException{
       
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessageById(message_id);
        System.out.println(updatedMessage);
       
        if(updatedMessage.message_text != null && updatedMessage.message_text.length() <=255){
            ctx.json(updatedMessage);
        }else{
            ctx.status(400);
        }
        
    }

    private void getAllMessagesByIdHandler (Context ctx){
        int account_input = Integer.parseInt(ctx.pathParam("account_id"));
        Message msg = messageService.getAllMessagesById(account_input);

        
       
        // ctx.json(msg.writeValueAsString(updatedFlight));
        
   
       
        
            
    }
}
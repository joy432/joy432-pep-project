package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler); //deleteMessageByIdHandler(Conte
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
            
            ctx.json(om.writeValueAsString(newAccount));
            ctx.status(200);
            
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
            
            ctx.json(om.writeValueAsString(msg));
            ctx.status(200);
        }else {
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
     if(msg != null ){
        ctx.status(200);
        ctx.json(msg);
     }else{
        ctx.status(400);
     }

    }      

    private void deleteMessageByIdHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        //Message message = om.readValue(ctx.body(), Message.class);
        int message_input = Integer.parseInt(ctx.pathParam("message_id"));
        Message msg = messageService.deleteMessageById(message_input);

        //ObjectMapper om = new ObjectMapper();
        //Message message = om.readValue(ctx.body(), Message.class);
        //Message msg = messageService.deleteMessageById(message);
        //int message_delete = Integer.parseInt(ctx.pathParam("message_id"));
        
       // if(msg == null){
          //ctx.json(msg);  
         // ctx.status(200) ;
         if (msg != null){
          ctx.json(om.writeValueAsString(msg));
          ctx.status(200);
        }else{
            ctx.body();
        }
        //delete(ctx, msg.message_id);
    }
    private void updateMessageByIdHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        Message message = om.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message massage = messageService.updateMessageById(message_id);
       
        if(message == null || message.getMessage_text()== null ){
            ctx.status(400);
        }else{
            ctx.json(message);
        }        
    }

    private void getAllMessagesByIdHandler (Context ctx){
        int account_input = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = messageService.getAllMessagesById(account_input);
        if(messages != null){
            ctx.json(messages);
            
        }   
   
       
        
            
    }
}
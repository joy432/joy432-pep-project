package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
       // app.get("example-endpoint", this::exampleHandler);
        app.post("/ register", this::registerHandler);
        app.post("/login", this::userLoginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/message{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByIdHandler);
        app.start(8080);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    //private void exampleHandler(Context context) {
     //   context.json("sample text");
    //}

    private String registerHandler(Context context){
        return null;
    }
    private void userLoginHandler(Context context){
        
    }
    private void createMessageHandler(Context context){
        
    }
    private void getAllMessagesHandler(Context context){
        
    }
    private void getMessageByIdHandler(Context context){
        
    }
    private void deleteMessageByIdHandler(Context context){
        
    }
    private void updateMessageByIdHandler(Context context){
        
    }
    private void getAllMessagesByIdHandler (Context context){
        
    }
}
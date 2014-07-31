package server;

import shared.communication.LoginUserParam;
import shared.communication.RegisterUserParam;
import shared.communication.ServerResponse;
import system.User;


/**
 *
 * @author Kevin MacMaster
 */
public class UserHandler implements IHandler{
    private ServerController controller;
    
    public UserHandler(ServerController controller) {
        this.controller = controller;
    }

    @Override
    public ServerResponse handle(String command, Object Json) {
        switch(command) {
            case "/user/login":	
            	return loginUser((LoginUserParam)JsonUtils.convertFromJson(
                                    LoginUserParam.class, (String)Json));
            case "/user/register":
                return registerUser((RegisterUserParam)JsonUtils.convertFromJson(
                                    RegisterUserParam.class, (String)Json));
            default:
                return null;
        }
    }
    
    /**
     * Creates new user if possible
     * 
     * @param parm info on new user
     * @return response with new user or failure
     */
    public ServerResponse registerUser(RegisterUserParam parm) {
        ServerResponse response = null;
        if(controller.canCreateUser(parm.getUsername(), parm.getPassword())) {
            User user = controller.createUser(parm.getUsername(), parm.getPassword());
            response = new ServerResponse(200, "Success");
            response.setCookie("catan.user=%7B%22authentication%22%3A%22-1286879297%22%2C%22name%22%3A%22" +
            		user.getUsername().getUsername() +
            		"%22%2C%22password%22%3A%22" +
            		user.getPassword().getPassword() +
            		"%22%2C%22playerID%22%3A" +
            		user.getId() +
            		"%7D;Path=/;");
        }
        else {
        	response = new ServerResponse(400, "Cannot create user");
        }
        
        return response;
    }
    
    /**
     * Attempts to login User
     * 
     * @param parm info on user
     * @return User or failures
     */
    public ServerResponse loginUser(LoginUserParam parm) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
}


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
            response.setCookie(controller.createCookie(user.getUsername().getUsername(),
                                user.getPassword().getPassword(), user.getId()));
        } else {
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
        
        User user = controller.loginUser(parm.getUsername(), parm.getPassword());
        
        if(user != null) {
            response = new ServerResponse(200, "Success");
            response.setCookie(controller.createCookie(user.getUsername().getUsername(),
                                user.getPassword().getPassword(), user.getId()));
        } else {
            response = new ServerResponse(400, "Login failed");
        }
        
        return response;
    }

    @Override
    public void applyCommand(String command, Object Json) {}
    
}


package client.login;

import client.base.*;
import client.misc.*;
import controller.ControllerFacade;
import controller.IControllerFacadeListener;
import game.GameModel;
import player.Player;
import system.Password;
import system.User;
import system.Username;

//import java.net.*;
//import java.io.*;
//import java.util.*;
//import java.lang.reflect.*;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController, IControllerFacadeListener {

	private IMessageView messageView;
	private IAction loginAction;
	
	/**
	 * LoginController constructor
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {
		
		super(view);
		this.messageView = messageView;
                ControllerFacade.getSingleton().addListener(this);
	}
        
	@Override
        public void gameModelChanged(GameModel gameModel){
        
        }
         
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		// log in user
		String username = getLoginView().getLoginUsername();
		String password = getLoginView().getLoginPassword();
		boolean success = ControllerFacade.getSingleton().signIn(username, password);
		
		// If log in succeeded
		if(success) {
			getLoginView().closeModal();
                        ControllerFacade.getSingleton().setClientPlayer(new Player(
                                new User(new Username(username), new Password(password), 1)));
			loginAction.execute();
			
		} else {
			//check for different kinds of errors
			loginFailed("Login Error", "Login failed - invalid username or password");
		}
	}

	@Override
	public void register() {
		
		//register new user (which, if successful, also logs them in)
		String username = getLoginView().getRegisterUsername();
		String password = getLoginView().getRegisterPassword();
		String passwordAgain = getLoginView().getRegisterPasswordRepeat();
		
		boolean success;
		if(!validateUsername(username) || !validatePassword(password, passwordAgain)) {
			success = false;
		} else {
			success = ControllerFacade.getSingleton().register(username, password);
		}
		
		if(success) {
			getLoginView().closeModal();
                        ControllerFacade.getSingleton().setClientPlayer(new Player(
                                new User(new Username(username), new Password(password), 1)));
			loginAction.execute();
		} else {
			//need to check for different kinds of errors
			loginFailed("Login Error", "Login failed - invalid username or password");
		}
	}
	
	/**
	 * creates and shows client an error message when login is not successful
	 * @param title
	 * @param message
	 */
	public boolean validateUsername(String username) {
		
            final int MIN_UNAME_LENGTH = 3;
            final int MAX_UNAME_LENGTH = 7;

            if (username.length() < MIN_UNAME_LENGTH
                    || username.length() > MAX_UNAME_LENGTH)
            {
                return false;
            }
            else
            {
                for (char c : username.toCharArray())
                {
                    if (!Character.isLetterOrDigit(c)
                            && c != '_' && c != '-')
                    {
                        return false;
                    }
                }
            }

            return true;
	}
	
	/**
	 * checks to see if the inputed password is valid before creating a new user
	 * @param password
	 * @param passwordAgain
	 * @return
	 */
	public boolean validatePassword(String password, String passwordAgain) {
		 final int MIN_PASS_LENGTH = 5;

         if (password.length() < MIN_PASS_LENGTH)
         {
             return false;
         }
         else
         {
             for (char c : password.toCharArray())
             {
                 if (!Character.isLetterOrDigit(c)
                         && c != '_' && c != '-')
                 {
                     return false;
                 }
             }
         }
         
         return passwordAgain.equals(password);
	}
	
	/**
	 * set the text for the error overlay and displays it
	 * @param title
	 * @param message
	 */
	public void loginFailed(String title, String message) {
		
		MessageView errorView = new MessageView();
		errorView.setTitle(title);
		errorView.setMessage(message);
		errorView.showModal();
	}
}


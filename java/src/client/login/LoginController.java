package client.login;

import controller.ControllerFacade;
import client.base.*;
import client.misc.*;

//import java.net.*;
//import java.io.*;
//import java.util.*;
//import java.lang.reflect.*;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

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
			loginAction.execute();
			
		} else {
			loginFailed("Login Errore", "Login failed - invalid username or password");
		}
	}

	@Override
	public void register() {
		
		//register new user (which, if successful, also logs them in)
		String username = getLoginView().getRegisterUsername();
		String password = getLoginView().getRegisterPassword();
		boolean success = ControllerFacade.getSingleton().register(username, password);
		
		// If register succeeded
		if(success) {
			getLoginView().closeModal();
			loginAction.execute();
			
		} else {
			loginFailed("Login Error", "Login failed - invalid username or password");
		}
	}
	/**
	 * creates and shows client an error message when login is not successful
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


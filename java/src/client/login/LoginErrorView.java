package client.login;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

import client.base.*;
import client.utils.*;


/**
 * Implementation for the game finished view, which is displayed when the game is over
 */
@SuppressWarnings({"serial", "unused"})
public class LoginErrorView extends OverlayView {

	private final int LABEL_TEXT_SIZE = 40;
	private final int MESSAGE_TEXT_SIZE = 14;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;
	
	private JLabel label;
	private JLabel message;
	private JLabel image;
	private JButton okButton;
	private JPanel buttonPanel;

	public LoginErrorView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Login Failed");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(label, BorderLayout.PAGE_START);

		JPanel middle = new JPanel();
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		this.add(middle, BorderLayout.CENTER);

		middle.add(Box.createRigidArea(new Dimension(0,5))); // Spacing
		
		message = new JLabel("***");
		Font messageFont = message.getFont();
		messageFont = messageFont.deriveFont(messageFont.getStyle(), MESSAGE_TEXT_SIZE);
		message.setFont(messageFont);
		message.setAlignmentX(Component.CENTER_ALIGNMENT);
		middle.add(message);
		
		middle.add(Box.createRigidArea(new Dimension(0,5))); // Spacing
		
		/**image = new JLabel(new ImageIcon(ImageUtils.DEFAULT_IMAGE));
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		middle.add(image);**/

		middle.add(Box.createRigidArea(new Dimension(0,5))); // Spacing
		
		okButton = new JButton("Close");
		okButton.addActionListener(actionListener);
		Font buttonFont = okButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		okButton.setFont(buttonFont);
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(okButton, BorderLayout.PAGE_END);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == okButton) {
				closeModal();
			}
		}	
	};
	
	public void setMessage() {
		String field = "Login Failed - invalid username or password";
		
		message.setText(field);
	}
}
package client.domestic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import shared.definitions.*;
import client.base.*;
import client.data.*;


/**
 * Implementation of the domestic trade overlay, which allows the user to propose
 * a domestic trade
 */
@SuppressWarnings("serial")
public class DomesticTradeOverlay extends OverlayView implements IDomesticTradeOverlay {

	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton tradeButton;
	private JButton cancelButton;
	private JPanel buttonPanel;

	public DomesticTradeOverlay() {
	
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Domestic Trade Overlay");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.CENTER);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(actionListener);
		Font buttonFont = cancelButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		cancelButton.setFont(buttonFont);
		
		tradeButton = new JButton("Trade!");
		tradeButton.addActionListener(actionListener);
		tradeButton.setFont(buttonFont);	
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(tradeButton);
		buttonPanel.add(cancelButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == tradeButton) {
				getController().sendTradeOffer();
			}
			else if (e.getSource() == cancelButton) {
				getController().cancelTrade();
			}
		}	
	};
	
	@Override
	public IDomesticTradeController getController() {
		return (IDomesticTradeController)super.getController();
	}

	@Override
	public void reset() {

	}

	@Override
	public void setPlayers(PlayerInfo[] value) {

	}

	@Override
	public void setPlayerSelectionEnabled(boolean enable) {

	}

	@Override
	public void setResourceAmount(ResourceType resource, String amount) {

	}

	@Override
	public void setResourceAmountChangeEnabled(ResourceType resource,
												boolean canIncrease, boolean canDecrease) {

	}

	@Override
	public void setResourceSelectionEnabled(boolean enable) {

	}

	@Override
	public void setStateMessage(String message) {

	}

	@Override
	public void setTradeEnabled(boolean enable) {

	}

	@Override
	public void setCancelEnabled(boolean enabled) {

	}

}


package client.maritime;

import client.base.*;
import game.cards.ResourceCard;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import shared.definitions.*;


/**
 * Implementation for the maritime trade overlay, which lets the user make a maritime trade
 */
@SuppressWarnings("serial")
public class MaritimeTradeOverlay extends OverlayView implements IMaritimeTradeOverlay {
    private ResourceType[] giveOptions= {ResourceType.BRICK,ResourceType.ORE,ResourceType.SHEEP,ResourceType.WHEAT,ResourceType.WOOD};
    private ResourceType[] getOptions= {ResourceType.BRICK,ResourceType.ORE,ResourceType.SHEEP,ResourceType.WHEAT,ResourceType.WOOD};
    // these arrays might actually have objects like buttons instead
    
	private final int LABEL_TEXT_SIZE = 40;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 10;

	private JLabel label;
	private JButton tradeButton;
	private JButton cancelButton;
	private JPanel buttonPanel;

	public MaritimeTradeOverlay() {
	
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Maritime Trade Overlay");
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

	@Override
	public IMaritimeTradeController getController() {
		return (IMaritimeTradeController)super.getController();
	}

	@Override
	public void reset() {
		//unselects the get and give option resource, numbers are reset
            //all resources are set visible
            //trade button message reset, trade button enabled(false)
	}

	@Override
	public void hideGetOptions() {
		//set visibility false for all but the selectedGetOption
	}

	@Override
	public void hideGiveOptions() {
		//set visibility false for all but the selectedGiveOption
	}

	@Override
	public void selectGetOption(ResourceType selectedResource, int amount) {
		//sets the selectedGetOption resource and the number (must be 1 in maritime trading)
	}

	@Override
	public void selectGiveOption(ResourceType selectedResource, int amount) {
		//sets the selectedGiveOption resource and the number
            //giveValue=2 if they have a port of that resource or 3 if they have a 3-1 port 
	}

	@Override
	public void setStateMessage(String message) {
		tradeButton.setText(message);
	}

	@Override
	public void setTradeEnabled(boolean enable) {
		tradeButton.setEnabled(enable);
	}

	@Override
	public void setCancelEnabled(boolean enabled) {
		cancelButton.setEnabled(enabled);
	}

	@Override
	public void showGetOptions(ResourceType[] enabledResources) {
//		for (int i=0; i <enabledResources.length; i++){
//                    //show which ones are enabled. the others are faded
//                }
	}

	@Override
	public void showGiveOptions(ResourceType[] enabledResources) {
//            if(enabledResources.length==0){
//                setStateMessage("You don't have enough resources to trade.");
//                showGetOptions(new ResourceType[0]);
//            }
//		for (int i=0; i <enabledResources.length; i++){
//                    //show which ones are enabled. the others are faded
//                }	
	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == tradeButton) {
				getController().makeTrade();
			}
			else if (e.getSource() == cancelButton) {
				getController().cancelTrade();
			}
		}	
	};

}


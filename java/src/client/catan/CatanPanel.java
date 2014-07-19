package client.catan;

import client.discard.DiscardController;
import client.discard.DiscardView;
import client.domestic.*;
import client.maritime.*;
import client.misc.WaitView;
import client.roll.RollController;
import client.roll.RollResultView;
import client.roll.RollView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import shared.definitions.ResourceType;

@SuppressWarnings("serial")
public class CatanPanel extends JPanel
{
	private TitlePanel titlePanel;
	private LeftPanel leftPanel;
	private MidPanel midPanel;
	private RightPanel rightPanel;
	
	private DiscardView discardView;
	private WaitView discardWaitView;
	private DiscardController discardController;
	
	private RollView rollView;
	private RollResultView rollResultView;
	private RollController rollController;
	
	public CatanPanel()
	{
		this.setLayout(new BorderLayout());
		
		titlePanel = new TitlePanel();
		midPanel = new MidPanel();
		leftPanel = new LeftPanel(titlePanel, midPanel.getGameStatePanel());
		rightPanel = new RightPanel(midPanel.getMapController());
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
		
                MaritimeTradeOverlay maritimeTradeOverlay= new MaritimeTradeOverlay();
                MaritimeTradeView maritimeTradeView= new MaritimeTradeView();
                final MaritimeTradeController maritimeTradeController= new MaritimeTradeController(maritimeTradeView,maritimeTradeOverlay);
                maritimeTradeView.setController(maritimeTradeController);
                maritimeTradeOverlay.setController(maritimeTradeController);
                
                DomesticTradeView domesticView = new DomesticTradeView();
                DomesticTradeOverlay domesticOverlay= new DomesticTradeOverlay();
                AcceptTradeOverlay acceptOverlay= new AcceptTradeOverlay();
                WaitView waitView= new WaitView();
                DomesticTradeController domesticTradeController= new DomesticTradeController(domesticView,domesticOverlay,waitView,acceptOverlay);
                domesticView.setController(domesticTradeController);
                domesticOverlay.setController(domesticTradeController);
                acceptOverlay.setController(domesticTradeController);
                waitView.setController(domesticTradeController);
		JButton testButton = new JButton("Test");
		testButton.addActionListener(new ActionListener() {
			
//			 @Override
//			 public void actionPerformed(ActionEvent e) {
//			
//			 new client.points.GameFinishedView().showModal();
//			 }
//			
//			 @Override
//			 public void actionPerformed(ActionEvent e) {
//			
//			 rollView.showModal();
//			 }
//			
//			 @Override
//			 public void actionPerformed(java.awt.event.ActionEvent
//			 e) {
//			
//			 midPanel.getMapController().startMove(PieceType.ROBBER,
//			 false, false);
//			 }
			
			int state = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
                            
                            maritimeTradeController.startTrade();
                            
//				
			}
		});
		this.add(testButton, BorderLayout.SOUTH);
	}
	
}

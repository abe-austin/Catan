package client.catan;

import client.discard.DiscardController;
import client.discard.DiscardView;
import client.domestic.*;
import client.maritime.*;
import client.misc.WaitView;
import client.roll.RollController;
import client.roll.RollResultView;
import client.roll.RollView;
import game.TradeOffer;
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
                final AcceptTradeOverlay acceptOverlay= new AcceptTradeOverlay();
                WaitView waitView= new WaitView();
                DomesticTradeController domesticTradeController= new DomesticTradeController(domesticView,domesticOverlay,waitView,acceptOverlay);
                domesticView.setController(domesticTradeController);
                domesticOverlay.setController(domesticTradeController);
                acceptOverlay.setController(domesticTradeController);
                waitView.setController(domesticTradeController);
                
                DiscardView discardView = new DiscardView();
                final DiscardController discardController = new DiscardController(discardView, waitView);
                
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
//			
//			int state = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
                            
                            TradeOffer tradeOffer=new TradeOffer();
                            tradeOffer.setBrick(2);
                            tradeOffer.setOre(0);
                            tradeOffer.setSheep(-2);
                            tradeOffer.setWheat(-3);
                            tradeOffer.setWood(0);
                            tradeOffer.setSenderIndex(1);
                            tradeOffer.setReceiverIndex(2);
                            
                            acceptOverlay.addGetResource(ResourceType.BRICK, 2);
                            acceptOverlay.addGiveResource(ResourceType.SHEEP, 2);
                            acceptOverlay.addGiveResource(ResourceType.WHEAT, 3);
                            acceptOverlay.setPlayerName("frank");
                            acceptOverlay.setAcceptEnabled(false);
                            acceptOverlay.showModal();
//				
			}
		});
		this.add(testButton, BorderLayout.SOUTH);
                
                rollController = new RollController(new RollView(), new RollResultView());
//                discardController = new DiscardController(new DiscardView(), new WaitView());
	}
	
}

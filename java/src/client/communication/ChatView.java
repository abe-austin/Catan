package client.communication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.*;

import client.base.*;


/**
 * Chat view implementation
 */
@SuppressWarnings("serial")
public class ChatView extends PanelView implements IChatView {
	
	private LogComponent logPanel;
	private JScrollPane logScroll;
	
	public ChatView() {

		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);

		logPanel = new LogComponent();
		
		logScroll = new JScrollPane(logPanel);
		
		this.add(logScroll, BorderLayout.CENTER);
		
	}
	
	@Override
	public IChatController getController() {
		return (IChatController)super.getController();
	}

	@Override
	public void setEntries(final List<LogEntry> entries) {
		logPanel.setEntries(entries);
		JScrollBar vertical = logScroll.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
;	
	}
	
}


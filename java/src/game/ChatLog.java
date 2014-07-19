package game;

import client.parse.ParsedChat;
import java.util.ArrayList;

/**
 *
 * @author Kevin MacMaster
 */
public class ChatLog {
    private ArrayList<ParsedChat> lines;

    public ChatLog() {
        lines = new ArrayList<ParsedChat>();
    }

    /**
     * 
     * @param line String of chat to add to ChatChat
     */
    public void addChatLine(ParsedChat line) {
        getChatLines().add(line);
    }

    /**
     * @return the lines
     */
    public ArrayList<ParsedChat> getChatLines() {
        return lines;
    }
}

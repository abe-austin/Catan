package game;

import java.util.ArrayList;

/**
 *
 * @author Kevin MacMaster
 */
public class ChatLog {
    private ArrayList<String> lines;

    public ChatLog() {
        lines = new ArrayList<String>();
    }

    /**
     * 
     * @param line String of chat to add to ChatLog
     */
    public void addChatLine(String line) {
        getChatLines().add(line);
    }

    /**
     * @return the lines
     */
    public ArrayList<String> getChatLines() {
        return lines;
    }
}

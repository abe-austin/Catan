package shared.definitions;

/**
 *
 * @author Kevin MacMaster
 */
public class Command {
    private String source;
    private String command;

    public Command(String source, String command) {
        this.source= source;
        this.command = command;
    }

    public String getSource() {
        return source;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

}

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
        //System.out.println(command);
        if(command.contains("\\u0027")){
//            System.out.println("command.split(\\u0027)[0] "+command.split("\\u0027")[0] );
//            System.out.println("\'");
            command =command.replace("\\u0027", "\'");
            //System.out.println(command);
          //  System.out.println("command.split(\\u0027)[0] "+command.split("\\u0027")[1] );
            //command=command.split("\\u0027")[0]+"\'"+command.split("\\u0027")[1];
        }
        return command;
    }

}

package game;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.Command;

/**
 *
 * @author Kevin MacMaster
 */
public class GameHistoryTest {

    /**
     * Test of getCommands method, of class GameHistory.
     */
    @Test
    public void testGetCommands() {
        System.out.println("getCommands");
        GameHistory instance = new GameHistory();
        List expResult = new ArrayList<Command>();

        expResult.add(new Command("Command One"));
        instance.addCommand(new Command("Command One"));

        expResult.add(new Command("Command Two"));
        instance.addCommand(new Command("Command Two"));

        List result = instance.getCommands();
        assertEquals(expResult.get(1), result.get(1));
    }

}
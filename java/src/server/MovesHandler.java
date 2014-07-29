package server;

import shared.communication.ServerResponse;

/**
 *
 * @author Kevin MacMaster
 */
public class MovesHandler implements IHandler {

    @Override
    public ServerResponse handle(String command, Object Json) {
        if(!command.contains("/moves/"))
            return null;
        
        switch(command) {
            case "/moves/sendChat":
                   
            case "/moves/rollNumber":
                   
            case "/moves/robPlayer":
                   
            case "/moves/finishTurn":
                   
            case "/moves/buyDevCard":
                   
            case "/moves/Year_Of_Plenty":
                   
            case "/moves/Road_Building":
                  
            case "/moves/Soldier":
                   
            case "/moves/Monopoly":
                   
            case "/moves/Monument":
                   
            case "/moves/buildRoad":
                    
            case "/moves/buildSettlement":
                    
            case "/moves/buildCity":
                    
            case "/moves/offerTrade":
                    
            case "/moves/acceptTrade":
                    
            case "/moves/maritimeTrade":
                    
            case "/moves/discardCards":
        }
        return null;
    }
    
}

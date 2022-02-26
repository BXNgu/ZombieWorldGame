package game;

import edu.monash.fit2099.engine.*;

public class PickUpItemBehaviour implements Behaviour {

	/**
	 * An action to pick up weapon for Zombies.
	 */
    @Override
    public Action getAction(Actor actor, GameMap map) {
    	if(actor.getInventory().size() == 0) {
			for(Item item : map.locationOf(actor).getItems()) {
	            if(item != null && item.getDisplayChar() != '*' && item.getDisplayChar() != '%') {
	                    return item.getPickUpAction();
	            }
	        }
    	}
        return null;
    }
    
}

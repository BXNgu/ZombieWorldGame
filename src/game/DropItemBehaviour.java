package game;

import edu.monash.fit2099.engine.*;

public class DropItemBehaviour implements Behaviour {

	/**
	 * To drop item from a Zombie's inventory
	 */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for(Item item : actor.getInventory())
            if(item != null) {
                return item.getDropAction();
            }
        return null;
    }
}

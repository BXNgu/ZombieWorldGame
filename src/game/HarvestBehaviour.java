package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A Harvest Behaviour that implements Behaviour class to allow Harvesting for actors
 * @author Bing Xian
 *
 */
public class HarvestBehaviour implements Behaviour {
	
	private String[] directions = {"top-left","left","bottom-left","top","center","bottom", 
			"top-right", "right","bottom-right"};

	/**
	 * This method is used to harvest crop
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		int x = map.locationOf(actor).x();
		int y = map.locationOf(actor).y();
		int counter = 0;
		for (int i = -1; i < 2; i++ ) {
			for (int j = -1; j < 2; j++) {
				try {
					if (map.at(x+i,y+j).getGround().getDisplayChar() == 'r'){
						return new HarvestAction(map.locationOf(actor).getGround(),
								directions[counter],x+i,y+j);
					}
				} catch (Exception e) {
					
				}
				counter++;
			}
		}
		return null;
	}

}

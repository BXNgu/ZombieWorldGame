package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class representing an action to fertilise crop
 * @author Bing Xian
 *
 */
public class FertiliseAction extends Action {
	
	private Crop crop;
	
	/**
	 * Constructor
	 * @param crop	Crop object
	 */
	public FertiliseAction(Crop crop) {
		this.crop = crop;
	}

	/**
	 * This method is used to fertilise crop
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		this.crop.fertilise();
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilise crop";
	}

}

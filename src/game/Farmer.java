package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * A Farmer.
 * 
 * @author Bing Xian
 *
 */
public class Farmer extends Human {
	
	private Behaviour[] behaviours = {
			new EatingBehaviour(this.hitPoints,this.maxHitPoints),
			new SowingBehaviour(),
			new HarvestBehaviour(),
			new WanderBehaviour()
	};
		
	/**
	 * Constructor
	 * @param name	Farmer's name
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
	}
	
	/**
	 * Farmer will perform sowing crops, fertilize, harvest or wander around
	 * 
	 * @param actions    list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map        the map where the current Farmer's location
	 * @param display    the Display where the Farmer will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?
		behaviours[0] = new EatingBehaviour(this.hitPoints,this.maxHitPoints);
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}

}
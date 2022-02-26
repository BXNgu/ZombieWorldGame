package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class to lose the game
 * @author Wincent
 *
 */
public class EndGameAction extends Action{

	private Actor player;
	
	/**
	 * Constructor
	 * @param player Player 
	 */
	public EndGameAction(Actor player) {
		this.player = player;
	}
	
	/**
	 * To lose the game
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(player);
		return menuDescription(player);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Exit Game";
	}

}

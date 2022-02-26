package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This interface provides the ability to add methods to Action, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ActionInterface {
	
	public default Actions getNextActions(GameMap map) {return null;}
	
	public default Actions getFollowUpActions(GameMap map, Actor actor, Action lastAction) {return null;}
}

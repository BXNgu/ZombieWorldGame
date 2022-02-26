package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * BONUS FEATURE
 * A behaviour that lets Doctor to go back to hospital
 * @author Wincent
 *
 */
public class BackToHospitalBehaviour implements Behaviour {


	private Class<?> targetClass;
	private String targetName; 
	private int maxRange;
	private HashSet<Location> visitedLocations = new HashSet<Location>();
	
	/**
	 * Constructor
	 * @param cls A Class that the Doctor goes to
	 * @param range An integer representing the spaces between the Doctor and the Class
	 */
	public BackToHospitalBehaviour(Class<?> cls, int range) {
		this.targetClass = cls;
		this.targetName = targetClass.getSimpleName();
		this.maxRange = range;
	}
	
	/**
	 * To search for the Hospital
	 * @param actor
	 * @param here
	 * @return
	 */
	private Action searchHospital(Actor actor, Location here) {
		visitedLocations.clear();
		ArrayList<Location> now = new ArrayList<Location>();
		
		now.add(here);
		
		ArrayList<ArrayList<Location>> layer = new ArrayList<ArrayList<Location>>();
		layer.add(now);

		for (int i = 0; i<maxRange; i++) {
			layer = getNextLayer(actor, layer);
			Location there = search(layer);
			if (there != null)
				return there.getMoveAction(actor, "towards a " + targetName, null);
		}
		return null;
	}

	/**
	 * This uses a breadth-first search algorithm and is based on code written for the
	 * FIT2099 assignment in S2 2019 by Spike.
	 * 
	 * @param actor An Actor
	 * @param layer A List of list of location
	 * @return List of list of location
	 */
	private ArrayList<ArrayList<Location>> getNextLayer(Actor actor, ArrayList<ArrayList<Location>> layer) {
		ArrayList<ArrayList<Location>> nextLayer = new ArrayList<ArrayList<Location>>();

		for (ArrayList<Location> path : layer) {
			List<Exit> exits = new ArrayList<Exit>(path.get(path.size() - 1).getExits());
			Collections.shuffle(exits);
			for (Exit exit : path.get(path.size() - 1).getExits()) {
				Location destination = exit.getDestination();
				if (!destination.getGround().canActorEnter(actor) || visitedLocations.contains(destination))
					continue;
				visitedLocations.add(destination);
				ArrayList<Location> newPath = new ArrayList<Location>(path);
				newPath.add(destination);
				nextLayer.add(newPath);
			}
		}
		return nextLayer;
	}
	
	/**
	 * 
	 * @param layer List of list of location
	 * @return a location or null
	 */
	private Location search(ArrayList<ArrayList<Location>> layer) {

		for (ArrayList<Location> path : layer) {
			if (containsHospital(path.get(path.size() - 1))) {
				return path.get(1);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param here A location
	 * @return a boolean
	 */
	private boolean containsHospital(Location here) {
		return (here.getGround().getDisplayChar() == '^' &&
				targetClass.isInstance(here.getGround()));
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		int kitCounter = 0;
		for (Item item : actor.getInventory()) {
			if (item.getDisplayChar() == '$') {
				kitCounter++;
			}
		}
		if (kitCounter == 0) {
			return searchHospital(actor, map.locationOf(actor));
		} else {
			return null;
		}
	}

}

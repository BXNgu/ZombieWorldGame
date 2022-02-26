package game;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * A class to add new features to the World class
 * @author Wincent
 *
 */
public class NewWorld extends World{
	
	private static boolean mamboMariePresent = false;
	private static MamboMarie[] mamboMarie = {null};
	private int mamboMarieHitPoint = 150;
	private static int mamboMarieCounter = -1;
	private static boolean mamboMarieAlive = true;
	
	private boolean endGame = false;
	
	Random random = new Random();
	
	/**
	 * Constructor
	 */
	public NewWorld(Display display) {
		super(display);
	}
	
	/**
	 * To check that human and zombie exist both map
	 */
	@Override
	protected boolean stillRunning() {
		boolean zombieExist = false;
		boolean humanExist = false;	
		
		
		for (Actor actor : actorLocations) {
			if (actor.hasCapability(ZombieCapability.UNDEAD) || mamboMarieAlive) {
				zombieExist = true;
			}

			if (actor.hasCapability(ZombieCapability.ALIVE) && actor != player) {
				humanExist = true;
			}
		}
		
		if (!humanExist) {
			if (!endGame) {
				System.out.println("All Human is dead");
			}
			
			endGame = true;
			return false;
		}
		
		if (!actorLocations.contains(player)) {
			endGame = true;
			return false;
		}
		
		if (!zombieExist) {
			if (!endGame) {
				System.out.println("You won the game!");
			}
			endGame = true;
			return false;
		}
		return actorLocations.contains(player);
	}
	
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}
			castMamboMarie();

		}
		display.println(endGameMessage());

	}
	
	/**
	 * To summon MamboMarie
	 */
	public void castMamboMarie() {
		double probability = Math.random();
		try {
			GameMap map = super.actorLocations.locationOf(player).map();
			int[] possibleX = {0, map.getXRange().max()};
			int[] possibleY = {0, map.getYRange().max()};
			if (mamboMarieAlive) {
				if (probability <= 0.05 && !mamboMariePresent && mamboMarie[0] == null) {
					int positionX = random.nextInt(2);
					int positionY = random.nextInt(2);
					mamboMarie[0] = new MamboMarie(mamboMarieHitPoint);
					map.at(possibleX[positionX], possibleY[positionY]).addActor(mamboMarie[0]);
					mamboMariePresent = true;
				}
			}
			if (!mamboMarie[0].isConscious()) {
				mamboMarieAlive = false;
			}
			if (mamboMariePresent) {
				mamboMarieCounter++;
			}
			if (mamboMarieCounter == 30) {
				System.out.println("Mambo Marie vanished");
				map.removeActor(mamboMarie[0]);
				mamboMarieHitPoint = mamboMarie[0].getHitPoint();
				mamboMarieCounter = -1;
				mamboMarie[0] = null;
				mamboMariePresent = false;
			}
		} catch (Exception e) {
		}
	
	}

}

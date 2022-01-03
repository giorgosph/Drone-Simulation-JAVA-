package DroneSimulation;

import java.util.Random;

/**
 * @author gphil
 */
public enum Direction {
	North, East, South, West;

	/**
	 * 
	 * @return a random direction
	 */
	public static Direction getRandomDirection() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

	/**
	 * 
	 * @param d current direction
	 * @return next direction
	 */
	public static Direction nextDirection(Direction d) {
		switch (d) {
		case North:
			d = East;
			break;
		case East:
			d = South;
			break;
		case South:
			d = West;
			break;
		case West:
			d = North;
			break;
		}
		return d;
	}
}

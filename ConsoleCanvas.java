package DroneSimulation;

/**
 * @author gphil(28000050)
 */
public class ConsoleCanvas {
	private int x, y;
	static char[][] area;
	private static char d1;

	/**
	 * Constructor Creates the Canvas with given values
	 * 
	 * @param a x-dimension
	 * @param b y-dimension
	 */
	ConsoleCanvas(int a, int b) {
		x = a;
		y = b;

		area = new char[y][x];

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				area[0][j] = '#';
				area[y - 1][j] = '#';
			}
			area[i][0] = '#';
			area[i][x - 1] = '#';
		}
	}

	/**
	 * Displays the Drone
	 * 
	 * @param dx
	 * @param dy
	 * @param d
	 */
	void showIt(int dx, int dy, char d) {
		d1 = d;
		if ((dx >= x - 1 || dx == 0) || (dy >= y - 1 || dy == 0)) {
			System.out.println("The drone " + d1 + " is out of the ring");
		} else {
			area[dy][dx] = d1; // prints them opposite
		}
	}

	public String toString() {
		String res = "";

		for (int i = 0; i < y; ++i) {
			for (int j = 0; j < x; ++j) {
				res = res + area[i][j];
			}
			res = res + "\n";
		}
		return res;
	}

	public static void main(String[] args) {
		ConsoleCanvas c = new ConsoleCanvas(20, 10); // create a canvas
		c.showIt(0, 0, 'D'); // add a Drone at 4,3
		c.showIt(9, 13, 'A');
		c.showIt(7, 10, 'B');
		c.showIt(4, 3, 'C');
		System.out.println(c.toString()); // display result
	}
}
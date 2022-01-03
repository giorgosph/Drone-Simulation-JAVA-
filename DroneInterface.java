package DroneSimulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import Week2Task2.stringSplitter;

/**
 * @author gphil(28000050)
 */
public class DroneInterface {
	private int arx=20, ary=10;
	private int xd, yd;
	stringSplitter sbp;
	String data;
	String[] Data;
	int[] num, dInfo;
	JFileChooser selection = new JFileChooser(
			"C:\\Users\\gphil\\OneDrive\\Desktop\\CS\\CS-y2\\Java\\Autumn Term\\Pracicals\\week 5");//Directory to save/load files on my pc
	private Scanner s; // scanner used for input from user
	private DroneArena myArena; // arena in which drones are shown
	private ConsoleCanvas myCanvas;
	
	/**
	 * constructor for DroneInterface sets up scanner used for input and the arena
	 * then has main loop allowing user to enter commands
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public DroneInterface() throws InterruptedException, IOException {
		s = new Scanner(System.in); // set up scanner for user input
		myArena = new DroneArena(arx, ary); // create arena of size 20*6

		char ch = ' ';
		do {
			System.out.print(
					"Enter (N)ew Arena, (A)dd drone, get (I)nformation, (D)isplay, (M)ove, (T)ravel 10 positions, (S)ave to file, (L)oad from file or e(X)it > ");
			ch = s.next().charAt(0);
			s.nextLine();
			switch (ch) {
			case 'N':
			case 'n':
				System.out.println("please insert the dimension for x-axis");
				arx = s.nextInt();
				System.out.println("please insert the dimension for y-axis");
				ary = s.nextInt();
				myArena = new DroneArena(arx, ary);
				myCanvas = new ConsoleCanvas(arx, ary);
				break;

			case 'A':
			case 'a':
				myArena.addDrone(); // add a new drone to arena
				break;
			case 'I':
			case 'i':
				System.out.print(myArena.toString() + "\n");
				break;
			case 'D':
			case 'd':
				doDisplay();
				break;
			case 'M':
			case 'm':
				DroneArena.moveAllDrones();
				doDisplay();
				break;
			case 'T':
			case 't':
				for (int i = 0; i < 10; i++) {
					DroneArena.moveAllDrones();
					doDisplay();
					System.out.print(myArena.toString() + "\n");
					Thread.sleep(200);
				}
				break;
			case 'S':
			case 's':
				saveFile(); // add a new drone to arena
				break;
			case 'L':
			case 'l':
				openFile(); // add a new drone to arena
				break;

			case 'x':
				ch = 'X'; // when X detected program ends
				break;
			}
		} while (ch != 'X'); // test if end

		s.close(); // close scanner
	}

	/**
	 * Display the drone arena on the console
	 */
	void doDisplay() {
		myCanvas = new ConsoleCanvas(arx, ary); // hence create a suitable sized ConsoleCanvas object
		myArena.showDrones(myCanvas); // call showDrones suitably
		System.out.print(myCanvas.toString() + "\n"); // then use the ConsoleCanvas.toString method
	}
	
	/**
	 * Filter that shows the user only .txt files
	 */
	FileFilter filter = new FileFilter() {

		@Override
		public boolean accept(File f) {
			if (f.getAbsolutePath().endsWith(".txt"))
				return true;
			if (f.isDirectory())
				return true;
			else
				return false;
		}

		@Override
		public String getDescription() {
			return "txt";
		}

	};

	/**
	 * Load informations form a .txt file
	 * 
	 * @throws FileNotFoundException
	 */
	public void openFile() throws FileNotFoundException {
		data = "";
		selection.setFileFilter(filter);
		int returnVal = selection.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selFile = selection.getSelectedFile();
			System.out.println("You opened this file: " + selFile.getName());
			if (selFile.isFile()) {
				@SuppressWarnings("resource")
				Scanner in = new Scanner(selFile);
				while (in.hasNext()) {
					data += in.nextLine();
					sbp = new stringSplitter(data, " ");
					Data = sbp.getStrings();
				}
			}
			num = new int[Data.length];
			for (int i = 0; i < Data.length; i++) {
				try {
					num[i] = Integer.parseInt(Data[i]);
					// System.out.println("int: " + Data[i]);
				} catch (NumberFormatException e) {
					// System.out.println("not an int");
				}
			}
			arx = num[2];
			ary = num[4];
			myArena = new DroneArena(arx, ary);
			myCanvas = new ConsoleCanvas(arx, ary);
			int k = 0;
			for (int i = 5; i < num.length; i++) {
				if (num[i] != 0) {
					k++;
				}
			}
			dInfo = new int[k];
			k = 0;
			for (int i = 5; i < num.length; i++) {
				if (num[i] != 0) {
					dInfo[k] = num[i];
					k++;
				}
			}
			for (int i = 0; i < dInfo.length; i++) {
				k = i % 3;
				switch (k) {
				case 0:
					break;
				case 1:
					xd = dInfo[i];
					break;
				case 2:
					yd = dInfo[i];
					myArena.inputDrone(xd, yd);
					break;
				}
			}
		}
	}

	/**
	 * Saves information into a .txt file
	 * 
	 * @throws IOException
	 */
	public void saveFile() throws IOException {

		int returnVal = selection.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File selFile = selection.getSelectedFile();
			File currdir = selection.getCurrentDirectory();
			System.out.println("You chose to save into this file: " + selFile.getName() + "in the directory: "
					+ currdir.getAbsolutePath());
			try (FileWriter fw = new FileWriter(selection.getSelectedFile() + ".txt")) {
				fw.write(myArena.toString());
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		@SuppressWarnings("unused")
		DroneInterface r = new DroneInterface(); // just call the interface
	}

}

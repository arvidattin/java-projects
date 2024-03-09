package battleShip;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.border.LineBorder;

// import Gameboard.PlaceButton;

import java.awt.event.ActionListener;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.Random;
import java.awt.event.ActionEvent;

/** class Gamebooard: creates game grid, ships and contains the methods for PvP and PvC*/
public class Gamebooard {

	HashSet<Integer> usedPositions = new HashSet<Integer>();
	HashSet<Integer> usedPositionsNGBH = new HashSet<Integer>();
	HashSet<Integer> usedNumbers = new HashSet<Integer>();
	ArrayList<Integer> orderedPositions = new ArrayList<Integer>();

	/* used in the PvC method */
	ArrayList<Integer> pvcRandom = new ArrayList<Integer>();
	HashSet<Integer> pvcRandomCheck = new HashSet<Integer>();

	/* playerA ship */
	HashSet<Integer> ship5A = new HashSet<Integer>();
	HashSet<Integer> ship4A = new HashSet<Integer>();
	HashSet<Integer> ship3firstA = new HashSet<Integer>();
	HashSet<Integer> ship3secA = new HashSet<Integer>();
	HashSet<Integer> ship2A = new HashSet<Integer>();

	/* playerB ship */
	HashSet<Integer> ship5B = new HashSet<Integer>();
	HashSet<Integer> ship4B = new HashSet<Integer>();
	HashSet<Integer> ship3firstB = new HashSet<Integer>();
	HashSet<Integer> ship3secB = new HashSet<Integer>();
	HashSet<Integer> ship2B = new HashSet<Integer>();

	JFrame frame;

	JPanel pA;
	JPanel pB;

	JPanel container;
	JPanel topPanel;
	JPanel botPanel;
	PlaceButton buttonsPA[] = new PlaceButton[100];
	PlaceButton buttonsPB[] = new PlaceButton[100];

	int[] shipLength = { 4, 3, 2, 2, 1 };
	int[] pos;
	int antalTräffarPA = 0;
	int antalMissarPA = 0;
	int antalTräffarPB = 0;
	int antalMissarPB = 0;
	int antalTräffarPC = 0;
	int antalMissarPC = 0;
	int antalLivPA = 17;
	int antalLivPB = 17;
	int antalLivPC = 17;
	int winner = 0;
	int click = 0;
	private int rand;

	JLabel labelA;
	JLabel labelB;
	JLabel labelC;
	JLabel labelD;

	JButton botPanelButton1;
	JButton botPanelButton2;
	JButton botPanelButton3;
	JButton botPanelButton4;
	JButton botPanelButton5;

	boolean playerAturn = true;
	boolean playerBturn = false;
	boolean computerTurn = false;
	boolean play = false;
	boolean createShipA = false;
	boolean pvc = false;
	boolean randBolean;

	Random random = new Random();

	ImageIcon Ship = new ImageIcon(this.getClass().getResource("Ship.png"));
	ImageIcon Water = new ImageIcon(this.getClass().getResource("Water.png"));
	ImageIcon Hit = new ImageIcon(this.getClass().getResource("Hit.png"));
	ImageIcon Miss = new ImageIcon(this.getClass().getResource("Miss.png"));

	public Gamebooard() {

		guiGameboard();

	}

	public void guiGameboard() {

		frame = new JFrame("Battleship");
		frame.setSize(800, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		topPanel = new JPanel();
		botPanel = new JPanel();

		container = new JPanel();

		pA = new JPanel();
		pB = new JPanel();

		botPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 30));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		topPanel.setLayout(new GridLayout(1, 2));
		botPanel.setLayout(new GridLayout(1, 5));

		topPanel.setBackground(Color.WHITE);
		botPanel.setBackground(Color.WHITE);
		container.setLayout(new GridLayout(1, 2));

		pA.setLayout(new GridLayout(10, 10));
		pB.setLayout(new GridLayout(10, 10));

		Font f1 = new Font(Font.SERIF, Font.BOLD, 15);
		Font f2 = new Font(Font.SERIF, Font.PLAIN, 14);

		labelA = new JLabel("      " + "Player A");
		labelA.setFont(f1);
		labelB = new JLabel("Player A place your ships");
		labelB.setFont(f2);
		labelC = new JLabel("      " + "Player B");
		labelC.setFont(f1);
		labelD = new JLabel("Player B place your ships");
		labelD.setFont(f2);

		labelD.setVisible(false);
		labelB.setVisible(false);

		botPanelButton1 = new JButton("Randomize");
		botPanelButton2 = new JButton("Finished");

		botPanelButton3 = new JButton("Randomize");
		botPanelButton4 = new JButton("Finished");

		botPanelButton5 = new JButton("Play");

		topPanel.add(labelA, BorderLayout.CENTER);
		topPanel.add(labelB, BorderLayout.WEST);
		topPanel.add(labelC, BorderLayout.CENTER);
		topPanel.add(labelD, BorderLayout.CENTER);

		for (int i = 0; i < 100; i++) {

			/* Gridlayout player A */
			buttonsPA[i] = new PlaceButton(i, 0, this);
			buttonsPA[i].setIcon(Water);
			buttonsPA[i].setBorder(new LineBorder(Color.BLACK));
			pA.add(buttonsPA[i]);

			/* Gridlayout player B */
			buttonsPB[i] = new PlaceButton(i, 0, this);
			buttonsPB[i].setIcon(Water);
			buttonsPB[i].setBorder(new LineBorder(Color.BLACK));
			pB.add(buttonsPB[i]);
			
			/* PC coordinates: is used in random PC shooting*/
			pvcRandom.add(i);

		}

		botPanel.add(botPanelButton1, BorderLayout.EAST);
		botPanel.add(botPanelButton2, BorderLayout.EAST);
		botPanel.add(botPanelButton5, BorderLayout.CENTER);

		botPanel.add(botPanelButton3, BorderLayout.CENTER);
		botPanel.add(botPanelButton4, BorderLayout.CENTER);

		container.add(pA);
		container.add(pB);

		/** ads the panel to our frame */
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(botPanel, BorderLayout.SOUTH);

		botPanelButton5.setVisible(play);

		botPanelButton1.setVisible(true);
		botPanelButton2.setVisible(true);
		botPanelButton3.setVisible(true);
		botPanelButton4.setVisible(true);
		botPanelButton5.setVisible(false);

		frame.add(container);
		frame.setVisible(true);

		ButtonRandomizeA();
		ButtonFinishedA();
		ButtonRandomizeB();
		ButtonFinishedB();

		ButtonPlay();

	}

	/** Button PvP */
	public void getButtonsPvP() {

		labelB.setVisible(true);
		labelD.setVisible(true);

	}

	/** Button PvC */
	public void getButtonsPvC() {
		labelC.setText("SkyNet");
		pvc = true;
		computerTurn = true;
		labelB.setVisible(true);

	}

	/** Button randomise A-Grid */
	public void ButtonRandomizeA() {

		botPanelButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Every time we push the randomise button we want to create a new ship placement
				 */
				createShipA = true;
				RandomizePlacement(buttonsPA);

			}

		});
	}

	/** Button Finished A-Grid */
	public void ButtonFinishedA() {

		botPanelButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/* hides icons of ships after the player presses the finished button */
				hideIcons(buttonsPA);
				botPanelButton1.setVisible(false);
				botPanelButton2.setVisible(false);
				labelD.setVisible(true);
				labelB.setVisible(true);

				click++;
				if (click == 2) {
					botPanelButton5.setVisible(true);
				}

			}

		});

	}

	/** Button Randomise B-Grid */
	public void ButtonRandomizeB() {

		botPanelButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/* we use this true and false statement to be sure that the ships for the players are not being written over*/
				createShipA = false; 
				if (pvc) {
					RandomizePlacement(buttonsPB);
					hideIcons(buttonsPB);

					labelD.setText("Computer is finished");
					labelD.setVisible(true);
				} else {
					RandomizePlacement(buttonsPB);
				}

			}

		});
	}

	/** Button Finished B-Grid */
	public void ButtonFinishedB() {

		botPanelButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				labelB.setText("Player A select your target");
				labelB.setVisible(true);
				showTargets(buttonsPB);
				hideIcons(buttonsPB);
				botPanelButton3.setVisible(false);
				botPanelButton4.setVisible(false);

				click++;
				if (click == 2) {
					botPanelButton5.setVisible(true);

				}

			}

		});
	}

	/** Button Play: only visible when both players are finished */
	public void ButtonPlay() {

		botPanelButton5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (pvc) {
					pA.setVisible(true);
				} else {
					pA.setVisible(false);
				}
				play = true;
				botPanelButton5.setVisible(false);

			}

		});
	}
	/** Method used to notify when a button in the grid is being pressed*/
	public void notify(int coordinates, int buttonValue) {

		/* play: when play button is being pressed the grid will be reachable */
		if (play) {

			if (playerAturn) {
				playerAturn(coordinates, buttonValue);

			} else if (playerBturn) {
				playerBturn(coordinates, buttonValue);

			}

		}

	}

	/** Random placement of ships in the grid (recursive)*/

	public void RandomizePlacement(PlaceButton[] pbArray) {
		int firstRandom;
		int random;
		for (int i = 0; i < 100; i++) {

			usedPositions.clear();
			usedPositionsNGBH.clear();

			orderedPositions.clear();

			pbArray[i].setIcon(Water);
			pbArray[i].setButtonValue(0);
		}
		/* clears the other player's ships if randomise button is pushed */
		if (createShipA) {
			ship2A.clear();
			ship3firstA.clear();
			ship3secA.clear();
			ship4A.clear();
			ship5A.clear();
			pvcRandom.clear();
		} else {
			ship2B.clear();
			ship3firstB.clear();
			ship3secB.clear();
			ship4B.clear();
			ship5B.clear();

		}
		for (int i = 0; i < 5; i++) {

			random = RandomizeNumber();

			/*
			 * check if the random starting point is already taken 
			 */
			if (!(usedNumbers.contains(random))) {

				usedNumbers.add(random);
				pbArray[random].setIcon(Ship);

				pbArray[random].setButtonValue(1);
				firstRandom = pbArray[random].getCoordinates(); 
				Expand(firstRandom, shipLength[i], pbArray);

			} else {

				random = RandomizeNumber();
				usedNumbers.add(random); // if to values are the same: creates a new random number
				pbArray[random].setIcon(Ship);
				pbArray[random].setButtonValue(1);
				firstRandom = pbArray[random].getCoordinates(); 
				Expand(firstRandom, shipLength[i], pbArray);

			}

		}
		/* Saves the ship coordinates for each ship*/
		if (createShipA) {
			for (int i = 0; i < 17; i++) {
				pvcRandom.add(orderedPositions.get(i)); 
				if (i < 5) {
					ship5A.add(orderedPositions.get(i));
				} else if (i < 9) {
					ship4A.add(orderedPositions.get(i));
				} else if (i < 12) {
					ship3firstA.add(orderedPositions.get(i));
				} else if (i < 15) {
					ship3secA.add(orderedPositions.get(i));
				} else {
					ship2A.add(orderedPositions.get(i));
				}
			}
		} else {
			for (int i = 0; i < 17; i++) {

				if (i < 5) {
					ship5B.add(orderedPositions.get(i));
				} else if (i < 9) {
					ship4B.add(orderedPositions.get(i));
				} else if (i < 12) {
					ship3firstB.add(orderedPositions.get(i));
				} else if (i < 15) {
					ship3secB.add(orderedPositions.get(i));
				} else {
					ship2B.add(orderedPositions.get(i));
				}
			}
		}

		/*
		 * Varje skeppcell får en koordinat när den placeras, och om två placeras på
		 * samma ruta så läggs inte dubletterna in i HashSetet, och vi vet då att allt
		 * annat än maxtalet (17) har någon dublett. [...]NGBH lägger till alla
		 * grannceller också.
		 */
		if (usedPositions.size() >= 17 && usedPositionsNGBH.size() > 45) {

		} else {
			try {
				RandomizePlacement(pbArray);
			} catch (StackOverflowError e) {
				RandomizePlacement(pbArray);
			}
		}
	}

	/** Method for random coordinates for the ships */
	public int RandomizeNumber() {

		rand = random.nextInt(99);

		usedPositions.add(rand);
		orderedPositions.add(rand);

		return rand;
	}

	/**
	 * Method for expanding the ships with random horizontal or vertical placements
	 */
	public void Expand(int R, int shipLength, PlaceButton[] pbArray) {

		randBolean = random.nextBoolean();
		pos = new int[shipLength];
		
		int gridBoarder = R - (R / 10) * 10; // Boarder placement
		
		int A = R;
		int B = R;
		int upper = gridBoarder; // upper boarder placement
		
		int D = R;
		int E = R;
		int left = gridBoarder; //left boarder placement
		int right = gridBoarder; // right boarder placement
		
		int counter = 0;
		
		/* (calculated by hand) calculation to set ships inside the boarder of the grid. */
		if (randBolean) {
			while ((B < 100 || A > upper) && counter < shipLength) {
				if (A > upper) {

					A = A - 10;

					pos[counter] = A;
					usedPositions.add(A);
					orderedPositions.add(A);
					pbArray[A].setButtonValue(1);
					CheckNeighbours(A);
					counter++;

				} else {

					B = B + 10;

					pos[counter] = B;
					usedPositions.add(B);
					orderedPositions.add(B);
					pbArray[B].setButtonValue(1);
					CheckNeighbours(B);
					counter++;
				}
			}
		} else {

			while ((left > 0 || right < 9) && counter < shipLength) {
				if (left > 0) {
					left--;
					D = D - 1;

					pos[counter] = D;
					usedPositions.add(D);
					orderedPositions.add(D);
					pbArray[D].setButtonValue(1);
					CheckNeighbours(D);
					counter++;
				} else {
					right++;
					E = E + 1;

					pos[counter] = E;
					usedPositions.add(E);
					orderedPositions.add(E);
					pbArray[E].setButtonValue(1);
					CheckNeighbours(E);
					counter++;
				}
			}
		}
		/* set the icon to ship on each ship coordinate*/
		for (int i = 0; i < shipLength; i++) {

			if (pbArray[pos[i]].getButtonValue() == 0) {
				pbArray[pos[i]].setIcon(Ship);

			} else {

				pbArray[pos[i]].setIcon(Ship);
			}

		}

	}
	/** ad all the neighboring shipsCell coordinates to minimise the risk for ship placement impact*/
	public void CheckNeighbours(int B) {

		usedPositionsNGBH.add(B + 10);
		usedPositionsNGBH.add(B - 10);
		usedPositionsNGBH.add(B - 1);
		usedPositionsNGBH.add(B + 1);

	}
	/** Hides all the ship icons on the grid*/
	public void hideIcons(PlaceButton[] pbArray) {

		for (int i = 0; i < 100; i++) {

			pbArray[i].setIcon(Water);

		}

	}

	/** Shows ship icons on the grid */
	public void showIcons(PlaceButton[] pbArray) {

		for (int i = 0; i < 100; i++) {

			if (pbArray[i].getButtonValue() == 1) {
				pbArray[i].setIcon(Ship);

			}

		}

	}

	/** Shows fireGrid for the player. Creates a red border around enemy cells */
	public void showTargets(PlaceButton[] pbArray) {

		for (int i = 0; i < 100; i++) {

			pbArray[i].setContentAreaFilled(false);
			pbArray[i].setBorder(new LineBorder(Color.RED));
		}

	}

	/**
	 * Shows a non fireGrid for the player. Creates a black border around the cells
	 */
	public void hideTargets(PlaceButton[] pbArray) {

		for (int i = 0; i < 100; i++) {

			pbArray[i].setContentAreaFilled(false);
			pbArray[i].setBorder(new LineBorder(Color.BLACK));
		}

	}

	/** Method for playerB turn */
	public boolean playerBturn(int coordinate, int hit) {

		pA.setVisible(true);
		if (hit == 1) {

			antalTräffarPB++;
			labelD.setText("Träff: " + antalTräffarPB);
			labelD.setForeground(Color.GREEN);
			buttonsPA[coordinate].setIcon(Hit);
			antalLivPA--;
			if (antalLivPA == 0) {
				winner = antalMissarPB;
				Algo.algoMethod(winner);
				labelD.setForeground(Color.BLACK);
				labelB.setForeground(Color.RED);
				labelB.setText("Game over! Player B has won");
				labelD.setText("You have won!");
				play = false; 

			}
			/* remove player A:s ship coordinate if hit */
			else if (ship5A.contains(coordinate)) {
				ship5A.remove(coordinate);

				if (ship5A.size() == 0) {
					System.out.println("skepp 5A sänkt!");
				}
			} else if (ship4A.contains(coordinate)) {
				ship4A.remove(coordinate);

				if (ship4A.size() == 0) {
					System.out.println("skepp 4A sänkt!");
				}
			} else if (ship3firstA.contains(coordinate)) {
				ship3firstA.remove(coordinate);

				if (ship3firstA.size() == 0) {
					System.out.println("skepp 3A sänkt!");
				}
			} else if (ship3secA.contains(coordinate)) {
				ship3secA.remove(coordinate);

				if (ship3secA.size() == 0) {
					System.out.println("skepp 3A sänkt!");
				}
			} else {
				ship2A.remove(coordinate);

				if (ship2A.size() == 0) {
					System.out.println("skepp 2A sänkt!");
				}
			}

			return playerBturn;

		} else {

			buttonsPA[coordinate].setIcon(Miss);

			pB.setVisible(true);
			pA.setVisible(false);

			hideTargets(buttonsPA);
			showTargets(buttonsPB);
			antalMissarPB++;
			labelD.setText("Miss: " + antalMissarPB);
			labelD.setForeground(Color.RED);
			wait(150);
			playerAturn = true;
			playerBturn = false;
			return playerBturn;

		}
	}

	/**
	 * Method for playerA turn
	 */
	public boolean playerAturn(int coordinate, int hit) {

		pB.setVisible(true);
		if (hit == 1) {
			antalTräffarPA++;
			labelB.setText("Träff: " + antalTräffarPA);
			labelB.setForeground(Color.GREEN);
			buttonsPB[coordinate].setIcon(Hit);
			antalLivPB--;
			antalLivPC--;
			if (antalLivPB == 0 || antalLivPC == 0) {
				winner = antalMissarPA;
				Algo.algoMethod(winner);
				labelD.setForeground(Color.RED);
				labelB.setForeground(Color.BLACK);
				labelA.setText("Game over! Player A has won");
				labelB.setText("You have won!");
				play = false; 

			}
			/* remove player B:s ship coordinate if hit */
			else if (ship5B.contains(coordinate)) {
				ship5B.remove(coordinate);

				if (ship5B.size() == 0) {
					System.out.println("skepp 5B sänkt!");
				}
			} else if (ship4B.contains(coordinate)) {
				ship4B.remove(coordinate);

				if (ship4B.size() == 0) {
					System.out.println("skepp 4B sänkt!");
				}
			} else if (ship3firstB.contains(coordinate)) {
				ship3firstB.remove(coordinate);

				if (ship3firstB.size() == 0) {
					System.out.println("skepp 3B sänkt!");
				}
			} else if (ship3secB.contains(coordinate)) {
				ship3secB.remove(coordinate);

				if (ship3secB.size() == 0) {
					System.out.println("skepp 3B sänkt!");
				}
			} else {
				ship2B.remove(coordinate);

				if (ship2B.size() == 0) {
					System.out.println("skepp 2B sänkt!");
				}
			}

			return playerAturn;
		} else {
			buttonsPB[coordinate].setIcon(Miss);

			pB.setVisible(false);
			pA.setVisible(true);
			buttonsPB[coordinate].setIcon(Miss);
			hideTargets(buttonsPB);
			showTargets(buttonsPA);
			antalMissarPA++;
			labelB.setText("Miss: " + antalMissarPA);
			labelB.setForeground(Color.RED);
			wait(150);
			if (!computerTurn) {
				playerAturn = false;
				playerBturn = true;
			} else {
				playerAturn = false;
				playerBturn = false;
				pvC(computerTurn);
			}
			return playerAturn;

		}

	}

	/**
	 * Method for Skynets turn
	 */
	public boolean pvC(boolean computerTurn) {

		wait(500);
		while (computerTurn) {

			rand = random.nextInt(99);

			if (antalLivPA == 0) {
				winner = antalMissarPC;
				Algo.algoMethod(winner);
				labelD.setForeground(Color.BLACK);
				labelB.setForeground(Color.RED);
				labelB.setText("Game over! Skynet has won");
				labelA.setText("You have won!");
				play = false; 
				computerTurn = false;

			}

			/* if the random number does not exists  */
			else if (!pvcRandomCheck.contains(rand) && pvcRandom.contains(rand)) {

				antalTräffarPC++;

				labelD.setText("Träff: " + antalTräffarPC);
				labelD.setForeground(Color.GREEN);

				buttonsPA[rand].setIcon(Hit);

				pvcRandomCheck.add(rand); // add random element

				if (ship5A.contains(rand)) {
					ship5A.remove(rand);
					if (ship5A.size() == 0) {
						System.out.println("skepp 5A sänkt!");
					}
				} else if (ship4A.contains(rand)) {
					ship4A.remove(rand);

					if (ship4A.size() == 0) {
						System.out.println("skepp 4A sänkt!");
					}
				} else if (ship3firstA.contains(rand)) {
					ship3firstA.remove(rand);

					if (ship3firstA.size() == 0) {
						System.out.println("skepp 3A sänkt!");
					}
				} else if (ship3secA.contains(rand)) {
					ship3secA.remove(rand);

					if (ship3secA.size() == 0) {
						System.out.println("skepp 3A sänkt!");
					}
				} else if (ship2A.contains(rand)) {
					ship2A.remove(rand);

					if (ship2A.size() == 0) {
						System.out.println("skepp 2A sänkt!");
					}
				}

			} else if (pvcRandomCheck.contains(rand)) { // if the random value exists, take a new number from the set  

				rand = random.nextInt(99);
			} else {

				antalMissarPC++;
				labelD.setText("Miss: " + antalMissarPC);
				labelD.setForeground(Color.RED);

				computerTurn = false;
				playerAturn = true;
				playerBturn = false;

				pB.setVisible(true);
				pA.setVisible(true);
				buttonsPA[rand].setIcon(Miss);
				hideTargets(buttonsPA);
				showTargets(buttonsPB);
			}

		}
		return playerAturn;
	}
	/** wait method: argument in millisecond*/
	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}

package battleShip;

import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** class GUI: creates the main board and main buttons */
public class GUI {

	private JButton HTPlay;
	private JButton Score;
	private JButton PC;
	private JButton local;
	private JPanel bakgrund;
	private JLabel label1;
	private JFrame menu;

	private Gamebooard gameboard;

	public GUI() {

		menu = new JFrame("Battleships");
		menu.setBackground(Color.gray);
		menu.setSize(300, 430);
		menu.setVisible(true);
		menu.setLayout(null);
		menu.setResizable(false);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label1 = new JLabel();
		label1.setText("Welcome to Battleships!");

		bakgrund = new JPanel();
		bakgrund.setSize(300, 430);
		bakgrund.setBackground(Color.gray);

		HTPlay = new JButton("How To Play");
		HTPlay.setBounds(76, 100, 150, 40);

		Score = new JButton("Highscore");
		Score.setBounds(76, 310, 150, 40);

		PC = new JButton("Play against PC");
		PC.setBounds(76, 170, 150, 40);

		local = new JButton("Play local");
		local.setBounds(76, 240, 150, 40);

		menu.add(HTPlay);
		menu.add(PC);
		menu.add(local);
		menu.add(Score);
		menu.add(label1);
		menu.add(bakgrund);
		bakgrund.add(label1);

		menuBoard();

	}

	/** collection of buttons in the main menu */
	public void menuBoard() {

		howTo();
		highScore();
		pc();
		local();
	}

	/** GUI-scoreboard HowToPlay button */
	public void howTo() {

		HTPlay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFrame htp = new JFrame("How To Play");

				JPanel image = new JPanel();
				image.setSize(705, 320);
				image.setLayout(new FlowLayout());
				JLabel picLabel = new JLabel(
						new ImageIcon("/home/danan623/eclipse-workspace/gitLabb/src/battleShip/HowToPlay.jpg"));

				image.add(picLabel);
				htp.add(image);
				htp.setVisible(true);
				htp.setSize(705, 420);
				htp.setResizable(false);

			}

		});

	}

	/** GUI-scoreboard HighScore button */
	public void highScore() {

		Score.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					highScore2();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

	}
	
	/** Reads the best player statistics from latest games */
	public void highScore2() throws IOException {

		
		BufferedReader reader = new BufferedReader(
				new FileReader("/home/danan623/eclipse-workspace/gitLabb/src/battleShip/score.txt"));

		String[] line = new String[10];
		int i = 0;
		while (reader.ready()) {
			line[i] = reader.readLine();
			i++;
		}

		JFrame HS = new JFrame("Highscore");
		HS.setSize(400, 470);
		HS.setVisible(true);
		HS.setLayout(null);
		HS.setLocationRelativeTo(null);
		HS.setResizable(false);

		JPanel main = new JPanel();
		main.setSize(400, 40);
		main.setBackground(Color.gray);
		main.setLocation(0, 0);

		JPanel första = new JPanel();
		första.setSize(400, 40);
		första.setBackground(Color.gray);
		första.setLocation(0, 40);

		JLabel first = new JLabel("1. ");
		första.add(first);
		JLabel first1 = new JLabel(line[0]);
		första.add(first1);

		JPanel andra = new JPanel();
		andra.setSize(400, 40);
		andra.setBackground(Color.gray);
		andra.setLocation(0, 80);

		JLabel second = new JLabel("2. ");
		andra.add(second);
		JLabel second1 = new JLabel(line[1]);
		andra.add(second1);

		JPanel tredje = new JPanel();
		tredje.setSize(400, 40);
		tredje.setBackground(Color.gray);
		tredje.setLocation(0, 120);

		JLabel third = new JLabel("3. ");
		tredje.add(third);
		JLabel third1 = new JLabel(line[2]);
		tredje.add(third1);

		JPanel fjärde = new JPanel();
		fjärde.setSize(400, 40);
		fjärde.setBackground(Color.gray);
		fjärde.setLocation(0, 160);

		JLabel fourth = new JLabel("4. ");
		fjärde.add(fourth);
		JLabel fourth1 = new JLabel(line[3]);
		fjärde.add(fourth1);

		JPanel femte = new JPanel();
		femte.setSize(400, 40);
		femte.setBackground(Color.gray);
		femte.setLocation(0, 200);

		JLabel fift = new JLabel("5. ");
		femte.add(fift);
		JLabel fift1 = new JLabel(line[4]);
		femte.add(fift1);

		JPanel sjätte = new JPanel();
		sjätte.setSize(400, 40);
		sjätte.setBackground(Color.gray);
		sjätte.setLocation(0, 240);

		JLabel sixth = new JLabel("6. ");
		sjätte.add(sixth);
		JLabel sixth1 = new JLabel(line[5]);
		sjätte.add(sixth1);

		JPanel sjunde = new JPanel();
		sjunde.setSize(400, 40);
		sjunde.setBackground(Color.gray);
		sjunde.setLocation(0, 280);

		JLabel seventh = new JLabel("7. ");
		sjunde.add(seventh);
		JLabel seventh1 = new JLabel(line[6]);
		sjunde.add(seventh1);

		JPanel åttonde = new JPanel();
		åttonde.setSize(400, 40);
		åttonde.setBackground(Color.gray);
		åttonde.setLocation(0, 320);

		JLabel eight = new JLabel("8. ");
		åttonde.add(eight);
		JLabel eight1 = new JLabel(line[7]);
		åttonde.add(eight1);

		JPanel nionde = new JPanel();
		nionde.setSize(400, 40);
		nionde.setBackground(Color.gray);
		nionde.setLocation(0, 360);

		JLabel ninth = new JLabel("9. ");
		nionde.add(ninth);
		JLabel ninth1 = new JLabel(line[8]);
		nionde.add(ninth1);

		JPanel tionde = new JPanel();
		tionde.setSize(400, 40);
		tionde.setBackground(Color.gray);
		tionde.setLocation(0, 400);

		JLabel tenth = new JLabel("10. ");
		tionde.add(tenth);
		JLabel tenth1 = new JLabel(line[9]);
		tionde.add(tenth1);

		JLabel PL1 = new JLabel();
		PL1.setText("Highscore");

		main.add(PL1);
		HS.add(main);
		HS.add(första);
		HS.add(andra);
		HS.add(tredje);
		HS.add(fjärde);
		HS.add(femte);
		HS.add(sjätte);
		HS.add(sjunde);
		HS.add(åttonde);
		HS.add(nionde);
		HS.add(tionde);
		
		reader.close();

	}

	/** GUI-scoreboard play against PC button */
	public void pc() {

		PC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameboard = new Gamebooard();
				gameboard.getButtonsPvC();

			}
		});

	}

	/** GUI-scoreboard play local button */
	public void local() {

		local.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameboard = new Gamebooard();
				gameboard.getButtonsPvP();

			}
		});
	}

}

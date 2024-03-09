package battleShip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/** Algo class: Reorder the best highScore in a txt file, least numbers of misses at top and most at the bottom (from each game). 
 * (in) argument: total number of misses from the winner.
 * */
public class Algo {

	public static void algoMethod(int winner) {
		
		if (!(winner == 0)) {

			BufferedWriter writer;
			BufferedReader reader;
			PrintWriter pw;
			int[] scoreboard = new int[10];
			
			try {
				reader = new BufferedReader(
						new FileReader("/home/danan623/eclipse-workspace/gitLabb/src/battleShip/score.txt"));

				for (int i = 0; i < 10; i++) {
					
					scoreboard[i] = Integer.parseInt(reader.readLine()); 
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Arrays.sort(scoreboard);

			for (int i = 0; i < 10; i++) {

				if (winner < scoreboard[9]) {
					scoreboard[9] = winner;
				}

			}

			FileWriter fw = null;

			try {
				fw = new FileWriter("/home/danan623/eclipse-workspace/gitLabb/src/battleShip/score.txt", false);
			} catch (IOException e) {
				e.printStackTrace();
			}

			writer = new BufferedWriter(fw);
			pw = new PrintWriter(writer);
			Arrays.sort(scoreboard);
			for (int i = 0; i < 10; i++) {
				pw.write(scoreboard[i] + "\n");

			}
			pw.close();
		}

	}

}

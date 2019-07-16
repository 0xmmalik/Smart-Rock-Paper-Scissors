import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Main {

	static Random rand = new Random();
	static ArrayList<Integer> prob = new ArrayList<Integer>();
	static float compScore = 0;
	static float playerScore = 0;

	public static void main(String[] args) {
		String[] corres = new String[3];
		corres[0] = "Rock";
		corres[1] = "Paper";
		corres[2] = "Scissors";

		Scanner scan = new Scanner(System.in);
		boolean play = true;
		int prevPlayer = 1;
		int prevComp = 1;
		boolean won = true;

		while (play) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.print("0 - quit\n1 - rock\n2 - paper\n3 - scissors\n\nEnter play: ");
			int playerMove = scan.nextInt();
			if (playerMove == 0) {
				play = false;
				System.out.println("\n\nYour score: " + playerScore + "\nComputer score: " + compScore);
			}

			int compPlay = genPlay(prevPlayer, prevComp, won);

			System.out.println("\n\n" + corres[playerMove - 1] + " against " + corres[compPlay - 1] + "\n\n");

			if (playerMove == 1 && compPlay == 2) {
				compScore++;
				prLoss();
				won = true;
			}

			else if (playerMove == 1 && compPlay == 3) {
				playerScore++;
				prWin();
				won = false;
			}

			else if (playerMove == 1 && compPlay == 1) {
				playerScore += 0.5;
				compScore += 0.5;
				prTie();
				won = false;
			}

			else if (playerMove == 2 && compPlay == 1) {
				playerScore++;
				prWin();
				won = false;
			}

			else if (playerMove == 2 && compPlay == 3) {
				compScore++;
				prLoss();
				won = true;
			}

			else if (playerMove == 2 && compPlay == 2) {
				playerScore += 0.5;
				compScore += 0.5;
				prTie();
				won = false;
			}

			else if (playerMove == 3 && compPlay == 1) {
				compScore++;
				prLoss();
				won = true;
			}

			else if (playerMove == 3 && compPlay == 2) {
				playerScore++;
				prWin();
				won = false;
			}

			else if (playerMove == 3 && compPlay == 3) {
				playerScore += 0.5;
				compScore += 0.5;
				prTie();
				won = false;
			}
			scan.nextLine();
			scan.nextLine();
			prevPlayer = playerMove;
			prevComp = compPlay;
			if(playerScore >= 30 || compScore >= 30) {
				System.out.println("Game over! WON = " + (playerScore > compScore));
				System.out.println("\n\nYour score: " + playerScore + "\nComputer score: " + compScore);
				play = false;
			}
		}
	}

	public static void prLoss() {
		System.out.println("Computer wins! (" + playerScore + " - " + compScore + ")");
	}

	public static void prWin() {
		System.out.println("You win! (" + playerScore + " - " + compScore + ")");
	}

	public static void prTie() {
		System.out.println("Tie! (" + playerScore + " - " + compScore + ")");
	}

	public static int genPlay(int prevPlay, int prevComp, boolean won) {
		prob.clear();
		if (prevPlay == prevComp) {
			prob.add(prevPlay);
			prob.add(1);
			prob.add(2);
			prob.add(3);
			prob.add(prevComp);
		} else if (won) {
			prob.add(prevComp);
			prob.add(6 - (prevComp + prevPlay));
			for (int i = 0; i < 3; i++) {
				prob.add(prevPlay);
			}
		} else {
			prob.add(prevComp);
			prob.add(prevPlay);
			for (int i = 0; i < 3; i++) {
				prob.add(6 - (prevComp + prevPlay));
			}
		}
		return prob.get(rand.nextInt(5));
	}
}

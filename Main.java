import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Main {

    private static Random rand = new Random();
    private static ArrayList<Integer> prob = new ArrayList<>();
    private static float compScore = 0;
    private static float playerScore = 0;

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

        System.out.println("Welcome to Rock, Paper, Scissors! [ENTER]");
        scan.nextLine();
        System.out.println("You'll be playing against a computer who has a strategy (which isn't perfect!)... The first of you to reach 30 points will have won! [ENTER]");
        scan.nextLine();

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
            System.out.println("[ENTER]");
            scan.nextLine();
            scan.nextLine();
            prevPlayer = playerMove;
            prevComp = compPlay;
            if (playerScore >= 30 || compScore >= 30) {
                System.out.println("Game over! WON = " + (playerScore > compScore));
                System.out.println("\n\nYour score: " + playerScore + "\nComputer score: " + compScore);
                play = false;
                playerScore = compScore; // DELETE LATER
                if(playerScore == compScore) {
                    System.out.println("SUDDEN DEATH -- TIEBREAKER! [ENTER]");
                    scan.nextLine();
                    System.out.println("The system has picked a number between 0 and 100... Take a guess!");
                    int playerGuess = scan.nextInt();
                    int compGuess = rand.nextInt(100);
                    int actual = rand.nextInt(100);
                    System.out.println("YOU: " + playerGuess + "\nCOMPUTER: " + compGuess + "\n[ENTER]");
                    scan.nextLine();
                    scan.nextLine();
                    System.out.println("ACTUAL: " + actual);
                    System.out.println("WON: " + (Math.abs(compGuess - actual) > Math.abs(playerGuess - actual)));
                }
            }
        }
    }

    private static void prLoss() {
        System.out.println("Computer wins! (" + playerScore + " - " + compScore + ")");
    }

    private static void prWin() {
        System.out.println("You win! (" + playerScore + " - " + compScore + ")");
    }

    private static void prTie() {
        System.out.println("Tie! (" + playerScore + " - " + compScore + ")");
    }

    private static int genPlay(int prevPlay, int prevComp, boolean won) {
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

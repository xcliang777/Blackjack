import java.util.*;

public class Game {

    private Scanner scanner;
    private Round round;
    private Player player;
    private Dealer dealer;
    private Deck deckplayer;
    private Deck deckdealer;

    private boolean continuePlay = true;
    private static final String ERROR = "Invalid input, please try it again.";


    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    }


    public void start() {
        scanner = new Scanner(System.in);
        System.out.println("Welcome to the blackjack game. Enjoy it!" );
        //-----------------------------------------------------------------------

        deckplayer = new Deck();
        deckdealer = new Deck();
        player = new Player("player", deckplayer, 1000);
        dealer = new Dealer("dealer", deckdealer);//Initialize player and dealer.

        //-----------------------------------------------------------------------

        while (continuePlay && player.getBank() > 0) {

            //choose bargaining chip
            int chip;
            while(true) {
                try {
                    System.out.println("Please enter your bargaining chip(1~" + player.getBank() + "):");
                    chip = scanner.nextInt();

                    if (player.payMoney(chip)) {
                        break;
                    }
                    else {
                        System.out.println(Game.ERROR);
                    }
                }
                catch (Exception e) {
                    System.out.println(Game.ERROR);
                }

            }

            //-----------------------------------------------------------------------
            ///satrt one single game, return winner
            //winner == -2: split, lose both.
            //winner == -1: dealer win.
            //winner == 0: split, win 1 game but lose another.
            //winner == 1: player win.
            //winner == 2: split, win both.

            round = new Round();
            int winner = round.start(player, dealer, chip);

            //-----------------------------------------------------------------------
            //calculate chip
            if (winner == 0) {

            } else if (winner == 1) {
                player.addbank(chip);
            } else if (winner == 2) {

            }

            //-----------------------------------------------------------------------
            ///choose cash out
            while (true) {
                System.out.println("Do you want to continue playing or cash out?");
                System.out.println("Please enter y/n(y:Continue, n:Cash out)");
                try {
                    String status = scanner.next();
                    if (status.equals("y")) {
                        break;
                    }
                    else if (status.equals("n")) {
                        continuePlay = false;
                        break;
                    }
                    else System.out.println(Game.ERROR);
                }
                catch (Exception e) {
                    System.out.println(Game.ERROR);
                }
            }
        }

        //-----------------------------------------------------------------------
        //print final score
        System.out.println("You have exited the game.");
        System.out.println("Your bank have $" + player.getBank() + " left.");




    }
}

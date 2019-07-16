import java.util.*;

public class Round {

    private Scanner scanner;

    public int start(Player player, Dealer dealer, int chip) {
        scanner = new Scanner(System.in);
        int winner = 0;
        System.out.println("1111111111111111111111");

        //---------------------------------------------------------------------------
        //Deal
        for (int i = 0; i < 4; i++) {
            if (i%2 == 0) {
                player.getDeck().addCard(Pool.getRandomCard());
            } else {
                dealer.getDeck().addCard(Pool.getRandomCard());
            }
        }
        System.out.println(player.getDeck().getScore());
        System.out.println("1111111111111111111111");

        //---------------------------------------------------------------------------
        //check blackjack and split
        Boolean blackjack = false;
        if (player.getDeck().checkBlackJack() == 1) {
            System.out.println("BlackJacked!");
            blackjack = true;
        }
        if (player.checkSplit(chip) && chip < player.getBank()) {
            System.out.println("Do you want to split? Please enter y/n");
            while(true) {
                try {
                    String splitOrNot = scanner.nextLine();
                    if (splitOrNot.equals("n")) break;
                    else if (splitOrNot.equals("y")){
                        //if split, pay bet
                        boolean paysplit = player.payMoney(chip);
                        player.split(chip);
                        player.getDeck().addCard(Pool.getRandomCard());
                    } else System.out.println("Invalid input, please enter again.");
                } catch (Exception e) {
                    System.out.println("Invalid input, please enter again.");
                }
            }


        }

        //---------------------------------------------------------------------------
        //Player makes move
        boolean lessThan21 = true;
        boolean bust1 = false;
        boolean bust2 = false;
        lessThan21 = makeMove(player, dealer, true, chip, blackjack);
        if (!lessThan21) {
            //It means player bust.
            //System.out.println("Player bust. Dealer win.");
            bust1 = true;
        }

        //if split, make move again.
        if (player.getSplitList().getScore() > 0) {
            player.getSplitList().addCard(Pool.getRandomCard());
            lessThan21 = true;
            lessThan21 = makeSplitMove(player, dealer, true, chip, blackjack);
            if (!lessThan21) {
                bust2 = true;
            }
        }

        //---------------------------------------------------------------------------
        //dealer make a move
        if (!blackjack && !bust1) {
            dealerMakeMove(player, dealer);
        }

        //---------------------------------------------------------------------------
        //get result
        if (bust1) {

            System.out.println("Dealer's Card:");
            System.out.println(dealer.getDeck().toString(false));
            System.out.println("Player's Card:");
            System.out.println(player.getDeck().toString(false));
            System.out.println("Player bust. Dealer win.");
            winner = -1;
        } else if (dealer.getDeck().getScore() > 21) {
            System.out.println("Dealer bust. Player win.");
            winner = 1;
        } else {
            if (player.getDeck().getScore() > dealer.getDeck().getScore()) {
                System.out.println("Player win.");
                winner = 1;
            } else if (player.getDeck().getScore() < dealer.getDeck().getScore()) {
                System.out.println("Dealer win.");
                winner = -1;
            } else if (player.getDeck().getScore() == dealer.getDeck().getScore()) {
                System.out.println("Push.");
                winner = 0;
            }
        }

        if (player.getSplitList().getScore() > 0) {
            System.out.println("Get the result of split card");
            if (bust2) {
                System.out.println("Dealer's Card:");
                System.out.println(dealer.getDeck().toString(false));
                System.out.println("Player's Card:");
                System.out.println(player.getDeck().toString(false));
                System.out.println("Player bust. Dealer win.");
                winner -= 1;
            } else if (dealer.getDeck().getScore() > 21) {
                System.out.println("Dealer bust. Player win.");
                winner += 1;
            } else {
                if (player.getSplitList().getScore() > dealer.getDeck().getScore()) {
                    System.out.println("Player win.");
                    winner += 1;
                } else if (player.getSplitList().getScore() < dealer.getDeck().getScore()) {
                    System.out.println("Dealer win.");
                    winner -= 1;
                } else if (player.getSplitList().getScore() == dealer.getDeck().getScore()) {
                    System.out.println("Push.");
                }
            }
        }


        //---------------------------------------------------------------------------
        //clear round deck.
        player.clearInfo();
        dealer.clearDeck();


        return winner;
    }


    public boolean makeMove(Player player, Dealer dealer, boolean lessThan21, int chip, boolean blackjack) {
        boolean ifDouble = false;
        while (lessThan21 && !ifDouble) {
            System.out.println("Dealer's Card:");
            System.out.println(dealer.getDeck().toString(true));
            System.out.println("Player's Card:");
            System.out.println(player.getDeck().toString(false));
            if (blackjack) {
                System.out.println("BlackJack!");
                break;
            }

            String choice;
            while(true) {
                try {
                    System.out.println("Please enter your choice:(h:hit, s:stand, d:double)");
                    choice = scanner.next();
                    if (choice.equals("h") || choice.equals("s") || choice.equals("d")) break;
                    System.out.println("Invalid input, please enter again.");
                } catch (Exception e) {
                    System.out.println("Invalid input, please enter again.");
                }
            }

            if (choice.equals("h")) {
                lessThan21 = player.hit();
            } else if (choice.equals("s")) {
                System.out.println("Dealer's Card:");
                System.out.println(dealer.getDeck().toString(true));
                System.out.println("Player's Card:");
                System.out.println(player.getDeck().toString(false));
                break;
            } else if (choice.equals("d")) {
                ifDouble = true;
                lessThan21 = player.doubleUp(chip, 0);
                if (lessThan21) {
                    System.out.println("Dealer's Card:");
                    System.out.println(dealer.getDeck().toString(true));
                    System.out.println("Player's Card:");
                    System.out.println(player.getDeck().toString(false));
                }
            }
        }
        return lessThan21;
    }

    public boolean makeSplitMove(Player player, Dealer dealer, boolean lessThan21, int chip, boolean blackjack) {
        boolean ifDouble = false;
        while (lessThan21 && !ifDouble) {
            System.out.println("Now playing the splitted card.");
            System.out.println("Dealer's Card:");
            System.out.println(dealer.getDeck().toString(true));
            System.out.println("Player's Card:");
            System.out.println(player.getSplitList().toString(false));
            if (blackjack) {
                System.out.println("BlackJack!");
                break;
            }

            String choice;
            while(true) {
                try {
                    System.out.println("Please enter your choice:(h:hit, s:stand, d:double)");
                    choice = scanner.next();
                    if (choice.equals("h") || choice.equals("s") || choice.equals("d")) break;
                    System.out.println("Invalid input, please enter again.");
                } catch (Exception e) {
                    System.out.println("Invalid input, please enter again.");
                }
            }

            if (choice.equals("h")) {
                lessThan21 = player.hit(1);
            } else if (choice.equals("s")) {
                System.out.println("Dealer's Card:");
                System.out.println(dealer.getDeck().toString(true));
                System.out.println("Player's Card:");
                System.out.println(player.getDeck().toString(false));
                break;
            } else if (choice.equals("d")) {
                ifDouble = true;
                lessThan21 = player.doubleUp(chip, 1);
                if (lessThan21) {
                    System.out.println("Dealer's Card:");
                    System.out.println(dealer.getDeck().toString(true));
                    System.out.println("Player's Card:");
                    System.out.println(player.getDeck().toString(false));
                }
            }
        }
        return lessThan21;
    }

    public void dealerMakeMove(Player player, Dealer dealer) {
        System.out.println("Dealer's turn.");
        while (dealer.getDeck().getScore() < 17) {
            dealer.getDeck().addCard(Pool.getRandomCard());
            System.out.println("Dealer's Card:");
            System.out.println(dealer.getDeck().toString(false));
            System.out.println("Player's Card:");
            System.out.println(player.getDeck().toString(false));
        }

    }


}

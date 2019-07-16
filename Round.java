import java.util.*;

public class Round {

    private Scanner scanner;

    public int start(Player player, Dealer dealer, int chip) {
        scanner = new Scanner(System.in);
        int winner = 0;
        //System.out.println("1111111111111111111111");

        //---------------------------------------------------------------------------
        //Deal
        for (int i = 0; i < 4; i++) {
            if (i%2 == 0) {
                player.getDeck().addCard(Pool.getRandomCard());
            } else {
                dealer.getDeck().addCard(Pool.getRandomCard());
            }
        }
        //System.out.println(player.getDeck().getScore());
       // System.out.println("1111111111111111111111");

        //---------------------------------------------------------------------------
        //check blackjack and split
        Boolean blackjack = false;
        if (player.getDeck().checkBlackJack() == 1) {
            System.out.println(player.toString(false));
            System.out.println("BlackJacked!");
            blackjack = true;
        }
        if (player.checkSplit(chip) && chip < player.getBank()) {
            System.out.println(player.toString(false));
            System.out.println("Do you want to split? Please enter y/n");
            while(true) {
                try {
                    String splitOrNot = scanner.nextLine();
                    if (splitOrNot.equals("n")) break;
                    else if (splitOrNot.equals("y")){
                        //if split, pay bet
                        player.payMoney(chip);
                        player.split(chip);
                        player.getDeck().addCard(Pool.getRandomCard());
                        break;
                    } else System.out.println("Invalid input, please enter again.");
                } catch (Exception e) {
                    System.out.println("Invalid input, please enter again.");
                }
            }


        }

        //---------------------------------------------------------------------------
        //Player makes move
        boolean lessThan21 = true;
        boolean ifDouble = false;
        boolean ifDouble2 = false;
        boolean bust1 = false;
        boolean bust2 = false;
        ifDouble = makeMove(player, dealer, chip, blackjack);
        if (player.getDeck().getScore() > 21) {
            //It means player bust.
            bust1 = true;
        }

        //if split, make move again.
        if (player.getSplitList().getScore() > 0) {
            player.getSplitList().addCard(Pool.getRandomCard());
            lessThan21 = true;
            ifDouble2 = makeSplitMove(player, dealer, true, chip, blackjack);
            if (player.getSplitList().getScore() > 21) {
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
            System.out.println(player.toString(false));
            System.out.println(dealer.toString(false));
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
                System.out.println(player.toString(false));
                System.out.println(dealer.toString(false));
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

        if (ifDouble) winner += 10;
        if (ifDouble2) winner += 10;


        //---------------------------------------------------------------------------
        //clear round deck.
        player.clearInfo();
        dealer.clearDeck();


        return winner;
    }


    public boolean makeMove(Player player, Dealer dealer, int chip, boolean blackjack) {
        boolean ifDouble = false;
        boolean lessThan21 = true;
        while (lessThan21 && !ifDouble) {
            System.out.println(player.toString(false));
            System.out.println(dealer.toString(true));
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
                player.hit();
                if (player.getDeck().getScore() >= 21) {
                    lessThan21 = false;
                }
            } else if (choice.equals("s")) {
                System.out.println(player.toString(false));
                System.out.println(dealer.toString(true));
                break;
            } else if (choice.equals("d")) {
                ifDouble = true;
                player.payMoney(chip);
                player.doubleUp(chip, 0);
                if (player.getDeck().getScore() <= 21) {
                    System.out.println(player.toString(false));
                    System.out.println(dealer.toString(true));
                }
            }
        }
        return ifDouble;
    }

    public boolean makeSplitMove(Player player, Dealer dealer, boolean lessThan21, int chip, boolean blackjack) {
        boolean ifDouble = false;
        while (lessThan21 && !ifDouble) {
            System.out.println("Now playing the splitted card.");
            System.out.println(player.toString(true));
            System.out.println(dealer.toString(true));
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
                System.out.println(player.toString(true));
                System.out.println(dealer.toString(true));
                break;
            } else if (choice.equals("d")) {
                ifDouble = true;
                player.payMoney(chip);
                player.doubleUp(chip, 1);

                if (player.getDeck().getScore() <= 21) {
                    System.out.println(player.toString(true));
                    System.out.println(dealer.toString(true));
                }
            }
        }
        return ifDouble;
    }

    public void dealerMakeMove(Player player, Dealer dealer) {
        System.out.println("Now it is dealer's turn.");
        if (dealer.getDeck().getScore() <= 21 && dealer.getDeck().getScore() > 17) {
            System.out.println(player.toString(false));
            System.out.println(dealer.toString(false));
        }
        while (dealer.getDeck().getScore() < 17) {
            dealer.getDeck().addCard(Pool.getRandomCard());
            System.out.println(player.toString(false));
            System.out.println(dealer.toString(false));

        }
    }


}

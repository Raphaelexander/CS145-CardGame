import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dealer {
    Scanner input = new Scanner(System.in);
    ArrayList<String> playerHand = new ArrayList<>();    
    ArrayList<String> dealerHand = new ArrayList<>(); 
    
    public ArrayList<String> shuffle(ArrayList<String> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    public void deal(ArrayList<String> shuffledDeck) {    
    
        for (int i = 0; i < 2; i++) {
            playerHand.add(shuffledDeck.get(shuffledDeck.size() - 1));
            shuffledDeck.remove(shuffledDeck.size() - 1);
        }
        for (int i = 0; i < 2; i++) {
            dealerHand.add(shuffledDeck.get(shuffledDeck.size() - 1));
            shuffledDeck.remove(shuffledDeck.size() - 1);
        }

        System.out.println("Your hand is " + playerHand + "\nTotal: " + getPlayerValue(playerHand));
        System.out.println("Dealer hand is " + dealerHand + "\nDealer Total: " + getDealerValue(dealerHand));
    }

    private int getPlayerValue(ArrayList<String> playerHand) {
        // This method should call Card's getPlayerValue()
        Card card = new Card();
        return card.getPlayerValue(playerHand);
    }

    private int getDealerValue(ArrayList<String> dealerHand) {
        // This method should call Card's getDealerValue()
        Card card = new Card();
        return card.getDealerValue(dealerHand);
    }

    public void playerMove(ArrayList<String> shuffledDeck) {
        System.out.println("\nHit or Hold?");
        String userInput = input.nextLine();
        String move = userInput.toUpperCase();
        if(getPlayerValue(playerHand) < 21) {
            if (move.contains("HIT")) {
            playerHand.add(shuffledDeck.get(shuffledDeck.size() - 1));
            shuffledDeck.remove(shuffledDeck.size() - 1);
            System.out.println("New Total: " + getPlayerValue(playerHand));
            dealerMove(shuffledDeck);
            if(getPlayerValue(playerHand) < 21) {
                playerMove(shuffledDeck);
            } else {
                 System.out.println("Your score is over 21. You Lose!");
                System.exit(0);
            }
            playerMove(shuffledDeck);
            } else if (move.contains("HOLD")) {
                dealerMove(shuffledDeck);
                FindWinner();
            }
        } else {
            System.out.println("Your score is over 21. You Lose!");
            System.exit(0);
        }
    }

    public void dealerMove(ArrayList<String> shuffledDeck) {
        if(getDealerValue(dealerHand) < 21){
            if(getDealerValue(dealerHand) < 14) {
            dealerHand.add(shuffledDeck.get(shuffledDeck.size()));
            shuffledDeck.remove(shuffledDeck.size() - 1);
            System.out.println("The Dealer draws. New Dealer Total: " + getDealerValue(dealerHand));
            FindWinner();
            } else {
            FindWinner();
            }
        } else {
            System.out.println("Dealer score is over 21. You Win!");
            System.exit(0);
        }
    }
        

    public boolean FindWinner(){
        if(getPlayerValue(playerHand) > getDealerValue(dealerHand) && getPlayerValue(playerHand) < 22) {
            System.out.println("You win!");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return true;
        } else if(getPlayerValue(playerHand) == 21) {
            System.out.println("You win!");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return true;
        } else if(getDealerValue(dealerHand) > getPlayerValue(playerHand) && getDealerValue(dealerHand) < 22){ 
            System.out.println("You lose");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return false;
        } else if(getDealerValue(dealerHand) == 21) {
            System.out.println("You lose");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return false;
        } else {
            return false;
        }
    }
}

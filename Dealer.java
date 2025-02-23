import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Dealer {
    Scanner input = new Scanner(System.in);
    String[] playerHand = new String[5];    
    String[] dealerHand = new String[5]; 
    
    public ArrayList<String> shuffle(ArrayList<String> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    public void deal(ArrayList<String> shuffledDeck) {    
        
        for (int i = 0; i < 2; i++) {
            playerHand[i] = shuffledDeck.get(i);
            shuffledDeck.remove(i);
        }
        for (int i = 0; i < 2; i++) {
            dealerHand[i] = shuffledDeck.get(i);
            shuffledDeck.remove(i);
        }

        System.out.println("Your hand is " + Arrays.toString(playerHand) + "\nTotal: " + getPlayerValue(playerHand));
        System.out.println("Dealer hand is " + Arrays.toString(dealerHand) + "\nDealer Total: " + getDealerValue(dealerHand));
    }

    private int getPlayerValue(String[] playerHand) {
        // This method should call Card's getPlayerValue()
        Card card = new Card();
        return card.getPlayerValue(playerHand);
    }

    private int getDealerValue(String[] dealerHand) {
        // This method should call Card's getDealerValue()
        Card card = new Card();
        return card.getDealerValue(dealerHand);
    }

    public int playerMove(ArrayList<String> shuffledDeck) {
        System.out.println("\nHit or Hold?");
        String userInput = input.nextLine();
        String move = userInput.toUpperCase();
        
        if (move.contains("HIT")) {
            playerHand[2] = shuffledDeck.get(0);
            shuffledDeck.remove(0);
            System.out.println("New Total: " + getPlayerValue(playerHand));
            
            FindWinner();
            playerMove(shuffledDeck);
        } else if (move.contains("HOLD")) {
            System.out.println("Final Total: " + getPlayerValue(playerHand));
            FindWinner();
        }
        return getPlayerValue(playerHand);
    }

    public int dealerMove(ArrayList<String> shuffledDeck) {
        if(getDealerValue(dealerHand) < 14) {
            dealerHand[2] = shuffledDeck.get(0);
            shuffledDeck.remove(0);
            System.out.println("The Dealer draws. New Dealer Total: " + getDealerValue(dealerHand));
            FindWinner();
            return getDealerValue(dealerHand);
        } else {
            System.out.println("Final Dealer Total: " + getDealerValue(dealerHand));
            FindWinner();
            return getDealerValue(dealerHand);
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

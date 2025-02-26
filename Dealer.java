import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dealer {
    Scanner input = new Scanner(System.in);
    ArrayList<String> playerHand = new ArrayList<>(); //arraylists for player and dealer to hold as many cards as needed    
    ArrayList<String> dealerHand = new ArrayList<>(); //
    
    public ArrayList<String> shuffle(ArrayList<String> deck) {
        Collections.shuffle(deck); //collections class that works with lists has a shuffle method used here to shuffle all items in arraylist
        return deck;
    }

    public void deal(ArrayList<String> shuffledDeck) {    
    
        for (int i = 0; i < 2; i++) {
            playerHand.add(shuffledDeck.get(shuffledDeck.size() - 1)); //adds a card to player hand that is on top of stack of cards
            shuffledDeck.remove(shuffledDeck.size() - 1); //removes the top card from deck
        }
        for (int i = 0; i < 2; i++) {
            dealerHand.add(shuffledDeck.get(shuffledDeck.size() - 1)); //adds a card to player hand that is on top of stack of cards
            shuffledDeck.remove(shuffledDeck.size() - 1); //removes the top card from deck
        }

        System.out.println("Your hand is " + playerHand + "\nTotal: " + getPlayerValue(playerHand));
        System.out.println("Dealer hand is " + dealerHand + "\nDealer Total: " + getDealerValue(dealerHand));
    }

    private int getPlayerValue(ArrayList<String> playerHand) {
        // This method calls Card's getPlayerValue()
        Card card = new Card();
        return card.getPlayerValue(playerHand);
    }

    private int getDealerValue(ArrayList<String> dealerHand) {
        // This method calls Card's getDealerValue()
        Card card = new Card();
        return card.getDealerValue(dealerHand);
    }

    public void playerMove(ArrayList<String> shuffledDeck) {
        System.out.println("Hit or Hold?");
        String userString = input.nextLine();
        String move = userString.toUpperCase();
        
        if (getPlayerValue(playerHand) < 22) {
            
            if (move.contains("HIT")) { //when player hits
                playerHand.add(shuffledDeck.get(shuffledDeck.size() - 1));//add card from top of deck
                shuffledDeck.remove(shuffledDeck.size() - 1);//remove card from top of deck
                
                if (getPlayerValue(playerHand) < 21) { //if after hit, player score is still under 21
                System.out.println(playerHand);
                System.out.println("New total: " + getPlayerValue(playerHand));
                playerMove(shuffledDeck);
                }
            }
        }
    }
    
    public void dealerMove(ArrayList<String> shuffledDeck) {
        if(getDealerValue(dealerHand) < 22) {
            if(getDealerValue(dealerHand) < 15) {
                dealerHand.add(shuffledDeck.get(shuffledDeck.size() - 1)); //add top card to dealer hand
                shuffledDeck.remove(shuffledDeck.size() - 1); //remove top card from deck
                System.out.println("New dealer hand: " + dealerHand);
                System.out.println("The Dealer draws. New Dealer Total: " + getDealerValue(dealerHand));//show new dealer total
            }
        }
    }         

    public boolean findWinner(){ //determine winner
        if(getPlayerValue(playerHand) > getDealerValue(dealerHand) && getPlayerValue(playerHand) < 22) { //if player score is higher than dealer and not over 21
            System.out.println("You win!");
            System.out.println("Your final hand: " + playerHand + "\nDealer final hand: " + dealerHand);
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return true;
        } else if(getPlayerValue(playerHand) == 21) { // if player hits 21
            System.out.println("You win!");
            System.out.println("Your final hand: " + playerHand + "\nDealer final hand: " + dealerHand);
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return true;
        } else if(getDealerValue(dealerHand) > getPlayerValue(playerHand) && getDealerValue(dealerHand) < 22){ //if dealer score is larger than player but not more than 21
            System.out.println("You lose");
            System.out.println("Your final hand: " + playerHand + "\nDealer final hand: " + dealerHand);
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return false;
        } else if(getDealerValue(dealerHand) == 21) { //if dealer hits 21
            System.out.println("You lose");
            System.out.println("Your final hand: " + playerHand + "\nDealer final hand: " + dealerHand);
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return false;
        } else if(getPlayerValue(playerHand) == 21) { //if dealer hits 21
            System.out.println("You lose");
            System.out.println("Your final hand: " + playerHand + "\nDealer final hand: " + dealerHand);
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return false;
        } else if (getDealerValue(dealerHand) > 21) { //if dealer is over 21
            System.out.println("You win!");
            System.out.println("Your final hand: " + playerHand + "\nDealer final hand: " + dealerHand);
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return true;
        } else if(getPlayerValue(playerHand) == getDealerValue(dealerHand)) { //if game is a draw
            System.out.println("The score is tied. The game is a draw.");
            System.exit(0);
        }
    return false;
    }  
}

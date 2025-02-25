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

    public String playerMove(ArrayList<String> shuffledDeck) { //method to determine logic during a players move
    System.out.println("\nHit or Hold?");
    String userInput = input.nextLine();
    String move = userInput.toUpperCase();
    if(getPlayerValue(playerHand) < 22) { // if player doesn't go over 21
        if (move.contains("HIT")) { //if player hits
            playerHand.add(shuffledDeck.get(shuffledDeck.size() - 1)); //add card from top of deck to players hand
            shuffledDeck.remove(shuffledDeck.size() - 1); //remove top card from deck
            System.out.println("New Total: " + getPlayerValue(playerHand));
            if (getPlayerValue(playerHand) < 22) { //if the hit doesn't result in player going over
                dealerMove(shuffledDeck); //move to dealer turn
            } else {
                System.out.println("Your score is over 21. You Lose!");
                System.exit(0);
            }
            return move;
        } else if (move.contains("HOLD")) {//if player holds
            dealerMove(shuffledDeck); //move to dealer move
            return move;
        }
    } else {
        System.out.println("Your score is over 21. You Lose!");
        System.exit(0);
    }
    return move;
}

    public void dealerMove(ArrayList<String> shuffledDeck) { //method for dealer move depending on playermove
        if(getDealerValue(dealerHand) < 22){ //if dealer hand does not go over 21
            if(getDealerValue(dealerHand) < 15) { //logic for dealer to hit or not
                dealerHand.add(shuffledDeck.get(shuffledDeck.size() - 1)); //add top card to dealer hand
                shuffledDeck.remove(shuffledDeck.size() - 1); //remove top card from deck
                System.out.println("The Dealer draws. New Dealer Total: " + getDealerValue(dealerHand));//show new dealer total
                if (getDealerValue(dealerHand) < 21) { // if dealer is less than 21 then move goes back to player
                    playerMove(shuffledDeck);
                } else if (getDealerValue(dealerHand) == 21) {
                    System.out.println("Dealer hit 21. You lose");
                    System.exit(0);
                } else {
                    System.out.println("Dealer score is over 21. You Win!");
                    System.exit(0);
                }           
            } else if ("HIT".equals(playerMove(shuffledDeck))) { //continue back to player move if they hit and dealer holds
                playerMove(shuffledDeck);
            } else if ("HOLD".equals(playerMove(shuffledDeck))) { //if both dealer and player hold then find winner
                FindWinner();
        }
        } else {
            System.out.println("Dealer score is over 21. You Win!");
            System.exit(0);
        }
    }
        

    public boolean FindWinner(){ //determine winner
        if(getPlayerValue(playerHand) > getDealerValue(dealerHand) && getPlayerValue(playerHand) < 22) { //if player score is higher than dealer and not over 21
            System.out.println("You win!");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return true;
        } else if(getPlayerValue(playerHand) == 21) { // if player hits 21
            System.out.println("You win!");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return true;
        } else if(getDealerValue(dealerHand) > getPlayerValue(playerHand) && getDealerValue(dealerHand) < 22){ //if dealer score is larger than player but not more than 21
            System.out.println("You lose");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return false;
        } else if(getDealerValue(dealerHand) == 21) { //if dealer hits 21
            System.out.println("You lose");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return false;
        } else if (getDealerValue(dealerHand) > 21) { //if dealer is over 21
            System.out.println("You win!");
            System.out.println("Final score " + getPlayerValue(playerHand) + " to " + getDealerValue(dealerHand));
            return true;
        }
    return false;
    }  
}

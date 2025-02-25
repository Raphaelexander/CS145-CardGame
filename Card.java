import java.util.ArrayList;


public class Card {
    private ArrayList<String> deck;
    private int playerTotal;
    private int dealerTotal;

    public void setDeck(ArrayList<String> deck) { //setter method
        this.deck = deck;
    }

    public ArrayList<String> getDeck() {//getter method
        return deck;
    }

    public ArrayList<String> newDeck() {//create deck of cards
        deck = new ArrayList<>();
        for (Suit s : Suit.values()) { // iterate over all suits in enum
            for (Rank r : Rank.values()) { // iterate over all ranks in enum
                deck.add(r + " of " + s); //create arraylist of strings
            }
        }

        return deck;
    }

    public int getPlayerValue(ArrayList<String> playerHand) { //method for finding value of user's hand
        playerTotal = 0;
        int aceCount = 0;
        for (int i = 0; i < playerHand.size(); i++) { 
            if (playerHand.get(i) != null) { //if player hand is not empty
                if (playerHand.get(i).contains("TWO")) {
                    playerTotal += 2;
                } else if (playerHand.get(i).contains("THREE")) {
                    playerTotal += 3; 
                } else if (playerHand.get(i).contains("FOUR")) {
                    playerTotal += 4;
                } else if (playerHand.get(i).contains("FIVE")) {
                    playerTotal += 5;
                } else if (playerHand.get(i).contains("SIX")) {
                    playerTotal += 6;
                } else if (playerHand.get(i).contains("SEVEN")) {
                    playerTotal += 7;
                } else if (playerHand.get(i).contains("EIGHT")) {
                    playerTotal += 8;
                } else if (playerHand.get(i).contains("NINE")) {
                    playerTotal += 9;
                } else if (playerHand.get(i).contains("TEN") || playerHand.get(i).contains("JACK") || playerHand.get(i).contains("QUEEN") || playerHand.get(i).contains("KING")) {
                    playerTotal += 10;
                } else if (playerHand.get(i).contains("ACE")) {
                    aceCount++;
                    playerTotal += 11; //automatically choose 11 for player if ace is drawn
                }
            }
        }
        while (playerTotal > 21 && aceCount > 0) {//loop to change ace value to 1 if user's score goes over 21 with an ace in their hand
            playerTotal -= 10;
            aceCount--;
        }
        return playerTotal;    
    }

    public int getDealerValue(ArrayList<String> dealerHand) { //method to get value of dealer hand, same logic as playerhandvalue except for aces
        dealerTotal = 0;
        for (int i = 0; i < dealerHand.size(); i++) {
            if (dealerHand.get(i) != null) {
                if (dealerHand.get(i).contains("TWO")) {
                    dealerTotal += 2;
                } else if (dealerHand.get(i).contains("THREE")) {
                    dealerTotal += 3; 
                } else if (dealerHand.get(i).contains("FOUR")) {
                    dealerTotal += 4;
                } else if (dealerHand.get(i).contains("FIVE")) {
                    dealerTotal += 5;
                } else if (dealerHand.get(i).contains("SIX")) {
                    dealerTotal += 6;
                } else if (dealerHand.get(i).contains("SEVEN")) {
                    dealerTotal += 7;
                } else if (dealerHand.get(i).contains("EIGHT")) {
                    dealerTotal += 8;
                } else if (dealerHand.get(i).contains("NINE")) {
                    dealerTotal += 9;
                } else if (dealerHand.get(i).contains("TEN") ||dealerHand.get(i).contains("JACK") || dealerHand.get(i).contains("QUEEN") || dealerHand.get(i).contains("KING")) {
                    dealerTotal += 10;
                } else if (dealerHand.get(i).contains("ACE")) { //logic that uses math.random method to randomly determine if the dealer decides if ace is worth 1 or 11
                    double dealersChoice = Math.random() * 100;
                    if (dealersChoice < 50) {
                        dealerTotal += 1;
                    } else {
                        dealerTotal += 11;
                    }
                }
            }
        }
        return dealerTotal;    
    }

    enum Suit {
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS;
    }

    enum Rank {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE;
    }
}

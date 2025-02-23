import java.util.ArrayList;
import java.util.Scanner;

public class Card {
    private ArrayList<String> deck;
    private int playerTotal;
    private int dealerTotal;
    Scanner input = new Scanner(System.in);

    public void setDeck(ArrayList<String> deck) {
        this.deck = deck;
    }

    public ArrayList<String> getDeck() {
        return deck;
    }

    public ArrayList<String> newDeck() {
        deck = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                deck.add(r + " of " + s);
            }
        }

        return deck;
    }

    public int getPlayerValue(String[] playerHand) {
        playerTotal = 0;
        for (int i = 0; i < playerHand.length; i++) {
            if (playerHand[i] != null) {
                if (playerHand[i].contains("TWO")) {
                    playerTotal += 2;
                } else if (playerHand[i].contains("THREE")) {
                    playerTotal += 3; 
                } else if (playerHand[i].contains("FOUR")) {
                    playerTotal += 4;
                } else if (playerHand[i].contains("FIVE")) {
                    playerTotal += 5;
                } else if (playerHand[i].contains("SIX")) {
                    playerTotal += 6;
                } else if (playerHand[i].contains("SEVEN")) {
                    playerTotal += 7;
                } else if (playerHand[i].contains("EIGHT")) {
                    playerTotal += 8;
                } else if (playerHand[i].contains("NINE")) {
                    playerTotal += 9;
                } else if (playerHand[i].contains("JACK") || playerHand[i].contains("QUEEN") || playerHand[i].contains("KING")) {
                    playerTotal += 10;
                } else if (playerHand[i].contains("ACE")) {
                    System.out.println("You got an Ace. Is it equal 1 or 11?");
                    int ace = input.nextInt();
                    playerTotal += ace; 
                }
            }
        }
        return playerTotal;    
    }

    public int getDealerValue(String[] dealerHand) {
        dealerTotal = 0;
        for (int i = 0; i < dealerHand.length; i++) {
            if (dealerHand[i] != null) {
                if (dealerHand[i].contains("TWO")) {
                    dealerTotal += 2;
                } else if (dealerHand[i].contains("THREE")) {
                    dealerTotal += 3; 
                } else if (dealerHand[i].contains("FOUR")) {
                    dealerTotal += 4;
                } else if (dealerHand[i].contains("FIVE")) {
                    dealerTotal += 5;
                } else if (dealerHand[i].contains("SIX")) {
                    dealerTotal += 6;
                } else if (dealerHand[i].contains("SEVEN")) {
                    dealerTotal += 7;
                } else if (dealerHand[i].contains("EIGHT")) {
                    dealerTotal += 8;
                } else if (dealerHand[i].contains("NINE")) {
                    dealerTotal += 9;
                } else if (dealerHand[i].contains("JACK") || dealerHand[i].contains("QUEEN") || dealerHand[i].contains("KING")) {
                    dealerTotal += 10;
                } else if (dealerHand[i].contains("ACE")) {
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
        JACK,
        QUEEN,
        KING,
        ACE;
    }
}

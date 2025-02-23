import java.util.ArrayList;

public class BlackJack { 
    public static void main(String[] args) {
        Card card = new Card();
        Dealer house = new Dealer();

        ArrayList<String> deck = card.newDeck();

        ArrayList<String> shuffledDeck = house.shuffle(deck);

        house.deal(shuffledDeck);
        house.playerMove(shuffledDeck);
        house.dealerMove(shuffledDeck);
        house.FindWinner();
        
        if(house.FindWinner() == false) {
            System.exit(0);
        } else {
            System.exit(0);
        }
    }
}

import java.util.ArrayList;

public class BlackJack { 
    public static void main(String[] args) {
        Card card = new Card();
        Dealer house = new Dealer();

        ArrayList<String> deck = card.newDeck(); //initilize deck

        ArrayList<String> shuffledDeck = house.shuffle(deck); //take deck and shuffle it

        house.deal(shuffledDeck); //deal to players
        house.playerMove(shuffledDeck); //initilize first player move
        house.dealerMove(shuffledDeck); //intilize first dealer move
        house.findWinner(); //determine final winner
        System.exit(0);
    }
}

import java.util.ArrayList;
import java.util.List;

public class Player implements BasicActions {

    private Turn turn;
    private Deck deck;
    private List<Card> playerCards;

    private int money = Settings.STARTING_CASH;

    Player(Deck deck, Turn turn) {
        this.deck = deck;
        this.turn = turn;
        playerCards = new ArrayList<>();
    }

    void drawStartingCards() {
        playerCards.clear();
        playerCards.add(deck.drawACard());
        playerCards.add(deck.drawACard());
    }

    @Override
    public void hit(Card[] cards) {

    }

    @Override
    public void stand() {

    }

    @Override
    public int getHandValue() {

        int value = 0;
        for (Card card : playerCards)
            value += card.getCardValue();

        return value;
    }

    void doubleDown() {

    }

    void split() {

    }

    void surrender() {

    }

    void takeInsurance() {

    }

    List<Card> getPlayerCards() {
        return playerCards;
    }

    int getMoney() {
        return money;
    }

    boolean bet(int amount) {

        if (amount <= money && amount > 0) {
            money -= amount;
            return true;
        }
        else
            return false;
    }
}

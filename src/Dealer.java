import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements BasicActions {

    private Deck deck;
    private Turn turn;
    private List<Card> dealerCards;
    private Card lastDrawnCard;

    Dealer(Deck deck, Turn turn) {
        this.deck = deck;
        dealerCards = new ArrayList<>();
        this.turn = turn;
    }

    void drawStartingCards() {
        dealerCards.clear();
        dealerCards.add(deck.drawACard());
        dealerCards.add(deck.drawACard());
    }

    @Override
    public void hit() {

        lastDrawnCard = deck.drawACard();
        dealerCards.add(lastDrawnCard);

    }

    public void stand() {
        turn.nextTurn();
    }

    @Override
    public int getHandValue() {

        int combinedValue = 0;

        for (Card card : dealerCards)
            combinedValue += card.getCardValue();

        return combinedValue;

    }

    List<Card> getDealerCards() {
        return this.dealerCards;
    }

    void takeTurn() {
        hit();
    }

    String getFaceUpCardName() {
        return this.dealerCards.get(0).getName();
    }

    int getFaceUpCardValue() {
        return this.dealerCards.get(0).getCardValue();
    }

    Card getLastDrawnCard() {
        return lastDrawnCard;
    }
}

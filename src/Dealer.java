import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements BasicActions {

    private Deck deck;
    private List<Card> dealerCards;
    private Card lastDrawnCard;

    Dealer(Deck deck) {
        lastDrawnCard = null;
        this.deck = deck;
        dealerCards = new ArrayList<>();
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

    }

    @Override
    public int getHandValue() {

        int combinedValue = 0;

        for (Card card : dealerCards)
            combinedValue += card.getCardValue();

        System.out.println(dealerCards.size());

        return combinedValue;

    }

    boolean hasBlackJack() {

        return this.dealerCards.size() == 2 && this.getHandValue() == 21;

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

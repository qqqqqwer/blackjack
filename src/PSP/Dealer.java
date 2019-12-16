package PSP;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements BasicActions{

    private List<Card> dealerCards;
    private Card lastDrawnCard;

    Dealer() {
        lastDrawnCard = null;
        dealerCards = new ArrayList<>();
    }

    @Override
    public void drawStartingCards() {
        dealerCards.clear();
        dealerCards.add(Deck.getInstance().drawACard());
        dealerCards.add(Deck.getInstance().drawACard());
    }

    @Override
    public void hit() {

        lastDrawnCard = Deck.getInstance().drawACard();
        dealerCards.add(lastDrawnCard);

    }

    @Override
    public int getHandValue() {

        int combinedValue = 0;

        for (Card card : dealerCards)
            combinedValue += card.getCardValue();

        return combinedValue;

    }

    boolean hasBlackJack() {

        return this.dealerCards.size() == 2 && this.getHandValue() == 21;

    }

    List<Card> getDealerCards() {
        return this.dealerCards;
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

package PSP;

import java.util.ArrayList;
import java.util.List;

public class Player implements BasicActions{

    private List<Card> playerCards;

    private int money = Settings.STARTING_CASH;

    private int bet;
    private int insuranceBet;

    public Player() {
        playerCards = new ArrayList<>();
        this.bet = 0;
        insuranceBet = 0;
    }

    @Override
    public void drawStartingCards() {
        playerCards.clear();
        playerCards.add(Deck.getInstance().drawACard());
        playerCards.add(Deck.getInstance().drawACard());
    }

    @Override
    public void hit() {

        Card tempCard;
        tempCard = Deck.getInstance().drawACard();

        if (tempCard.getCardValue() == 11 && getHandValue() > 10)
            tempCard.setCardValue(1);

        getPlayerCards().add(tempCard);

    }

    @Override
    public int getHandValue() {

        int combinedValue = 0;
        for (Card card : playerCards)
            combinedValue += card.getCardValue();
        return combinedValue;
    }

    boolean doubleDown() {

        if (bet < money) {
            money -= bet;
            bet *= 2;
            hit();
            return true;
        } else {
            return false;
        }
    }

    void surrender() {
        this.money += this.bet / 2;
    }

    boolean takeInsurance() {

        if (money >= this.bet / 2) {
            insuranceBet += this.bet / 2;
            money += -insuranceBet;
            return true;
        }

        return false;
    }

    List<Card> getPlayerCards() {
        return playerCards;
    }

    int getMoney() {
        return money;
    }

    public boolean bet(int amount) {

        this.bet = amount;

        if (amount <= money && amount > 0) {
            money -= amount;
            return true;
        }
        else
            return false;
    }

    void payout(Dealer dealer) {

        payInsurance(dealer);

        if (playerCards.size() == 2)
            this.money += bet * Settings.BLACKJACK_PAY;
        else
            this.money += bet * Settings.NORMAL_PAY;

    }

    private void payInsurance(Dealer dealer) {

        if (dealer.hasBlackJack()) {
            this.money += insuranceBet * Settings.INSURANCE_PAY;
            insuranceBet = 0;
        }

    }


}

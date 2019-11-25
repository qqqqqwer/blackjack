import java.util.ArrayList;
import java.util.List;

public class Player implements BasicActions {

    private Turn turn;
    private Deck deck;
    private List<Card> playerCards;
    private Dealer dealer;

    private int money = Settings.STARTING_CASH;

    private int bet;
    private int insuranceBet;

    Player(Deck deck, Turn turn, Dealer dealer) {
        this.deck = deck;
        this.turn = turn;
        this.dealer = dealer;
        playerCards = new ArrayList<>();
        this.bet = 0;
        insuranceBet = 0;
    }

    void drawStartingCards() {
        playerCards.clear();
        playerCards.add(deck.drawACard());
        playerCards.add(deck.drawACard());
    }

    @Override
    public void hit() {

        Card tempCard;
        tempCard = deck.drawACard();

        if (tempCard.getCardValue() == 11 && getHandValue() > 10)
            tempCard.setCardValue(1);

        getPlayerCards().add(tempCard);

    }

    @Override
    public void stand() {

    }

    @Override
    public int getHandValue() {

        int combinedValue = 0;
        for (Card card : playerCards)
            combinedValue += card.getCardValue();
        return combinedValue;
    }

    boolean doubleDown() {

        if (bet > money) {
            money -= bet;
            bet *= 2;
            hit();
            return false;
        } else {
            return true;
        }
    }

    void split() {

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

    boolean bet(int amount) {

        this.bet = amount;

        if (amount <= money && amount > 0) {
            money -= amount;
            return true;
        }
        else
            return false;
    }

    void giveMoney(int value) {
        this.money += value;
    }

    void payout() {

        payInsurance();

        if (playerCards.size() == 2)
            this.money += bet * Settings.BLACKJACK_PAY;
        else
            this.money += bet * Settings.NORMAL_PAY;

    }

    private void payInsurance() {

        if (this.dealer.hasBlackJack()) {
            this.money += insuranceBet * Settings.INSURANCE_PAY;
            insuranceBet = 0;
        }

    }


}

public class Dealer implements BasicActions {

    private Turn turn;

    Dealer(Deck deck, Turn turn) {
        this.turn = turn;
    }

    @Override
    public void hit(Card[] cards) {
        turn.nextTurn();
    }

    public void stand() {
        turn.nextTurn();
    }

    @Override
    public int getHandValue() {
        return 0;
    }
}

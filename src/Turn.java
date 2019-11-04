import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Turn {

    private int turn;
    private final ImageView faceDownCard = new ImageView(new Image("file:resources/cards/facedown.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true));

    Turn() {
        turn = 0;
    }

    void nextTurn() {
        turn++;
        turn %= 2;
    }

    public int getTurn() {
        turn %= 3;
        return turn;
    }

    public static final int BIDDING_TURN = 0;
    public static final int PLAYERS_TURN = 1;
    public static final int DEALERS_TURN = 2;
}

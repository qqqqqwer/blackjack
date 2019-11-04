import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cardsInDeck;
    private List<Card> drawnCards;
    private ImageView faceDownCardImage;

    Deck() {
        initializeCards();
        reshuffleDeck();
    }

    public ImageView getFaceDownCardImage() {
        return faceDownCardImage;
    }

    private void initializeCards() {

        faceDownCardImage = new ImageView(new Image("file:resources/cards/facedown.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true));

        drawnCards = new ArrayList<>();
        this.cardsInDeck = new ArrayList<Card>();
        this.cardsInDeck.add(new Card(2, "two", new ImageView(new Image("file:resources/cards/2C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(2, "two", new ImageView(new Image("file:resources/cards/2D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(2, "two", new ImageView(new Image("file:resources/cards/2H.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(2, "two", new ImageView(new Image("file:resources/cards/2S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(3, "three", new ImageView(new Image("file:resources/cards/3C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(3, "three", new ImageView(new Image("file:resources/cards/3D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(3, "three", new ImageView(new Image("file:resources/cards/3h.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(3, "three", new ImageView(new Image("file:resources/cards/3S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(4, "four", new ImageView(new Image("file:resources/cards/4C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(4, "four", new ImageView(new Image("file:resources/cards/4D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(4, "four", new ImageView(new Image("file:resources/cards/4H.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(4, "four", new ImageView(new Image("file:resources/cards/4S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(5, "five", new ImageView(new Image("file:resources/cards/5C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(5, "five", new ImageView(new Image("file:resources/cards/5D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(5, "five", new ImageView(new Image("file:resources/cards/5H.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(5, "five", new ImageView(new Image("file:resources/cards/5S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(6, "six",  new ImageView(new Image("file:resources/cards/6C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(6, "six",  new ImageView(new Image("file:resources/cards/6D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(6, "six",  new ImageView(new Image("file:resources/cards/6H.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(6, "six",  new ImageView(new Image("file:resources/cards/6S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(7, "seven",  new ImageView(new Image("file:resources/cards/7C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(7, "seven",  new ImageView(new Image("file:resources/cards/7D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(7, "seven",  new ImageView(new Image("file:resources/cards/7H.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(7, "seven",  new ImageView(new Image("file:resources/cards/7S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(8, "eight",  new ImageView(new Image("file:resources/cards/8C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(8, "eight",  new ImageView(new Image("file:resources/cards/8D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(8, "eight",  new ImageView(new Image("file:resources/cards/8H.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(8, "eight",  new ImageView(new Image("file:resources/cards/9S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(9, "nine",  new ImageView(new Image("file:resources/cards/9C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(9, "nine",  new ImageView(new Image("file:resources/cards/9D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(9, "nine",  new ImageView(new Image("file:resources/cards/9H.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(9, "nine",  new ImageView(new Image("file:resources/cards/9S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "ten",  new ImageView(new Image("file:resources/cards/10C.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "ten",  new ImageView(new Image("file:resources/cards/10D.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "ten",  new ImageView(new Image("file:resources/cards/10H.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "ten",  new ImageView(new Image("file:resources/cards/10S.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "knight", new ImageView(new Image("file:resources/cards/JC.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "knight", new ImageView(new Image("file:resources/cards/JD.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "knight", new ImageView(new Image("file:resources/cards/JH.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "knight", new ImageView(new Image("file:resources/cards/JS.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "queen", new ImageView(new Image("file:resources/cards/QC.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "queen", new ImageView(new Image("file:resources/cards/QD.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "queen", new ImageView(new Image("file:resources/cards/QH.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "queen", new ImageView(new Image("file:resources/cards/QS.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "king", new ImageView(new Image("file:resources/cards/QC.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "king", new ImageView(new Image("file:resources/cards/QD.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "king", new ImageView(new Image("file:resources/cards/QH.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(10, "king", new ImageView(new Image("file:resources/cards/QS.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(11, "ace", new ImageView(new Image("file:resources/cards/AC.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(11, "ace", new ImageView(new Image("file:resources/cards/AD.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(11, "ace", new ImageView(new Image("file:resources/cards/AH.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
        this.cardsInDeck.add(new Card(11, "ace", new ImageView(new Image("file:resources/cards/AS.png", Settings.IMAGE_WIDTH, Settings.IMAGE_HEIGHT, true, true))));
    };

    void reshuffleDeck() {

        cardsInDeck.addAll(drawnCards);
        drawnCards.clear();

        Collections.shuffle(cardsInDeck);
    }

    Card drawACard() {

        Card cardToDraw = cardsInDeck.get(0);
        drawnCards.add(cardToDraw);
        cardsInDeck.remove(cardToDraw);

        return cardToDraw;
    }

}

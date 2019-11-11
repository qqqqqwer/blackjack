import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Card {

    private int value;
    private String name;
    private ImageView image;

    Card(int value, String name, ImageView image) {
        this.value = value;
        this.name = name;
        this.image = image;
    }

    ImageView getImage() {
        return this.image;
    }

    int getCardValue() {
        return this.value;
    }

    void setCardValue(int value) {
        this.value = value;
    }

    String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name + " " + this.value;
    }
}

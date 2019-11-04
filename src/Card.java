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

    double getImageWidth() {
        return this.image.getImage().getWidth();
    }

    double getImageHeight() {
        return this.image.getImage().getHeight();
    }

    int getCardValue() {
        return value;
    }
}

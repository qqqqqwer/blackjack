package PSP;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class MenuWindow {

    private Stage stageHandler;

    MenuWindow(Stage stage) {
        this.stageHandler = stage;
    }

    Scene getView() {

        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: black");
        root.setAlignment(Pos.CENTER);

        Label startGameLabel = new Label("Start");
        startGameLabel.setTextFill(Color.WHITE);
        startGameLabel.setFont(new Font(50));
        startGameLabel.setOnMouseClicked(mouseEvent -> { stageHandler.setScene(new GameWindow(this, stageHandler).getView()); });

        Label exitLabel = new Label("Exit");
        exitLabel.setTextFill(Color.WHITE);
        exitLabel.setFont(new Font(50));
        exitLabel.setOnMouseClicked(mouseEvent -> { Platform.exit(); });

        ImageView title = new ImageView("file:resources/title.png");
        VBox.setMargin(title, new Insets(0, 0, (double)Settings.WINDOW_HEIGHT / 6, 0));

        root.getChildren().addAll(title, startGameLabel, exitLabel);

        return new Scene(root, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }


}

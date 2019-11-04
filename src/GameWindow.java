import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class GameWindow {

    private MenuWindow menuWindow;
    private Stage stageHandler;

    private Player player;
    private Dealer dealer;

    private Label statusLabel;
    private final Color textColor = Color.WHITE;

    GameWindow(MenuWindow menuWindow, Stage stage) {

        this.menuWindow = menuWindow;
        this.stageHandler = stage;

        Deck deck = new Deck();
        Turn turn = new Turn();
        player = new Player(deck, turn);
        player.drawStartingCards();
        dealer = new Dealer(deck, turn);
    }

    private void goBackToMenu() {
        stageHandler.setScene(menuWindow.getView());
    }

    Scene getView() {

        GridPane root = createGrid();
        addGameElements(root);

        return new Scene(root, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
    }

    private GridPane createGrid() {

        GridPane pane = new GridPane();

        final int innerRowCount = 3;
        final int outerRowCount = 2;

        final int outerRowSize = 100;
        final int innerRowSize = (Settings.WINDOW_HEIGHT - (outerRowCount * outerRowSize)) / innerRowCount;

        pane.getRowConstraints().add(new RowConstraints(outerRowSize));
        pane.getRowConstraints().add(new RowConstraints(innerRowSize));
        pane.getRowConstraints().add(new RowConstraints(innerRowSize));
        pane.getRowConstraints().add(new RowConstraints(innerRowSize));
        pane.getRowConstraints().add(new RowConstraints(outerRowSize));

        final int columnSize = Settings.WINDOW_WIDTH;

        pane.getColumnConstraints().add(new ColumnConstraints(columnSize));

        pane.setGridLinesVisible(false);
        String backgroundColor = "#354262";
        pane.setStyle("-fx-background-color: " + backgroundColor);

        return pane;
    }

    private void addGameElements(GridPane root) {

        addLabels(root);
        loadBiddingUI(root);
    }

    private void addLabels(GridPane root) {

        final int vBoxSpacing = 10;
        VBox vBox = new VBox(vBoxSpacing);
        vBox.setAlignment(Pos.CENTER);

        final int statusLabelFontSize = 17;
        statusLabel = new Label("Bidding..");
        statusLabel.setTextFill(textColor);
        statusLabel.setFont(new Font(statusLabelFontSize));

        final int playerLabelRowIndex = 4;
        final int playerLabelColumnIndex = 0;
        final int textSize = 30;

        Label playerLabel = new Label("Player");
        playerLabel.setTextFill(textColor);
        playerLabel.setFont(new Font(textSize));

        vBox.getChildren().addAll(playerLabel, statusLabel);

        GridPane.setHalignment(vBox, HPos.CENTER);
        root.add(vBox, playerLabelColumnIndex, playerLabelRowIndex);

        final int dealerLabelRowIndex = 0;
        final int dealerLabelColumnIndex = 0;

        Label dealerLabel = new Label("Dealer");
        dealerLabel.setTextFill(textColor);
        dealerLabel.setFont(new Font(textSize));
        GridPane.setHalignment(dealerLabel, HPos.CENTER);

        root.add(dealerLabel, dealerLabelColumnIndex, dealerLabelRowIndex);
    }

    private void addCards(GridPane root) {

        final int playerCardsRowIndex = 3;
        final int playerCardsColumnIndex = 0;

        HBox playerCardsContainer = new HBox(8);
        playerCardsContainer.setAlignment(Pos.CENTER);
        playerCardsContainer.setFillHeight(true);

        for (Card card : player.getPlayerCards())
            playerCardsContainer.getChildren().add(card.getImage());



        root.add(playerCardsContainer, playerCardsColumnIndex, playerCardsRowIndex);
    }

    private void setUpPlayerButtonsStyle(Button[] buttons, final int buttonsWidth, final int buttonsHeight) {

        final String buttonsColor = "black";
        final int buttonsFontSize = 16;

        for (Button button : buttons) {
            button.setPrefWidth(buttonsWidth);
            button.setPrefHeight(buttonsHeight);
            button.setTextFill(textColor);
            button.setStyle("-fx-background-color: " + buttonsColor);
            button.setFont(new Font(buttonsFontSize));
        }
    }

    private void loadBiddingUI(GridPane root) {

        final int columnIndex = 0;
        final int rowIndex = 2;

        final int spacing = 13;
        VBox vBox = new VBox(spacing);
        vBox.setAlignment(Pos.CENTER);

        final int labelFontSize = 30;
        Label bidLabel = new Label("Enter your bid (You currently have $" + player.getMoney() + ")");
        bidLabel.setFont(new Font(labelFontSize));
        bidLabel.setStyle("-fx-font-weight: bold");
        bidLabel.setTextFill(Color.WHITE);

        final int textFieldFontSize = 23;
        TextField bidTextField = new TextField("");
        bidTextField.setAlignment(Pos.CENTER);
        bidTextField.setFont(new Font(textFieldFontSize));
        bidTextField.setStyle("-fx-background-color: transparent; -fx-text-inner-color: orange;");
        bidTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                bidTextField.setText(newValue.replaceAll("[^\\d]", ""));

            if (newValue.equals(""))
                bidTextField.setText("1");
        });

        Button bidButton = new Button("Confirm bid");
        bidButton.setOnAction(actionEvent -> {

            if (player.bet(Integer.parseInt(bidTextField.getText()))) {
                try {
                    loadPlayingUI(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Not enough money");

        });

        final int buttonsWidth = 150;
        final int buttonsHeight = 30;
        setUpPlayerButtonsStyle(new Button[] {bidButton}, buttonsWidth, buttonsHeight);

        vBox.getChildren().addAll(bidLabel, bidTextField, bidButton);

        clearCell(root, rowIndex, columnIndex);
        root.add(vBox, columnIndex, rowIndex);
    }

    private void clearCell(GridPane root, final int rowIndex, final int columnIndex) {

        for (Node node : root.getChildren()) {
            if (GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node) == columnIndex) {
                root.getChildren().remove(node);
                break;
            }
        }
    }

    private void loadPlayingUI(GridPane root) throws Exception{
        final int rowIndex = 2;
        final int columnIndex = 0;

        statusLabel.setText("Drawing..");
        addCards(root);


        statusLabel.setText("Your hand value is: " + player.getHandValue());

        final int hBoxSpacing = 75;
        HBox hBox = new HBox(hBoxSpacing);
        hBox.setAlignment(Pos.CENTER);

        Button hitButton = new Button("HIT");
        hitButton.setOnAction(actionEvent -> {

        });

        Button standButton = new Button("STAND");
        Button doubleDownButton = new Button("DOUBLE DOWN");
        Button surrenderButton = new Button("SURRENDER");
        Button takeInsuranceButton = new Button("TAKE INSURANCE");

        final int buttonsWidth = 150;
        final int buttonsHeight = 50;
        setUpPlayerButtonsStyle(new Button[] {hitButton, standButton, doubleDownButton, surrenderButton, takeInsuranceButton}, buttonsWidth, buttonsHeight);
        hBox.getChildren().addAll(hitButton, standButton, doubleDownButton, surrenderButton, takeInsuranceButton);

        GridPane.setHalignment(hBox, HPos.CENTER);
        clearCell(root, rowIndex, columnIndex);
        root.add(hBox, columnIndex, rowIndex);
    }
}

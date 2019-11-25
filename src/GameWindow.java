import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

class GameWindow {

    private MenuWindow menuWindow;
    private Stage stageHandler;

    private Deck deck;

    private Player player;
    private Dealer dealer;

    private Label statusLabel;
    private final Color textColor = Color.WHITE;

    private HBox playerCardContainer;
    private HBox dealerCardContainer;

    private GridPane root;

    GameWindow(MenuWindow menuWindow, Stage stage) {

        this.menuWindow = menuWindow;
        this.stageHandler = stage;

        deck = new Deck();
        Turn turn = new Turn();
        dealer = new Dealer(deck, turn);
        player = new Player(deck, turn, dealer);
    }

    private void goBackToMenu() {
        stageHandler.setScene(menuWindow.getView());
    }

    Scene getView() {

        root = createGrid();
        addGameElements();

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

    private void addGameElements() {

        addLabels();
        loadBiddingUI();
    }

    private void addLabels() {

        final int vBoxSpacing = 10;
        VBox vBox = new VBox(vBoxSpacing);
        vBox.setAlignment(Pos.CENTER);

        final int statusLabelFontSize = 24;
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

    private void addCards() {

        player.drawStartingCards();
        dealer.drawStartingCards();

        final int playerCardsRowIndex = 3;
        final int playerCardsColumnIndex = 0;

        playerCardContainer = new HBox(8);
        playerCardContainer.setAlignment(Pos.CENTER);
        playerCardContainer.setFillHeight(true);
        addPlayerCardsToUI();

        final int dealerCardsRowIndex = 1;
        final int dealerCardsColumnIndex = 0;

        dealerCardContainer = new HBox(8);
        dealerCardContainer.setAlignment(Pos.CENTER);
        dealerCardContainer.setFillHeight(true);
        addStartingDealerCardsToUI();

        root.add(dealerCardContainer, dealerCardsColumnIndex, dealerCardsRowIndex);
        root.add(playerCardContainer, playerCardsColumnIndex, playerCardsRowIndex);
    }

    private void setUpPlayerButtonsStyle(List<Button> buttons, final int buttonsWidth, final int buttonsHeight) {

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

    private void setUpPlayerButtonStyle(Button button, final int buttonsWidth, final int buttonsHeight) {

        final String buttonsColor = "black";
        final int buttonsFontSize = 16;

        button.setPrefWidth(buttonsWidth);
        button.setPrefHeight(buttonsHeight);
        button.setTextFill(textColor);
        button.setStyle("-fx-background-color: " + buttonsColor);
        button.setFont(new Font(buttonsFontSize));
    }

    private void loadBiddingUI() {

        final int columnIndex = 0;
        final int rowIndex = 2;

        final int playerCardsRow = 3;
        final int dealerCardsRow = 1;

        clearCell(playerCardsRow, columnIndex);
        clearCell(dealerCardsRow, columnIndex);

        final int spacing = 13;
        VBox vBox = new VBox(spacing);
        vBox.setAlignment(Pos.CENTER);

        final int labelFontSize = 30;
        Label bidLabel = new Label("Enter your bid (You currently have $" + player.getMoney() + ")");
        bidLabel.setFont(new Font(labelFontSize));
        bidLabel.setStyle("-fx-font-weight: bold");
        bidLabel.setTextFill(Color.WHITE);

        final int textFieldFontSize = 23;
        TextField bidTextField = new TextField("1");
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

            if (player.bet(Integer.parseInt(bidTextField.getText())))
                loadPlayingUI();
            else
                statusLabel.setText("Invalid sum.");

        });

        final int buttonsWidth = 150;
        final int buttonsHeight = 30;
        setUpPlayerButtonStyle(bidButton, buttonsWidth, buttonsHeight);

        vBox.getChildren().addAll(bidLabel, bidTextField, bidButton);

        clearCell(rowIndex, columnIndex);
        root.add(vBox, columnIndex, rowIndex);
    }

    private void clearCell(final int rowIndex, final int columnIndex) {

        for (Node node : root.getChildren()) {
            if (GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node) == columnIndex) {
                root.getChildren().remove(node);
                break;
            }
        }
    }

    private void addPlayerCardsToUI() {

        playerCardContainer.getChildren().clear();
        for (Card card : player.getPlayerCards())
            playerCardContainer.getChildren().add(card.getImage());

    }

    private void addStartingDealerCardsToUI() {

        dealerCardContainer.getChildren().clear();
        dealerCardContainer.getChildren().addAll(dealer.getDealerCards().get(0).getImage(), deck.getFaceDownCardImage());
    }

    private void loadPlayingUI() {
        deck.reshuffleDeck();
        addCards();
        addPlayerButtons();
    }

    private List<Button> buttons = new ArrayList<>();
    private void addPlayerButtons() {

        final int rowIndex = 2;
        final int columnIndex = 0;

        statusLabel.setText("Drawing..");
        statusLabel.setText("Your hand value is: " + player.getHandValue());

        final int hBoxSpacing = 75;
        HBox hBox = new HBox(hBoxSpacing);
        hBox.setAlignment(Pos.CENTER);

        Button surrenderButton = new Button("SURRENDER");
        Button takeInsuranceButton = new Button("TAKE INSURANCE");
        Button doubleDownButton = new Button("DOUBLE DOWN");
        Button hitButton = new Button("HIT");

        hitButton.setOnAction(actionEvent -> {
            player.hit();
            statusLabel.setText("Your hand value is: " + player.getHandValue());
            addPlayerCardsToUI();
            checkIfPlayerWentOver();
            hBox.getChildren().removeAll(doubleDownButton, surrenderButton, takeInsuranceButton);
        });

        Button standButton = new Button("STAND");

        standButton.setOnAction(actionEvent -> {
            dealersTurn();
            hBox.getChildren().removeAll(hitButton, standButton, doubleDownButton, surrenderButton, takeInsuranceButton);
        });

        surrenderButton.setOnAction(actionEvent -> {

            hBox.getChildren().remove(surrenderButton);
            player.surrender();
            loadBiddingUI();

        });

        takeInsuranceButton.setOnAction(actionEvent -> {

            hBox.getChildren().remove(takeInsuranceButton);

            if (!player.takeInsurance())
                statusLabel.setText("Not enough money");
        });

        doubleDownButton.setOnAction(actionEvent -> {

            hBox.getChildren().remove(doubleDownButton);

            player.hit();
            statusLabel.setText("Your hand value is: " + player.getHandValue());
            addPlayerCardsToUI();
            checkIfPlayerWentOver();
            dealersTurn();
        });

        final int buttonsWidth = 150;
        final int buttonsHeight = 50;

        List<Button> buttons = new ArrayList<>();
        buttons.add(hitButton);
        buttons.add(standButton);
        buttons.add(doubleDownButton);
        
        if (dealer.getFaceUpCardName().equals("ace")) {
            buttons.add(surrenderButton);
            buttons.add(takeInsuranceButton);
        }

        if (dealer.getFaceUpCardValue() == 10)
            buttons.add(surrenderButton);


        setUpPlayerButtonsStyle(buttons, buttonsWidth, buttonsHeight);
        hBox.getChildren().addAll(buttons);

        GridPane.setHalignment(hBox, HPos.CENTER);
        clearCell(rowIndex, columnIndex);
        root.add(hBox, columnIndex, rowIndex);
    }

    private void dealersTurn() {

        final int playerCardsRow = 2;
        final int playerCardsColumn = 0;

        clearCell(playerCardsRow, playerCardsColumn);

        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(actionEvent1 -> {dealerCardContainer.getChildren().add(dealer.getLastDrawnCard().getImage());});
        revealDealerCards();

        while (dealer.getHandValue() < 17) {
            dealer.hit();
            pause.play();
        }

        PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
        pause2.setOnFinished(actionEvent -> { checkForRoundWinner(); });
        pause2.play();

    }

    private void revealDealerCards() {
        dealerCardContainer.getChildren().clear();

        for (Card card : dealer.getDealerCards())
            dealerCardContainer.getChildren().add(card.getImage());
    }


    private void checkForRoundWinner() {

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(actionEvent -> { loadBiddingUI(); });

        if (dealer.getHandValue() > Settings.ROUND_GOAL) {
            statusLabel.setText("Dealer went over. Player won.");
            player.payout();
            pause.play();
        } else if (dealer.getHandValue() > player.getHandValue()) {
            statusLabel.setText("Dealer's hand value is greater. Dealer won.");
            pause.play();
        } else if (player.getHandValue() > dealer.getHandValue()) {
            statusLabel.setText("Player's hand value is greater. Player won.");
            player.payout();
            pause.play();
        } else if (player.getHandValue() == dealer.getHandValue()) {
            statusLabel.setText("Both hands are equal. Draw.");
            pause.play();
        }
    }

    private void checkIfPlayerWentOver() {

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(actionEvent -> { loadBiddingUI(); });

        if (player.getHandValue() > Settings.ROUND_GOAL) {
            statusLabel.setText("Player went over. Dealer won.");
            pause.play();
        }

    }

}

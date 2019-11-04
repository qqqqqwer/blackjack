import javafx.application.Application;
import javafx.stage.Stage;

//class responsible for displaying windows
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setScene(new MenuWindow(stage).getView());
        stage.setResizable(false);
        stage.show();
    }

}

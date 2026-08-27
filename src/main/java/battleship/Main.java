package battleship;
import battleship.Game.Game;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

import static battleship.Game.Game.back;
import static battleship.Game.Game.finish;

public class Main extends Application {
    Welcome welcome = new Welcome();

    @Override
    public void start(Stage stage) {
        stage.setScene(welcome.startGame());
        welcome.bot.setOnAction(actionEvent -> {
            Game game = new Game();
            stage.setScene(game.createGUI());
            stage.show();
        });
        back.setOnAction(actionEvent -> stage.setScene(welcome.startGame()));
        welcome.end.setOnAction(actionEvent -> stage.close());
        finish.setOnFinished(actionEvent -> stage.setScene(welcome.startGame()));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

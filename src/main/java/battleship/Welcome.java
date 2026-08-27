package battleship;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Welcome {
    public Button bot = new Button("1 vs. COM - Start");
    public Button end = new Button("Beenden");
    public Scene startGame() {
        VBox main = new VBox();
        HBox buttons = new HBox();

        Label title = new Label("Schiffe versenken");

        main.getStyleClass().add("main");
        title.getStyleClass().add("title");
        buttons.getStyleClass().add("buttons");

        bot.getStyleClass().add("start-button");
        end.getStyleClass().add("start-button");

        buttons.getChildren().addAll(bot, end);
        main.getChildren().addAll(title, buttons);
        Scene scene = new Scene(main, 1000, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/startScreen.css")).toExternalForm());
        return scene;
    }
}

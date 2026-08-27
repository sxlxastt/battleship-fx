package battleship;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Welcome {
    public Button bot = new Button("1 vs. COM - Start");
    public Button end = new Button("Beenden");
    public Scene startGame() {
        VBox main = new VBox();
        HBox buttons = new HBox();
        Label title = new Label("Schiffe versenken");

        buttons.getChildren().addAll(bot, end);
        main.getChildren().addAll(title, buttons);
        return new Scene(main, 600, 400);
    }
}

package battleship.Game;
import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class Game {
    private final Button setshipB = new Button("Setzen");
    public Spielfeld player1 = new Spielfeld();
    public Spielfeld player2 = new Spielfeld();
    GridPane pane = new GridPane();
    public static PauseTransition finish = new PauseTransition(Duration.seconds(3));
    private static final Random random = new Random();

    public static Button back = new Button("Zurück zum Menü | Spiel beenden");
    private final HBox grid = new HBox();
    public Label output = new Label("Ausgabe");

    public String gewonnen() {
        for (int i = 0; i < player1.getFeld().length; i++)
            for (int j = 0; j < player1.getFeld()[i].length; j++) {
                for (int k = 0; k < player2.getFeld().length; k++)
                    for (int l = 0; l < player2.getFeld()[k].length; l++) {
                        if (player2.getFeld()[k][l].wurdeSchiffVersenkt() && player2.getFeld()[k][l].istSchiffAufFeld()) {
                            return "Du hast gewonnen! Das Spiel ist beendet.";
                        }
                    }
                if (player1.getFeld()[i][j].wurdeSchiffVersenkt() && player1.getFeld()[i][j].istSchiffAufFeld()) {
                    finish.play();
                    return "Du hast verloren! Das Spiel ist beendet";
                }
            }
        return null;
    }

    public String setzeSchiff(Spielfeld player, int x, int y) {
        int maxShips = 10;
        if (maxShips == player.getSetShips()) {
            setshipB.setDisable(true);
            return "Maximale Anzahl an Schiffen erreicht";
        }
        player.setzeSchiffAufGebiet(x,y);
        return "";
    }

    private GridPane getPane() {
        for (int x = 0; x < player1.getFeld().length; x++) {
            Label label = new Label(Integer.toString(x+1));
            GridPane.setHalignment(label, HPos.CENTER);
            label.setFont(Font.font(20));
            label.setTextFill(Color.WHITE);
            pane.add(label,x+1,0);
        }
        for (int y = 0; y < player1.getFeld().length; y++) {
            Label label = new Label(Integer.toString(y+1));
            GridPane.setHalignment(label, HPos.CENTER);
            label.setFont(Font.font(20));
            label.setTextFill(Color.WHITE);
            pane.add(label,0,y+1);
        }
        for (int i = 0; i < player1.getFeld().length; i++) {
            for (int j = 0; j < player1.getFeld()[i].length; j++) {
                        if (player1.getFeld()[i][j].istSchiffAufFeld() && !player1.getFeld()[i][j].wurdeFeldBeschossen()) {
                            pane.add(new Rectangle(50,50, Color.RED),i+1,j+1);
                        } else if (player2.getFeld()[i][j].wurdeFeldBeschossen() && !player2.getFeld()[i][j].istSchiffAufFeld()) {
                            pane.add(new Rectangle(50,50, Color.YELLOW),i+1,j+1);
                        } else if (player2.getFeld()[i][j].wurdeFeldBeschossen() && player2.getFeld()[i][j].istSchiffAufFeld()) {
                            pane.add(new Rectangle(50,50, Color.MAGENTA),i+1,j+1);
                        } else if (player1.getFeld()[i][j].istSchiffAufFeld() && player1.getFeld()[i][j].wurdeFeldBeschossen()) {
                            pane.add(new Rectangle(50,50, Color.ORANGE), i+1, j+1);
                        } else if (player1.getFeld()[i][j].wurdeFeldBeschossen() && !player1.getFeld()[i][j].istSchiffAufFeld()) {
                            pane.add(new Rectangle(50,50,Color.LIMEGREEN), i+1, j+1);
                        } else {
                            pane.add(new Rectangle(50,50, Color.BLUE),i+1,j+1);
                        }
            }

        }
        pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/mainScreen.css")).toExternalForm());
        return pane;
    }
    private void updateGUI() {
        GridPane pane = new GridPane();
        grid.getChildren().set(0,getPane());
        pane.setPadding(new Insets(40, 10, 10, 10));
        if (gewonnen() != null) {
            output.setText(gewonnen());
        }
    }

    public Scene createGUI() {
        Button shootB = new Button("Schießen");
        PauseTransition botShot = new PauseTransition(Duration.seconds(2));
        botShot.setOnFinished(event -> {
            player1.schiesse(random.nextInt(1,11), random.nextInt(1,11));
            updateGUI();
            shootB.setDisable(false);
        });
        VBox main = new VBox();
        VBox controls = new VBox();
        HBox shoot = new HBox();
        HBox setship = new HBox();

        Label title = new Label("Schiffe versenken");
        Label shootL = new Label("Schießen");

        TextField shootX = new TextField();
        TextField shootY = new TextField();

        main.getStyleClass().add("main");
        controls.getStyleClass().add("controls");
        shoot.getStyleClass().add("control-row");
        setship.getStyleClass().add("control-row");

        title.getStyleClass().add("title");
        shootL.getStyleClass().add("control-label");
        pane.getStyleClass().add("pane");

        shootX.getStyleClass().add("coordinate-field");
        shootY.getStyleClass().add("coordinate-field");



        shootB.getStyleClass().add("action-button");
        setshipB.getStyleClass().add("action-button");

        output.getStyleClass().add("output");
        back.getStyleClass().add("back-button");
        shootY.setPromptText("Y-Koordinate");
        shootX.setPromptText("X-Koordinate");

        shootB.setOnAction(actionEvent -> {
            try {
                int shotX = Integer.parseInt(shootX.getText());
                int shotY = Integer.parseInt(shootY.getText());
                output.setText(player2.schiesse(shotX, shotY));
                shootB.setDisable(true);
                botShot.play();
            } catch (NumberFormatException n) {
                output.setText("Keine ganze Zahl eingegeben!");
            } catch (ArrayIndexOutOfBoundsException a) {
                output.setText("Zu hohe Zahl eingegeben!");
            }
            updateGUI();
        });
        shoot.getChildren().addAll(shootL, shootX, shootY, shootB);

        Label setshipL = new Label("Schiff setzen");
        setshipL.getStyleClass().add("control-label");
        TextField setshipX = new TextField();
        TextField setshipY = new TextField();

        setshipY.setPromptText("Y-Koordinate");
        setshipX.setPromptText("X-Koordinate");
        setshipX.getStyleClass().add("coordinate-field");
        setshipY.getStyleClass().add("coordinate-field");
        setshipB.setOnAction(actionEvent -> {
            try {
                int setX = Integer.parseInt(setshipX.getText());
                int setY = Integer.parseInt(setshipY.getText());
                output.setText(setzeSchiff(player1, setX, setY));
            } catch (NumberFormatException n) {
                output.setText("Keine ganze Zahl eingegeben!");
            } catch (ArrayIndexOutOfBoundsException a) {
                output.setText("Zu hohe Zahl eingegeben!");
            }
            updateGUI();
        });
        setship.getChildren().addAll(setshipL, setshipX, setshipY, setshipB);
        controls.getChildren().addAll(shoot, setship, output, back);
        grid.getChildren().addAll(getPane(), controls);
        pane.setPadding(new Insets(40, 10, 10, 10));
        pane.setHgap(10);
        pane.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        main.getChildren().addAll(title, grid);
        Scene scene = new Scene(main, 1200, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/mainScreen.css")).toExternalForm());
        return scene;
    }
}

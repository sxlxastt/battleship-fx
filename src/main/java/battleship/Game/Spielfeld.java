package battleship.Game;

public class Spielfeld {

    private int setShips;
    private final Gebiet[][] feld = new Gebiet[10][10];
    public Spielfeld() {
        for (int i = 0; i < feld.length; i++)
            for (int j = 0; j < feld[i].length; j++) {
                feld[i][j] = new Gebiet();
            }
    }

    public void setzeSchiffAufGebiet(int x, int y) {
        feld[x-1][y-1].setzeSchiff();
        setShips++;
    }

    public String schiesse(int x, int y) {
        if (!feld[x-1][y-1].wurdeFeldBeschossen()) {
            feld[x-1][y-1].beschiesseFeld();
            if (feld[x-1][y-1].wurdeSchiffVersenkt()) {
                return "Treffer";
            }
            return "Daneben";
        }
        return null;
    }

    public Gebiet[][] getFeld() {
        return feld;
    }

    public int getSetShips() {
        return setShips;
    }
}

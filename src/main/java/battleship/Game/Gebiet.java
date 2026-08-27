package battleship.Game;

public class Gebiet {
    private boolean schiffIstAufFeld;
    private boolean feldWurdeBeschossen;
    private boolean schiffVersenkt;

    public Gebiet() {
        schiffIstAufFeld = false;
        feldWurdeBeschossen = false;
        schiffVersenkt = false;
    }
    public boolean wurdeSchiffVersenkt() {
        return this.schiffVersenkt;
    }
    public boolean wurdeFeldBeschossen() {
        return this.feldWurdeBeschossen;
    }
    public boolean istSchiffAufFeld() {
        return this.schiffIstAufFeld;
    }
    public void beschiesseFeld() {
        this.feldWurdeBeschossen = true;
        if (istSchiffAufFeld()) {
            this.schiffVersenkt = true;
        }
    }
    public void setzeSchiff() {
        if (!istSchiffAufFeld()) {
            this.schiffIstAufFeld = true;
        }
    }

}

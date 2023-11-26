package tuile;

import java.awt.Image;

public class Tuile {
    public Image img;
    public boolean zonePosable = false; //Plus tard pour savoir si une zone est une zone où une tour peut être posé
    public boolean estLibre = false; //Pour savoir s'il y a déjà une tour dessus
}

package entite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import main.Controle;
import main.GamePanel;

public class Curseur extends Entite {
    GamePanel gp;
    Controle touchePressee;
    Image up,down,left,right;
    String direction;

    public Curseur(GamePanel gp, Controle toucheP){
        this.gp = gp; 
        this.touchePressee = toucheP;
        setValeurParDefaut(); //des coordonnées x, y et la vitesse

        up = Toolkit.getDefaultToolkit().getImage("ressource/curseur/nayeonUP.png");
        down = Toolkit.getDefaultToolkit().getImage("ressource/curseur/nayeonDOWN.png");
        left = Toolkit.getDefaultToolkit().getImage("ressource/curseur/nayeonLEFT.png");
        right = Toolkit.getDefaultToolkit().getImage("ressource/curseur/nayeonRIGHT.png");
        direction = "down";
    }

    public void setValeurParDefaut(){
        x = 0;
        y = 0;
        vitesse = 10;
    }

    public void update(){
        if(touchePressee.upPressed == true){ 
            direction = "up";
            y -= vitesse;
            if(y<0){y = 0;} //pour pas sortir des bordures
        }
        else if(touchePressee.downPressed == true){
            direction = "down";
            y += vitesse;
            if(y+64>gp.fenetreHauteur){y = gp.fenetreHauteur-64;}
        }
        else if(touchePressee.leftPressed == true){
            direction = "left";
            x -= vitesse;
            if(x<0){x = 0;}
        }
        else if(touchePressee.rightPressed == true){
            direction = "right";    
            x += vitesse;
            if(x+64>gp.fenetreLargeur){x = gp.fenetreLargeur-64;}
        }
    }

    public void draw(Graphics2D component2d){
         //A supprimer plus tard
        Image image = null;

        switch(direction){
            case "up":
                image = up;
                break;
            case "down":
                image = down;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }

        component2d.drawImage(image, x, y, gp.tuileSize, gp.tuileSize, null);
    }
}

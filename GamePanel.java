import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{ //GamePanel est une subclass de JPanel d'où l'utilisation de super
    
    //Paramètre de l'écran de jeu
    final int tuileSize = 64; //on veut qu'une tuile de l'écran soit du 64px*64px

    //la largeur de la fenêtre
    final int nbCol = 20;
    final int fenetreLargeur = nbCol * tuileSize; //1280 px

    //la hauteur de la fenêtre
    final int nbLigne = 14; 
    final int fenetreHauteur = nbLigne * tuileSize; //896 px

    Controle touchePressee = new Controle();
    Thread gameThread;

    //Paramètre du déplacement de case en case
    int curseurX = 0;
    int curseurY = 0;
    int deplacementVitesse = 10; //vu qu'on va de case en case, on se déplace de 64 (pour l'instant je mets 10)

    //Paramètre des fps
    int FPS = 60; //on limite les fps à 60

    public GamePanel(){
        this.setPreferredSize(new Dimension(fenetreLargeur, fenetreHauteur));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //permet d'améliorer les performances
        this.addKeyListener(touchePressee); //permet de reconnaitre les input du clavier
        this.setFocusable(true); //permet au GamePanel de "focus" sur l'input
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //Création de la boucle de jeu par rapport au fps max (60)
        double intervale = 1000000000/FPS; //1 000 000 = 1 nanoseconde
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/intervale;
            lastTime = currentTime;
            if(delta >= 1){
                //Etape 1 : Update --> Mettre à jour les infos (comme la position des éléments)
                update();
                //Etape 2 : Draw --> Redessinner l'écran avec les nouvelles infos
                repaint(); //pour appeler paintComponent qui est une fonction built in java 

                delta--;
            }
        }
    }

    public void update(){ //On update la position du curseur selon la touche pressée
        if(touchePressee.upPressed == true){ 
            curseurY -= deplacementVitesse;
            if(curseurY<0){curseurY = 0;} //pour pas sortir des bordures
        }
        else if(touchePressee.downPressed == true){
            curseurY += deplacementVitesse;
            if(curseurY+64>fenetreHauteur){curseurY = fenetreHauteur-64;}
        }
        else if(touchePressee.leftPressed == true){
            curseurX -= deplacementVitesse;
            if(curseurX<0){curseurX = 0;}
        }
        else if(touchePressee.rightPressed == true){
            curseurX += deplacementVitesse;
            if(curseurX+64>fenetreLargeur){curseurX = fenetreLargeur-64;}
        }

    }

    public void paintComponent(Graphics component){
        super.paintComponent(component);
        //On change le component en Graphics2d
        Graphics2D component2d = (Graphics2D)component; //class extends de la class Graphics (elle permet un meilleur contrôle sur la géométrie)

        //A supprimer plus tard
        Image nayeon = Toolkit.getDefaultToolkit().getImage("image/nayeonimportant64x64.jpg");
        component2d.drawImage(nayeon, curseurX, curseurY, Color.green, null);

        //Pour dessiner un carré vert 
        component2d.setColor(Color.green);
        //component2d.fillRect(curseurX, curseurY, tuileSize, tuileSize); //(positionX, positionY, largeur, hauteur)
        component2d.dispose(); //ça peut marcher sans cette ligne mais ça permet d'économiser de la mémoire
    }

}

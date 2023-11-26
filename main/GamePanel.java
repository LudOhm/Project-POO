package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entite.Curseur;
import tuile.TuileGestion;

public class GamePanel extends JPanel implements Runnable{ //GamePanel est une subclass de JPanel d'où l'utilisation de super
    
    //Paramètre de l'écran de jeu
    public final int tuileSize = 64; //on veut qu'une tuile de l'écran soit du 64px*64px

    //la largeur de la fenêtre
    public final int nbCol = 20;
    public final int fenetreLargeur = nbCol * tuileSize; //1280 px

    //la hauteur de la fenêtre
    public final int nbLigne = 14; 
    public final int fenetreHauteur = nbLigne * tuileSize; //896 px

    Thread gameThread;

    //Création du curseur
    Controle touchePressee = new Controle();
    Curseur curseur = new Curseur(this, touchePressee);
    TuileGestion tuileG = new TuileGestion(this);

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

    public void update(){ //On update l'écran
     curseur.update();

    }

    public void paintComponent(Graphics component){
        super.paintComponent(component);
        //On change le component en Graphics2d
        Graphics2D component2d = (Graphics2D)component; //class extends de la class Graphics (elle permet un meilleur contrôle sur la géométrie)

        tuileG.draw(component2d);
        curseur.draw(component2d);

        component2d.dispose(); //ça peut marcher sans cette ligne mais ça permet d'économiser de la mémoire
    }

}

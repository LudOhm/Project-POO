import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener{ //Pour implémenter les contrôles
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); //le code de la touche qui vient d'être pressé
        //Flèche du haut
        if(keyCode == KeyEvent.VK_UP){
            upPressed = true;
        }
        //Flèche du bas
        if(keyCode == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        //Flèche de gauche
        if(keyCode == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        //Flèche de droite
        if(keyCode == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //Flèche du haut
        if(keyCode == KeyEvent.VK_UP){
            upPressed = false;
        }
        //Flèche du bas
        if(keyCode == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        //Flèche de gauche
        if(keyCode == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        //Flèche de droite
        if(keyCode == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Non utilisé
    }

    
}

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pour arrêter le programme quand on ferme la fenêtre
        fenetre.setResizable(false); //Pour que l'utilisateur ne puisse pas modifier les dimensions de le fenêtre
        fenetre.setTitle("Time To TowerDefense"); //Le nom de la fenêtre
        
        GamePanel gp = new GamePanel();
        fenetre.add(gp);
        fenetre.pack(); //la fenêtre prend la taille de GamePanel

        fenetre.setLocationRelativeTo(null); //null donc la fenêtre sera au centre de l'écran (pour l'instant ça marche pas parce que j'ai pas mis le size)
        fenetre.setVisible(true);

        gp.startGameThread();

    }
    
}
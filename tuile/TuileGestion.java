package tuile;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class TuileGestion {
    GamePanel gp;
    Tuile[] tuile;
    int[][] mapTuileNum;

    public TuileGestion(GamePanel gp){
        this.gp = gp;
        tuile = new Tuile[20]; //nombre total de sorte de tuile (pour l'instant j'ai mis 20)
        mapTuileNum = new int[gp.nbCol][gp.nbLigne]; //pour mettre le fichier .txt de la map dans ce tab
        getTuileImage();
        chargementMap("/ressource/map/map1.txt");
    }

    public void getTuileImage(){
        tuile[0] = new Tuile();
        tuile[0].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel1.png");

        tuile[1] = new Tuile();
        tuile[1].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel2.png");

        tuile[2] = new Tuile();
        tuile[2].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel3.png");

        tuile[3] = new Tuile();
        tuile[3].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/ciel4.png");

        tuile[4] = new Tuile();
        tuile[4].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe1.png");

        tuile[5] = new Tuile();
        tuile[5].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe2.png");

        tuile[6] = new Tuile();
        tuile[6].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe3.png");
        
        tuile[7] = new Tuile();
        tuile[7].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/herbe4.png");

        tuile[8] = new Tuile();
        tuile[8].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol.png");

        tuile[9] = new Tuile();
        tuile[9].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol2.png");
         
        tuile[10] = new Tuile();
        tuile[10].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol3.png");

        tuile[11] = new Tuile();
        tuile[11].img = Toolkit.getDefaultToolkit().getImage("ressource/tuile/sol4.png");

    }

    public void chargementMap(String map){ //méthode pour utiliser un fichier txt pour faire une carte
        try {
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int ligne = 0;
            while(ligne<gp.nbLigne){
                String ligneTXT = br.readLine();
                while(col<gp.nbCol){
                    String nombre[] = ligneTXT.split(" "); //mettre chaque chiffre de la ligne dans le tableau (sans compter les espaces)
                    int num = Integer.parseInt(nombre[col]); //pour transformer en int
                    mapTuileNum[col][ligne] = num; //on met le chiffre dans le tab de la map
                    col++;
                }
                col = 0;
                ligne++;
            }
            br.close();  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D component2d){
        int y = 0;
        for(int ligne=0; ligne<gp.nbLigne; ligne++){
            int x = 0;
            for(int col = 0; col<gp.nbCol; col++){
                int tuileNum = mapTuileNum[col][ligne];
                component2d.drawImage(tuile[tuileNum].img, x, y, gp.tuileSize, gp.tuileSize, null);
                x += gp.tuileSize;
            }
                y += gp.tuileSize;
            }
        }
    
    
}

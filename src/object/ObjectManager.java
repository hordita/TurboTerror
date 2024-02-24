package object;

import main.GamePanel;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class ObjectManager {

    GamePanel gp;
    public Object[] object;
    public int[][][] mapObjectNum;

    public ObjectManager(GamePanel gp) {
        this.gp = gp;
        object = new Object[10];
        mapObjectNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getObjectImage();
        loadMap("/maps/obj01.txt",0);
        loadMap("/maps/obj02.txt",1);
        loadMap("/maps/obj03.txt",2);


    }

    public void getObjectImage() {
        try {
            object[1] = new Object();
            object[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/Butoi.png")));
            object[1].collision=true;

            object[2] = new Object();
            object[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/tomberon.png")));
            object[2].collision=false;

            object[3] = new Object();
            object[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/foc.png")));
            object[3].collision=true;

            object[4] = new Object();
            object[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/cafea.png")));
            object[4].collision=false;

            object[5] = new Object();
            object[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/mancare.png")));
            object[5].collision=false;

            object[6] = new Object();
            object[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/felinar.png")));
            object[6].collision=false;

            object[7] = new Object();
            object[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/speed.png")));
            object[7].collision=true;

            object[8] = new Object();
            object[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bancomat.png")));
            object[8].collision=false;

            object[9] = new Object();
            object[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/arme.png")));
            object[9].collision=true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePatch,int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePatch);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapObjectNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int objectNum = mapObjectNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            }

            if(objectNum==2)
                g2.drawImage(object[objectNum].image, screenX, screenY, gp.tileSize*2, gp.tileSize, null);
            if(objectNum==6)
                g2.drawImage(object[objectNum].image, screenX, screenY, gp.tileSize/2, gp.tileSize*2, null);
            if(object[objectNum]!=null&&objectNum!=2&&objectNum!=6)
                g2.drawImage(object[objectNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }
    }
}

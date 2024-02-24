package entity;

import main.DataBase;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static main.DataBase.conn;

public class Player extends Entity {
    KeyHandler KeyH;
    public final int screenX;
    public final int screenY;
    public DataBase db=new DataBase();
    public int nr_butoaie;
    public int ar_mod = 0;
    public boolean mort=false;
    public Player(GamePanel gp, KeyHandler KeyH) {
        super(gp);
        this.KeyH = KeyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle();

        solidArea.x = 20;
        solidArea.y = 16;
        solidAreaDefaultX = 20;
        solidAreaDefaultY = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 23;
        speed = 3;
        mort=false;
        direction = "left";
        ar_mod = 0;
        nr_butoaie = 0;
        db.InsertScore(nr_butoaie);
    }

    public void getPlayerImage() {
        if (ar_mod == 0) {
            try {
                up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/yellow_car_up.png")));
                down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/yellow_car_down.png")));
                left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/yellow_car_left.png")));
                right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/yellow_car_right.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/yellow_car_up_mod.png")));
                down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/yellow_car_down_mod.png")));
                left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/yellow_car_left_mod.png")));
                right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/yellow_car_right_mod.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void colectare(int x, int y, int z) {
        if (z == 1) {
            gp.objectM.mapObjectNum[gp.currentMap][x][y] = 0;
            nr_butoaie++;
            db.InsertScore(nr_butoaie);
        }
        if (z == 7) {
            gp.objectM.mapObjectNum[gp.currentMap][x][y] = 0;
            speed++;
        }
        if (z == 9) {
            gp.objectM.mapObjectNum[gp.currentMap][x][y] = 0;
            ar_mod = 1;
            getPlayerImage();
        }
    }

    public void update() throws SQLException {
        if (KeyH.upPressed) {
            if (direction.equals("up") || direction.equals("down")
                    || !KeyH.leftPressed && !KeyH.rightPressed)
                direction = "up";
            if (KeyH.leftPressed) {
                worldY = worldY - speed;
                worldX = worldX - speed;
            } else if (KeyH.rightPressed) {
                worldY = worldY - speed;
                worldX = worldX + speed;
            } else if (!KeyH.downPressed)
                worldY = worldY - speed;

        } else if (KeyH.downPressed) {
            if (direction.equals("up") || direction.equals("down")
                    || !KeyH.leftPressed && !KeyH.rightPressed)
                direction = "down";
            if (KeyH.leftPressed) {
                worldY = worldY + speed;
                worldX = worldX - speed;
            } else if (KeyH.rightPressed) {
                worldY = worldY + speed;
                worldX = worldX + speed;
            } else worldY = worldY + speed;
        } else if (KeyH.leftPressed) {
            direction = "left";
            if (!KeyH.rightPressed)
                worldX = worldX - speed;
        } else if (KeyH.rightPressed) {
            direction = "right";
            worldX = worldX + speed;
        }
        collisionOn = false;
        gp.cChecker.checkTile(this);
        int[] x = new int[3];
        gp.cChecker.checkObject(this, x);//!!sunt inversate in matrice liniile cu coloanele
//        for (int i = 0; i < 4; i++)
//            if(gp.cChecker.checkEntity(this, gp.rob[i]))
//                mort=true;

        if (collisionOn) {
            colectare(x[0], x[1], x[2]);
            if (KeyH.upPressed) {
                if (direction.equals("up") || direction.equals("down")
                        || !KeyH.leftPressed && !KeyH.rightPressed)
                    direction = "up";
                if (KeyH.leftPressed) {
                    worldY = worldY + speed;
                    worldX = worldX + speed;
                } else if (KeyH.rightPressed) {
                    worldY = worldY + speed;
                    worldX = worldX - speed;
                } else if (!KeyH.downPressed)
                    worldY = worldY + speed;

            } else if (KeyH.downPressed) {
                if (direction.equals("up") || direction.equals("down")
                        || !KeyH.leftPressed && !KeyH.rightPressed)
                    direction = "down";
                if (KeyH.leftPressed) {
                    worldY = worldY - speed;
                    worldX = worldX + speed;
                } else if (KeyH.rightPressed) {
                    worldY = worldY - speed;
                    worldX = worldX - speed;
                } else worldY = worldY - speed;
            } else if (KeyH.leftPressed) {
                direction = "left";
                if (!KeyH.rightPressed)
                    worldX = worldX + speed;
            } else if (KeyH.rightPressed) {
                direction = "right";
                worldX = worldX - speed;
            }


        }
        if (nr_butoaie == 15) {

            setDefaultValues();
            getPlayerImage();
            gp.rob[0] = new Rob(gp, 27, 8);
            gp.rob[1] = new Rob(gp, 27, 33);
            gp.rob[2] = new Rob(gp, 9, 7);
            gp.rob[3] = new Rob(gp, 11, 30);
            if (gp.currentMap == 0 || gp.currentMap == 1) {
                gp.currentMap++;
                gp.gameState = gp.nextLevel;
            } else
                gp.gameState = gp.finalGame;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> up;
            case "down" -> down;
            case "left" -> left;
            case "right" -> right;
            default -> null;
        };

        if (image == up || image == down) {
            solidArea.x = 16;
            solidArea.y = 16;
            solidArea.width = 32;
            solidArea.height = 72;
            g2.drawImage(image, screenX, screenY, gp.tileSize, 2 * gp.tileSize, null);
        } else if (image == right || image == left) {
            solidArea.x = 16;
            solidArea.y = 16;
            solidArea.width = 72;
            solidArea.height = 32;
            g2.drawImage(image, screenX, screenY, 2 * gp.tileSize, gp.tileSize, null);
        }

    }
}


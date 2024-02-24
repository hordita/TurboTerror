package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Rob extends Entity {


    public Rob(GamePanel gp, int x, int y) {
        super(gp);
        worldX = gp.tileSize * x;
        worldY = gp.tileSize * y;
        direction = "left";
        speed = 1;
        getRobImage();
    }

    public void update() {
        if (onPath) {
            int goalCol = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
             switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }

        } else {
            autoLockCounter++;
            if (autoLockCounter == 200 || collisionOn) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;
                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i < 100) {
                    direction = "right";
                }
                autoLockCounter = 0;
            }
            checkCollision();
            if (!collisionOn)
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
        }
    }
    public void getRobImage() {
        try {
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/rob/rob_up.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/rob/rob_down.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/rob/rob_left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/rob/rob_right.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

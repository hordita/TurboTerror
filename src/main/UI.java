package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_30;
    public int commandNum = 0;
    public int commandPau = 0;
    public int commandHel = -1;


    public UI(GamePanel gp) {
        this.gp = gp;
        arial_30 = new Font("Arial", Font.PLAIN, 30);
    }

    public void draw(Graphics2D g2) throws IOException {
        this.g2 = g2;

        g2.setFont(arial_30);
        g2.setColor(Color.orange);

        if (gp.gameState == gp.playState) {
            g2.setFont(arial_30);
            g2.setColor(Color.orange);
            g2.drawImage(gp.objectM.object[1].image, 15, 13, null);
            int y=gp.player.db.SelectForJTable();
            g2.drawString(" x " + y* 3, 50, 50);
        }

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        if (gp.gameState == gp.titleState) {
            commandHel = -1;
            drawTitleScreen();
        }

        if (gp.gameState == gp.helpState) {
            drawHelpScreen();
        }

        if(gp.gameState==gp.nextLevel){
            drawNextLevelScreen();
        }
        if(gp.gameState==gp.finalGame)
            drawFinalGame();


    }

    public void drawTitleScreen() throws IOException {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/Preview3.png")));
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        String text = "TURBO TERROR";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.black);
        g2.drawString(text, x, y);
        g2.setColor(Color.orange);
        g2.drawString(text, x + 5, y + 5);

        //
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 5;
        g2.setColor(Color.black);
        g2.drawString(text, x - 3, y - 3);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize / 6, y);
        }

        text = "LOAD";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x - 3, y - 3);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize / 6, y);
        }

        text = "HELP";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x - 3, y - 3);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize / 6, y);
        }

        text = "EXIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x - 3, y - 3);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize / 6, y);
        }


    }

    public void drawPauseScreen() throws IOException {
        g2.setColor(Color.black);
        g2.setFont(new Font("BOLD", Font.BOLD, 40));
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/Preview4.png")));
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2 - 96;
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        text = "Back to Start Menu";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3;
        g2.setColor(Color.black);
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandPau == 0) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize / 6, y);
        }

        text = "Resume Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandPau == 1) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize / 6, y);
        }

        text = "Restart Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandPau == 2) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize / 6, y);
        }

        text = "Exit";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.black);
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandPau == 3) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize / 6, y);
        }
    }

    public void drawHelpScreen() throws IOException { g2.setColor(Color.black);
        g2.setFont(new Font("BOLD", Font.BOLD, 40));
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/Preview3.png")));
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        String text = "Help";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2 - 96;
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/wasd-keys.png")));
        g2.drawImage(image, x-gp.tileSize, y-gp.tileSize, gp.tileSize*4, gp.tileSize*6, null);



        text="Comenzile pentru deplasarea in joc";
        x=getXforCenteredText(text);
        y+=gp.tileSize*3;
        g2.setColor(Color.orange);
        g2.setFont(new Font("arial",Font.ITALIC,20));
        g2.drawString(text, x+gp.tileSize, y);



        text = "Back";
        x = getXforCenteredText(text);
        y = gp.tileSize*12;
        g2.setColor(Color.black);
        g2.setFont(new Font("BOLD", Font.BOLD, 40));

        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (commandHel == 0) {
            g2.drawString("<>", x - gp.tileSize, y);
            g2.drawString("<>", gp.screenWidth - x + gp.tileSize , y);
        }
    }
    public void drawNextLevelScreen() throws IOException {
        g2.setColor(Color.black);
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/Preview4.png")));
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(new Font("BOLD", Font.BOLD, 40));
        String text = "Press ENTER to next level...";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight *3/4;
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

    }
    public void drawFinalGame() throws IOException {
        g2.setColor(Color.black);
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/Preview4.png")));
        g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(new Font("BOLD", Font.BOLD, 40));
        String text = "FELICITARI";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        text = "Press ENTER...";
         x = getXforCenteredText(text);
         y = gp.screenHeight * 3/4;
        g2.setColor(Color.black);
        g2.setFont(new Font("bold",Font.ITALIC,30));
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}

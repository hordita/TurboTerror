package main;

import ai.PathFinder;
import entity.Player;
import entity.Rob;
import object.ObjectManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 40;

    public final int maxMap = 3;
    public int currentMap = 0;
    int FPS = 60;
    public UI ui = new UI(this);
    public TileManager tileM = new TileManager(this);
    public ObjectManager objectM = new ObjectManager(this);
    KeyHandler KeyH = new KeyHandler(this);
    public PathFinder pFinder=new PathFinder(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, KeyH);
    public Rob[] rob = new Rob[4];
    public int gameState;
    public int resetStatus = 0;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public final int restartState = 3;
    public final int helpState = 4;
    public final int nextLevel = 5;
    public final int finalGame = 6;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);

        this.addKeyListener(KeyH);
        this.setFocusable(true);
        rob[0] = new Rob(this, 27, 8);
        rob[1] = new Rob(this, 27, 33);
        rob[2] = new Rob(this, 9, 7);
        rob[3] = new Rob(this, 11, 30);

    }

    public void setupGame() {
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {

            double drawInterval = (double) 1000000000 / FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

            try {
                update();
            } catch (SQLException e) {

            }

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;//MAI MARE viteza creste
                if (remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime = nextDrawTime + drawInterval;


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update() throws SQLException {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < 4; i++) {
                rob[i].update();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (gameState == titleState) {
            try {
                if (resetStatus == 1) {
                    resetStatus = 0;
                    reset();
                }
                ui.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (gameState == playState) {
            resetStatus = 1;
            tileM.draw(g2);
            objectM.draw(g2); //desenez obiecte la fel ca si texturi
            player.draw(g2);
            for (int i = 0; i < 4; i++) {
                rob[i].draw(g2);
            }
            try {
                ui.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (gameState == restartState) {
            try {
                reset();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            gameState = playState;
        } else if (gameState == pauseState) {
            try {
                ui.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (gameState == helpState) {
            try {
                ui.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (gameState == nextLevel) {
            try {
                ui.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (gameState == finalGame) {
            try {
                ui.draw(g2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        g2.dispose();
    }

    public void reset() throws SQLException {

        player.db.stmt.close();
        player.db.conn.close();
        player = new Player(this, KeyH);
        objectM = new ObjectManager(this);
        tileM = new TileManager(this);
        rob[0] = new Rob(this, 27, 8);
        rob[1] = new Rob(this, 27, 33);
        rob[2] = new Rob(this, 9, 7);
        rob[3] = new Rob(this, 11, 30);
        currentMap = 0;
    }
}
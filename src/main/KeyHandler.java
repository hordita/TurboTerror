package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                if (gp.ui.commandNum == 0)
                    gp.ui.commandNum = 3;
                else
                    gp.ui.commandNum--;
            }
            if (code == KeyEvent.VK_S) {
                if (gp.ui.commandNum == 3)
                    gp.ui.commandNum = 0;
                else
                    gp.ui.commandNum++;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0)
                    gp.gameState = gp.playState;
                if (gp.ui.commandNum == 1)
                    gp.gameState = gp.playState;
                if (gp.ui.commandNum == 2)
                    gp.gameState = gp.helpState;
                if (gp.ui.commandNum == 3)
                    System.exit(0);

            }

        }
        if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_W) {
                if (gp.ui.commandPau == 0)
                    gp.ui.commandPau = 3;
                else
                    gp.ui.commandPau--;
            }
            if (code == KeyEvent.VK_S) {
                if (gp.ui.commandPau == 3)
                    gp.ui.commandPau = 0;
                else
                    gp.ui.commandPau++;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandPau == 0)
                    gp.gameState = gp.titleState;
                if (gp.ui.commandPau == 1)
                    gp.gameState = gp.playState;
                if (gp.ui.commandPau == 2)
                    gp.gameState = gp.restartState;
                if (gp.ui.commandPau == 3)
                    System.exit(0);

            }

        }

        if (gp.gameState == gp.helpState) {
            if (code == KeyEvent.VK_S)
                gp.ui.commandHel = 0;
            if (code == KeyEvent.VK_ENTER)
                if (gp.ui.commandHel == 0)
                    gp.gameState = gp.titleState;
        }

        if (gp.gameState == gp.nextLevel) {
            if(code==KeyEvent.VK_ENTER)
                gp.gameState=gp.playState;
        }

        if (gp.gameState == gp.finalGame) {
            if(code==KeyEvent.VK_ENTER)
                gp.gameState=gp.titleState;
        }

        if (gp.gameState == gp.playState || gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;

            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                if (gp.gameState == gp.playState)
                    gp.gameState = gp.pauseState;
                else if (gp.gameState == gp.pauseState)
                    gp.gameState = gp.playState;
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;

        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}

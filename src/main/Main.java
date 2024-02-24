package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setTitle("Turbo tTerror");
        windows.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        windows.add(gamePanel);
        windows.pack();
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
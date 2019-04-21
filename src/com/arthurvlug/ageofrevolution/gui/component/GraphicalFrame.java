package com.arthurvlug.ageofrevolution.gui.component;

import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import lombok.Getter;

public class GraphicalFrame extends JFrame {
    @Getter
    private final GamePanel gamePanel;
    private final KeyListener gameKeyboardListener;

    public GraphicalFrame(GamePanel gamePanel, final KeyListener gameKeyboardListener, final int width, final int height) {
        super("Age Of Revolution");

        this.gamePanel = gamePanel;
        this.gameKeyboardListener = gameKeyboardListener;
        initialise();
    }
    
    public void rerenderGamePanel() {
        gamePanel.repaint();
    }
    
    private void initialise() {
        addKeyListener(gameKeyboardListener);
        this.getContentPane().add(gamePanel);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setUndecorated(true);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

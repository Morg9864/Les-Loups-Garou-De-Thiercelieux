package source.controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import source.view.AppView;

public class Controls {
    private Controls() {
    }

    public static void update(AppView appView, JPanel panel) {
        // appView.changeContent(panel);
        appView.getContentPane().removeAll();
        appView.getContentPane().add(panel, BorderLayout.CENTER);
        appView.getContentPane().repaint();
        appView.getContentPane().revalidate();
    }
    
}

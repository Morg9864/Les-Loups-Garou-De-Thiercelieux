package source.view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import source.controller.Controls;

public class GenerateRoleGUI extends JFrame{
    private static final int width = 1760;
    private static final int height = 1020;
    private static final String backgroundPath = "images/generate.jpg";
    private static final String backImagePath = "images/back.png";
    // private static final String fontPath = "font/TheMacabre.otf";
    // private static final float fontSize = 60f; 

    public GenerateRoleGUI(){
        setTitle("Les Loups-Garous de Thiercelieux");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height + 60));
        setContentPane(layeredPane);

        JLabel background = new JLabel(new ImageIcon(backgroundPath));
        background.setBounds(0, -50, width, height + 60);
        layeredPane.add(background, Integer.valueOf(0));

        JButton backButton = new JButton(new ImageIcon(backImagePath));
        backButton.addActionListener(e -> {Controls.returnToMainMenuButtonFunc(this);});
        backButton.setBounds(30, 30, 150, 150);
        layeredPane.add(backButton, Integer.valueOf(1));

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}

package source.view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import source.controller.Controls;

public class SimulatorMainGUI extends JFrame{
    private static final int width = 1760;
    private static final int height = 1020;
    private static final String backgroundPath = "images/background.jpg";
    private static final String fontPath = "font/TheMacabre.otf";
    private static final float fontSize = 60f;
    private static final int buttonPosition[][] = {
        {309, 654, 462, 139},
        {975, 654, 462, 139}
    };

    public SimulatorMainGUI() {
        // Création de la fenêtre
        setTitle("Les Loups-Garous de Thiercelieux");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du layeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height + 60));
        setContentPane(layeredPane);

        // Ajout du fond d'écran
        JLabel background = new JLabel(new ImageIcon(backgroundPath));
        background.setBounds(0, -50, width, height + 60);
        layeredPane.add(background, Integer.valueOf(0));

        // Création de 2 boutons
        JButton generateRoleButton = new JButton("Générer les rôles");
        generateRoleButton.addActionListener(e -> {Controls.generateRoleButtonFunc(this);});
        generateRoleButton.setBounds(buttonPosition[0][0], buttonPosition[0][1], buttonPosition[0][2], buttonPosition[0][3]);
        layeredPane.add(generateRoleButton, Integer.valueOf(1));

        JButton loadRoleButton = new JButton("Charger les rôles");
        loadRoleButton.addActionListener(e -> {Controls.loadRoleButtonFunc(this);});
        loadRoleButton.setBounds(buttonPosition[1][0], buttonPosition[1][1], buttonPosition[1][2], buttonPosition[1][3]);
        layeredPane.add(loadRoleButton, Integer.valueOf(1));
        
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            generateRoleButton.setFont(font.deriveFont(fontSize));
            loadRoleButton.setFont(font.deriveFont(fontSize));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }

        // Affichage de la fenêtre
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SimulatorMainGUI();
    }
}

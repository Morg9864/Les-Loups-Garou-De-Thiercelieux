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

public class SimulatorMainGUI extends JFrame{

    public SimulatorMainGUI() {
        // Création de la fenêtre
        setTitle("Les Loups-Garous de Thiercelieux");
        setSize(1760, 1020);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création du layeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1760, 1080));
        setContentPane(layeredPane);

        // Ajout du fond d'écran
        JLabel background = new JLabel(new ImageIcon("images/background.jpg"));
        background.setBounds(0, -50, 1760, 1080);
        layeredPane.add(background, Integer.valueOf(0));

        // Création de 2 boutons
        JButton generateRoleButton = new JButton("Générer les rôles");
        generateRoleButton.setBounds(309, 654, 462, 139);
        layeredPane.add(generateRoleButton, Integer.valueOf(1));

        JButton loadRoleButton = new JButton("Charger les rôles");
        loadRoleButton.setBounds(975, 654, 462, 139);
        layeredPane.add(loadRoleButton, Integer.valueOf(1));
        
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/TheMacabre.otf"));
            generateRoleButton.setFont(font.deriveFont(60f));
            loadRoleButton.setFont(font.deriveFont(60f));
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

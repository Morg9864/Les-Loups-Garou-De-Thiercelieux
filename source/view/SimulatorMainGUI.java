package source.view;
import java.awt.Dimension;

import javax.swing.ImageIcon;
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

        // Affichage de la fenêtre
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SimulatorMainGUI();
    }
}

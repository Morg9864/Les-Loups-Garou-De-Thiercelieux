package source.view;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;

public class AppView extends JFrame{
    private static final String backgroundPath = "images/background.jpg";
    private static final String fontPath = "font/TheMacabre.otf";
    private static final float fontSize = 60f;

    public AppView(){
        setTitle("Les Loups-Garous de Thiercelieux");
        setSize(1760, 1020);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        JPanel panel = mainMenu();
        changeContent(panel);
    }

    public void changeContent(JPanel newContent) {
        getContentPane().removeAll();
        getContentPane().add(newContent, BorderLayout.CENTER);
        getContentPane().repaint();
        getContentPane().revalidate();
    }

    protected JPanel mainMenu() {

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1760, 1080));
        panel.add(layeredPane);

        JLabel background = new JLabel(new ImageIcon(backgroundPath));
        background.setBounds(0, -50, 1760, 1080);
        layeredPane.add(background, Integer.valueOf(0));

        JButton generateRoleButton = new JButton("Générer les rôles");
        generateRoleButton.addActionListener(e -> {changeContent(new SettingUpGenerator(this));});
        generateRoleButton.setBounds(309, 654, 462, 139);
        layeredPane.add(generateRoleButton, Integer.valueOf(1));

        JButton loadRoleButton = new JButton("Charger les rôles");
        loadRoleButton.addActionListener(e -> {changeContent(new LoadingRoles(this));});
        loadRoleButton.setBounds(975, 654, 462, 139);
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

        return panel;
    }
}

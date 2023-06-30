package source.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import source.controller.Controls;
import source.model.Const;

public class MainMenu extends JPanel implements Const{
    public MainMenu(AppView appView){

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        add(layeredPane);

        JLabel background = new JLabel(new ImageIcon(MAIN_BACKGROUNG));
        background.setBounds(0, -50, WINDOW_WIDTH, WINDOW_HEIGHT);
        layeredPane.add(background, BACKGROUND_CONSTRAINT);

        JButton generateRoleButton = new JButton("Générer les rôles");
        generateRoleButton.addActionListener(e -> {Controls.update(appView, new SettingUpGenerator(appView));});
        generateRoleButton.setBounds(309, 654, 462, 139);
        layeredPane.add(generateRoleButton, FOREGROUND_CONSTRAINT);

        JButton loadRoleButton = new JButton("Charger les rôles");
        loadRoleButton.addActionListener(e -> {Controls.update(appView, new LoadingRoles(appView));});
        loadRoleButton.setBounds(975, 654, 462, 139);
        layeredPane.add(loadRoleButton, FOREGROUND_CONSTRAINT);
        
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(THE_MACABRE_FONT));
            generateRoleButton.setFont(font.deriveFont(60f));
            loadRoleButton.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }
    }
}

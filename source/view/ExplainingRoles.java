package source.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import source.controller.Controls;
import source.model.Const;
import source.model.Role;

public class ExplainingRoles extends JPanel implements Const{
    public ExplainingRoles(AppView appView, JPanel rolePanel, Role role) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        add(layeredPane);

        JLabel background = new JLabel(new ImageIcon(EXPLAIN_BACKGROUND));
        background.setBounds(0, -50, WINDOW_WIDTH, WINDOW_HEIGHT);
        layeredPane.add(background, BACKGROUND_CONSTRAINT);

        ImageIcon imageIcon = new ImageIcon(role.getImage());
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));

        JLabel image = new JLabel(imageIcon);
        image.setBounds((WINDOW_WIDTH/2) - 150, 205, 300, 300);
        layeredPane.add(image, FOREGROUND_CONSTRAINT);

        JLabel name = new JLabel(role.getName());
        name.setBounds((WINDOW_WIDTH/2) - 400, 505, 800, 100);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(THE_MACABRE_FONT));
            name.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
            name.setFont(name.getFont().deriveFont(60f));

        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
            name.setFont(name.getFont().deriveFont(60f));
        }
        name.setForeground(Color.WHITE);
        name.setHorizontalAlignment(JLabel.CENTER);
        layeredPane.add(name, FOREGROUND_CONSTRAINT);

        JTextArea description = new JTextArea();
        description.setBounds((WINDOW_WIDTH/2) - 480, 655, 1000, 300);
        description.setText(role.getDescription());
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(THE_MACABRE_FONT));
            description.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
            description.setFont(description.getFont().deriveFont(60f));

        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
            description.setFont(description.getFont().deriveFont(60f));
        }
        description.setOpaque(false);
        description.setEditable(false);
        description.setAlignmentX(CENTER_ALIGNMENT);
        description.setAlignmentY(CENTER_ALIGNMENT);
        description.setForeground(Color.WHITE);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setCaretPosition(0);
        layeredPane.add(description, FOREGROUND_CONSTRAINT);

        ImageIcon backButtonIcon = new ImageIcon(BACK_ARROW);
        backButtonIcon = new ImageIcon(backButtonIcon.getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH));
        RoundButton backButton = new RoundButton(backButtonIcon);
        backButton.setBounds(35, 30, 150, 130);
        backButton.addActionListener(e -> {Controls.update(appView, rolePanel);});
        layeredPane.add(backButton, FOREGROUND_CONSTRAINT);

    }
}

package source.view;

import java.awt.Color;
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
import javax.swing.JTextArea;

import source.model.NbPlayersIncorrectException;
import source.model.Simulator;

public class GeneratingRoles extends JPanel{
    public GeneratingRoles(AppView appView, int nbPlayers, boolean avecExtensionVillage, boolean avecExtensionNouvelleLune) {
        Simulator simulator = generateRoles(nbPlayers, avecExtensionVillage, avecExtensionNouvelleLune);
        if(simulator != null)
            successfulGenerating(appView);
        else
            failedGenerating(appView);
    }

    private Simulator generateRoles(int nbPlayers, boolean avecExtensionVillage, boolean avecExtensionNouvelleLune) {
        try {
            Simulator simulator = new Simulator(nbPlayers, avecExtensionVillage, avecExtensionNouvelleLune);
            return simulator;
        } catch (NbPlayersIncorrectException e) {
            System.out.println("Problème avec le nombre de joueurs");
            e.printStackTrace();
        }
        
        return null;
    }

    private void successfulGenerating(AppView appView) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1760, 1080));
        add(layeredPane);

        JLabel background = new JLabel(new ImageIcon("images/generate.jpg"));
        background.setBounds(0, -50, 1760, 1080);
        layeredPane.add(background, Integer.valueOf(0));

        JTextArea textArea = new JTextArea();
        JTextArea textArea2 = new JTextArea();
        textArea.setBounds(580, 350, 1305, 150);
        textArea2.setBounds(430, 500, 1305, 100);
        textArea.setText("Les rôles ont été générés avec succès !");
        textArea2.setText("Vous pouvez maintenant les afficher en cliquant sur le bouton");
        textArea.setOpaque(false);
        textArea2.setOpaque(false);
        textArea.setEditable(false);
        textArea2.setEditable(false);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/TheMacabre.otf"));
            textArea.setFont(font.deriveFont(60f));
            textArea2.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }
        // Centre le texte
        textArea.setAlignmentX(CENTER_ALIGNMENT);
        textArea.setAlignmentY(CENTER_ALIGNMENT);

        textArea2.setAlignmentX(CENTER_ALIGNMENT);
        textArea2.setAlignmentY(CENTER_ALIGNMENT);
        
        textArea.setForeground(Color.WHITE);
        textArea2.setForeground(Color.WHITE);
        textArea.setWrapStyleWord(true);
        textArea2.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea2.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea2.setCaretPosition(0);
        layeredPane.add(textArea, Integer.valueOf(1));
        layeredPane.add(textArea2, Integer.valueOf(1)); 

        JButton displayRoleButton = new JButton("Afficher les rôles");
        displayRoleButton.setBounds(595, 715, 560, 150);
        displayRoleButton.addActionListener(e -> {System.out.println("Clicked");;});
        layeredPane.add(displayRoleButton, Integer.valueOf(1));

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/TheMacabre.otf"));
            displayRoleButton.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }
    }

    private void failedGenerating(AppView appView) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1760, 1080));
        add(layeredPane);

        JLabel background = new JLabel(new ImageIcon("images/load.jpg"));
        background.setBounds(0, -50, 1760, 1080);
        layeredPane.add(background, Integer.valueOf(0));

        JTextArea textArea = new JTextArea();
        JTextArea textArea2 = new JTextArea();
        textArea.setBounds(600, 350, 1305, 150);
        textArea2.setBounds(460, 500, 1305, 100);
        textArea.setText("La génération des rôles a échoué !");
        textArea2.setText("Référez-vous au terminal pour plus d'informations");
        textArea.setOpaque(false);
        textArea2.setOpaque(false);
        textArea.setEditable(false);
        textArea2.setEditable(false);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/TheMacabre.otf"));
            textArea.setFont(font.deriveFont(60f));
            textArea2.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }
        // Centre le texte
        textArea.setAlignmentX(CENTER_ALIGNMENT);
        textArea.setAlignmentY(CENTER_ALIGNMENT);

        textArea2.setAlignmentX(CENTER_ALIGNMENT);
        textArea2.setAlignmentY(CENTER_ALIGNMENT);
        
        textArea.setForeground(Color.WHITE);
        textArea2.setForeground(Color.WHITE);
        textArea.setWrapStyleWord(true);
        textArea2.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea2.setLineWrap(true);
        textArea.setCaretPosition(0);
        textArea2.setCaretPosition(0);
        layeredPane.add(textArea, Integer.valueOf(1));
        layeredPane.add(textArea2, Integer.valueOf(1)); 

        JButton returnButton = new JButton("Retour");
        returnButton.setBounds(595, 715, 560, 150);
        returnButton.addActionListener(e -> {appView.changeContent(appView.mainMenu());});
        layeredPane.add(returnButton, Integer.valueOf(1));

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/TheMacabre.otf"));
            returnButton.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }     
    }
}

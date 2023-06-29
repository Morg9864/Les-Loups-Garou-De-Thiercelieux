package source.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SettingUpGenerator extends JPanel{
    public SettingUpGenerator(AppView appView){
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1760, 1080));
        add(layeredPane);

        JLabel background = new JLabel(new ImageIcon("images/generate.jpg"));
        background.setBounds(0, -50, 1760, 1080);
        layeredPane.add(background, Integer.valueOf(0));

        JTextArea textArea = new JTextArea();
        textArea.setBounds(630, 250, 1305, 150);
        textArea.setText("Configuration de la génération");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("font/TheMacabre.otf"));
            textArea.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setAlignmentX(CENTER_ALIGNMENT);
        textArea.setAlignmentY(CENTER_ALIGNMENT);
        textArea.setForeground(Color.WHITE);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        layeredPane.add(textArea, Integer.valueOf(1));

        getNumberOfPlayers(appView, layeredPane);


    }

    private void getNumberOfPlayers(AppView appView, JLayeredPane layeredPane) {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(720, 450, 350, 70);
        textArea.setText("Nombre de joueurs :");
        Font font = null;
        // Make it normal
        font = textArea.getFont();
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("font/TheMacabre.otf"));
            textArea.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setAlignmentX(CENTER_ALIGNMENT);
        textArea.setAlignmentY(CENTER_ALIGNMENT);
        textArea.setForeground(Color.WHITE);
        textArea.setLineWrap(true);
        textArea.setCaretPosition(0);
        layeredPane.add(textArea, Integer.valueOf(1));

        // Liste déroulante pour le nombre de joueurs
        // Elle va de 8 à 46
        Integer[] nbPlayers = new Integer[39];
        for (int i = 0; i < 39; i++) {
            nbPlayers[i] = i + 8;
        }
        JComboBox<Integer> nbPlayersList = new JComboBox<Integer>(nbPlayers);
        nbPlayersList.setBounds(720, 550, 300, 75);
        nbPlayersList.setFont(font.deriveFont(60f));
        nbPlayersList.setBackground(Color.WHITE);
        nbPlayersList.setForeground(Color.BLACK);
        nbPlayersList.setSelectedIndex(0);
        layeredPane.add(nbPlayersList, Integer.valueOf(1));

        // Bouton pour valider le nombre de joueurs
        JButton validateButton = new JButton("Valider");
        validateButton.setBounds(720, 700, 300, 75);
        validateButton.setFont(font.deriveFont(60f));
        validateButton.setForeground(Color.BLACK);
        layeredPane.add(validateButton, Integer.valueOf(1));

        validateButton.addActionListener(e -> {
            int nbPlayersSelected = (int) nbPlayersList.getSelectedItem();
            System.out.println(nbPlayersSelected + " joueurs séléctionnés");
            if(nbPlayersSelected < 43){
                textArea.setText("Choix extension :");
                textArea.setBounds(735, 450, 350, 70);
                layeredPane.remove(nbPlayersList);
                layeredPane.remove(validateButton);
                chooseExtension(appView, layeredPane, nbPlayersSelected);
            } else {
                appView.changeContent(new GeneratingRoles(appView, nbPlayersSelected, true, true));
            }
        });
    }

    private void chooseExtension(AppView appView, JLayeredPane layeredPane, int nbPlayersSelected) {
            // Création de 4 boutons pour les extensions
            // Le 1er pour l'extension Village
            // Le 2ème pour l'extension Nouvelle Lune
            // Le 3ème pour les deux extensions
            // Le 4ème pour aucune extension

            Font font = null;
            // Make it normal
            font = new JButton().getFont();
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("font/TheMacabre.otf"));
            } catch (FontFormatException e) {
                System.out.println("Erreur lors du chargement de la police");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Fichier de police introuvable");
                e.printStackTrace();
            }

            JButton villageButton = new JButton("Village");
            villageButton.setBounds(720, 550, 300, 75);
            villageButton.setFont(font.deriveFont(60f));
            villageButton.setForeground(Color.BLACK);
            villageButton.addActionListener(e -> {appView.changeContent(new GeneratingRoles(appView, nbPlayersSelected, true, false));});
            layeredPane.add(villageButton, Integer.valueOf(1));

            JButton newMoonButton = new JButton("Nouvelle Lune");
            newMoonButton.setBounds(720, 650, 300, 75);
            newMoonButton.setFont(font.deriveFont(60f));
            newMoonButton.setForeground(Color.BLACK);
            newMoonButton.addActionListener(e -> {appView.changeContent(new GeneratingRoles(appView, nbPlayersSelected, false, true));});
            layeredPane.add(newMoonButton, Integer.valueOf(1));
            
            JButton bothButton = new JButton("Les deux");
            bothButton.setBounds(720, 750, 300, 75);
            bothButton.setFont(font.deriveFont(60f));
            bothButton.setForeground(Color.BLACK);
            bothButton.addActionListener(e -> {appView.changeContent(new GeneratingRoles(appView, nbPlayersSelected, true, true));});
            layeredPane.add(bothButton, Integer.valueOf(1));

            JButton noneButton = new JButton("Aucune");
            noneButton.setBounds(720, 850, 300, 75);
            noneButton.setFont(font.deriveFont(60f));
            noneButton.setForeground(Color.BLACK);
            noneButton.addActionListener(e -> {appView.changeContent(new GeneratingRoles(appView, nbPlayersSelected, false, false));});
            layeredPane.add(noneButton, Integer.valueOf(1));

            layeredPane.repaint();
    }
}
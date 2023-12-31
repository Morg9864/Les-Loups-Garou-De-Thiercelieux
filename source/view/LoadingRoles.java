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

import source.controller.Controls;
import source.model.Const;
import source.model.NbPlayersIncorrectException;
import source.model.Simulator;

public class LoadingRoles extends JPanel implements Const{
    public LoadingRoles(AppView appView) {

        Simulator simulator = loadRoles();
        
        if(simulator != null)
            successfulLoading(appView, simulator);
        else
            failedLoading(appView);
    }

    private Simulator loadRoles() {
        try {
            Simulator simulator = new Simulator(ROLES_FILE);
            return simulator;
        } catch (NbPlayersIncorrectException e) {
            System.out.println("Problème avec le nombre de joueurs");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Impossible de trouver le fichier de rôles");
            e.printStackTrace();
        }

        return null;
    }

    private void successfulLoading(AppView appView, Simulator simulator) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        add(layeredPane);

        JLabel background = new JLabel(new ImageIcon(LOAD_BACKGROUND));
        background.setBounds(0, -50, WINDOW_WIDTH, WINDOW_HEIGHT);
        layeredPane.add(background, BACKGROUND_CONSTRAINT);

        JTextArea textArea = new JTextArea();
        JTextArea textArea2 = new JTextArea();
        textArea.setBounds(580, 350, 1305, 150);
        textArea2.setBounds(430, 500, 1305, 100);
        textArea.setText("Les rôles ont été chargés avec succès !");
        textArea2.setText("Vous pouvez maintenant les afficher en cliquant sur le bouton");
        textArea.setOpaque(false);
        textArea2.setOpaque(false);
        textArea.setEditable(false);
        textArea2.setEditable(false);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(THE_MACABRE_FONT));
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
        textArea.setCaretPosition(BACKGROUND_CONSTRAINT);
        textArea2.setCaretPosition(BACKGROUND_CONSTRAINT);
        layeredPane.add(textArea, FOREGROUND_CONSTRAINT);
        layeredPane.add(textArea2, FOREGROUND_CONSTRAINT); 

        JButton displayRoleButton = new JButton("Afficher les rôles");
        displayRoleButton.setBounds(595, 715, 560, 150);
        displayRoleButton.addActionListener(e -> {Controls.update(appView, new DisplayingRoles(appView, simulator));});
        layeredPane.add(displayRoleButton, FOREGROUND_CONSTRAINT);

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(THE_MACABRE_FONT));
            displayRoleButton.setFont(font.deriveFont(60f));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }
    }

    private void failedLoading(AppView appView){
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        add(layeredPane);

        JLabel background = new JLabel(new ImageIcon(LOAD_BACKGROUND));
        background.setBounds(0, -50, WINDOW_WIDTH, WINDOW_HEIGHT);
        layeredPane.add(background, BACKGROUND_CONSTRAINT);

        JTextArea textArea = new JTextArea();
        JTextArea textArea2 = new JTextArea();
        textArea.setBounds(600, 350, 1305, 150);
        textArea2.setBounds(460, 500, 1305, 100);
        textArea.setText("Le chargement des rôles a échoué !");
        textArea2.setText("Référez-vous au terminal pour plus d'informations");
        textArea.setOpaque(false);
        textArea2.setOpaque(false);
        textArea.setEditable(false);
        textArea2.setEditable(false);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(THE_MACABRE_FONT));
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
        textArea.setCaretPosition(BACKGROUND_CONSTRAINT);
        textArea2.setCaretPosition(BACKGROUND_CONSTRAINT);
        layeredPane.add(textArea, FOREGROUND_CONSTRAINT);
        layeredPane.add(textArea2, FOREGROUND_CONSTRAINT); 

        JButton returnButton = new JButton("Retour");
        returnButton.setBounds(595, 715, 560, 150);
        returnButton.addActionListener(e -> {Controls.update(appView, new MainMenu(appView));});
        layeredPane.add(returnButton, FOREGROUND_CONSTRAINT);

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(THE_MACABRE_FONT));
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

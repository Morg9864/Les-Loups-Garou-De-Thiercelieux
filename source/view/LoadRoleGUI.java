package source.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import source.model.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import source.controller.Controls;

public class LoadRoleGUI extends JFrame{
    private static final int width = 1760;
    private static final int height = 1020;
    private static final String backgroundPath = "images/load.jpg";
    private static final String backImagePath = "images/back.png";
    private static final String fontPath = "font/TheMacabre.otf";
    private static final float fontSize = 60f;

    private JLayeredPane layeredPane;

    public LoadRoleGUI(){
        setTitle("Les Loups-Garous de Thiercelieux");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height + 60));
        setContentPane(layeredPane);

        JLabel background = new JLabel(new ImageIcon(backgroundPath));
        background.setBounds(0, -50, width, height + 60);
        layeredPane.add(background, Integer.valueOf(0));

        loadedGUI();

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void loadedGUI() {
        JTextArea textArea = new JTextArea();
        JTextArea textArea2 = new JTextArea();
        textArea.setBounds((width / 2) - 300, 350, 1305, 150);
        textArea2.setBounds((width / 2) - 450, 500, 1305, 100);
        textArea.setText("Les rôles ont été chargés avec succès !");
        textArea2.setText("Vous pouvez maintenant les afficher en cliquant sur le bouton");
        textArea.setOpaque(false);
        textArea2.setOpaque(false);
        textArea.setEditable(false);
        textArea2.setEditable(false);
        // Pour le font, on va chercher la police dans le dossier font
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            textArea.setFont(font.deriveFont(fontSize));
            textArea2.setFont(font.deriveFont(fontSize));
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
        displayRoleButton.addActionListener(e -> {Controls.displayRolesButtonFunc(this, layeredPane);});
        layeredPane.add(displayRoleButton, Integer.valueOf(1));

        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            displayRoleButton.setFont(font.deriveFont(fontSize));
        } catch (FontFormatException e) {
            System.out.println("Erreur lors du chargement de la police");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Fichier de police introuvable");
            e.printStackTrace();
        }
    }

    public static void rolesGUI(LoadRoleGUI lrg) {
        JLabel background = new JLabel(new ImageIcon(backgroundPath));
        background.setBounds(0, -50, width, height + 60);
        lrg.layeredPane.add(background, Integer.valueOf(0));

        JButton backButton = new JButton(new ImageIcon(backImagePath));
        backButton.addActionListener(e -> {Controls.returnToMainMenuButtonFunc(lrg);});
        backButton.setBounds(30, 30, 130, 100);
        lrg.layeredPane.add(backButton, Integer.valueOf(1));

        try {
            Simulator sim = new Simulator("test.txt");
            lrg.displayRoles(lrg.layeredPane, sim);
        } catch (NbPlayersIncorrectException e1) {
            e1.printStackTrace();
            System.out.println("Erreur lors due la vérification du nombre de joueurs");
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("Erreur lors de la lecture du fichier");
        }
    }

    private void displayRoles(JLayeredPane layeredPane, Simulator sim) {
        JPanel panel = new JPanel();

        int nbLines;
        int resize;
        if(sim.getNbRoles() <= 16){
            nbLines = 2;
            resize = 175;
        }
        else if(sim.getNbRoles() <= 24){
            nbLines = 3;
            resize = 150;
        }
        else{
            nbLines = 4;
            resize = 125;
        }

        int nbColumns =  (int) Math.ceil((double) sim.getNbRoles() / nbLines);
        GridLayout layout = new GridLayout(nbLines, nbColumns, 10, 10);
        panel.setLayout(layout);
        panel.setOpaque(false);
        createCases(panel, sim, resize);

        // I want a 1710, 800 panel
        panel.setBounds(23, 170, 1710, 800);
        layeredPane.add(panel, Integer.valueOf(1));
    }
    
    private void createCases(JPanel panel, Simulator sim, int resize) {
        HashMap<Role, Integer> roles = sim.getSelectedRoles();
        for(Role role : roles.keySet()){
            ImageIcon imageIcon = new ImageIcon(role.getImage());
            imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(resize, resize, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setPreferredSize(new Dimension(125, 125));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel nameLabel = new JLabel(role.getName() + " x" + roles.get(role));
            nameLabel.setHorizontalAlignment(JLabel.CENTER);

            int verticalPadding = 10; // Définir la valeur de padding vertical souhaitée

            JPanel labelPanel = new JPanel(new BorderLayout());
            labelPanel.setBorder(new EmptyBorder(verticalPadding, 0, verticalPadding, 0));
            labelPanel.add(imageLabel, BorderLayout.CENTER);
            labelPanel.add(nameLabel, BorderLayout.SOUTH);

            // Change the background color of the panel
            if(role.getTeam() == Team.VILLAGE)
                labelPanel.setBackground(Color.GREEN);
            else if(role.getTeam() == Team.LOUPS)
                labelPanel.setBackground(Color.RED);
            else
                labelPanel.setBackground(Color.YELLOW);


            //panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(labelPanel, BorderLayout.CENTER);
        }
    }

}

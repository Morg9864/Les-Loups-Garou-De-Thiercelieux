package source.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import source.controller.Controls;
import source.model.Const;
import source.model.ModifiedMouseListener;
import source.model.Role;
import source.model.Simulator;
import source.model.Team;

public class DisplayingRoles extends JPanel implements Const{
    public DisplayingRoles(AppView appView, Simulator simulator) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        add(layeredPane);

        JLabel background = new JLabel(new ImageIcon(ROLES_BACKGROUND));
        background.setBounds(0, -50, WINDOW_WIDTH, WINDOW_HEIGHT);
        layeredPane.add(background, BACKGROUND_CONSTRAINT);

        ImageIcon backButtonIcon = new ImageIcon(BACK_ARROW);
        backButtonIcon = new ImageIcon(backButtonIcon.getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH));
        RoundButton backButton = new RoundButton(backButtonIcon);
        backButton.setBounds(35, 30, 150, 130);
        backButton.addActionListener(e -> {Controls.update(appView, new MainMenu(appView));});
        layeredPane.add(backButton, FOREGROUND_CONSTRAINT);

        ImageIcon saveButtonIcon = new ImageIcon(SAVE_ICON);
        saveButtonIcon = new ImageIcon(saveButtonIcon.getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH));
        RoundButton saveButton = new RoundButton(saveButtonIcon);
        saveButton.setBounds(1575, 30, 150, 130);
        saveButton.addActionListener(e -> {Controls.saveConfiguration(appView, simulator);});
        layeredPane.add(saveButton, FOREGROUND_CONSTRAINT);


        JPanel grid = new JPanel();
        grid.setBounds(23, 170, 1710, 800);
        layeredPane.add(grid, FOREGROUND_CONSTRAINT);

        int nbLines, nbColumns, resize;
        
        if(simulator.getNbRoles() <= 16){
            nbLines = 2;
            resize = 175;
        }
        else if(simulator.getNbRoles() <= 24){
            nbLines = 3;
            resize = 150;
        }
        else{
            nbLines = 4;
            resize = 125;
        }

        nbColumns =  (int) Math.ceil((double) simulator.getNbRoles() / nbLines);
        GridLayout layout = new GridLayout(nbLines, nbColumns, 10, 10);
        grid.setLayout(layout);
        grid.setOpaque(false);
        createCases(appView, this, grid, simulator, resize);

    }

    private void createCases(AppView appView, JPanel rolePanel, JPanel panel, Simulator sim, int resize){
        HashMap <Role, Integer> roles = sim.getSelectedRoles();
        for(Role role : roles.keySet()){
            ImageIcon imageIcon = new ImageIcon(role.getImage());
            imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(resize, resize, Image.SCALE_SMOOTH));

            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setPreferredSize(new Dimension(125, 125));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel nameLabel = new JLabel(role.getName() + " x" + roles.get(role));
            nameLabel.setHorizontalAlignment(JLabel.CENTER);

            
            nameLabel.setFont(nameLabel.getFont().deriveFont(15f));

            int verticalPadding = 10; // Définir la valeur de padding vertical souhaitée

            JPanel labelPanel = new JPanel(new BorderLayout());
            labelPanel.setBorder(new EmptyBorder(verticalPadding, 0, verticalPadding, 0));
            labelPanel.add(imageLabel, BorderLayout.CENTER);
            labelPanel.add(nameLabel, BorderLayout.SOUTH);
            labelPanel.addMouseListener(new ModifiedMouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    Controls.update(appView, new ExplainingRoles(appView, rolePanel, role));
                }
            });
            
            // Change the background color of the panel
            if(role.getTeam() == Team.VILLAGE)
                labelPanel.setBackground(Color.GREEN);
            else if(role.getTeam() == Team.LOUPS)
                labelPanel.setBackground(Color.RED);
            else
                labelPanel.setBackground(Color.YELLOW);

            panel.add(labelPanel);
        }
    }
}   

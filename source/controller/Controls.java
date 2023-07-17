package source.controller;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import source.model.Const;
import source.model.Simulator;
import source.view.AppView;
import source.view.GeneratingRoles;
import source.view.SavePopUp;
import source.view.SettingUpGenerator;

public class Controls implements Const{
    private Controls() {
    }

    public static void update(AppView appView, JPanel panel) {
        // appView.changeContent(panel);
        appView.getContentPane().removeAll();
        appView.getContentPane().add(panel, BorderLayout.CENTER);
        appView.getContentPane().repaint();
        appView.getContentPane().revalidate();
    }
    
    public static void saveConfiguration(AppView appView, Simulator simulator){
        try {
            simulator.save(ROLES_FILE);
            new SavePopUp(appView);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de la configuration");
            e.printStackTrace();
        }
    }

    public static void getExtension(AppView appView, JLayeredPane layeredPane, JTextArea textArea,
            JComboBox<Integer> nbPlayersList, JButton validateButton) {
        int nbPlayersSelected = (int) nbPlayersList.getSelectedItem();
        if(nbPlayersSelected < 43){
            textArea.setText("Choix extension :");
            textArea.setBounds(735, 350, 350, 70);
            layeredPane.remove(nbPlayersList);
            layeredPane.remove(validateButton);
            SettingUpGenerator.extension(appView, layeredPane, nbPlayersSelected);
        } else {
            Controls.update(appView, new GeneratingRoles(appView, nbPlayersSelected, true, true));
        }
    }
}

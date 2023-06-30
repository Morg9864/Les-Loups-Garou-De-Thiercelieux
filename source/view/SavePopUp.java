package source.view;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

import source.model.Const;

public class SavePopUp extends JDialog implements Const{
    public SavePopUp(AppView appView) {
        super(appView, "Configuration sauvegardée", true);
        setSize(500, 200);
        JLabel label = new JLabel("La configuration a été sauvegardée avec succès !");
        label.setFont(new Font("Arial", Font.BOLD, 17));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add(label);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setVisible(true);
    }
}

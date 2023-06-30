package source.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import source.controller.Controls;
import source.model.ConvertIntoIcon;

public class AppView extends JFrame implements Const{
    public AppView(){
        setTitle("Les Loups-Garous de Thiercelieux");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT - 60);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        ImageIcon icon = ConvertIntoIcon.convert();
        setIconImage(icon.getImage()); 

        Controls.update(this, new MainMenu(this));

    }
}

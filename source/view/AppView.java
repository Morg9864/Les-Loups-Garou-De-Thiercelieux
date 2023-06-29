package source.view;

import javax.swing.JFrame;

import source.controller.Controls;

public class AppView extends JFrame implements Const{
    public AppView(){
        setTitle("Les Loups-Garous de Thiercelieux");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT - 60);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        Controls.update(this, new MainMenu(this));

    }
}

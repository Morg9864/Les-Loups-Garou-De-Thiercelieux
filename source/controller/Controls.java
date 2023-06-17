package source.controller;

import java.awt.Window;

import javax.swing.JLayeredPane;

import source.view.GenerateRoleGUI;
import source.view.LoadRoleGUI;
import source.view.SimulatorMainGUI;

public class Controls {
    public static void loadRoleButtonFunc(Window window){
        new LoadRoleGUI();
        window.dispose();
    }

    public static void generateRoleButtonFunc(Window window){
        new GenerateRoleGUI();
        window.dispose();
    }

    public static void returnToMainMenuButtonFunc(Window window){
        new SimulatorMainGUI();
        window.dispose();
    }

    public static void displayRolesButtonFunc(LoadRoleGUI lrg, JLayeredPane layeredPane){
        // Vide le layeredPane
        layeredPane.removeAll();
        source.view.LoadRoleGUI.rolesGUI(lrg);
        layeredPane.repaint();
    }
}

package Project.Main;

import Project.GUI.MainFrame;
import Project.Listener.Button;

import static Project.GUI.MainFrame.*;

public class Main {
    public static void main(String[] args) {
        Button.register();
        MainFrame.initialize();
        frame.setVisible(true);
    }
}
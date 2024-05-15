package Project.Main;

import Project.GUI.Blocks;
import Project.GUI.MainFrame;
import Project.GUI.I_Shape_Blocks;
import Project.Listener.Button;
import Project.Listener.Listener;

import static Project.GUI.MainFrame.*;

public class Main {
    public static final Timer timer = new Timer();
    public static void main(String[] args) {

        I_Shape_Blocks.register();
        Blocks.Initialize();

        Button.register();
        Listener.register();
        MainFrame.initialize();

        frame.setVisible(true);
        timer.execute();// 启动计时器
    }
}
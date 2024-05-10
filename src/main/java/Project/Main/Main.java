package Project.Main;

import Project.GUI.MainFrame;
import Project.Listener.Button;
import Project.Listener.Listener;

import static Project.GUI.MainFrame.*;

public class Main {
    public static void main(String[] args) {
        Button.register();
        Listener.register();
        MainFrame.initialize();
        frame.setVisible(true);
        Timer.timer.execute();// 启动计时器
    }
}
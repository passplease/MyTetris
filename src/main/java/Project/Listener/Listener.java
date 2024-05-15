package Project.Listener;

import Project.GUI.MainFrame;
import Project.Main.Timer;

import javax.swing.*;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Project.GUI.MainFrame.frame;
import static Project.GUI.MainFrame.sayText;
import static Project.Listener.Actions.*;

public class Listener {
    public static final InputMap inputMap = MainFrame.colorPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    public static final ActionMap actionMap = MainFrame.colorPanel.getActionMap();
    private static void Initialize(){
        addMap(KeyEvent.VK_W,clockwise,rotateClockwise);
        addMap(KeyEvent.VK_S,counterClockwise,rotateCounterClockwise);
        addMap(KeyEvent.VK_A,left,moveLeft);
        addMap(KeyEvent.VK_D,right,moveRight);
        addMap(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK,down,moveDown);
        // 要按下ctrl才能一下移动到底
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标进入窗口时触发
                super.mouseEntered(e);
                Timer.resume();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                // 鼠标移出窗口时触发
                sayText("您的鼠标在窗口外，点击将使得游戏失去焦点！");
            }
        });
    }
    public static void addMap(int key,String string,AbstractAction action){
        addMap(key,0,string,action);
    }
    public static void addMap(int key,int mode,String string,AbstractAction action){
        inputMap.put(KeyStroke.getKeyStroke(key,mode),string);
        // 0表示不使用任何修饰键(修饰键指shift等等的)
        actionMap.put(string,action);
    }
    public static void register(){
        MainFrame.register(Listener::Initialize,"键盘和鼠标的监听器的初始化");
    }
}
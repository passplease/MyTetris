package Project.Listener;

import Project.GUI.MainFrame;
import javax.swing.*;

import static Project.Listener.Actions.*;

public class Button{
    public static final JButton Clockwise = new JButton(clockwise);
    public static final JButton CounterClockwise = new JButton(counterClockwise);
    public static final JButton Left = new JButton(left);
    public static final JButton Right = new JButton(right);
    public static final JButton Down = new JButton(down);
    private static void Initialize(){
        addAction();
    }
    public static void addAction(){
        Clockwise.addActionListener(Actions.rotateClockwise);
        CounterClockwise.addActionListener(Actions.rotateCounterClockwise);
        Left.addActionListener(Actions.moveLeft);
        Right.addActionListener(Actions.moveRight);
        Down.addActionListener(Actions.moveDown);
    }
    public static void register(){
        MainFrame.register(Button::Initialize,"来自按钮的初始化");
    }
}
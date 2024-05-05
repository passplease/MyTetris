package Project.Listener;

import Project.GUI.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static Project.GUI.MainFrame.*;

public class Button{
    public static final String clockwise = "顺时针旋转";
    public static final JButton Clockwise = new JButton(clockwise);
    public static final String counterClockwise = "逆时针旋转";
    public static final JButton CounterClockwise = new JButton(counterClockwise);
    public static final String left = "向左移动";
    public static final JButton Left = new JButton(left);
    public static final String right = "向右移动";
    public static final JButton Right = new JButton(right);
    public static final String down = "迅速到位";
    public static final JButton Down = new JButton(down);
    public static void Initialize(){
        addAction();
    }
    public static void addAction(){
        Clockwise.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBlock().rotateClockwise();
            }
        });
        CounterClockwise.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getBlock().rotateCounterClockwise();
            }
        });
        Left.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x -= 1;
                if(getBlock().examineNow())
                    repaint();
                else x += 1;
            }
        });
        Right.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x += 1;
                if(getBlock().examineNow())
                    repaint();
                else x -= 1;
            }
        });
        Down.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (getBlock().examineDrop() && y <= height)
                    y += 1;
                getBlock().solidified();
            }
        });
    }
    public static void register(){
        MainFrame.register(Button::Initialize,"来自按钮的初始化");
    }
}
package Project.Listener;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static Project.GUI.MainFrame.*;

public class Actions {
    public static final String clockwise = "顺时针旋转";
    public static final String counterClockwise = "逆时针旋转";
    public static final String left = "向左移动";
    public static final String right = "向右移动";
    public static final String down = "迅速到位";
    public static final AbstractAction rotateClockwise = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            getBlock().rotateClockwise();
            repaint();
        }
    };
    public static final AbstractAction rotateCounterClockwise = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            getBlock().rotateCounterClockwise();
            repaint();
        }
    };
    public static final AbstractAction moveLeft = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            x -= 1;
            if(getBlock().examineNow())
                repaint();
            else x += 1;
        }
    };
    public static final AbstractAction moveRight = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            x += 1;
            if(getBlock().examineNow())
                repaint();
            else x -= 1;
        }
    };
    public static final AbstractAction moveDown = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            while (getBlock().examineDrop() && y <= height)
                y += 1;
            getBlock().solidified();
        }
    };
}

package Project.Listener;

import Project.GUI.Blocks;
import Project.Main.Timer;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import static Project.GUI.MainFrame.*;

public class FrameListener implements ComponentListener {
    // 用于监听窗口大小等变化
    @Override
    public void componentResized(ComponentEvent e) {
        /*  窗口大小改变时调用此方法
            在这里编写调整 JPanel 大小的代码
            可以根据 JFrame 的新大小来调整 JPanel 的大小
            例如，设置 JPanel 的新大小为 JFrame 的大小
        */
        int dividedPoint1 = frame.getWidth() / 5 + 1;
        int dividedPoint2 = dividedPoint1 + length * Blocks.size + 100;
        int height = frame.getHeight();
        int X = frame.getX();
        panel.setLocation(0,0);
        panel.setSize(dividedPoint1,height);
        colorPanel.setLocation(dividedPoint1,0);
        colorPanel.setSize(dividedPoint2 - dividedPoint1,height);
        scorePanel.setLocation(dividedPoint2,0);
        scorePanel.setSize(frame.getWidth() + X - dividedPoint2,height);
        text.setTextLocation();
        calculateStartPaintHeight();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // 窗口移动时调用此方法
        calculateStartPaintHeight();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // 窗口显示时调用此方法
        Timer.resume();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // 窗口隐藏时调用此方法
        Timer.pause();
    }
    private static void calculateStartPaintHeight(){
        if(colorPanel.getHeight() > Blocks.size * height)
            startPaintHeight = colorPanel.getY() + colorPanel.getHeight() / 2 - Blocks.size * height / 2;
        else startPaintHeight = colorPanel.getY();
    }
}

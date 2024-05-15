package Project.Listener;

import Project.GUI.Blocks;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import static Project.GUI.MainFrame.*;
import static Project.Main.Main.timer;

public class FrameListener implements ComponentListener {
    // 用于监听窗口大小等变化
    @Override
    public void componentResized(ComponentEvent e) {
        /*  窗口大小改变时调用此方法
            在这里编写调整 JPanel 大小的代码
            可以根据 JFrame 的新大小来调整 JPanel 的大小
            例如，设置 JPanel 的新大小为 JFrame 的大小
        */
        colorPanel.setSize(length * Blocks.size + 100,frame.getHeight());
        colorPanel.setLocation(frame.getWidth() / 5 + 1,0);
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
        timer.execute();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // 窗口隐藏时调用此方法
        timer.cancel(true);
    }
    private static void calculateStartPaintHeight(){
        if(colorPanel.getHeight() > Blocks.size * height)
            startPaintHeight = colorPanel.getY() + colorPanel.getHeight() / 2 - Blocks.size * height / 2;
        else startPaintHeight = colorPanel.getY();
    }
}

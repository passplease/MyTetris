package Project.Main;

import javax.swing.*;

import java.awt.*;

import static Project.GUI.MainFrame.*;
import static Project.Main.Main.timer;

public class Timer extends SwingWorker<Integer, Integer>{
    // 在这里写俄罗斯方块的总逻辑
    public static final int time = 1000 * 2;
    // 一个加载周期的时长，也控制方块下落速度
    private volatile boolean working = true;
    private volatile Object lock = new Object();
    @Override
    protected Integer doInBackground(){// 这里面进行计时规划操作
        try {
            while (true) {
                if(working && frame.isFocusable()){
                    if(!getBlock().drop()){
                        if(!getBlock().spawn())
                            end();
                        eliminateColor();
                        spawnNextBlock();
                    }
                    repaint();
                    System.out.println("Timer is running");
                    Thread.sleep(time);
                }else lock.wait();
            }
        }catch (Exception e){
            sayText("游戏出现故障！请重新启动！");
        }
        return 0;
        /*
            在Swing中，按键事件和其他事件（比如鼠标点击事件）是由事件分发线程（Event Dispatch Thread，简称EDT）处理的。
            所有的UI事件都应该在EDT上进行处理，而且在EDT上执行的代码应该尽可能地快速完成，以确保用户界面的响应性和流畅性。
            如果用户按下按键时，程序正在进行其他工作，而且这些工作是在EDT上执行的，
            那么直到这些工作完成后，才会处理按键事件。这可能会导致用户感到程序的响应速度变慢，因为UI线程被占用。
            为了确保UI的响应性，应该将长时间运行的任务放在单独的线程中执行，而不是在EDT上执行。
            这可以通过使用SwingWorker、ExecutorService等工具来实现。
            这样，即使在程序进行其他工作时，按键事件仍然可以得到及时的处理。
        */
    }
    public static void resume(){
        timer.working = true;
        timer.lock.notify();
    }
    public static void end(){
        pause();
        frame.dispose();
    }
    public static void pause(){
        timer.working = false;
    }
    public static void eliminateColor(){
        for (int i = 0; i < height - 1; i++) {
            for (int j = 0; j < length - 1; j++) {
                if(color[j][i] != color[j+1][i])
                    break;
                else if(j == length - 2){
                    for (j = 0;j < length;j++){
                        for (int k = i; k < height; k++) {
                            if(k < height - 1)
                                color[j][k] = color[j][k+1];
                            else color[j][k] = new Color(0,0,0,0);// 赋予透明颜色
                        }
                    }
                    break;
                }
            }
        }
    }
}
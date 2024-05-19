package Project.Main;

import Project.GUI.MainFrame;
import javax.swing.*;
import java.awt.*;
import static Project.GUI.MainFrame.*;

public class Timer extends SwingWorker<Integer, Integer>{
    // 在这里写俄罗斯方块的总逻辑
    public static final int time = 1000 * 2;
    // 一个加载周期的时长，也控制方块下落速度
    private static volatile boolean working = true;
    private static final Object lock = new Object();
    // 注：下面那个是HTML标签
    /**<pre><font color=yellow >
     *      当在 <code>SwingWorker</code> 的 <code>doInBackground()</code>方法中需要对 Swing 组件进行操作时，
     *      确保使用 <code>SwingUtilities.invokeLater()</code> 或 <code>SwingUtilities.invokeAndWait()</code>
     *      来确保界面操作在事件调度线程中执行，以下是具体的做法：
     *      </font>
     *<span style="color: rgb(93,169,29);">
     * 一、使用 <code>SwingUtilities.invokeLater()</code>:
     *      如果你需要在 <code>doInBackground()</code> 中更新 Swing 组件，
     *      可以使用 <code>SwingUtilities.invokeLater()</code> 来在事件调度线程中执行更新操作。例如：</span>
     *<font color=red>-----------------------------------------------------------------------------------</font>
     * <span style="color: rgb(42,172,184);">
     * SwingUtilities.invokeLater(new Runnable() {
     *     public void run() {
     *         // 在这里进行对 Swing 组件的操作
     *     }
     * });</span>
     *<font color=red>-----------------------------------------------------------------------------------</font>
     * <span style="color: rgb(93,169,29);">
     * 二、使用 <code>SwingUtilities.invokeAndWait()</code>:
     *      如果需要等待更新操作执行完成后才继续 <code>doInBackground()</code> 的执行，
     *      可以使用 <code>SwingUtilities.invokeAndWait()</code>，例如：</span>
     *<font color=red>-----------------------------------------------------------------------------------</font>
     * <span style="color: rgb(42,172,184);">try {
     *     SwingUtilities.invokeAndWait(new Runnable() {
     *         public void run() {
     *             // 在这里进行对 Swing 组件的操作
     *         }
     *     });
     * } catch (InterruptedException | InvocationTargetException e) {
     *     // 处理异常
     * };</span>
     *<font color=red>-----------------------------------------------------------------------------------</font>
     * <font color=yellow >通过以上方法，
     *      在 <code>SwingWorker</code> 的 <code>doInBackground()</code> 方法中进行界面操作时，
     *      可以确保这些操作在事件调度线程中执行，避免多线程操作导致的线程安全问题。
     *      这样可以确保界面操作与后台任务的执行不会相互干扰，提高程序的稳定性和可靠性。</font></pre>
     * */
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
                }else try {
                    lock.wait();
                } catch (InterruptedException e) {
                    MainFrame.throwError();
                    throw new RuntimeException(e);
                }
                System.out.println("Timer is running");
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            MainFrame.throwError();
            throw new RuntimeException(e);
        }
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
        working = true;
        synchronized (lock){
            lock.notify();
        }
    }
    public static void end(){
        sayText("游戏正在关闭……");
        pause();
        frame.dispose();
    }
    public static void pause(){
        working = false;
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
                                color[j][k] = color[j][k + 1];
                            else color[j][k] = new Color(0,0,0,0);// 赋予透明颜色
                        }
                    }
                    break;
                }
            }
        }
    }
}
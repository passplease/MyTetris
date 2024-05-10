package Project.Main;

import javax.swing.*;

import static Project.GUI.MainFrame.*;

public class Timer {
    // 在这里写俄罗斯方块的总逻辑
    public static final int time = 1000 * 2;
    // 一个加载周期的时长，也控制方块下落速度
    public static final SwingWorker timer = new SwingWorker() {
        @Override
        protected Object doInBackground(){
            try {
                while (true) {
                    if(!getBlock().drop()){
                        if(!getBlock().spawn())
                            EndGame();
                    }
                    for (int i = 0; i < length - 1; i++) {
                        for (int j = 0; j < height - 1; j++) {
                            if(color[i][j] != color[i + 1][j]){
                                break;
                            }else if (j == height - 2)
                                for (int k = 0; k < height - 1; k++) {
                                    color[i][k] = null;
                                }
                        }
                    }
                    repaint();
                    Thread.sleep(time);
                }
            }catch (Exception e){
                sayText("游戏出现故障！请重新启动！");
            }
            return null;
        }// 这里面进行计时规划操作
        /*
            在Swing中，按键事件和其他事件（比如鼠标点击事件）是由事件分发线程（Event Dispatch Thread，简称EDT）处理的。
            所有的UI事件都应该在EDT上进行处理，而且在EDT上执行的代码应该尽可能地快速完成，以确保用户界面的响应性和流畅性。
            如果用户按下按键时，程序正在进行其他工作，而且这些工作是在EDT上执行的，
            那么直到这些工作完成后，才会处理按键事件。这可能会导致用户感到程序的响应速度变慢，因为UI线程被占用。
            为了确保UI的响应性，应该将长时间运行的任务放在单独的线程中执行，而不是在EDT上执行。
            这可以通过使用SwingWorker、ExecutorService等工具来实现。
            这样，即使在程序进行其他工作时，按键事件仍然可以得到及时的处理。
        */
    };
}
package Project.GUI;

import Project.Listener.FrameListener;
import Project.SomeInterface.FunctionalInterface;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static Project.GUI.Blocks.randomGetBlock;

public class MainFrame {
    public static final String title = "我的俄罗斯方块";
    public static final JFrame frame = new JFrame(title);
    public static final int height = 20;// 高20个方块
    public static final int length = 10;// 长10个方块
    private static final Map<String,FunctionalInterface> map = new HashMap<>();
    public static final JTextArea text = new JTextArea();
    public static void setBounds(){
        text.setBounds(scorePanel.getX() + scorePanel.getWidth() / 2 - 5,scorePanel.getY() + scorePanel.getHeight() / 10,100,40);
    }
    // 写玩家分数
    public static int score = 0;
    public static int startPaintHeight;
    public static int x;// 方块坐标，以左侧为0
    public static int y;// 方块坐标，以上方为0
    public static Color[][] color = new Color[length][height];
    public static final JPanel colorPanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 绘制color图层
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < height; j++) {
                    g.setColor(color[i][j]);
                    g.fillRect(colorPanel.getX() + i * Blocks.size,startPaintHeight + j * Blocks.size,Blocks.size,Blocks.size);
                }
            }
        }
    };
    public static final JPanel panel = new JPanel();
    public static final JPanel scorePanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    };
    protected static Blocks block;
    protected static Blocks nextBlock;

    public static void initialize(){
        frame.setLayout(null);// 禁用布局管理器，这样可以自己设置组件位置
        frame.setBounds(100,200,600,500);// 设置显示位置
        frame.addComponentListener(new FrameListener());// 添加组件监听器，监听窗口变化
        colorPanel.setBackground(new Color(0x3D3B3B));
        scorePanel.setBackground(new Color(42,172,184));
        panel.setBackground(new Color(0x321F2424, true));
        frame.add(colorPanel);
        frame.add(scorePanel);
        frame.add(panel);
        scorePanel.add(text);
        setBounds();
        text.setEditable(false);
        frame.setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                color[i][j] = new Color(0,0,0,0);
            }
        }
        for(Map.Entry<String, FunctionalInterface> mapping : map.entrySet()){
            mapping.getValue().register();//执行记录的操作
        }
        block = randomGetBlock();
        block.spawn();
        nextBlock = randomGetBlock();
        text.setFont(new Font("楷体",Font.BOLD,20));
        text.setBackground(new Color(0,0,0,0));
        text.append(String.valueOf(score));
    }
    public static void register(FunctionalInterface function,boolean time,String text){
        if(time)
            function.register();
            //将函数作为参数调用这个函数
        else
            map.put(text,function);
    }
    public static void register(FunctionalInterface function,String text){
        register(function,false,text);
    }
    public static void repaint(){
        text.repaint();
        block.show();
        nextBlock.readyShow();
        colorPanel.repaint();
        frame.repaint();
    }
    public static void sayText(String text){}
    public static Blocks getBlock(){
        return block;
    }
    public static void spawnNextBlock(){
        block = nextBlock;
        block.spawn();
        nextBlock = randomGetBlock();
        nextBlock.readyShow();
    }
    public static void throwError(){
        sayText("游戏出现故障！请重新启动！");
    }
}
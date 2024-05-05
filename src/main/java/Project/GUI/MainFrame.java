package Project.GUI;

import Project.SomeInterface.FunctionalInterface;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame {
    public static final String title = "我的俄罗斯方块";
    public static final JFrame frame = new JFrame(title);
    public static final JPanel panel = new JPanel();
    public static final int height = 20;// 高20个方块
    public static final int length = 10;// 长10个方块
    private static final Map<String,FunctionalInterface> map = new HashMap<>();
    public static int ScreenWidth;
    public static int ScreenHeight;
    public static int x;// 方块坐标，以左侧为0
    public static int y;// 方块坐标，以上方为0
    public static Color[][] color = new Color[length][height];
    protected static Blocks block;

    public static void initialize(){
        ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setSize(1000,500);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(Map.Entry<String, FunctionalInterface> mapping : map.entrySet()){
            mapping.getValue().register();//执行记录的操作
        }
        color = null;
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
        block.show();
        frame.repaint();
        panel.repaint();
    }
    public static Blocks getBlock(){
        return block;
    }
}
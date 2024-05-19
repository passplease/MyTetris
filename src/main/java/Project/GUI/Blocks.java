package Project.GUI;

import Project.SomeInterface.FunctionalInterface;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static Project.GUI.MainFrame.*;

public abstract class Blocks {
    protected static final Map<Integer,Blocks> allBlocks = new HashMap<>();
    private static final Map<Byte, FunctionalInterface> registerMap = new HashMap<>();
    public static final int size = 20;// 定义每个格子占几个像素
    public static Color color = null;
    public abstract void rotateClockwise();
    public abstract void rotateCounterClockwise();
    // 旋转要检查自己能不能旋转
    abstract void show();
    // 渲染自己
    public abstract void solidified();
    // 固化为背景颜色，如果可以消除就要消除一排，自带repaint
    public abstract boolean examineDrop();
    // 检查是否还能下落
    public abstract boolean examineNow();
    // 检测能不能在这里存在
    public final boolean drop(){
        y += 1;
        if(examineDrop()){
            repaint();
            return true;
        }else {
            solidified();
            return false;
        }
        // 如果不可以下落就执行solidified
    }
    public final boolean spawn(){
        show();
        x = length / 2 + 1;
        y = 0;
        return examineNow();
    }
    // 返回false时代表游戏已经失败
    abstract void readyShow();
    // 在等待区域显示
    protected static Blocks randomGetBlock(){
        return allBlocks.get(new Random().nextInt() % allBlocks.size());
    }
    public static void Initialize(){
        for(Map.Entry<Byte,FunctionalInterface> mapping : registerMap.entrySet()){
            mapping.getValue().register();
        }
    }
    public static void Register(FunctionalInterface function){
        registerMap.put((byte) 0,function);
    }
}

package Project.GUI;

import Project.SomeInterface.FunctionalInterface;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static Project.GUI.MainFrame.*;

public interface Blocks {
    Map<Integer,Blocks> allBlocks = new HashMap<>();
    Map<Byte,FunctionalInterface> registerMap = new HashMap<>();
    int size = 20;// 定义每个格子占几个像素
    Color color = null;
    void rotateClockwise();
    void rotateCounterClockwise();
    // 旋转要检查自己能不能旋转
    void show();
    // 渲染自己
    void solidified();
    // 固化为背景颜色，如果可以消除就要消除一排，自带repaint
    boolean examineDrop();
    // 检查是否还能下落
    boolean examineNow();
    // 检测能不能在这里存在
    default boolean drop(){
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
    default boolean spawn(){
        show();
        x = length / 2 + 1;
        y = 0;
        return examineNow();
    }
    // 返回false时代表游戏已经失败
    void readyShow();
    // 在等待区域显示
    static Blocks randomGetBlock(){
        return allBlocks.get(new Random().nextInt() % allBlocks.size());
    }
    static void Initialize(){
        for(Map.Entry<Byte,FunctionalInterface> mapping : registerMap.entrySet()){
            mapping.getValue().register();
        }
    }
    static void Register(FunctionalInterface function){
        registerMap.put((byte) 0,function);
    }
}

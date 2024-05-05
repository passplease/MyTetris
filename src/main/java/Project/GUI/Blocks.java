package Project.GUI;

import java.awt.*;

import static Project.GUI.MainFrame.*;

public interface Blocks {
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
            repaint();
            return false;
        }
        // 如果不可以下落就执行solidified
    }
    default boolean spawn(){
        x = length / 2 + 1;
        y = 0;
        if(examineNow()){
            repaint();
            return true;
        }else return false;
    }
    // 返回false时代表已经失败
}

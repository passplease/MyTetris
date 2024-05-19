package Project.GUI;

import javax.swing.*;
import java.awt.*;

public class Text extends JTextArea {
    public Text(String text){
        super();
        this.setTextLocation();
        this.setFont(new Font("楷体",Font.BOLD,20));
        this.setBackground(new Color(0,0,0,0));
        this.setEditable(false);
        this.setText(text);
    }
    public void setTextLocation(JPanel panel){
        this.setBounds(panel.getX() + panel.getWidth() / 10,panel.getY() + panel.getHeight() / 10,100,40);
        this.setAlignmentX(0);// 设置X对齐起点
        this.setAlignmentY(0);// 设置Y对齐起点
    }
    public void setTextLocation(){
        setTextLocation(MainFrame.scorePanel);
    }
    @Deprecated// 表示废弃
    public void insertText(String text){
        String[] dividedText = text.split("\n");
        for (int i = 0;i < dividedText.length;i++){
            this.append(dividedText[i] + "\n");
        }
    }
    public void resetText(String text){
        this.setText(null);
        insertText(text);
    }
}
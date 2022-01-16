package com.geekpig.draw;

import javax.swing.*;
import java.awt.*;
//practice drawing circle

//JFrame is a window
public class DrawCircle extends JFrame{
    //define a panel
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }
    public DrawCircle(){
        //initialize a panel
        mp = new MyPanel();
        //put panel into window
        this.add(mp);
        this.setSize(400,300);//set the size of window
        this.setVisible(true);//set window visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
//First define a MyPanel class extends JPanel,
//JPanel is a panel used to draw
class MyPanel extends JPanel{

    //Graphics g is a pen to draw
    @Override
    public void paint(Graphics g) {//drawing method

        //Call the method of the parent class to complete the initialization

        super.paint(g);//must be reserved
        g.drawOval(10,10,100,100);
    }
}

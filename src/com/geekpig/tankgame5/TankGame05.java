package com.geekpig.tankgame5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankGame05 extends JFrame {
    //define a MyPanel
    private MyPanel mp = null;
    int i=0;

    public static void main(String[] args) {
        new TankGame05();
    }
    //constructor can't bu put in main method
    public TankGame05(){
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);

        this.setSize(1300,950);
        this.setVisible(true);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}

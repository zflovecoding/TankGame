package com.geekpig.tankgame2;

import javax.swing.*;


public class TankGame02 extends JFrame {
    //define a MyPanel
    private MyPanel mp = null;
    int i=0;

    public static void main(String[] args) {
        new TankGame02();
    }
    //constructor can't bu put in main method
    public TankGame02(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,750);
        this.setVisible(true);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}

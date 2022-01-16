package com.geekpig.tankgame;

import javax.swing.*;

public class TankGame01 extends JFrame {
    //define a MyPanel
    private  MyPanel mp = null;
    public static void main(String[] args) {
        new TankGame01();
    }
    //constructor can't bu put in main method
    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

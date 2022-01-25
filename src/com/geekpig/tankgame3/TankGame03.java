package com.geekpig.tankgame3;

import javax.swing.*;


public class TankGame03 extends JFrame {
    //define a MyPanel
    private MyPanel mp = null;
    int i=0;

    public static void main(String[] args) {
        new TankGame03();
    }
    //constructor can't bu put in main method
    public TankGame03(){
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);

        this.setSize(1000,750);
        this.setVisible(true);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

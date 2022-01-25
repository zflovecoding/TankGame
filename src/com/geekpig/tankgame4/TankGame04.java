package com.geekpig.tankgame4;

import javax.swing.*;


public class TankGame04 extends JFrame {
    //define a MyPanel
    private MyPanel mp = null;
    int i=0;

    public static void main(String[] args) {
        new TankGame04();
    }
    //constructor can't bu put in main method
    public TankGame04(){
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);

        this.setSize(1200,950);
        this.setVisible(true);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

package com.geekpig.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame {
    private MyPanel mp = null;
    public static void main(String[] args) {
        new BallMove();
    }
    public BallMove(){
        mp =  new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JFrame add a window listener,can listen the event happened in panel
        this.addKeyListener(mp);
        this.setVisible(true);
    }
}
//KeyListener is a listener which can listen what happened to keyboard
class MyPanel extends JPanel implements KeyListener {
    //set the x y variable to make ball move
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }
    //when a char is input ,this method get triggered
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            y--;
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            y++;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            x--;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            x++;
        }
        //repaint the panel ,probably like refresh
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

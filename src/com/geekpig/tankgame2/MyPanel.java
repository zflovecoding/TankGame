package com.geekpig.tankgame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//the drawing area of TankGame
public class MyPanel extends JPanel implements KeyListener {
    //define my tank
    MyTank myTank = null;
    //define enemy tanks put into vector considering multithreading
    //Vector is thread-safe
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //the numbers of enemy tanks
    private int enemyTankNum = 3;
    //change the speed of tank should change it when initialization
    public MyPanel(){
        myTank = new MyTank(100,100);//initialization my tank
        myTank.setSpeed(5);
        //initialize enemy tanks
        for (int i = 0; i < enemyTankNum; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//fill the rectangle,default black
        //draw tank here----package into a method to draw tank
        drawTank(myTank.getX(),myTank.getY(),g,myTank.getDirect(),0);
        //draw enemy tanks
        for (int i = 0; i < enemyTanks.size(); i++) {
            drawTank(enemyTanks.get(i).getX(),enemyTanks.get(i).getY(),g,enemyTanks.get(i).getDirect(),1);
        }
    }
//the method draw tank
    /**
     * @param x the x coordinate of the upper left corner of teh tank
     * @param y the y coordinate of the upper left corner
     * @param g the pen to draw
     * @param direct the direction of tank ,up ,down..
     * @param type the type of tank--our/enemy
     */
    public void drawTank(int x,int y, Graphics g,int direct,int type){
        //set the color of tank due to the type
        switch(type){
            //0 for myTank
            //1 for enemy
            case 0:
                g.setColor(Color.cyan);
                break;//switch case must have a "break"
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        //draw tank due to the direction
        //0 up
        //1 right
        //2 down
        //3 left
        switch(direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+60,x+20,y+30);
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("暂时未处理");


        }

    }
    //
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //detect key press
    @Override
    public void keyPressed(KeyEvent e) {
        //listen the W key to go up
        if(e.getKeyCode()==KeyEvent.VK_W){
            myTank.setDirect(0);
            myTank.moveUp();
        }else if(e.getKeyCode()==KeyEvent.VK_D){//D
            myTank.setDirect(1);
            myTank.moveRight();
        }else if(e.getKeyCode()==KeyEvent.VK_S){//S
            myTank.setDirect(2);
            myTank.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_A){//A
            myTank.setDirect(3);
            myTank.moveLeft();
        }
        this.repaint();//repaint the panel to refresh
    }
    //detect key release
    @Override
    public void keyReleased(KeyEvent e) {

    }
}

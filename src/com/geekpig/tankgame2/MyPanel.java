package com.geekpig.tankgame2;

import javax.swing.*;
import java.awt.*;

//the drawing area of TankGame
public class MyPanel extends JPanel {
    //define my tank
    MyTank myTank = null;
    public MyPanel(){
        myTank = new MyTank(100,100);//initialization
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//fill the rectangle,default black
        //draw tank here----package into a method to draw tank
        drawTank(myTank.getX(),myTank.getY(),g,0,0);

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
            //0 for myTank ,1 for enemy
            case 0:
                g.setColor(Color.cyan);
                break;//switch case must have a "break"
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        //draw tank due to the direction
        switch(direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            default:
                System.out.println("暂时未处理");


        }

    }
}

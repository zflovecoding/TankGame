package com.geekpig.tankgame3;
//shoot bullets
public class Shoot implements  Runnable{
    private int x;//the x coordinate of bullets
    private int y;//the y coordinate of bullets
    private int direct=0;//the direction of bullets
    private int speed = 2;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirect() {
        return direct;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    boolean isAlive = true;//if the bullet is alive or not
    //the constructor
    public Shoot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        //continue shooting
        while(true){
            //sleep to see the track of bullets
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0://up
                    y -= speed;
                    break;
                case 1://right
                    x += speed;
                    break;
                case 2://down
                    y += speed;
                    break;
                case 3://left
                    x -= speed;
                    break;
            }
            System.out.println("x coordinate"+x+"y coordinate "+y);
            //condition to kill the shoot thread
            //when a bullet reach the board ,the shoot thread should be killed
            if(!(x>=0 && x<= 1000 && y>=0 && y<=750)){
                isAlive = false;
                break;
            }
        }



    }
}

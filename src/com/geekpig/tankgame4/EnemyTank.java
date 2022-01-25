package com.geekpig.tankgame4;

import java.util.Vector;

//the tank of enemy
public class EnemyTank extends Tank implements Runnable{
    boolean isAlive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    Vector<Shoot> shoots = new Vector<>();

    @Override
    public void run() {
        while (true){
            //due to tank direction to continue moving
            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if(getY()>0){
                            moveUp();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if(getX()+60 <1000){
                            moveRight();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if(getY()+60 < 750){
                            moveDown();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if(getX()>0){
                            moveLeft();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //set the random direction
            setDirect((int)(Math.random() * 4));//0~3

            //exit the thread conditions
            if(!isAlive){
                break;
            }

        }
    }
}

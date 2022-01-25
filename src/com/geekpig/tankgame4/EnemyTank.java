package com.geekpig.tankgame4;

import java.util.Vector;
@SuppressWarnings({"all"})
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
            //change enemyTank can only shoot one bullets
            //这里我们判断如果shots size() =0, 创建一颗子弹，放入到
            //shots集合，并启动
            if (isAlive && shoots.size() < 1) {
                Shoot s = null;
                //判断坦克的方向，创建对应的子弹
                switch (getDirect()) {
                    case 0:
                        s = new Shoot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shoot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2: //向下
                        s = new Shoot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3://向左
                        s = new Shoot(getX(), getY() + 20, 3);
                        break;
                }
                shoots.add(s);
                //启动
                new Thread(s).start();

            }



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

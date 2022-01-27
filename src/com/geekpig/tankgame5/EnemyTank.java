package com.geekpig.tankgame5;

import java.util.Vector;

@SuppressWarnings({"all"})
//the tank of enemy
public class EnemyTank extends Tank implements Runnable{
    boolean isAlive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    Vector<Shoot> shoots = new Vector<>();
    //get a Vector<EnemyTank>  to detect tanks overlapping problem
    Vector<EnemyTank> enemyTanks =new Vector<>();
    //provide a method to get tanks added into the vector

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
    //编写方法，判断当前的这个敌人坦克，是否和 enemyTanks 中的其他坦克发生的重叠或者碰撞
    public boolean isTouchEnemyTank() {

        //判断当前敌人坦克(this) 方向
        switch (this.getDirect()) {
            case 0: //上
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右上角的坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }

                }
                break;
            case 1: //右
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 右上角的坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 右上角的坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //下
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 右下角的坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //左
                //让当前敌人坦克和其它所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //老韩分析
                        //1. 如果敌人坦克是上/下 x的范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY() ]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是 右/左
                        //老韩分析
                        //1. 如果敌人坦克是右/左  x的范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //                     y的范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //2. 当前坦克 左上角的坐标 [this.getX(), this.getY() ]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //3. 当前坦克 左下角的坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return  false;
    }


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
                        if(getY()>0 && !isTouchEnemyTank()){
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
                        if(getX()+60 <1000&& !isTouchEnemyTank()){
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
                        if(getY()+60 < 750&& !isTouchEnemyTank()){
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
                        if(getX()>0&& !isTouchEnemyTank()){
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

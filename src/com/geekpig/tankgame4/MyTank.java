package com.geekpig.tankgame4;

//our tanks
public class MyTank extends Tank {

    public MyTank(int x, int y) {
        super(x, y);
    }
    //define a shoot object to express shoot thread of myTank
    Shoot shoot = null;
    //myTank should have the bullet related method



    public void shootEnemyTank(){

        //create shoot obj due to myTank location and direction
        switch(getDirect()){
            case 0://up
                shoot = new Shoot(getX()+20,getY(),0);
                break;
            case 1://right
                shoot = new Shoot(getX()+60,getY()+20,1);
                break;
            case 2:
                shoot = new Shoot(getX()+20,getY()+60,2);
                break;
            case 3:
                shoot = new Shoot(getX(),getY()+20,3);
                break;
        }

        //after create the shoot obj ,we should launch the shoot thread
        //Shoot implements Runnable interface
        //start up the shoot thread
        new Thread(shoot).start();
    }
}

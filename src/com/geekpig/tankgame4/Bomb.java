package com.geekpig.tankgame4;

public class Bomb {
    int x;//the x coordinate of bombs
    int y;
    int life = 9;//the life of bomb
    boolean isAlive = true;
    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDecrease(){//to display the explosion better
        if(life>0){
            life--;
        }else{
            isAlive = false;
        }
    }
}

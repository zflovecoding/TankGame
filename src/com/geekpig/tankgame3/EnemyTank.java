package com.geekpig.tankgame3;

import java.util.Vector;

//the tank of enemy
public class EnemyTank extends Tank {
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    Vector<Shoot> shoots = new Vector<>();
}

package com.geekpig.tankgame5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//the drawing area of TankGame
//to make panel repaint constantly ,wo need to make myPanel implements Runnable,to be like a thread to use
public class MyPanel extends JPanel implements KeyListener ,Runnable{
    //define my tank
    MyTank myTank = null;
    //define enemy tanks put into vector considering multithreading
    //Vector is thread-safe
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //the numbers of enemy tanks
    private int enemyTankNum = 3;
    //put the bombs into a Vector
    Vector<Bomb> bombs = new Vector<>();
    //define three pics to display explosion
    Vector<Node> nodes = new Vector<>();
    Image image1 =  null;
    Image image2 =  null;
    Image image3 =  null;
    //change the speed of tank should change it when initialization
    public MyPanel(String key){
        //1 new game
        //2 continue the last game
        //先判断记录的文件是否存在
        //如果存在，就正常执行，如果文件不存在，提示，只能开启新游戏，key = "1"
        File file = new File(Recorder.getFilePath());
        if (file.exists()) {
            nodes = Recorder.getNodesAndEnemyTankRec();
        } else {
            System.out.println("文件不存在，只能开启新的游戏");
            key = "1";
        }
        Recorder.setEnemyTanks(enemyTanks);
        myTank = new MyTank(400,400);//initialization my tank
        myTank.setSpeed(5);
        switch(key){
            case "1":
                //initialize enemy tanks
                for (int i = 0; i < enemyTankNum; i++) {
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
                    //将enemyTanks 设置给 enemyTank !!!
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(2);
                    //when create a tank ,then create a bullet of the tank
                    //the x & y coordinate of bullet should adjust to the line of tank
                    Shoot shoot = new Shoot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    //add the tank bullet to the Vector in EnemyTank
                    enemyTank.shoots.add(shoot);
                    //start up the shoot thread
                    new Thread(shoot).start();
                    enemyTanks.add(enemyTank);
                    //start upo the enemyTank thread
                    new Thread(enemyTank).start();
                }
                break;
            case "2":
                //继续上局游戏
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(),node.getY());
                    //将enemyTanks 设置给 enemyTank !!!
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(node.getDirect());
                    //when create a tank ,then create a bullet of the tank
                    //the x & y coordinate of bullet should adjust to the line of tank
                    Shoot shoot = new Shoot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    //add the tank bullet to the Vector in EnemyTank
                    enemyTank.shoots.add(shoot);
                    //start up the shoot thread
                    new Thread(shoot).start();
                    enemyTanks.add(enemyTank);
                    //start upo the enemyTank thread
                    new Thread(enemyTank).start();
                }
                break;
            default:
                System.out.println("你的输入有误...");
        }

        //init the image obj
        image1 = Toolkit.getDefaultToolkit().getImage((Panel.class.getResource("/bomb_1.gif")));
        image2 = Toolkit.getDefaultToolkit().getImage((Panel.class.getResource("/bomb_2.gif")));
        image3 = Toolkit.getDefaultToolkit().getImage((Panel.class.getResource("/bomb_3.gif")));


        new AePlayWave("src\\111.wav").start();
    }
    public  void showInfo(Graphics g){
        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累积击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 1);//画出一个敌方坦克
        g.setColor(Color.BLACK);//这里需要重新设置成黑色
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        showInfo(g);
        g.fillRect(0,0,1000,750);//fill the rectangle,default black
        //draw tank here----package into a method to draw tank
        if(myTank.isAlive){
            drawTank(myTank.getX(),myTank.getY(),g,myTank.getDirect(),0);
        }

        //draw enemy tanks
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //when enemyTank is alive ,we start to draw them
            if(enemyTank.isAlive){
                drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
                //bullets of enemyTank need to repaint constantly
                //take out all the bullets of tank
                for(int j=0;j<enemyTank.shoots.size();j++){
                    Shoot shoot = enemyTank.shoots.get(j);
                    if(shoot.isAlive){
                        g.draw3DRect(shoot.getX(),shoot.getY(),1,1,false);
                    }else {
                        //remove the dead bullets from Vector ,Or it will be painted constantly
                        enemyTank.shoots.remove(shoot);
                    }

                }
            }

        }
        //画出myTank射击的子弹
        if (myTank.shoot != null && myTank.shoot.isAlive == true) {
            g.draw3DRect(myTank.shoot.getX(), myTank.shoot.getY(), 1, 1, false);

        }
        //draw the bullet of myTank
//        for (int i = 0; i < myTank.shoots.size(); i++) {
//            Shoot shoot = myTank.shoots.get(i);
//            if(shoot!=null && shoot.isAlive){
//
//                g.draw3DRect(shoot.getX(),shoot.getY(),1,1,false);
//            }else{
//                myTank.shoots.remove(shoot);
//            }
//        }

        //if bombs Vector consists obj ,draw the bomb
        for (int i = 0; i < bombs.size(); i++) {
            //take the bomb out of the Vector
            Bomb bomb = bombs.get(i);
            if(bomb.life >6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if(bomb.life>3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else if(bomb.life>0){
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //decrease the life of bomb ,better display
            bomb.lifeDecrease();
            //if the life  of bomb is zero ,the remove it from Vector
            if(!bomb.isAlive){
                bombs.remove(bomb);
            }
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
    //如果我们的坦克可以发射多个子弹
    //在判断我方子弹是否击中敌人坦克时，就需要把我们的子弹集合中
    //所有的子弹，都取出和敌人的所有坦克，进行判断
    public  void hitMyTank(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank =enemyTanks.get(i);
            for (int i1 = 0; i1 < enemyTank.shoots.size(); i1++) {
                Shoot shoot = enemyTank.shoots.get(i1);
                if(myTank.isAlive && shoot.isAlive){
                    hitTank(shoot,myTank);
                }
            }
        }
    }

    public void hitEnemyTank(){
        //遍历我们的子弹
//        for (int i = 0; i < myTank.shoots.size(); i++) {
//            Shoot shoot = myTank.shoots.get(i);
        //判断是否击中了敌人坦克
//            if(shoot!=null && shoot.isAlive){

        //遍历敌人所有的坦克
//                for(int j=0;j<enemyTanks.size();j++){
//                    EnemyTank enemyTank = enemyTanks.get(j);
//                    hitTank(shoot,enemyTank);
//                }
//            }
//        }

        //单颗子弹。
        if (myTank.shoot != null && myTank.shoot.isAlive) {//当我的子弹还存活

            //遍历敌人所有的坦克
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(myTank.shoot, enemyTank);
            }

        }

    }
    //the method detect when our bullets hit enemyTank
    //when to judge whether myTank hits enemy---->run() method
    //this method is to judge if tank(both myTank and enemyTank) is hit ,
    public void hitTank(Shoot s , Tank Tank){

        switch (Tank.getDirect()){
            case 0://up
            case 2://down
                if(s.getX() > Tank.getX() && s.getX() < Tank.getX()+40
                && s.getY()>Tank.getY() && s.getY() < Tank.getY()+60 &&  Tank.isAlive){
                    s.isAlive = false;
                    Tank.isAlive =false;
                    if(Tank instanceof  EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //remove dead tank from Vector<EnemyTank>-------->//my solution:if( ...  && enemyTank.isAlive)
                    enemyTanks.remove( Tank);
                    //create bomb obj ,add to the Vector
                    Bomb bomb = new Bomb( Tank.getX(), Tank.getY());
                    bombs.add(bomb);

                }
                break;
            case 1://right
            case 3://left
                if(s.getX() >  Tank.getX() && s.getX() < Tank.getX()+60
                        && s.getY()> Tank.getY() && s.getY() <  Tank.getY()+40&&  Tank.isAlive){
                    s.isAlive = false;
                    Tank.isAlive = false;
                    if(Tank instanceof  EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //remove dead tank from Vector<EnemyTank>
                    enemyTanks.remove( Tank);
                    Bomb bomb = new Bomb( Tank.getX(), Tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //detect key press
    @Override
    public void keyPressed(KeyEvent e) {
        //listen the W key to go up
        if(e.getKeyCode() == KeyEvent.VK_W){
            myTank.setDirect(0);
            //control location of tank
            if(myTank.getY()>0){
                myTank.moveUp();
            }

        }else if(e.getKeyCode()==KeyEvent.VK_D){//D
            myTank.setDirect(1);
            if(myTank.getX() + 60 < 1000){
                myTank.moveRight();
            }

        }else if(e.getKeyCode()==KeyEvent.VK_S){//S
            myTank.setDirect(2);
            if(myTank.getY() + 60 < 750){
                myTank.moveDown();
            }

        }else if(e.getKeyCode()==KeyEvent.VK_A){//A
            myTank.setDirect(3);
            if(myTank.getX()>0){
                myTank.moveLeft();
            }

        }
        //if listen key "J" pressed , myTank will shoot a bullet
        if(e.getKeyCode() == KeyEvent.VK_J){
            if(myTank.shoot == null || !myTank.shoot.isAlive){
                myTank.shootEnemyTank();
            }
            //myTank.shootEnemyTank();
        }
        this.repaint();//repaint the panel to refresh
    }
    //detect key release
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //before press "J" myTank.shoot == null --> NullPointerException
            //judge whether our bullets hit enemies
            hitEnemyTank();
            //judge whether myTank is hit by enemyTank
            hitMyTank();
            this.repaint();
        }

    }
}

package com.geekpig.tankgame5;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static  int  allEnemyTankNum = 0 ;
    private static BufferedReader br  =null;
    private static BufferedWriter bw = null;
    private static String filePath =  "src\\record.txt";
    private static Vector<EnemyTank> enemyTanks = new Vector<>();
    private static Vector<Node> nodes  = new Vector<>();
    public static  void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static Vector<Node> getNodesAndEnemyTankRec(){
        BufferedReader br = null;
        try {
            String  line = null;
            br = new BufferedReader(new FileReader(filePath));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            while ((line = br.readLine())!=null){
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return nodes;
    }
    public static void keepRecord(){

        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(allEnemyTankNum+"\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                String location =  enemyTank.getX() +" "+ enemyTank.getY()+" "+enemyTank.getDirect()+" ";
                bw.write(location+"\r\n");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw !=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyNum) {
        Recorder.allEnemyTankNum = allEnemyNum;
    }
    public static  void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }

}

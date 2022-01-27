package com.geekpig.tankgame5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Recorder {
    private static  int  allEnemyTankNum = 0 ;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static String filePath =  "src\\record.txt";

    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(allEnemyTankNum+"\r\n");


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

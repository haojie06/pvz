package models.gamesystem;

import Interface.Getable;
import models.effect.Effect;
import models.entities.plant.Plant;
;
import models.entities.weapon.bullets.Bullet;
import models.entities.zombie.Zombie;
import models.mission.Mission;
import models.mission.PlantMission;
import models.mission.ZombieMission;

import java.util.List;

import java.io.*;
import java.util.ArrayList;

public class StageSave {

    private static Stage s;

    private static int  playerGroup =-1;
    //僵尸列表
    private static List<Zombie> ZombieList = new ArrayList<>();
    //植物列表
    private static List<Plant> PlantList = new ArrayList<>();
    //游戏中的效果列表
    private static List<Effect> EffectList = new ArrayList<>();

    private int group = 1;

    private String MissionName;

    private static int curScore=-1;
    private static int curMoney = -1;

    public StageSave(Stage s) { this.s = s;
    }

    public static void serializeStage() throws IOException {

        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(new File("d:/Save/Plant.txt")));
        oos1.writeObject(s.getAllPlantList());
        oos1.close();

        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(new File("d:/Save/Zombie.txt")));
        oos2.writeObject(s.getAllZombieList());
        oos2.close();


        ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream(new File("d:/Save/Effect.txt")));
        oos3.writeObject(s.getAllEffectList());
        oos3.close();

        ObjectOutputStream oos4 = new ObjectOutputStream(new FileOutputStream(new File("d:/Save/PlayerGroup.txt")));
        oos4.writeObject(Stage.getPlayerGroup());
        oos4.close();

        ObjectOutputStream oos5 = new ObjectOutputStream(new FileOutputStream(new File("d:/Save/Mission.txt")));
        oos5.writeObject(s.getMission().getGroup());
        oos5.close();

//        ObjectOutputStream oos5 = new ObjectOutputStream(new FileOutputStream(new File("d:/Save/Mission.txt")));
//        oos5.writeObject(s.getMission().);
//        oos5.close();

        ObjectOutputStream oos6 = new ObjectOutputStream(new FileOutputStream(new File("d:/Save/ScoreSystem.txt")));
        oos6.writeObject(ScoreSystem.getScore());
        oos6.close();

        ObjectOutputStream oos7 = new ObjectOutputStream(new FileOutputStream(new File("d:/Save/EconomySystem.txt")));
        oos7.writeObject(EconomySystem.getMoney());
        oos7.close();

        System.out.println("对象序列化成功！");


    }

    public static void deserializeStage() throws Exception {
        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(new File("d:/Save/Plant.txt")));
        PlantList= (ArrayList)ois1.readObject();

        ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(new File("d:/Save/Zombie.txt")));
        ZombieList= (ArrayList)ois2.readObject();

        ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream(new File("d:/Save/Effect.txt")));
        EffectList= (ArrayList)ois3.readObject();

        ObjectInputStream ois4 = new ObjectInputStream(new FileInputStream(new File("d:/Save/PlayerGroup.txt")));
        playerGroup= (int)ois4.readObject();

//        ObjectInputStream ois5 = new ObjectInputStream(new FileInputStream(new File("d:/Save/Mission.txt")));
//        mission= (Mission)ois5.readObject();

        ObjectInputStream ois6 = new ObjectInputStream(new FileInputStream(new File("d:/Save/ScoreSystem.txt")));
        curScore= (int)ois6.readObject();


        ObjectInputStream ois7 = new ObjectInputStream(new FileInputStream(new File("d:/Save/EconomySystem.txt")));
        curMoney= (int)ois7.readObject();

//        for(int i=0;i<obj.length;i++ ){
//            System.out.println(obj[i].getName());
//        }

        System.out.println("对象反序列化成功！");

    }

    public static Stage newStage(){
        s = new Stage();
        s.setAllPlantList(PlantList);
        s.setAllZombieList(ZombieList);
        s.setAllEffectList(EffectList);
        Stage.setPlayerGroup(playerGroup);
//        s.setMission(mission);
        ScoreSystem.setScore(curScore);
        EconomySystem.setMoney(curMoney);

        return s;
    }
}
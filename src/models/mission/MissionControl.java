package models.mission;

import models.gamesystem.SoundControl;
import models.gamesystem.Stage;

import java.util.Scanner;

//关卡控制器，在这里选择关卡，初始化关卡（根据玩家选择的关卡不同而参数不同）
public class MissionControl {

    //阵营选择 1--植物阵营
    int group = 1;
    Stage gameStage;
    int mission;

    public MissionControl(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public Mission loadMission(int group, int missionNum) {
        //让用户选择关卡
        //select();
        //根据用户的参数初始化关卡
        this.group = group;
        this.mission = missionNum;
        return initialMission();
    }

    //这一部分应该以图形界面取代
    public void select(){
        System.out.println("请选择阵营 1,植物 2,僵尸");
        Scanner scanner = new Scanner(System.in);
        group = scanner.nextInt();
        if (group == 1){
            //設置陣營
            Stage.setPlayerGroup(1);
            System.out.println("你选择了植物阵营");
            SoundControl.playSound("lawnmower.mp3");
            //选择关卡，关卡只影响背景图片，背景音乐，生成僵尸的属性 etc 三关
            System.out.println("请选择关卡 1,level1 2,level2 3,level3");
            mission = scanner.nextInt();
            //选择出战的植物
        } else {
            System.out.println("選擇僵尸陣營");
            Stage.setPlayerGroup(2);
            System.out.println(Stage.getPlayerGroup());
            System.out.println("你选择了僵尸阵营");
            SoundControl.playSound("zombiegroup.mp3");
            System.out.println("请选择关卡 1,level1 2,level2 3,level3");
            mission = scanner.nextInt();
        }
    }

    //生成关卡，传入选择，进行初始化，返回Mission
    public Mission initialMission() {
        Mission curMission;
        if (Stage.getPlayerGroup() == 1) {
            //调用方法生成植物的关卡
            curMission = new PlantMission("plant mission", gameStage);
            ((PlantMission) (curMission)).generatePlantMission(mission);

        }
        else {
            //调用方法生成新的僵尸的关卡
            curMission = new ZombieMission("zombie mission", gameStage);
            ((ZombieMission) curMission).generateMission(mission);
        }
        //设置背景图片 和最大行數
        gameStage.getMap().setBackGroundSrc(curMission.getBackgroundPicSrc());
        gameStage.getMap().setMaxRow(curMission.getTotalRow());
        gameStage.setMission(curMission);
        gameStage.setPlayerGroup(Stage.getPlayerGroup());
        System.out.println("关卡初始化完毕");
        return (Mission) curMission;
    }
    //获得玩家选择的阵营，然后通过controller调用menuview绘制不同阵营的UI
    public int getChooseGroup() {
        return group;
    }
}
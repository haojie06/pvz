package models.events;

import models.entities.Entity;
import models.gamesystem.*;
import models.entities.plant.Plant;
import models.entities.zombie.Zombie;
import models.mission.PlantMission;

import java.util.List;

//死亡事件的处理，确定不同的死亡动画以及状态的改变
public class DeadEventListener extends EventListener {
    public void eventHandle(Event event) {

        if (event.getEventName().equals("僵尸死亡")) {
            Entity deadEntity = ((DeadEvent) event).entity;
            //获得由事件传递的死亡的对象
            if (deadEntity.getIfAlive() == 2) {
                if (Stage.getPlayerGroup() == 1) {
                    //植物击杀僵尸只能获得分数
                    ScoreSystem.addScore(deadEntity.getScore());
                }
                SoundControl.playSound("grassstep.mp3");

                //使用动画切换类的方法切换之后要播放的动画
                AnimeControl control = new AnimeControl();
                String dieAniName;
                deadEntity.setIfAlive(1);
                if (((Zombie) deadEntity).getDieReason() == 0) {
                    dieAniName = "dieh";
                    deadEntity.setMaxDeadTime(20);
                    deadEntity.setDeadTime(0);
                } else {
                    dieAniName = "died";
                    deadEntity.setDeadTime(0);
                    deadEntity.setMaxDeadTime(20);
                }
                control.animeChange(deadEntity, dieAniName);
                //清除单元格中的痕迹
                Stage stage = ((DeadEvent) event).stage;

                //如果选择的是植物时增加关卡中击杀数,需要修改
                if (stage.getMission().getGroup() == 1) {
                    ((PlantMission) (stage.getMission())).addKillNum();
                }
            } else if (deadEntity.getIfAlive() == 0) {

            }
        } else if (event.getEventName().equals("植物死亡")) {
            Plant plant = (Plant) ((DeadEvent) event).entity;
            if (plant.getIfAlive() == 2) {
                //分数处理 僵尸击杀植物可以获得分数，还可以获得一定的金钱
                if (Stage.getPlayerGroup() == 2) {
                    ScoreSystem.addScore(plant.getScore());
                    EconomySystem.addMoney(25);
                }
                //清除植物，并恢复同一单元格中的僵尸运动
                SoundControl.playSound("planthit.mp3");
                int row, col;
                row = plant.getRow();
                col = plant.getCol();
                Stage stage = ((DeadEvent) event).stage;
                stage.getAllPlantList().remove(plant);
                List<Zombie> zombies = stage.getMap().getMapBlockList().get(row).get(col).getZombies();
                //从单元格中移除植物
                stage.getMap().getMapBlockList().get(row).get(col).removeEntity(plant);
                for (Zombie z : zombies) {
                    z.resumeMove();
                    AnimeControl animeControl = new AnimeControl();
                    if (z.getIfAlive() == 2 && z.getHealth() > 0) {
                        animeControl.animeChange(z, "move");
                    }
                }
                plant.setIfAlive(1);
            }
        }
    }
}

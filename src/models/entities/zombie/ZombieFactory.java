package models.entities.zombie;

import models.gamesystem.MapBlock;
import models.gamesystem.Stage;
import models.events.DeadEventListener;
import models.events.MoveEventListener;

import java.util.List;

//僵尸工厂
public class ZombieFactory {
    Stage gameStage;

    public ZombieFactory(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public void genZombie(String kind, int row, int col) {
        int pX, pY;
        int startX, startY;
        List<List<MapBlock>> mapBlocks = gameStage.getMap().getMapBlockList();
        startX = mapBlocks.get(0).get(col).getPostionX();
        startY = mapBlocks.get(row).get(0).getPostionY();
        Zombie newZombie = null;
        if (kind.equals("普通僵尸")) {
            pX = startX - 40;
            pY = startY - 60;
            newZombie = new NormalZombie(pX, pY);
            //有待改进，应该可以只设置行不设置坐标，起点横坐标一致（画面外），纵坐标由行得到
        } else if (kind.equals("墓碑")) {
            pX = startX;
            pY = startY - 20;
            newZombie = new GraveStone(pX, pY);
        } else if (kind.equals("运动员僵尸")) {
            pX = startX - 20;
            pY = startY - 60;
            newZombie = new FootballZombie(pX, pY);
        } else if (kind.equals("爆炸僵尸")){
            pX = startX - 80;
            pY = startY - 90;
            newZombie = new JackinTheBoxZombie(pX,pY);
        } else if (kind.equals("旗手僵尸")) {
            pX = startX - 80;
            pY = startY - 80;
            newZombie = new FlagZombie(pX, pY);
        } else if (kind.equals("小鬼僵尸")) {
            pX = startX - 50;
            pY = startY - 50;
            newZombie = new ImpZombie(pX, pY);
        } else if (kind.equals("铁桶僵尸")) {
            pX = startX - 50;
            pY = startY - 75;
            newZombie = new BucketHeadZombie(pX, pY);
        } else if (kind.equals("路障僵尸")) {
            pX = startX - 50;
            pY = startY - 75;
            newZombie = new ConeheadZombie(pX, pY);
        } else if (kind.equals("撑杆跳僵尸")) {
            pX = startX - 115;
            pY = startY - 160;
            newZombie = new PoleVaultingZombie(pX, pY);
        }
        newZombie.setRow(row);
        newZombie.bindListener(new DeadEventListener());
        newZombie.bindListener(new MoveEventListener());
        gameStage.getAllZombieList().add(newZombie);
        gameStage.getMap().getMapBlockList().get(row).get(col).addEntity(newZombie);

    }
}

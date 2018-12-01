package models.entities.plant;

import models.gamesystem.MapBlock;
import models.gamesystem.Stage;
import models.events.DeadEventListener;

import java.util.List;

//生成植物请提供根据行/列（row/col）生成的 方法
public class PlantFactory {
    int pX, pY;
    private Stage gameStage;

    public PlantFactory(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public void genPlant(String kind, int row, int col) {
        int pX, pY;
        int startX, startY;
        List<List<MapBlock>> mapBlocks = gameStage.getMap().getMapBlockList();
        startX = mapBlocks.get(0).get(col).getPostionX();
        startY = mapBlocks.get(row).get(0).getPostionY();
        Plant newPlant = null;
        if (kind.equals("机枪射手")) {
            pX = startX;
            pY = startY - 25;
            newPlant = new GatlingPea(pX, pY);
            //有待改进，应该可以只设置行不设置坐标，起点横坐标一致（画面外），纵坐标由行得到
            newPlant.setRow(row);
            //还需要绑定一个攻击事件的监听器
            //newPlant.bindListener(new MoveEventListener());
        } else if (kind.equals("樱桃炸弹")) {
            pX = startX - 10;
            pY = startY - 10;
            newPlant = new CherryBomb(pX, pY);
            newPlant.setRow(row);
        } else if (kind.equals("太阳花")) {
            pX = startX + 10;
            pY = startY + 10;
            newPlant = new SunFlower(pX, pY);
            newPlant.setRow(row);
        } else if (kind.equals("土豆雷")) {
            pX = startX;
            pY = startY;
            newPlant = new PotatoMine(pX, pY);
            newPlant.setRow(row);
        } else if (kind.equals("卷心菜投手")) {
            pX = startX - 40;
            pY = startY - 40;
            newPlant = new Cabbage(pX, pY);
            newPlant.setRow(row);
        } else if (kind.equals("地刺")) {
            pX = startX;
            pY = startY + 50;
            newPlant = new Spikeweed(pX, pY);
            newPlant.setRow(row);
        } else if (kind.equals("高坚果")) {
            pX = startX + 5;
            pY = startY - 30;
            newPlant = new TallWallNut(pX, pY);
            newPlant.setRow(row);
        } else if (kind.equals("坚果墙")) {
            pX = startX + 5;
            pY = startY + 10;
            newPlant = new WallNut(pX, pY);
            newPlant.setRow(row);
        } else if (kind.equals("食人花")) {
            pX = startX + 5;
            pY = startY - 30;
            newPlant = new Chomper(pX, pY);
            newPlant.setRow(row);
        }else if (kind.equals("普通豌豆射手")) {
            pX = startX + 5;
            pY = startY - 40;
            newPlant = new Peashooter(pX, pY);
            newPlant.setRow(row);
        }else if (kind.equals("冰冻豌豆射手")) {
            pX = startX + 20;
            pY = startY - 40;
            newPlant = new SnowPea(pX, pY);
            newPlant.setRow(row);
        }else if (kind.equals("双向豌豆射手")) {
            pX = startX + 15;
            pY = startY - 40;
            newPlant = new SplitPea(pX, pY);
            newPlant.setRow(row);
        }else if (kind.equals("三头豌豆射手")) {
            pX = startX + 5;
            pY = startY - 30;
            newPlant = new Threepeater(pX, pY);
            newPlant.setRow(row);
        }else if (kind.equals("地刺王")) {
            pX = startX + 15;
            pY = startY - 35;
            newPlant = new Spikerock(pX, pY);
            newPlant.setRow(row);
        }else if (kind.equals("双头向日葵")) {
            pX = startX + 75;
            pY = startY - 45;
            newPlant = new TwinSunflower(pX, pY);
            newPlant.setRow(row);
        }


        newPlant.bindListener(new DeadEventListener());
        gameStage.getAllPlantList().add(newPlant);
    }
}

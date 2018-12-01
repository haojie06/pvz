package models.gamesystem;

import Interface.*;
import models.entities.Entity;
import models.entities.plant.*;
import models.entities.plant.plantcard.PlantCard;
import models.events.*;
import models.entities.weapon.CurveLaucher;
import models.entities.weapon.Weapon;
import models.entities.weapon.bullets.Bullet;
import models.entities.weapon.bullets.LawnCleaner;
import models.entities.weapon.bullets.Skull;
import models.entities.weapon.bullets.Sunshine;
import models.entities.zombie.JackinTheBoxZombie;
import models.entities.zombie.Zombie;

import java.util.ArrayList;
import java.util.List;

public class Economic implements EventSource {
    private List<EventListener> listeners = new ArrayList<>();
    private Stage EconomicStage;
    public static int SunNum = 0;
    public static int SkullNum = 0;

    public Economic(Stage EconomicStage) {
        this.EconomicStage = EconomicStage;
        //绑定各个监听器
        this.bindListener(new AttackEventListener());
        this.bindListener(new ClickGetableEventListener());
    }

    public MapBlock clickWhich(int x, int y) {
        List<List<MapBlock>> blocks = EconomicStage.getMap().getMapBlockList();
        //遍历单元格
        for (int i = 0; i < blocks.size(); i++) {
            List<MapBlock> blockList = blocks.get(i);
            for (int j = 0; j < blockList.size(); j++) {
                MapBlock block = blockList.get(j);
                if (x > block.getPostionX() && x < block.getPostionX() + 80)
                    if (y > block.getPostionY() && y < block.getPostionY() + 90) {
                        System.out.println("你点击了" + block.getRow() + "行" + block.getColumn() + "列");
                        //判断这个格子是否包含阳光金币之类的，Judgement内进行处理，传入鼠标坐标
                        return block;
                    }
            }

        }
        return null;
    }

    @Override
    public void bindListener(EventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void notifyListener(Event event) {
        for (EventListener l : listeners) {
            l.eventHandle(event);
        }
    }
    public void genPlantSunUse(String cardname){
        List<List<MapBlock>> mapBlocks = EconomicStage.getMap().getMapBlockList();
        Plant newPlant = null;
        if (cardname.equals("机枪射手")&&SunNum >=200) {
            SunNum = SunNum -200;
        } else if (cardname.equals("樱桃炸弹")&&SunNum >=150) {
            SunNum = SunNum -150;
        } else if (cardname.equals("太阳花")&&SunNum >=50) {
            SunNum = SunNum -50;
        } else if (cardname.equals("土豆雷")&&SunNum >=25) {
            SunNum = SunNum -25;
        } else if (cardname.equals("卷心菜投手")&&SunNum >=100) {
            SunNum = SunNum -100;
        } else if (cardname.equals("地刺")&&SunNum >=100) {
            SunNum = SunNum -100;
        } else if (cardname.equals("高坚果")&&SunNum >=125) {
            SunNum = SunNum -125;
        } else if (cardname.equals("坚果墙")&&SunNum >=50) {
            SunNum = SunNum -50;
        } else if (cardname.equals("食人花")&&SunNum >=150) {
            SunNum = SunNum -150;
        }
    }
    public void genZombieSunUse(String cardname){
        List<List<MapBlock>> mapBlocks = EconomicStage.getMap().getMapBlockList();
        Plant newZombie = null;
        if (cardname.equals("普通僵尸")&&SkullNum >=50) {
            SkullNum = SkullNum -50;
        } else if (cardname.equals("撑杆僵尸")&&SkullNum >=75) {
            SkullNum = SkullNum -75;
        } else if (cardname.equals("铁桶僵尸")&&SkullNum >=125) {
            SkullNum = SkullNum -125;
        } else if (cardname.equals("路障僵尸")&&SkullNum >=75) {
            SkullNum = SkullNum -75;
        } else if (cardname.equals("小丑僵尸")&&SkullNum >=300) {
            SkullNum = SkullNum -300;
        } else if (cardname.equals("读报僵尸")&&SkullNum >=100) {
            SkullNum = SkullNum -100;
        } else if (cardname.equals("铁栅门僵尸")&&SkullNum >=150) {
            SkullNum = SkullNum -150;
        }
    }
}
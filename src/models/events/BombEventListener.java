package models.events;

import models.gamesystem.AnimeControl;
import models.entities.Entity;
import models.gamesystem.MapBlock;
import models.gamesystem.SoundControl;
import models.entities.plant.Plant;
import models.entities.weapon.Bomb;
import models.entities.zombie.JackinTheBoxZombie;

import java.util.ArrayList;
import java.util.List;

//爆炸事件的监听/处理器，处理会爆炸的植物（土豆雷，樱桃炸弹一类）以及能够爆炸的僵尸JackintheboxZombie产生的爆炸事件
public class BombEventListener extends EventListener {
    @Override
    public void eventHandle(Event event) {
        int bombFieldX, bombFieldY;
        if (event.getEventName().equals("爆炸事件")) {
            BombEvent bombEvent = (BombEvent) event;
            Entity bombEntity = bombEvent.getEntity();
            if (bombEntity instanceof Plant) {
                //播放爆炸音效
                SoundControl.playSound(bombEntity.getName() + ".mp3");
                //原来只有植物能够爆炸，但后来加入了爆炸僵尸
                   Plant bomber = (Plant) bombEntity;
                    if (!bombEntity.getCurAnimeName().equals("attack")) {
                        AnimeControl animeControl = new AnimeControl();
                        animeControl.animeChange(bombEntity, "attack");
                    }
                    //爆炸的对象实现了Explosive接口，ifAlive == 1的时候不会直接被清除而是播放对应的爆炸动画，播放完之后才清除
                bombEntity.setIfAlive(1);
                Bomb bomb = (Bomb) bomber.getWeapon();
                bombFieldX = bomb.getAttackFieldX();
                bombFieldY = bomb.getAttackFieldY();
                List<List<MapBlock>> blockList = bombEvent.getGameStage().getMap().getMapBlockList();
                //爆炸起始点以及爆炸截至点的坐标，某些爆炸有范围伤害的效果
                int startX, startY, endX, endY;
                startX = bomber.getCol() - bombFieldX;
                startY = bomber.getRow() - bombFieldY;
                endX = bomber.getCol() + bombFieldX;
                endY = bomber.getRow() + bombFieldY;
                int totalCol = bombEvent.getGameStage().getMap().getTotalColumn();
                int totalRow = bombEvent.getGameStage().getMap().getTotalRow();
                if (startX < 0)
                    startX = 0;
                if (startX > totalCol)
                    startX = totalCol;
                if (startY < 0)
                    startY = 0;
                if (startY > totalRow)
                    startY = totalRow;
                if (endX < 0)
                    endX = 0;
                if (endX > totalCol)
                    endX = totalCol;
                if (endY < 0)
                    endY = 0;
                if (endY > totalRow)
                    endY = totalRow;

                //八个方向搜索，如果超出边界就不搜索了
                int cout = 0;
                List<Entity> hitList = new ArrayList<>();
                for (int i = startY; i <= endY; i++) {
                    for (int j = startX; j <= endX; j++) {
                        if (i >= 0 && i <= 4) {
                            hitList.addAll(blockList.get(i).get(j).getZombies());
                        }
                        }
                        cout++;
                    }

                bomber.getWeapon().attack(hitList);
            }
            //爆炸僵尸的处理
            else if (bombEntity.getName().equals("JackinTheBoxZombie")){
                JackinTheBoxZombie jackinTheBoxZombie = (JackinTheBoxZombie) bombEntity;
                MapBlock block = bombEvent.getGameStage().getMap().getMapBlockList().get(bombEntity.getRow()).get(bombEntity.getCol());
                if (((JackinTheBoxZombie) bombEvent.getEntity()).getCount() >= 22) {
                    if (block != null) {
                        jackinTheBoxZombie.setHealth(-2);
                        jackinTheBoxZombie.setDieReason(1);
                        jackinTheBoxZombie.getWeapon().attack(block.getPlantEntity());
                    }
                }else {
                    jackinTheBoxZombie.setHealth(-2);
                    jackinTheBoxZombie.setDieReason(1);
                }
            }
        }
    }
}

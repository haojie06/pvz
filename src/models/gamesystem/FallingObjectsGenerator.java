package models.gamesystem;

import models.entities.weapon.bullets.Skull;
import models.entities.weapon.bullets.Sunshine;

import java.util.Random;

//掉落物生成 生成从天而降的阳光/骷髅
public class FallingObjectsGenerator {
    Stage gameStage;
    //依据阵营设置以下值
    int fallMode = 1; //掉落物模式 1--阳光 2--骷髅
    //掉落物范围 超出范围清除
    int startX = 100, endX = 1000, startY = 0, endY = 700;
    int objectX = 0, objectY = 0;

    public FallingObjectsGenerator(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public void generateFallingObj(int fallMode) {
        this.fallMode = fallMode;
        Random random = new Random();
        int rand = random.nextInt(500);
        if (rand < 10) {
            //随机生成掉落物坐标
            //范围 100---1000
            objectX = gameStage.getMap().getMapBlockList().get(0).get(random.nextInt(10) + 1).getPostionX() + 10;
            //获得目标行
            int targetRow = gameStage.getMap().getMapBlockList().get(random.nextInt(5)).get(0).getPostionY() + 10;
            if (fallMode == 1) {
                Sunshine b = new Sunshine("Sunshine", 5, 0, objectX, objectY);
                b.setTargetBlockRow(targetRow);
                b.setMoveFlag(4);
                gameStage.getAllBuletList().add(b);
            } else if (fallMode == 2) {
                Skull skull = new Skull("Skull", 5, 0, objectX, objectY);
                skull.setMoveFlag(4);
                skull.setTargetY(targetRow);
                gameStage.getAllBuletList().add(skull);

            }
        }
    }
}

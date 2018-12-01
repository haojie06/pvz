package models.entities.zombie;

import models.entities.weapon.Pole;
import models.entities.weapon.ZombieBite;

//撑杆跳僵尸
public class PoleVaultingZombie extends Zombie {
    //移动的模式，0水平 1向上 -1 向下
    int jumpStep = 0, firstMode = 0, secondMode = 0, injump = 0;
    private int moveMode = 0;

    public PoleVaultingZombie(int positionX, int positionY) {
        super("PoleVaultingZombie", positionX, positionY);
        this.setBoxPadding(-170, -180, 90, 50);
        //修改坐标修正因子
        loadMotion("attack", "attack", "", "", 14);
        loadMotion("dieh", "die", "", "", 20);
        loadMotion("died", "die", "", "", 20);
        loadMotion("move", "move", "", "", 10);
        //设置僵尸使用的武器 （出现了一个比较特殊的武器长杆）
        this.setWeapon(new Pole(this));
        this.setSpeed(10);
        this.setHealth(500);
    }

    public int getMoveMode() {
        return moveMode;
    }

    public void jump(int ifJump, int firstMode, int secondMode) {
        this.injump = ifJump;
        this.firstMode = firstMode;
        this.secondMode = secondMode;
    }

    @Override
    public void move() {
        if (injump == 1) {
            if (jumpStep < 8) {
                moveMode = firstMode;
            } else if (jumpStep < 17) {
                moveMode = secondMode;
            } else {
                //切换武器
                moveMode = 0;
                injump = 0;
                loadMotion("move", "move", "", "", 10);
                    setSpeed(6);
                setWeapon(new ZombieBite(20));

            }
            //计数器增加
            jumpStep++;
        }

        //平时水平移动
        if (moveMode == 0) {
            super.move();
        } else if (moveMode == 1) {
            //向左上方移动
            this.positionX -= 6;
            this.positionY -= 6;
        } else if (moveMode == -1) {
            this.positionX -= 6;
            this.positionY += 6;
        } else if (moveMode == 2) {
            //遇见高坚果的时候跳不过去
            this.positionY -= 1;
            this.positionX -= 1;
        } else if (moveMode == -2) {
            this.positionY += 1;
            this.positionX += 1;
        }

    }

}

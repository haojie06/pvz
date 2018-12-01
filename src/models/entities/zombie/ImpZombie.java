package models.entities.zombie;

import models.entities.weapon.ZombieBite;

//小鬼僵尸
public class ImpZombie extends Zombie {
    public ImpZombie(int positionX, int positionY) {
        super("ImpZombie", positionX, positionY);
        this.setBoxPadding(-15, -55, 60, 80);
        //修改坐标修正因子
        //setPositionCorrect(-80);
        loadMotion("attack", "attack", "", "", 7);
        loadMotion("relax", "relax", "", "", 10);
        loadMotion("died", "die", "", "h", 13);
        loadMotion("dieh", "die", "", "d", 13);
        loadMotion("move", "move", "", "", 11);
        //自行设置碰撞箱边距

        //设置僵尸使用的武器
        this.setWeapon(new ZombieBite(20));
        //设置血量
        this.setHealth(80);
        //速度比较快
        this.setSpeed(10);
    }
}

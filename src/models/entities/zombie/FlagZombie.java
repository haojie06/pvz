package models.entities.zombie;

import models.entities.weapon.ZombieBite;

//持旗僵尸
public class FlagZombie extends Zombie {
    public FlagZombie(int positionX, int positionY) {
        super("FlagZombie", positionX, positionY);
        this.setBoxPadding(-40, -100, 80, 60);
        //修改坐标修正因子
        //setPositionCorrect(-80);

        loadMotion("attack", "attack", "", "", 21);
        loadMotion("relax", "relax", "", "", 10);
        loadMotion("dieh", "die", "", "h", 9);
        loadMotion("died", "die", "", "d", 20);
        loadMotion("move", "move", "", "", 12);
        //自行设置碰撞箱边距

        //设置僵尸使用的武器
        this.setWeapon(new ZombieBite(20));
        //设置血量
        this.setHealth(370);
    }
}

package models.entities.zombie;

import models.entities.weapon.ZombieBite;

//普通僵尸
public class NormalZombie extends Zombie {

    public NormalZombie(int positionX, int positionY) {
        super("NormalZombie", positionX, positionY);
        this.setBoxPadding(-10, -70, 120, 100);
        //修改坐标修正因子
        //setPositionCorrect(-80);

        loadMotion("attack", "attack", "", "", 21);
        loadMotion("relax", "relax", "", "", 10);
        loadMotion("dieh", "die", "", "h", 9);
        loadMotion("died", "die", "", "d", 20);
        loadMotion("move", "move", "", "", 18);
        //自行设置碰撞箱边距

        //设置僵尸使用的武器
        this.setWeapon(new ZombieBite(20));
        //设置血量
        this.setHealth(370);
    }

    //重写加载动作方法，在这里可以添加一些独有动作...该方法在构造函数设置好名字之后调用

}

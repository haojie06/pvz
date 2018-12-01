package models.entities.zombie;

import models.entities.weapon.ZombieBite;

public class FootballZombie extends Zombie {
    //当前是哪一套图片
    int curPic = 0;
    public FootballZombie(int positionX, int positionY) {
        super("FootballZombie", positionX, positionY);
        this.setBoxPadding(-10, -70, 120, 80);
        //修改坐标修正因子
        //setPositionCorrect(-80);
        loadMotion("attack", "attack", "", "", 10);
        loadMotion("relax", "relax", "", "", 15);
        loadMotion("died", "die", "", "d", 20);
        loadMotion("dieh", "die", "_hurtlevel2", "h", 9);
        loadMotion("move", "move", "", "", 11);
        //自行设置碰撞箱边距
        this.setMaxDeadTime(20);
        //设置僵尸使用的武器
        this.setWeapon(new ZombieBite(20));
        //设置血量
        this.setHealth(1670);
        this.setSpeed(8);
    }

    @Override
    public void move() {
        super.move();
        if (this.getHealth() <= 30 && curPic == 0){
            curPic = 1;
            //切换图片为没有头盔的
            loadMotion("attack", "attack", "_hurtlevel2", "", 11);
            loadMotion("move", "move", "_hurtlevel2", "", 11);
            //加速
            this.setSpeed(this.getSpeed() + 4);
        }
    }
}

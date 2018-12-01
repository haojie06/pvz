package models.entities.zombie;

import models.entities.weapon.SkullGenerator;

//玩家选择僵尸阵营时的经济来源，骷髅的生产者
public class GraveStone extends Zombie {
    public GraveStone(int positionX, int positionY) {
        super("GraveStone", positionX, positionY);
        this.setHealth(1000);
        this.setDirectionX(0);
        this.setDirectionY(0);
        this.setSpeed(0);
        this.setIfAlive(2);
        this.setMaxDeadTime(90);
        this.setBoxPadding(-10, -30, 80, 80);
        //默认一开始就移动
        loadMotion("move", "move", "", "", 1);
        //速度，每次移动多少像素
        this.setWeapon(new SkullGenerator(50, 25, 1));
    }
}

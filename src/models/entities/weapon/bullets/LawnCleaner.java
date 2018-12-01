package models.entities.weapon.bullets;

import models.gamesystem.SoundControl;

//特殊子弹类 最后的防线--除草机  ：p
public class LawnCleaner extends Bullet {

    private int ifActivated = 0; //记录是否被激活

    public LawnCleaner(int positionX, int positionY, int row) {
        super("LawnCleaner", positionX, positionY);
        //秒杀一切：D
        this.setDamage(30000);
        this.setRow(row);
        //一开始不移动
        this.setSpeed(7);
        this.setDirectionX(0);
        this.setIfAlive(2);
        this.setBoxPadding(10, 10, 80, 80);
        //除草车只有一张图，车子不会损坏
        this.loadMotion(1, 1);
        this.setCurAnimeName("fly");
    }

    //没被激发之前都不移动或者说速度为0
    @Override
    public void move() {
        super.move();
    }

    //僵尸进入范围后激活，杀死整行的僵尸
    public void activate() {
        if (ifActivated == 0) {
            SoundControl.hashPlayer("lawnmower.mp3", this.hashCode());
            ifActivated = 1;
            this.setSpeed(30);
            this.setDirectionX(1);
        }
    }

    public int getIfActivated() {
        return ifActivated;
    }

    public void setIfActivated(int ifActivated) {
        this.ifActivated = ifActivated;
    }
}

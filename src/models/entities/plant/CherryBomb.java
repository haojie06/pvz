package models.entities.plant;

import Interface.Explosive;
import Interface.Timer;
import models.entities.weapon.Bomb;

public class CherryBomb extends Plant implements Explosive, Timer {
    public CherryBomb(int positionX, int positionY) {
        super("CherryBomb", positionX, positionY);
        this.setWeapon(new Bomb(1000, 1, 1, "樱桃炸弹", this));
        //加载动作，在当前方法下请务必保证dance是最后加载的动作
        loadMotion("attack", "", "", 12);
        //重要！！为了完整的播放爆炸动画
        loadMotion("dance", "", "", 7);
        this.setMaxDeadTime(12);
        this.setBoxPadding(-20, -20, 90, 80);
        this.setHealth(50);
    }
}

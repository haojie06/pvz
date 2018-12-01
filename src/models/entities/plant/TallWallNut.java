package models.entities.plant;

import models.entities.weapon.NutShield;

//高坚果 没有攻击方法/只挨打...
public class TallWallNut extends Plant {
    public TallWallNut(int positionX, int positionY) {
        super("TallWallNut", positionX, positionY);
        this.setBoxPadding(-20, -50, 80, 60);
        this.setHealth(8000);
        this.loadMotion("dance", "", "_level1_", 14);
        //坚果的“武器” 并不攻击，负责检测坚果的状态，改变图片，并不是很好的办法。。
        this.setWeapon(new NutShield(this, 13, 12));
    }
}

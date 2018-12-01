package models.entities.plant;

import models.entities.weapon.NutShield;

public class WallNut extends Plant {
    public WallNut(int positionX, int positionY) {
        super("WallNut", positionX, positionY);
        this.setBoxPadding(-10, -10, 60, 60);
        this.setHealth(4000);
        this.loadMotion("dance", "", "_level1_", 16);
        //坚果的“武器” 并不攻击，负责检测坚果的状态，改变图片，并不是很好的办法。。
        this.setWeapon(new NutShield(this, 11, 15));
    }
}
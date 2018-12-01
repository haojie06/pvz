package models.entities.plant;

import Interface.RemoteShooter;
import models.entities.weapon.CurveLaucher;

//曲线攻击植物 卷心菜投手
public class Cabbage extends Plant implements RemoteShooter {
    public Cabbage(int positionX, int positionY) {
        super("Cabbage", positionX, positionY);
        //武器--曲线发射器 子弹 卷心菜
        loadMotion("shoot", "", "", 14);
        loadMotion("dance", "", "", 23);
        this.setWeapon(new CurveLaucher("卷心菜发射器", 20, 20, 1, this));
        this.setHealth(300);
        this.setBoxPadding(-50, -50, 90, 90);
    }
}

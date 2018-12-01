package models.entities.plant;

import Interface.Explosive;
import Interface.Trigger;
import models.entities.weapon.Bomb;

public class PotatoMine extends Plant implements Explosive, Trigger {
    public PotatoMine(int positionX, int positionY) {
        super("PotatoMine", positionX, positionY);
        this.setWeapon(new Bomb(10000, 0, 0, "Mine", this));
        this.loadMotion("attack", "", "", 8);
        this.setMaxDeadTime(8);
        this.loadMotion("dance", "", "", 8);
        this.setBoxPadding(-20, -10, 60, 60);
    }

    //这个方法如果有图片的话，土豆雷一开始还没有激活
    @Override
    public Boolean ifActivated() {
        return Boolean.TRUE;
    }
}

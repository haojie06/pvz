package models.entities.plant;

import models.entities.weapon.SunGenerator;

//太阳花向日葵（两个头的） 阳光生产者
public class TwinSunflower extends Plant {
    public TwinSunflower(int positionX, int positionY) {
        super("TwinSunflower", positionX, positionY);
        //阳光生成器
        this.setDirectionX(1);
        this.setWeapon(new SunGenerator(50, 25, 2));
        loadMotion("dance", "", "", 20);
        this.setBoxPadding(0, 0, 65, 75);
        this.setHealth(300);
    }
}

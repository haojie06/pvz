package models.entities.plant;

import models.entities.weapon.SunGenerator;

//太阳花向日葵 阳光生产者
public class SunFlower extends Plant {
    public SunFlower(int positionX, int positionY) {
        super("SunFlower", positionX, positionY);
        //阳光生成器
        this.setDirectionX(1);
        this.setWeapon(new SunGenerator(50, 25, 1));
        loadMotion("dance", "", "", 18);
        this.setBoxPadding(0, 0, 65, 75);
        this.setHealth(300);
    }
}

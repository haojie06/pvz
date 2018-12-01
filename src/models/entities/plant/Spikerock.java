package models.entities.plant;

import Interface.Trigger;
import models.entities.weapon.MeleeWeapon;

//地刺王
public class Spikerock extends Plant implements Trigger {
    public Spikerock(int positionX, int positionY) {
        super("Spikerock", positionX, positionY);
        this.setDirectionX(0);
        this.setBoxPadding(-5, 20, 60, 50);
        this.setWeapon(new MeleeWeapon("Stab", 40, this, 10));
        loadMotion("dance", "", "", 8);
        this.setHealth(400);
    }

    @Override
    public Boolean ifActivated() {
        return Boolean.TRUE;
    }
}

package models.entities.plant;

import Interface.Trigger;
import models.entities.weapon.MeleeWeapon;

//地刺
public class Spikeweed extends Plant implements Trigger {
    public Spikeweed(int positionX, int positionY) {
        super("Spikeweed", positionX, positionY);
        this.setDirectionX(0);
        this.setBoxPadding(-5, 20, 60, 50);
        this.setWeapon(new MeleeWeapon("Stab", 20, this, 10));
        loadMotion("dance", "", "", 18);
        this.setHealth(300);
    }

    @Override
    public Boolean ifActivated() {
        return Boolean.TRUE;
    }
}

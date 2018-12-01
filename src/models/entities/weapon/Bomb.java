package models.entities.weapon;

import models.entities.Entity;
import models.entities.plant.Plant;

import java.util.List;

public class Bomb extends Weapon {

    private int attackFieldX, attackFieldY;
    Entity attacker;

    //attackFieldX横向攻击范围,切换动作为attack
    public Bomb(int damage, int attackFieldX, int attackFieldY, String bombName, Entity attacker) {
        this.setDamage(damage);
        this.setWeaponName(bombName);
        this.attackFieldX = attackFieldX;
        this.attackFieldY = attackFieldY;
        this.attacker = attacker;
    }

    @Override
    public void attack(List<Entity> entityList) {
        for (Entity e : entityList) {
            e.setHealth(e.getHealth() - this.getDamage());
            e.setDieReason(1);
            if (e instanceof Plant){
                e.setDieReason(0);
            }
        }
    }

    public int getAttackFieldX() {
        return attackFieldX;
    }

    public void setAttackFieldX(int attackFieldX) {
        this.attackFieldX = attackFieldX;
    }

    public int getAttackFieldY() {
        return attackFieldY;
    }

    public void setAttackFieldY(int attackFieldY) {
        this.attackFieldY = attackFieldY;
    }
}

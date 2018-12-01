package models.entities.weapon;

import models.entities.Entity;
import models.gamesystem.SoundControl;

import java.util.List;

//植物的近战武器
public class MeleeWeapon extends Weapon {
    Entity attacker;

    public MeleeWeapon(String weaponName, int damage, Entity attacker, int coolTime) {
        this.setWeaponName(weaponName);
        this.setCoolTime(coolTime);
        this.setDamage(damage);
        this.attacker = attacker;
    }

    @Override
    public void attack(List<Entity> entityList) {
        if (getCurCoolTime() <= 0 && entityList.size() != 0) {
            for (int i = 0; i < entityList.size(); i++) {
                Entity entity = entityList.get(i);
                entity.setHealth(entity.getHealth() - getDamage());
                this.setCurCoolTime(getCoolTime());
                //每攻击一次耐久/生命减少
                if (attacker.getName().equals("Spikeweed") || attacker.getName().equals("SpikeweedKing")) {
                    SoundControl.playSound("tap.mp3");
                    attacker.setHealth(attacker.getHealth() - 5);
                } else if (attacker.getName().equals("Chomper")) {
                    //食人花攻击，直接移除僵尸
                    SoundControl.playSound("chomp.mp3");
                }
            }
        }

    }
}

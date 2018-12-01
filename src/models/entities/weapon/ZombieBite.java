package models.entities.weapon;

import models.entities.Entity;

import java.io.Serializable;
import java.util.List;

//僵尸撕咬 。。。中二的名字
public class ZombieBite extends Weapon implements Serializable {


    public ZombieBite(int damage) {
        this.setWeaponName("ZombieBite");
        //指定攻击范围
        this.setAttackField(20);
        //指定冷却时间 0--持续攻击
        this.setCoolTime(0);
        this.setCurCoolTime(0);
        //指定伤害（每次攻击造成受攻击对象生命的减少量）
        this.setDamage(damage);
        //1号武器
        this.setWeaponID(1);
    }

    //攻击方法，该武器直接减少受攻击血量就好，没有特效（可扩展），在Listener处调用，传入受攻击的实体列表，某些植物会在这里
    //调用shoot方法，发射子弹，间接攻击
    @Override
    public void attack(List<Entity> entityList) {
        //僵尸之爪只对列表第一个植物起作用
        Entity entity = entityList.get(0);
        entity.setHealth(entity.getHealth() - this.getDamage());
    }

}

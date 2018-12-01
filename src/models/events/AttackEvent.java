package models.events;

import models.entities.Entity;
import models.entities.weapon.Weapon;

import java.util.List;

//攻击事件 传递攻击源
public class AttackEvent extends Event {
    //攻击发起者
    private Entity entity, attackSource;
    private Weapon weapon;
    private List<Entity> entities;

    //传递武器以及在攻击范围内的对象
    public AttackEvent(Entity attackSource, Weapon weapon, List<Entity> entityList) {
        this.attackSource = attackSource;
        eventName = "攻击事件";
        this.weapon = weapon;
        this.entities = entityList;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public Entity getAttackSource() {
        return attackSource;
    }


}

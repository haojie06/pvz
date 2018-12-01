package models.events;

import models.entities.weapon.bullets.Bullet;
import models.entities.Entity;

import java.util.List;

//命中事件，发射的子弹得以命中僵尸时产生
public class HitEvent extends Event {
    //子弹
    private Bullet bullet;
    //命中范围内的实体（根据子弹属性取数量）
    private List<Entity> entities;

    public HitEvent(Bullet bullet, List<Entity> entities) {
        this.setEventName("命中事件");
        this.bullet = bullet;
        this.entities = entities;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public List<Entity> getEntities() {
        return entities;
    }


    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}

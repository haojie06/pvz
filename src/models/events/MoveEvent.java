package models.events;

import models.entities.Entity;

//移动事件
public class MoveEvent extends Event {
    Entity moveEntity;

    public MoveEvent(Entity moveEntity) {
        this.moveEntity = moveEntity;
        eventName = "移动事件";
    }
}

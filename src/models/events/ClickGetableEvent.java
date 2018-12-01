package models.events;

import models.entities.Entity;

//玩家点击了骷髅/向日葵这些Getable接口下的对象时会产生拾取事件
public class ClickGetableEvent extends Event {
    Entity getableEntity;

    public ClickGetableEvent(Entity clickEntity) {
        eventName = "拾取事件";
        this.getableEntity = clickEntity;
    }
}

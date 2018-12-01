package models.events;

import models.entities.Entity;

//处理移动事件，因为不同的实体移动的行为不一样因此调用各自的方法
public class MoveEventListener extends EventListener {
    @Override
    public void eventHandle(Event event) {
        if (event.getEventName().equals("移动事件")) {
            Entity entity = ((MoveEvent) event).moveEntity;
            entity.move();
        }
    }
}

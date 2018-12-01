package models.events;


import models.entities.Entity;
import models.entities.weapon.bullets.Skull;
import models.entities.weapon.bullets.Sunshine;
import models.gamesystem.Economic;
import models.gamesystem.EconomySystem;

//拾取事件的监听/处理 目前只有阳光/骷髅
public class ClickGetableEventListener extends EventListener {
    @Override
    public void eventHandle(Event event) {
        if (event.getEventName().equals("拾取事件")) {
            Entity entity = ((ClickGetableEvent) event).getableEntity;
            if (entity.getName().equals("Sunshine")) {
                //当点击的可拾取对象为阳光时，拾取对象，设置移动轨迹，采用新的移动方法,计分器/金钱 增加
                Sunshine sunshine = (Sunshine) entity;
                sunshine.setMoveFlag(2);
                EconomySystem.addMoney(25);
            } else if (entity.getName().equals("Skull")) {
                Skull skull = (Skull) entity;
                skull.loadMotion(8, 6);
                skull.setMoveFlag(2);
                EconomySystem.addMoney(25);
            }
        }
    }
}

package models.events;

import java.io.Serializable;

//所有事件监听器的抽象父类
public abstract class EventListener implements Serializable {
    private static final long serialVersionUID = 1L;
    public abstract void eventHandle(Event event);
    String eventName;
}

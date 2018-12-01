package models.events;

//所有事件的抽象父类
public abstract class Event {
    protected String eventName;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String name) {
        eventName = name;
    }

}

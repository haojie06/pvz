package Interface;

import models.events.Event;
import models.events.EventListener;

public interface EventSource {
    //List<EventListener> listenerList = new ArrayList<>();
    public void bindListener(EventListener listener);

    public void notifyListener(Event event);

}

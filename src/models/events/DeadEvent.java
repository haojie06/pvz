package models.events;

import models.entities.Entity;
import models.gamesystem.Stage;

public class DeadEvent extends Event {
    Entity entity;
    Stage stage;

    public DeadEvent(Entity deadEntity, String name, Stage stage) {
        entity = deadEntity;
        eventName = name;
        this.stage = stage;
    }
}

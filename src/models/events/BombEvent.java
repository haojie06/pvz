package models.events;

import models.entities.Entity;
import models.gamesystem.Stage;

public class BombEvent extends Event {
    private Entity entity;
    private Stage gameStage;

    public BombEvent(Entity bombSource, Stage stage) {
        this.eventName = "爆炸事件";
        this.entity = bombSource;
        gameStage = stage;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public void setGameStage(Stage gameStage) {
        this.gameStage = gameStage;
    }
}

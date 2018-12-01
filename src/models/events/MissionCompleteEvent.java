package models.events;

import models.gamesystem.Stage;

//关卡
public class MissionCompleteEvent extends Event {
    Stage gameStage;

    public MissionCompleteEvent(Stage stage) {
        this.setEventName("关卡胜利");
        this.gameStage = stage;
    }
}

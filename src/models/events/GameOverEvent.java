package models.events;

import models.gamesystem.Stage;

//游戏结束事件，植物方僵尸到达最左边，僵尸方时间耗尽
public class GameOverEvent extends Event {
    Stage gameStage;
    //激发事件的阵营
    int chooseGroup = 1;

    public GameOverEvent(Stage gameStage, int chooseGroup) {
        this.setEventName("游戏失败");
        this.gameStage = gameStage;
        this.chooseGroup = chooseGroup;
    }
}

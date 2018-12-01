package models.events;

import models.gamesystem.Stage;
import models.mission.Mission;

public class MissionCompleteEventListener extends EventListener {
    @Override
    public void eventHandle(Event event) {
        if (event.getEventName().equals("关卡胜利")) {
            MissionCompleteEvent completeEvent = (MissionCompleteEvent) event;
            //显示关卡胜利的框，里面有玩家名，分数，返回主菜单 / 再来一局
            if (Stage.getPlayerGroup() == 1) {
                //植物获得了胜利
                System.out.println("植物获得了胜利");
            } else {
                System.out.println("僵尸获得了胜利");
            }
        }
    }
}

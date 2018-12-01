package models.events;


import models.gamesystem.SoundControl;
import models.gamesystem.Stage;

//游戏失败事件的监听器，通过controller调用view绘制失败时的界面
//根据阵营显示界面背景图片 显示玩家名字 ， 当前得分 提供功能按钮 再来一局 返回主菜单 退出游戏
public class GameOverEventListener extends EventListener {
    @Override
    public void eventHandle(Event event) {
        if (event.getEventName().equals("游戏失败")) {
            GameOverEvent gameOverEvent = (GameOverEvent) event;
            if (Stage.getPlayerGroup() == 1) {
                System.out.println("植物阵营游戏失败,退出游戏，该方法请在GameOverEventListener中修改，改为显示菜单");
                SoundControl.playSound("zombiegroup.mp3");
                System.exit(1);
            } else {
                SoundControl.playSound("readysetplant.mp3");
                System.out.println("僵尸阵营游戏失败,退出游戏，该方法请在GameOverEventListener中修改，改为显示菜单");
                System.exit(1);
            }
        }
    }
}

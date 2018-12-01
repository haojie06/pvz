package models.mission;

import Interface.EventSource;
import models.events.Event;
import models.events.EventListener;
import models.events.GameOverEventListener;
import models.events.MissionCompleteEventListener;

import java.util.ArrayList;
import java.util.List;

//关卡类 分为植物关卡/僵尸关卡，两个阵营会影响到玩家使用的实体，天上的掉落物，可放置的区域
// ，游戏胜利/游戏失败的判断，关卡还会影响地图的初始化（背景图，并且有些图只有5行还有背景音乐）
public abstract class Mission implements EventSource {
    //阵营
    private int group = 1;
    //关卡名字
    private String MissionName;
    //分别记录关卡对应的背景地图和音乐
    private String backgroundMusicName = "faster.mp3";
    private String backgroundPicSrc = "src/resources/images/interface/background3.jpg";
    List<EventListener> listeners = new ArrayList<>();
    //关卡地图有几行
    private int totalRow = 5;
    public Mission(String missionName) {
        MissionName = missionName;
        this.bindListener(new MissionCompleteEventListener());
        this.bindListener(new GameOverEventListener());
    }
    public abstract void processControl();

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public String getBackgroundMusicName() {
        return backgroundMusicName;
    }

    public void setBackgroundMusicName(String backgroundMusicName) {
        this.backgroundMusicName = backgroundMusicName;
    }

    public String getBackgroundPicSrc() {
        return backgroundPicSrc;
    }

    public void setBackgroundPicSrc(String backgroundPicSrc) {
        this.backgroundPicSrc = backgroundPicSrc;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public void bindListener(EventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void notifyListener(Event event) {
        for (EventListener l : listeners) {
            l.eventHandle(event);
        }
    }
}

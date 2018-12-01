package models.effect;

import models.gamesystem.SoundControl;
import models.gamesystem.Stage;

public class EffectControl {
    private Stage gameStage;

    public EffectControl(Stage gameStage) {
        this.gameStage = gameStage;
    }

    //展示一大波僵尸
    public void showLargeWave() {
        LargeWave largeWave = new LargeWave(-1, 1000, 100, -20, 0, 1000, 300, -300, 300);
        gameStage.getAllEffectList().add(largeWave);
        SoundControl.playSound(largeWave.getEffectSoundSrc());
    }

    //游戏刚开始时提示玩家种植
    public void showPrepareGrow() {
        PrepareGrowPlant prepareGrowPlant = new PrepareGrowPlant(-1, 1000, 100, -20, 0, 1000, 300, -300, 300);
        gameStage.getAllEffectList().add(prepareGrowPlant);
        SoundControl.playSound(prepareGrowPlant.getEffectSoundSrc());
    }

    //提示最后一波
    public void showFinalWave() {
        FinalWave finalWave = new FinalWave(-1, 1000, 100, -20, 0, 1000, 300, -300, 300);
        gameStage.getAllEffectList().add(finalWave);
        SoundControl.playSound(finalWave.getEffectSoundSrc());
    }
    
    public void moveEffect() {
        for (int i = 0; i < gameStage.getAllEffectList().size(); i++) {
            Effect e = gameStage.getAllEffectList().get(i);
            if (e.getLastTimes() == 0) {
                //持续时间结束清除效果
                gameStage.getAllEffectList().remove(e);
            } else {
                e.move();
            }
        }
    }
}

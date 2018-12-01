package models.entities.plant;

import Interface.Trigger;
import models.gamesystem.SoundControl;
import models.entities.weapon.MeleeWeapon;

//食人花 触发类
public class Chomper extends Plant implements Trigger {

    public Chomper(int positionX, int positionY) {
        super("Chomper", positionX, positionY);
        loadMotion("attack", "", "_", 9);
        loadMotion("dance", "", "_hungry_", 13);
        this.setAnimePlayerMode(0);
        this.setHealth(500);
        this.setBoxPadding(-5, -40, 70, 60);
        this.setWeapon(new MeleeWeapon("ChomperBite", 1000, this, 60));
    }

    @Override
    public Boolean ifActivated() {
        //不规范的操作，在这里检测冷却时间/更新激活状态/切换当前图片
        if (getWeapon().getCurCoolTime() <= 0) {
            //冷却（消化）完成之后
            if (this.getAnimePlayerMode() != 0) {
                setCurPic(0);
                loadMotion("dance", "", "_hungry_", 13);
                this.setAnimePlayerMode(0);
            }
            return Boolean.TRUE;
        } else {
            //食人花在攻击时会播放9帧的攻击动画
            if (getCurPic() < 9) {
                if (this.getAnimePlayerMode() != 1) {
                    this.setAnimePlayerMode(1);
                    loadMotion("attack", "", "_", 9);
                }
                setCurPic(getCurPic() + 1);
            } else {
                if (this.getAnimePlayerMode() != 2) {
                    SoundControl.playSound("Chomp.mp3");
                    loadMotion("dance", "", "_full_", 6);
                    this.setAnimePlayerMode(2);
                }
            }
            return Boolean.FALSE;
        }
    }
}

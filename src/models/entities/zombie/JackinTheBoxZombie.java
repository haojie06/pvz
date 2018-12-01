package models.entities.zombie;

import Interface.Explosive;
import Interface.Timer;
import models.gamesystem.SoundControl;
import models.entities.weapon.Bomb;

//爆炸僵尸...
public class JackinTheBoxZombie extends Zombie implements Explosive, Timer {
    int count = 0;
    public JackinTheBoxZombie(int positionX, int positionY) {
        super("JackinTheBoxZombie", positionX, positionY);
        this.setBoxPadding(-80, -100, 120, 80);
        this.loadMotion("attack_level1_", "attack", "", "_level1_", 7);
        this.loadMotion("attack_level2_", "attack", "", "_level2_", 7);
        this.loadMotion("attack_level3_", "attack", "", "_level3_", 6);
        loadMotion("dieh", "die", "", "h", 20);
        loadMotion("died", "die", "", "d", 20);
        this.loadMotion("move", "move", "", "", 8);
        this.setMaxDeadTime(20);
        //设置僵尸使用的武器
        this.setWeapon(new Bomb(500,1,0,"JackBomb",this));
        //设置血量
        this.setHealth(400);
        this.setSpeed(5);
    }

    public void addCount(){
        count++;
    }

    //根据倒计时切换动画
    @Override
    public String getAttackMotionName() {
        if (count >= 22) {
            this.setHealth(-1);
            this.setDieReason(1);
            return "died";
        } else if (count >= 17) {
            SoundControl.hashPlayer("explosion.mp3", this.hashCode() + 2);
            return "attack_level3_";
        }
        else if (count >=10) {
            SoundControl.hashPlayer("polevault.mp3", this.hashCode() + 1);
            return "attack_level2_";
        }
        else {
            SoundControl.hashPlayer("jackinthebox.mp3", this.hashCode());
            return "attack_level1_";
        }
    }

    public int getCount() {
        return count;
    }
}

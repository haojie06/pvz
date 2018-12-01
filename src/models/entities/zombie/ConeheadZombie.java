package models.entities.zombie;

import models.gamesystem.SoundControl;
import models.entities.weapon.ZombieBite;

//路障僵尸 和水桶僵尸差不多的..
public class ConeheadZombie extends Zombie {
    public ConeheadZombie(int positionX, int positionY) {
        super("ConeheadZombie", positionX, positionY);
        //修改坐标修正因子

        //此类僵尸血量过低的时候直接把名字改为普通僵尸加载普通僵尸的动画
        loadMotion("attack", "attack", "", "", 11);
        loadMotion("relax", "relax", "", "", 8);
        loadMotion("dieh", "die", "", "h", 10);
        loadMotion("died", "die", "", "d", 20);
        loadMotion("move", "move", "", "", 21);
        //自行设置碰撞箱边距
        this.setBoxPadding(-40, -90, 120, 70);
        //设置僵尸使用的武器
        this.setWeapon(new ZombieBite(20));
        //设置血量
        //血量为普通僵尸的2倍 血量少于一半的时候换为普通僵尸
        this.setHealth(640);
        //塑料击中
        this.setHitSoundName("plastichit.mp3");
    }

    //在这里监测血量变化
    @Override
    public void move() {
        super.move();
        //血量过低的时候切换成普通僵尸
        if (getHealth() < (getMaxHealth() / 2) && getName().equals("ConeheadZombie")) {
            this.setName("NormalZombie");
            this.setHitSoundName("tap.mp3");
            this.setPositionX(this.getPositionX() + 40);
            loadMotion("attack", "attack", "", "", 21);
            loadMotion("relax", "relax", "", "", 10);
            loadMotion("dieh", "die", "", "h", 9);
            loadMotion("died", "die", "", "d", 20);
            loadMotion("move", "move", "", "", 18);
            SoundControl.playSound("plastichit.mp3");
        }
    }
}

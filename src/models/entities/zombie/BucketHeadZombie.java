package models.entities.zombie;

import models.gamesystem.SoundControl;
import models.entities.weapon.ZombieBite;

import java.util.Random;

//铁桶僵尸 重写MOVE方法，其中增加血量检测，图片切换
public class BucketHeadZombie extends Zombie {
    public BucketHeadZombie(int positionX, int positionY) {
        super("BucketHeadZombie", positionX, positionY);
        this.setBoxPadding(-40, -90, 120, 70);
        //修改坐标修正因子
        //此类僵尸血量过低的时候直接把名字改为普通僵尸加载普通僵尸的动画
        loadMotion("attack", "attack", "", "", 11);
        loadMotion("relax", "relax", "", "", 10);
        loadMotion("dieh", "die", "", "h", 10);
        loadMotion("died", "die", "", "d", 20);
        loadMotion("move", "move", "", "", 15);
        //自行设置碰撞箱边距
        //设置僵尸使用的武器
        this.setWeapon(new ZombieBite(20));
        //设置血量
        //血量为普通僵尸的3倍，如果血量少于一半，直接将名字改为普通僵尸
        this.setHealth(1370);
        //铁桶被击中的声音
        Random r = new Random();
        //两种铁桶的声音
        this.setHitSoundName("shieldhit" + String.valueOf(r.nextInt(2)) + ".mp3");
    }

    //在这里监测血量变化
    @Override
    public void move() {
        super.move();
        //血量过低的时候切换成普通僵尸
        if (getHealth() < (getMaxHealth() / 2) && getName().equals("BucketHeadZombie")) {
            this.setName("NormalZombie");
            //铁通没了声音也要有变化
            this.setHitSoundName("tap.mp3");
            //坐标修正--两个图片不同，要对齐
            this.setPositionX(this.getPositionX() + 30);
            loadMotion("attack", "attack", "", "", 21);
            loadMotion("relax", "relax", "", "", 10);
            loadMotion("dieh", "die", "", "h", 9);
            loadMotion("died", "die", "", "d", 20);
            loadMotion("move", "move", "", "", 18);
            SoundControl.playSound("shieldhit1.mp3");
        }
    }
}

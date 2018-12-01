package models.entities.zombie;

import models.entities.Entity;
import models.gamesystem.Motion;
import models.entities.weapon.Weapon;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Zombie extends Entity implements Serializable {
    //死因 0--掉头 2--变成灰
    private int dieReason = 0;
    //动作的添加在实现类中进行
    private int tempSpeed;//临时速度
    private Weapon weapon;
    public Zombie(String name, int positionX, int positionY) {
        super(name, positionX, positionY);
        this.setMaxDeadTime(90);
        //默认一开始就移动
        this.setCurAnimeName("move");
        //速度，每次移动多少像素
        this.setSpeed(3);
        tempSpeed = this.getSpeed();
        //方向 水平以及竖直 默认只有水平方向运动且向左(-1)
        this.setDirectionX(-1);
        this.setDirectionY(0);

        //设置被击中和死亡的声音
        this.setHitSoundName("peahit.mp3");
        this.setDeadSoundName("grassstep.mp3");
    }


    //僵尸的碰撞箱范围
    int curBoxFirX = this.getBoxFirPositionX();
    int curBoxFirY = this.getBoxFirPositionY();
    int curBoxSecX = this.getBoxSecPositionX();
    int curBoxSecY = this.getBoxSecPositionY();


    //需要在实现类中实现的-动作初始化 构造方法设置好了名字之后进行
    //新的加载图片方法， 动作名，后缀（hurtLevel1 etc.），图片数量 ，
    public void loadMotion(String motionName, String dirName, String classSuffix, String motionSuffix, int picNum) {
        this.setCurAnimeName(motionName);
        Motion curMotion = this.getMotion();
        ArrayList<String> motionPicSrcList = new ArrayList<>();

        for (int i = 1; i <= picNum; i++) {
            //添加后缀区别不同的伤害情况
            String src = "src//resources/ZombiePng/" + this.getName() + classSuffix + "/" + dirName + "/" + dirName + motionSuffix + i + ".png";
            motionPicSrcList.add(src);
            if (i == 1) {
                //当前执行的动作请最后加载
                this.setCurImgSrc(src);
            }
        }
        //移除原有的动作
        curMotion.getMotionList().remove(motionName);
        curMotion.addMotionSrcList(motionName, motionPicSrcList);
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getDieReason() {
        return dieReason;
    }

    public void setDieReason(int dieReason) {
        this.dieReason = dieReason;
    }
}

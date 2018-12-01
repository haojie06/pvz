package models.entities.plant;

import models.entities.Entity;
import models.gamesystem.Motion;
import models.entities.weapon.Weapon;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Plant extends Entity implements Serializable {
    //默认是没有武器的
    private Weapon weapon = null;
    public Plant(String name, int positionX, int positionY) {
        super(name, positionX, positionY);
        //植物默认一开始摇摆（其实大部分植物攻击也是这个动作）
        //设置速度为0 没有方向
        this.setSpeed(0);
        this.setDirectionX(0);
    }

    //植物的攻击识别箱范围
    int curBoxFirX = this.getBoxFirPositionX();
    int curBoxFirY = this.getBoxFirPositionY();
    int curBoxSecX = this.getBoxSecPositionX();
    int curBoxSecY = this.getBoxSecPositionY();

    private int price;  //价格


    //植物加载动画的方法 传入动作名字以及图片数量，根据需要重复调用 现在这个写法要比僵尸类的简洁
    public void loadMotion(String motionName, String classSuffix, String motionSuffix, int picNum) {
        this.setCurAnimeName(motionName);
        //获得当前植物的动作对象（详情查看Motion类）
        Motion curMotion = this.getMotion();
        //指定动作的图片的路径列表
        ArrayList<String> motionPicSrcList = new ArrayList<>();
        for (int i = 1; i <= picNum; i++) {
            String src = "src//resources/PlantPng/" + this.getName() + classSuffix + "/" + motionName + "/" + this.getName() + motionSuffix + i + ".png";
            motionPicSrcList.add(src);
            if (i == 1) {
                this.setCurImgSrc(src);
            }
        }
        curMotion.addMotionSrcList(motionName, motionPicSrcList);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}

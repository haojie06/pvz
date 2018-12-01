package models.entities.weapon.bullets;

import models.entities.Entity;
import models.gamesystem.Motion;
import Interface.MoveInterface;
import models.events.MoveEventListener;

import java.io.Serializable;
import java.util.ArrayList;

//弹药类的父类（豆子，西瓜，玉米等等都会是它的子类） 弹药类保留于武器类中  武器决定威力，射速，而子弹决定射击轨迹，子弹样貌
public abstract class Bullet extends Entity implements MoveInterface, Serializable {
    //一颗子弹能打击多少个敌人l
    private int attackNum = 1;
    private int damage = 5;

    public Bullet(String name, int positionX, int positionY) {
        super(name, positionX, positionY);
        //子弹破碎后保留时间5回合
        this.setIfAlive(2);
        this.bindListener(new MoveEventListener());
    }

    @Override
    public void move() {
        super.move();
    }

    //加载动画 2种，飞行与破坏
    public void loadMotion(int numOfFlying, int numOfBreak) {
        Motion motion = this.getMotion();
        //加载飞行动画
        ArrayList<String> flyImgs = new ArrayList<>();
        for (int i = 1; i <= numOfFlying; i++) {
            String src = "src//resources/Bullet/" + this.getName() + "/fly/" + "fly" + i + ".png";
            flyImgs.add(src);
        }
        motion.addMotionSrcList("fly", flyImgs);
        ArrayList<String> breakImgs = new ArrayList<>();
        for (int i = 1; i <= numOfBreak; i++) {
            String src = "src//resources/Bullet/" + this.getName() + "/break/break" + i + ".png";
            breakImgs.add(src);
        }
        motion.addMotionSrcList("break", breakImgs);
    }

    public int getAttackNum() {
        return attackNum;
    }

    public void setAttackNum(int attackNum) {
        this.attackNum = attackNum;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

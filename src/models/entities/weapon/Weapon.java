package models.entities.weapon;


import models.entities.weapon.bullets.Bullet;
import models.entities.Entity;
import models.gamesystem.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//!!!!注意 部分植物的攻击方式并非远程，该如何抽象 使用一个List<Bullet> 武器对象用来产生Bullet
// 远程的武器（豌豆射击）可以产生多个 bullet（豆子）而近距离武器（地刺的刺，樱桃炸弹之类的）只产生一个Bullet，
//每次遍历所有的植物的BULLETLIST，查看所属的单元格中是否有僵尸，如果有，判断是否击中，如果击中，产生hitevent
// （BUllet应自带如何处理事件的方法，在listener中调用）
public abstract class Weapon implements Serializable {
    //武器ID
    private int weaponID;
    //武器名字
    private String weaponName;
    //武器伤害值
    private int Damage;
    //武器效果（例如冰冻/燃烧
    private String effect;
    //武器冷却时间 当前冷却的时间
    private int coolTime, curCoolTime;
    //武器攻击方式
    //武器攻击行为（产生子弹计算出路径，以一定路径飞行，每次移动判断是否碰撞，例如西瓜投手，计算出攻击当前行离得最近的僵尸的曲线，产生ammo（西瓜），判断碰撞后西瓜破碎，范围伤害）
    //武器产生的所有的子弹
    private List<Bullet> curBulletList = new ArrayList<>();
    //武器的攻击范围（多远进行攻击，如果为-1的则一直进行攻击，冷却既发射）
    private int attackField;

    public abstract void attack(List<Entity> entityList);

    //无参数的attack只管发射
    public void attack(Entity entity, Stage stage) {
    }

    public void attack(List<Entity> entityList, Stage stage) {

    }

    //武器的攻击方法，有的爆炸，有的近距离攻击，还有的会发射BUllet
    //武器的属性在构造方法里面指定，不接受参数
    public Weapon() {
    }

    public int getWeaponID() {
        return weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getCoolTime() {
        return coolTime;
    }

    public void setCoolTime(int coolTime) {
        this.coolTime = coolTime;
    }

    public int getCurCoolTime() {
        return curCoolTime;
    }

    public void setCurCoolTime(int curCoolTime) {
        this.curCoolTime = curCoolTime;
    }

    public List<Bullet> getCurBulletList() {
        return curBulletList;
    }

    public void setCurBulletList(List<Bullet> curBulletList) {
        this.curBulletList = curBulletList;
    }

    public int getAttackField() {
        return attackField;
    }

    public void setAttackField(int attackField) {
        this.attackField = attackField;
    }

    //发射
    public void shootBullet(Bullet bullet) {
        this.curBulletList.add(bullet);
    }

    public void coolDown() {
        if (curCoolTime > 0) {
            this.curCoolTime -= 1;
        } else {
            this.curCoolTime = 0;

        }
    }
}

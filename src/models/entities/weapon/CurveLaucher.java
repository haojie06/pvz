package models.entities.weapon;

import models.entities.Entity;
import models.gamesystem.Stage;
import models.entities.weapon.bullets.CabbageBullet;
import models.entities.weapon.bullets.CurveBullets;

import java.util.List;

//曲线发射器--用于发射西瓜 玉米 卷心菜等等抛射的子弹
public class CurveLaucher extends Weapon {
    String weaponName;
    Entity attacker;
    //一次发射几发
    int shootNum = 1;

    //参数分别是武器名字（用于确定是用什么子弹）冷却时间（回合）和 攻击力
    public CurveLaucher(String weaponName, int coolTime, int damage, int shoutNum, Entity attacker) {
        this.weaponName = weaponName;
        this.attacker = attacker;
        this.shootNum = shoutNum;
        //计算寻找一行中距离最近的僵尸并予以攻击
        this.setAttackField(12);
        //现在100ms刷新一次，刷新30次发射一次，相当于3s一发
        this.setCoolTime(coolTime);
        //当前可以直接发射
        this.setCurCoolTime(0);
        //伤害以后再决定
        this.setDamage(damage);
    }

    @Override
    public void attack(List<Entity> entityList) {

    }

    public void attack(List<Entity> entityList, Stage judgeStage) {
        int startX, targetX, targetSpeed;
        int x, y;
        //子弹起始点
        x = attacker.getPositionX();
        y = attacker.getPositionY();
        for (int i = 0; i <= this.shootNum; i++) {
            if (getCurCoolTime() == 0) {
                startX = attacker.getPositionX();
                targetX = entityList.get(i).getPositionX();
                targetSpeed = entityList.get(i).getSpeed();
                if (weaponName.equals("卷心菜发射器")) {
                    CurveBullets bullet = new CabbageBullet("CabbageBullet", 0, getDamage(), x, y, startX, targetX, targetSpeed, 1, attacker.getRow());
                    judgeStage.getAllBuletList().add(bullet);
                    //cd
                    this.setCurCoolTime(this.getCoolTime());
                }
                //西瓜
                //玉米...
            }
        }
        setCurCoolTime(this.getCoolTime());
    }
}
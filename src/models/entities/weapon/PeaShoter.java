package models.entities.weapon;

import models.entities.weapon.bullets.Beans;
import models.entities.Entity;
import models.gamesystem.Stage;

import java.util.List;
import java.util.Random;

//豌豆发射器（普通）
public class PeaShoter extends Weapon {
    //一次发射几发
    int shootNum = 1;
    //参数分别是冷却时间（回合）和 攻击力
    public PeaShoter(int coolTime, int damage, int shoutNum) {
        this.shootNum = shoutNum;
        //-1代表无限攻击距离（其实就是一直攻击不需要有僵尸）
        this.setAttackField(-1);
        //现在100ms刷新一次，刷新30次发射一次，相当于3s一发
        this.setCoolTime(coolTime);
        //当前可以直接发射
        Random random = new Random();
        this.setCurCoolTime(random.nextInt(coolTime));
        //伤害以后再决定
        this.setDamage(damage);
    }

    //这个武器为间接攻击武器（通过发射子弹攻击僵尸）

    public void attack(List<Entity> entityList) {
        //不调用这个方法
    }

    //使用这个无参方法 把发射者传进去
    public void attack(Entity attacker, Stage judgeStage) {
        for (int i = 1; i <= this.shootNum; i++) {
            if (getCurCoolTime() == 0) {
                //冷却时间为0 发射豆子 speed为子弹移动速度
                //请矫正以下豆子的位置
                Beans bean = new Beans("Beans", 20, attacker.getPositionX() + 30 * i, attacker.getPositionY() + 15, getDamage(), 1, attacker.getRow());
                judgeStage.getAllBuletList().add(bean);
            }
        }
        setCurCoolTime(this.getCoolTime());
    }
}

package models.entities.weapon;

import models.entities.Entity;
import models.gamesystem.Stage;
import models.entities.weapon.bullets.Sunshine;

import java.util.List;

//阳光生成器
public class SunGenerator extends Weapon {

    int shootNum = 1;

    public SunGenerator(int coolTime, int damage, int shoutNum) {
        this.shootNum = shoutNum;
        //不需要判断与僵尸距离的关系就可以生成阳光
        this.setAttackField(-1);
        //现在100ms刷新一次，刷新30次发射一次，相当于3s一发
        this.setCoolTime(coolTime);
        //当前可以直接发射
        this.setCurCoolTime(20);
        //伤害以后再决定
        this.setDamage(damage);
    }

    @Override
    public void attack(List<Entity> entityList) {

    }

    //阳光生成器比较特殊。。。武器生成的是阳光，竖直方向移动，点击后销毁，产生得分事件，金盏花类似
    public void attack(Entity attacker, Stage judgeStage) {
        if (getCurCoolTime() == 0) {
            for (int i = 1; i <= this.shootNum; i++) {
                //冷却时间为0 发射豆子 speed为子弹移动速度
                //可能需要纵向多判断几个格子（竖直方向移动）
                Sunshine sunshine = new Sunshine("Sunshine", 10, attacker.getRow(), attacker.getPositionX() - attacker.getBoxLeftWidth() - 20, attacker.getPositionY() - attacker.getBoxLeftHeight() - 20);
                judgeStage.getAllBuletList().add(sunshine);
                judgeStage.getAllGetablesList().add(sunshine);
            }
        }
        setCurCoolTime(this.getCoolTime());
    }
}

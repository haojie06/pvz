package models.entities.weapon;

import models.entities.Entity;
import models.gamesystem.Stage;
import models.entities.weapon.bullets.Skull;

import java.util.List;

//墓碑持有的骷髅生成器，僵尸方召唤僵尸需要使用
public class SkullGenerator extends Weapon {
    int shootNum = 1;

    public SkullGenerator(int coolTime, int damage, int shootNum) {
        this.setWeaponName("SkullGenerator");
        this.shootNum = shootNum;
        this.setAttackField(-1);
        //现在100ms刷新一次，刷新30次发射一次，相当于3s一发
        this.setCoolTime(coolTime);
        //当前可以直接发射
        this.setCurCoolTime(10);
        //伤害以后再决定
        this.setDamage(damage);
    }

    //骷髅生成器。。。武器生成的是骷髅，竖直方向移动，点击后销毁，产生得分事件，金盏花类似
    public void attack(Entity attacker, Stage judgeStage) {
        if (getCurCoolTime() <= 0) {
            for (int i = 0; i <= this.shootNum; i++) {
                Skull skull = new Skull("Skull", 10, attacker.getRow(), attacker.getPositionX() - attacker.getBoxLeftWidth() - 20, attacker.getPositionY() - attacker.getBoxLeftHeight() - 20);
                judgeStage.getAllBuletList().add(skull);
                judgeStage.getAllGetablesList().add(skull);
                setCurCoolTime(this.getCoolTime());
            }
        } else {
            coolDown();
        }


    }

    @Override
    public void attack(List<Entity> entityList) {

    }
}

package models.entities.weapon;

import models.entities.Entity;
import models.entities.plant.Plant;
import models.entities.zombie.PoleVaultingZombie;

import java.util.List;

//撑杆跳僵尸第一次使用的长杆，没有攻击力，不触发攻击事件，使僵尸跳到植物的背后，只能用一次，之后切换为ZombieBite
public class Pole extends Weapon {
    //跳跃动作计数
    int jumpStep = 0;
    //跳跃模式（1 -1 / 2 -2 的区别是 2，-2是原地跳（遇见高坚果的时候））
    int firstMode, secondMode;
    PoleVaultingZombie attacker;

    public Pole(Entity attacker) {
        this.setWeaponName("Pole");
        this.attacker = (PoleVaultingZombie) attacker;
        //指定攻击范围
        this.setAttackField(1);
        //指定冷却时间 0--持续判断，跳跃
        this.setCoolTime(0);
        this.setCurCoolTime(0);
        //指定伤害（每次攻击造成受攻击对象生命的减少量）
        this.setDamage(0);
    }

    @Override
    public void attack(List<Entity> entityList) {
        //在这里进行跳跃，跳不过高坚果,需要跳过的植物
        Plant plant = (Plant) entityList.get(0);
        //跳的动作有17帧，8张向上，9张向下 （暂定）
        if (!(attacker.getCurAnimeName().equals("jump")) && attacker.getWeapon().getWeaponName().equals("Pole") && entityList.size() != 0) {
            attacker.loadMotion("jump", "move", "", "_jump_", 17);
        }
        if (!plant.getName().equals("TallWallNut")) {
            firstMode = 1;
            secondMode = -1;
        } else {
            firstMode = 2;
            secondMode = -2;
        }
        attacker.jump(1, firstMode, secondMode);
    }
}

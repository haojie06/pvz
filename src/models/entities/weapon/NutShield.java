package models.entities.weapon;

import models.entities.Entity;
import models.gamesystem.Stage;
import models.entities.plant.Plant;

import java.util.List;

//坚果的盾牌..其实目前起到的作用只有检测生命 切换图片
public class NutShield extends Weapon {
    double level2Line;
    double level3Line;
    int level2Arraved = 0, level3Arraved = 0, level2PicNum, level3PicNum;
    Plant attacker;

    public NutShield(Plant attacker, int level2PicNum, int level3PicNum) {
        this.setWeaponName("NutShield");
        this.level2PicNum = level2PicNum;
        this.level3PicNum = level3PicNum;
        this.attacker = attacker;
        this.setCoolTime(0);
        //持续进行检测
        this.setAttackField(-1);
        //武器创建初期赋值，此时的生命坚果的生命值是满的
        int maxHealth = attacker.getMaxHealth();
        level2Line = 0.5 * maxHealth;
        level3Line = 0.25 * maxHealth;
    }


    @Override
    public void attack(List<Entity> entityList) {

    }

    public void attack(Entity e, Stage stage) {
        if (attacker.getHealth() <= level3Line && level3Arraved == 0) {
            level3Arraved = 1;
            attacker.loadMotion("dance", "", "_level3_", level3PicNum);
        } else if (attacker.getHealth() <= level2Line && level2Arraved == 0) {
            level2Arraved = 1;
            attacker.loadMotion("dance", "", "_level2_", level2PicNum);
        }
    }
}

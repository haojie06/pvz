package models.entities.plant;
import Interface.RemoteShooter;
import models.entities.weapon.PeaShoter;


//冰冻豌豆射手
public class SnowPea extends Plant implements RemoteShooter {
    //使僵尸速度减慢

    //请在PlantFactory里面将行数转为坐标（不同的类由于图片不同，坐标要对应不同）
    public SnowPea(int positionX, int positionY) {
        super("SnowPea", positionX, positionY);
        //第一个参数是冷却时间，第二个--伤害
        this.setWeapon(new PeaShoter(50, 10, 1));
        loadMotion("dance", "", "", 15);
        this.setBoxPadding(-20, -30, 90, 80);
        this.setHealth(300);
    }
}
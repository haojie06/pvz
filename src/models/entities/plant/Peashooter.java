package models.entities.plant;
import Interface.RemoteShooter;
import models.entities.weapon.PeaShoter;


//普通豌豆射手
public class Peashooter extends Plant implements RemoteShooter {
    //一次射出一颗豌豆

    //请在PlantFactory里面将行数转为坐标（不同的类由于图片不同，坐标要对应不同）
    public Peashooter(int positionX, int positionY) {
        super("Peashooter", positionX, positionY);
        //第一个参数是冷却时间，第二个--伤害
        this.setWeapon(new PeaShoter(50, 10, 1));
        loadMotion("dance", "", "", 13);
        this.setBoxPadding(-20, -30, 90, 80);
        this.setHealth(300);
    }
}

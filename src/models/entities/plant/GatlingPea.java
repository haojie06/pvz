package models.entities.plant;
import Interface.RemoteShooter;
import models.entities.weapon.PeaShoter;


//机枪射手
public class GatlingPea extends Plant implements RemoteShooter {
    //机枪射速快一些

    //请在PlantFactory里面将行数转为坐标（不同的类由于图片不同，坐标要对应不同）
    public GatlingPea(int positionX, int positionY) {
        super("GatlingPea", positionX, positionY);
        //第一个参数是冷却时间，第二个--伤害
        this.setWeapon(new PeaShoter(50, 10, 4));
        loadMotion("dance", "", "", 11);
        this.setBoxPadding(-20, -30, 90, 80);
        this.setHealth(300);
    }
}
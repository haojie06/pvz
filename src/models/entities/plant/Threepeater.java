package models.entities.plant;
import Interface.RemoteShooter;
import models.entities.weapon.PeaShoter;


//3个头的豌豆射手
public class Threepeater extends Plant implements RemoteShooter {
    /**********三个豌豆于射手三个头的位置对应还没做*********/

    //请在PlantFactory里面将行数转为坐标（不同的类由于图片不同，坐标要对应不同）
    public Threepeater(int positionX, int positionY) {
        super("Threepeater", positionX, positionY);
        //第一个参数是冷却时间，第二个--伤害
        this.setWeapon(new PeaShoter(50, 40, 3));
        loadMotion("dance", "", "", 16);
        this.setBoxPadding(-20, -30, 90, 80);
        this.setHealth(300);
    }
}

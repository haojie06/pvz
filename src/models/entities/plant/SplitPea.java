package models.entities.plant;
import Interface.RemoteShooter;
import models.entities.weapon.PeaShoter;


//双向豌豆射手
public class SplitPea extends Plant implements RemoteShooter {
    //向前后均需射出豌豆
    /******************向后射出豌豆还未实现*************************************/
    //请在PlantFactory里面将行数转为坐标（不同的类由于图片不同，坐标要对应不同）
    public SplitPea(int positionX, int positionY) {
        super("SplitPea", positionX, positionY);
        //第一个参数是冷却时间，第二个--伤害
        this.setWeapon(new PeaShoter(20, 10, 1));
        loadMotion("dance", "", "", 14);
        this.setHealth(300);
        this.setBoxPadding(-20, -30, 90, 80);
    }
}

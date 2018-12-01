package models.entities.weapon.bullets;

//射出的豌豆
public class Beans extends Bullet {

    //威力和武器相关
    public Beans(String name, int speed, int positionX, int positionY, int damage, int directionX, int row) {
        super("Beans", positionX, positionY);
        this.setDamage(damage);
        this.loadMotion(1, 5);
        this.setSpeed(speed);
        this.setRow(row);
        //x轴正向飞行
        this.setDirectionX(directionX);
        this.setBoxPadding(10, 10, 20, 30);
        //默认一开始就飞行
        this.setCurAnimeName("fly");
        this.setHitSoundName("peahit.mp3");
    }

    //普通豆子直线 x轴正向传播
    @Override
    public void move() {
        this.positionX += getSpeed() * this.getDirectionX();
    }
}

package models.entities.weapon.bullets;

//曲线飞行的所有子弹父类，此类子弹的move方法比较特殊，子类中设置图片以及攻击力/特效等属性
public class CurveBullets extends Bullet {
    int targetX, startX, launchX, targetSpeed;
    //刷新多少次后击中目标
    int moveTimes = 20, curMove = 0;
    //射高40px
    int shootHeight = 60;
    //飞行时XY方向的移动速度
    int xSpeed, ySpeed;

    //威力和武器相关 targetX 目标的X坐标 攻击时传入
    public CurveBullets(String name, int speed, int positionX, int positionY, int launchX, int targetX, int targetSpeed, int damage, int directionX, int row) {
        super(name, positionX, positionY);
        this.targetX = targetX;
        this.targetSpeed = targetSpeed;
        this.launchX = launchX;
        this.setDamage(damage);
        //图片请在子类中加载
        this.loadMotion(0, 0);
        this.setSpeed(speed);
        this.setRow(row);
        //x轴正向飞行
        this.setDirectionX(directionX);
        this.setBoxPadding(10, 10, 40, 40);
        //默认一开始就飞行
        this.setCurAnimeName("fly");
        //计算移动速度（并不保证一定击中）
        this.setHitSoundName("tap.mp3");
        calSpeed();
    }

    //根据目标确定一条曲线，同一曲线顶点
    @Override
    public void move() {
        curMove++;
        ySpeed = Math.abs(ySpeed);
        //一半上升一般下降 射高一定是正数
        if (curMove < (moveTimes / 2)) {
            ySpeed = -1 * ySpeed;
        }
        //移动
        positionX += xSpeed;
        positionY += ySpeed;
    }

    //以下方法根据起点坐标和终点坐标以及移动次数计算飞行时xy方向的速度
    private void calSpeed() {
        int xDif = ((targetX + moveTimes * targetSpeed) - launchX);
        //暂时使用20次移动至目的区域,之后可以根据距离不同设置不同的次数
        xSpeed = xDif / moveTimes;
        ySpeed = (shootHeight * 2) / moveTimes;
    }
}

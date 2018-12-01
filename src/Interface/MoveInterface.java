package Interface;

//移动接口
public interface MoveInterface {
    abstract void changeDirection(int directionX, int directionY);

    abstract void changeSpeed(int newSpeed);

    //运动
    abstract void move();

    //停止运动
    abstract void stopMove();

    //恢复运动
    abstract void resumeMove();
}

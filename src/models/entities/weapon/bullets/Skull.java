package models.entities.weapon.bullets;

import Interface.Getable;
import models.gamesystem.AnimeControl;

//骷髅
public class Skull extends Bullet implements Getable {
    //特殊移动移动路径,用于将太阳移动到左上角 横向每次移动10像素吧
    int moveX = 2; //X轴方向每次移动多少 ！！会影响太阳移动得速度 这个是不是应该采用动态计算（离得越远越快
    int moveY;
    //当前移动次数 总移动次数
    int moveCount = 0, moveMaxCount = 0;
    private int targetY = 0;
    //移动方式 1为默认 2计算拾取后的移动路径 3采用新路径进行移动
    private int moveFlag = 1;

    public Skull(String name, int speed, int row, int positionX, int positionY) {
        super(name, positionX, positionY);
        this.setDamage(0);
        //一直不点击就会消失
        this.setHealth(100);
        this.loadMotion(8, 6);
        this.setIfAlive(2);
        this.setSpeed(speed);
        this.setRow(row);
        this.setDirectionX(-1);
        this.setDirectionY(-1); //先向上移动，竖直方向移动比较特殊
        this.setBoxPadding(0, 0, 50, 50);
        //默认一开始就飞行
        this.setCurAnimeName("fly");
    }

    //加载动画 2种，飞行与破坏
    //阳光的“破坏效果” 到了左上角后逐渐变小/变透明，然后消失

    //特殊的移动，移动一次生命减少一点，生命少少于一定的值之后向下移动，再少于一定的值之后停止
    @Override
    public void move() {
        if (moveFlag == 1) {
            if (this.getHealth() >= 95) {
                this.decreaseHealth(1);
                //初期向上移动
                this.positionY += this.getDirectionY() * this.getSpeed();
                this.positionX += this.getDirectionX() * 10;
            } else if (this.getHealth() >= 88) {
                this.decreaseHealth(1);
                //方向改为向下
                this.setDirectionY(1);
                this.positionY += this.getDirectionY() * this.getSpeed();
            } else {
                loadMotion(1, 6);
                this.decreaseHealth(1);
                if (this.getHealth() <= 0) {
                    loadMotion(8, 6);
                    this.moveFlag = 2;
                }
                //死亡判断交给清洁工类
            }
        } else if (moveFlag == 2) {
            moveFlag = 3;
            int difX = 380 - this.getPositionX();
            int difY = 36 - this.getPositionY();
            int absDifX = Math.abs(difX);
            moveX = absDifX / 20 + 4;
            moveMaxCount = Math.abs((381 - this.getPositionX())) / Math.abs(moveX) + 2;
            moveX = difX / moveMaxCount;
            moveY = difY / moveMaxCount;
            //计算向左上角移动的路径
        } else if (moveFlag == 3) {
            this.positionX += moveX;
            this.positionY += moveY;
            moveCount++;

            if (moveCount == (moveMaxCount - 2)) {
                this.setIfAlive(1);
                AnimeControl animeControl = new AnimeControl();
                animeControl.animeChange(this, "break");
            }

        }
        //特殊 当太阳来自随机生成，向下移动，移动到某一单元格停止
        else if (moveFlag == 4) {
            if (this.positionY >= targetY) {
                //避免停止下来之后还向下移动，要和moveflag1 中的最后一个条件一样
                this.setHealth(30);
                moveFlag = 1;
                //运动到指定格子后停止运动
                this.setDirectionX(0);
                this.setDirectionY(0);
            } else {
                //没有移动到指定格子时向下运动
                this.positionY += this.getSpeed();
            }
        }
    }

    public int getMoveFlag() {
        return moveFlag;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void setMoveFlag(int moveFlag) {
        this.moveFlag = moveFlag;
    }
}

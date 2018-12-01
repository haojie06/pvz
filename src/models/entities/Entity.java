package models.entities;

/*
* 实体类 植物/僵尸/英雄/障碍物/弹药等游戏中出现的实体的抽象父类
* */

import Interface.MoveInterface;
import models.gamesystem.Motion;
import models.events.Event;
import models.events.EventListener;
import Interface.EventSource;
import models.entities.weapon.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements EventSource, MoveInterface, Serializable {
    private static final long serialVersionUID = 1L;
    //速度，每次移动多少像素 row 代表其在第几行 col代表列
    private int speed = 0, tempSpeed = 0;
    //死亡时间（记录以及死亡/被破坏了多久）
    private int deadTime = 0, maxDeadTime = 5;
    //实体处于哪一行（一共六行），哪一列的单元格
    private int row = 0, col = 0;
    //运动方向 水平以及竖直 默认只有水平方向运动且向 1向右 -1向左
    private int directionX = 0, directionY = 0;
    //绑定的监听器列表，产生事件时会一一通知监听器，对应的监听器会进行处理
    private List<EventListener> listenerList = new ArrayList<>();
    //实体名字
    private String name;
    //实体的像素位置 （贴图左上点的像素位置
    public int positionX, positionY;
    //当前贴图路径，以及当前的动画名字
    private String curImgSrc, curAnimeName;
    //实体所具有的动作 具体请查看Motion类
    private Motion motion = new Motion();
    //当前贴图是第几张
    private int imgID = 1;
    //当前动作贴图路径列表
    private List<String> motionImgList;
    //当前生命，默认100 最大生命值
    private int health = 100, maxHealth = -1;
    //实体死亡原因
    private int dieReason = 0;
    //每个实体被击杀带来的分数（当然子弹和自己阵营的实体被击杀不会增加分数）
    private int score = 0;
    //碰撞箱左上点坐标以及宽度高度
    //boxLeftWidth--碰撞箱左边框距离图片坐标的宽度，另外几项也类似请在子类中设置好
    private int boxLeftWidth, boxLeftHeight, boxRightWidth, boxRightHeight;
    //表示是否存活的变量 2-存活 1-死亡（未清除） 0-请进行彻底清除（从list中remove掉
    private int ifAlive = 2;
    //坐标修正因子（为了对齐多个动画，引入坐标修正因子）
    private int positionCorrect = 0;
    //武器
    private Weapon weapon;
    //实体被击中时播放的声音名字
    private String hitSoundName = "tap.mp3", deadSoundName = "grassstep.mp3";
    //动画控制部分 动画播放模式（放指定次数或者循环播放）指定的次数 当前动画一共有多少张 当前播放到哪一张
    private int AnimePlayerMode, repeatTime, maxPic, curPic;

    //碰撞箱坐标请在实现类中写出，根据px py计算
    public Entity(String name, int positionX, int positionY) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        //设置好碰撞/识别箱
        setBoxPadding(30, 30, 30, 30);

    }


    public void setBoxPadding(int boxLeftWidth, int boxLeftHeight, int boxRightWidth, int boxRightHeight) {
        this.boxLeftWidth = boxLeftWidth;
        this.boxLeftHeight = boxLeftHeight;
        this.boxRightWidth = boxRightWidth;
        this.boxRightHeight = boxRightHeight;
    }

    @Override
    public void move() {
        //活着才移动
        if (getIfAlive() == 2) {
            //僵尸当前的坐标
            int curPositionX = this.getPositionX();
            int curPositionY = this.getPositionY();
            //水平移动
            this.setPositionX(curPositionX + directionX * speed);
            //垂直移动___还没有实现，还不知道是否有必要
            //this.setPositionY(curPositionY);
        }
    }

    public void decreaseHealth(int i) {
        this.health -= i;
    }

    @Override
    public void changeDirection(int directionX, int directionY) {
        this.directionX = directionX;
        this.directionY = directionY;
    }

    @Override
    public void changeSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    @Override
    public void stopMove() {
        if (speed != 0) {
            tempSpeed = speed;
        }
        speed = 0;
    }

    @Override
    public void resumeMove() {
        speed = tempSpeed;
    }


    @Override
    public void bindListener(EventListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void notifyListener(Event event) {
        for (EventListener ev : listenerList) {
            ev.eventHandle(event);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setBoxRightHeight(int boxRightHeight) {
        this.boxRightHeight = boxRightHeight;
    }

    public String getCurImgSrc() {
        return curImgSrc;
    }

    public void setCurImgSrc(String curImgSrc) {
        this.curImgSrc = curImgSrc;
    }

    public List<String> getMotionImgList(String motionName) {
        return motion.getMotionSrcList(motionName);
    }

    public void setMotionImgList(List<String> motionImgList) {
        this.motionImgList = motionImgList;
    }

    public String getCurAnimeName() {
        return curAnimeName;
    }

    public void setCurAnimeName(String curAnimeName) {
        this.curAnimeName = curAnimeName;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public Motion getMotion() {
        return motion;
    }

    public int getIfAlive() {
        return ifAlive;
    }

    public void setIfAlive(int ifAlive) {
        this.ifAlive = ifAlive;
    }

    public int getPositionCorrect() {
        return positionCorrect;
    }


    public int getDeadTime() {
        return deadTime;
    }

    //获得碰撞箱两点距离

    public int getBoxFirPositionX() {
        return positionX - boxLeftWidth;
    }

    public int getBoxFirPositionY() {
        return positionY - boxLeftHeight;
    }

    public int getBoxSecPositionX() {
        return positionX + boxRightWidth;
    }

    public int getBoxSecPositionY() {
        return positionY + boxRightHeight;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        tempSpeed = speed;
    }



    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }


    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }


    //生命
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (maxHealth == -1) {
            maxHealth = health;
        }
    }

    //死亡时间增加
    public void addDeadTime() {
        deadTime++;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


    public int getBoxLeftWidth() {
        return boxLeftWidth;
    }

    public int getBoxLeftHeight() {
        return boxLeftHeight;
    }

    public int getBoxRightWidth() {
        return boxRightWidth;
    }

    public int getBoxRightHeight() {
        return boxRightHeight;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDeadTime(int deadTime) {
        this.deadTime = deadTime;
    }

    public int getMaxDeadTime() {
        return maxDeadTime;
    }

    public void setMaxDeadTime(int maxDeadTime) {
        this.maxDeadTime = maxDeadTime;
    }

    public int getDieReason() {
        return dieReason;
    }

    public void setDieReason(int dieReason) {
        this.dieReason = dieReason;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getAnimePlayerMode() {
        return AnimePlayerMode;
    }

    public void setAnimePlayerMode(int animePlayerMode) {
        AnimePlayerMode = animePlayerMode;
    }

    public int getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(int repeatTime) {
        this.repeatTime = repeatTime;
    }

    public int getMaxPic() {
        return maxPic;
    }

    public void setMaxPic(int maxPic) {
        this.maxPic = maxPic;
    }

    public int getCurPic() {
        return curPic;
    }

    public void setCurPic(int curPic) {
        this.curPic = curPic;
    }


    public String getHitSoundName() {
        return hitSoundName;
    }

    public void setHitSoundName(String hitSoundName) {
        this.hitSoundName = hitSoundName;
    }

    public String getDeadSoundName() {
        return deadSoundName;
    }

    public void setDeadSoundName(String deadSoundName) {
        this.deadSoundName = deadSoundName;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    //用于切换攻击阶段图片
    public String getAttackMotionName(){
        return "attack";
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

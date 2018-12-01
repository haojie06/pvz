package models.effect;

import Interface.MoveInterface;

import java.io.Serializable;

//视觉效果类，除实体外的特效都在这里 如 飘过的字（一大波僵尸正在接近中。。。全屏闪烁。。。等等特效）
public class Effect implements MoveInterface, Serializable {
    //特效名字
    private static final long serialVersionUID = 1L;
    private String effectName;
    //持续刷新回合
    private int lastTimes = 0;
    //特效图片的坐标
    private int positionX = 0, positionY = 0;
    //特效图片X Y 轴的速度
    private int speedX = 0, speedY = 0;
    private int startX, startY, endX, endY;
    //特效图片路径 音效路径
    private String effectImgSrc, effectSoundSrc;

    public Effect(String effectName, int lastTimes, int positionX, int positionY, int speedX, int speedY, int startX, int startY, int endX, int endY) {
        this.effectName = effectName;
        this.lastTimes = lastTimes;
        this.positionX = positionX;
        this.positionY = positionY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    public void showEffect() {

    }

    @Override
    public void changeDirection(int directionX, int directionY) {

    }


    @Override
    public void changeSpeed(int speed) {

    }

    public void changeSpeed(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }


    @Override
    public void move() {
        //重要！
        this.positionX = this.positionX + speedX;
        this.positionY = this.positionY + speedY;
        if (positionX < endX) {
            lastTimes = 0;
        }
    }

    @Override
    public void stopMove() {

    }

    @Override
    public void resumeMove() {

    }


    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }

    public int getLastTimes() {
        return lastTimes;
    }

    public void setLastTimes(int lastTimes) {
        this.lastTimes = lastTimes;
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

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public String getEffectImgSrc() {
        return effectImgSrc;
    }

    public void setEffectImgSrc(String effectImgSrc) {
        this.effectImgSrc = effectImgSrc;
    }

    public String getEffectSoundSrc() {
        return effectSoundSrc;
    }

    public void setEffectSoundSrc(String effectSoundSrc) {
        this.effectSoundSrc = effectSoundSrc;
    }
}

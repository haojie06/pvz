package models.effect;

//僵尸到来之前准备安放植物
public class PrepareGrowPlant extends Effect {
    public PrepareGrowPlant(int lastTimes, int positionX, int positionY, int speedX, int speedY, int startX, int startY, int endX, int endY) {
        super("PrepareGrowPlant", lastTimes, positionX, positionY, speedX, speedY, startX, startY, endX, endY);
        this.setEffectImgSrc("src//resources/EffectPng/PrepareGrowPlants.png");
        this.setEffectSoundSrc("readysetplant.mp3");
    }

    @Override
    public void move() {
        this.setPositionX(this.getPositionX() + getSpeedX());
        this.setPositionY(getPositionY() + getSpeedY());
        if (getPositionX() <= getEndX()) {
            this.setLastTimes(0);

        }
    }
}

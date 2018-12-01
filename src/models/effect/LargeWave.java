package models.effect;

//一大波僵尸接近中的效果
public class LargeWave extends Effect {
    public LargeWave(int lastTimes, int positionX, int positionY, int speedX, int speedY, int startX, int startY, int endX, int endY) {
        super("LargeWave", lastTimes, positionX, positionY, speedX, speedY, startX, startY, endX, endY);
        this.setEffectImgSrc("src//resources/EffectPng/LargeWave.png");
        this.setEffectSoundSrc("hugewave.mp3");
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

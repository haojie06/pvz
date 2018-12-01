package models.effect;

public class FinalWave extends Effect {
    //最后一波效果
    public FinalWave(int lastTimes, int positionX, int positionY, int speedX, int speedY, int startX, int startY, int endX, int endY) {
        super("FinalWave", lastTimes, positionX, positionY, speedX, speedY, startX, startY, endX, endY);
        this.setEffectImgSrc("src//resources/EffectPng/FinalWave.png");
        this.setEffectSoundSrc("finalwave.mp3");
    }

}

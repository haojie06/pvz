package views;

import models.gamesystem.Stage;
import models.effect.Effect;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EffectView extends JLabel {
    private Graphics graphics;

    public EffectView(Graphics graphics) {
        this.graphics = graphics;
    }

    //绘制效果
    public void drawEffects(Stage gameStage) {
        List<Effect> effectList = gameStage.getAllEffectList();
        for (Effect e : effectList) {
            Image entityImg = new ImageIcon(e.getEffectImgSrc()).getImage();
            graphics.drawImage(entityImg, e.getPositionX(), e.getPositionY(), null);
        }
    }
}

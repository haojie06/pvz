package views;

import models.entities.Entity;
import models.gamesystem.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//绘制实体
public class EntityView extends JLabel {
    private Graphics graphics;

    public EntityView(Graphics graphics) {
        this.graphics = graphics;
    }

    //绘制实体，要求传入实体列表
    public void drawEntities(Stage gameStage) {
        //所有实体一起画
        List<Entity> entities = new ArrayList<>();
        entities.addAll(gameStage.getAllZombieList());
        entities.addAll(gameStage.getAllPlantList());
        entities.addAll(gameStage.getAllBuletList());

        for (int i = 0; i < 6; i++) {
            for (Entity entity : entities) {
                //绘制实体碰撞框
                //System.out.println(entity.getBoxSecPositionX());
                if (entity.getRow() == i) {
                    graphics.setColor(Color.BLACK);
                   // graphics.drawRect(entity.getBoxFirPositionX(), entity.getBoxFirPositionY(), entity.getBoxRightWidth(), entity.getBoxRightHeight());
                    Image entityImg = new ImageIcon(entity.getCurImgSrc()).getImage();
                    graphics.drawImage(entityImg, entity.getPositionX(), entity.getPositionY(), null);

                }
            }
        }
    }
}

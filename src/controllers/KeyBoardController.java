package controllers;

import models.gamesystem.SoundControl;
import models.gamesystem.Stage;
import models.gamesystem.StageSave;
import views.Canvas;
import views.MapView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


//键盘事件的处理

public class KeyBoardController extends JFrame {
    private Canvas canvas;
    private Stage stage;

    private MapView mapView;

    public KeyBoardController(Canvas canvas, Stage stage) {
        this.canvas = canvas;
        this.stage = stage;


        canvas.getJf().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //移动地图的时候所有实体向相反方向移动
                //System.out.println(e.getKeyChar());
                char getchar = e.getKeyChar();
                if (getchar == 'd' || getchar == 'D') {
                    mapView.moveMap(-1);
                    stage.moveEntities(-1);
                    SoundControl.playSound("gravebutton.mp3");
                } else if (getchar == 'a' || getchar == 'A') {
                    mapView.moveMap(1);
                    stage.moveEntities(1);
                    SoundControl.playSound("gravebutton.mp3");
                }else if(getchar=='q'||getchar=='Q'){
                    StageSave s = new StageSave(stage);
                    try {
                        StageSave.serializeStage();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
}

package controllers;

import models.gamesystem.Stage;
import models.gamesystem.StageSave;
import views.Canvas;
import views.MapView;

public class GameController {
    //controller对象保有canvas对象,controller接受model的数据，使用canvas控制绘图
    private Canvas canvas;
    private Stage stage;
    private StageSave st;
    private MapView mapView;
    private MouseControl mouseControl;
    KeyBoardController keyBoardController;
    public void drawMap(Stage stage) {
        canvas.drawGame(stage);
    }

    //僵尸阵营时绘制倒计时
    public void drawCount(String time, int x, int y, int fontSize) {
        canvas.drawString(time, x, y, fontSize);
    }

    public GameController(Canvas canvas, Stage stage) {
        this.canvas = canvas;
        this.stage = stage;
        mouseControl = new MouseControl(canvas, stage);
        keyBoardController = new KeyBoardController(canvas, stage);
    }

    public void setCount(String s) {
        canvas.setZcount(s);
    }

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public MouseControl getMouseControl() {
        return mouseControl;
    }

    public KeyBoardController getKeyBoardController() {
        return keyBoardController;
    }
}

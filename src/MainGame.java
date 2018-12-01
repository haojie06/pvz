import controllers.GameController;
import models.gamesystem.Stage;
import models.gamesystem.StageSave;
import models.mission.Mission;
import views.Canvas;
import views.CardView;
import views.MenuView;

import java.awt.*;

public class MainGame {

    public static void main(String[] args) throws Exception {
        //游戏主进程 model
        Stage gameStage = new Stage();
       /* StageSave.deserializeStage();
        Stage gameStage=StageSave.newStage();*/
        //游戏主菜单

        MenuView m = new MenuView();
        m.createPanel();
        m.toShow();
        //在上面就要完成阵营/关卡/卡片的选择
        //用一个循环来等待用户准备完毕
        while (CardView.getIfStart() == 0) {
            System.out.println("");
        }
        Canvas canvas = new Canvas();
        //控制器(注意构造方法和后面的set方法有重复
        GameController controller = new GameController(canvas, gameStage);
        controller.getKeyBoardController().setMapView(canvas.getMapView());
        //绑定
        gameStage.setController(controller);
        canvas.setGameController(controller);
        //游戏开始 传入阵营和关卡
        gameStage.playGame(1, 2);

        //游戏菜单

        //关卡循环 Model中进行数据处理，View中进行绘制，开两个线程，使用controller进行通信
    }
}

package models.gamesystem;

import Interface.Getable;
import controllers.GameController;
import models.entities.Entity;
import models.effect.Effect;
import models.effect.EffectControl;
import models.events.DeadEventListener;
import models.events.MoveEvent;
import models.mission.Mission;
import models.mission.MissionControl;
import models.mission.PlantMission;
import models.entities.plant.Plant;
import models.entities.plant.PlantFactory;
import models.entities.weapon.bullets.Bullet;
import models.entities.zombie.Zombie;
import models.entities.zombie.ZombieFactory;
import views.CardView;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Stage 类 游戏发生的场所，所有逻辑处理都在这里被调用
public class Stage implements Serializable {
    private static final long serialVersionUID = 1L;

    int entityMoveLeftMax = 310; //地图最多向右移动310px
    int curMove = 0; //当前地图偏移
    int curMoveDirection = 0;//当前地图移动方向 0不动 1左移 -1右移
    int moveDistance = 30; //每次移动的距
    //玩家的陣營
    static int playerGroup = 2;
    JudgeMent judger;
    //控制器
    GameController controller;
    //僵尸列表
    private List<Zombie> allZombieList = new ArrayList<>();
    //植物列表
    private List<Plant> allPlantList = new ArrayList<>();
    //子弹列表
    private List<Bullet> allBuletList = new ArrayList<>();
    //可拾取对象列表
    private List<Getable> allGetablesList = new ArrayList<>();
    //游戏中的效果列表
    private List<Effect> allEffectList = new ArrayList<>();
    //当前地图
    private Map map = new Map(this);
    //清洁工（清除已经死亡的对象）
	public Graphics graphics;
    Cleaner cleaner;
    FallingObjectsGenerator generator;
    //僵尸工厂
    ZombieFactory zombieFactory = new ZombieFactory(this);
    //植物工厂
    PlantFactory plantFactory = new PlantFactory(this);
    private EffectControl effectControl;
    //游戏关卡
    private Mission mission;
    //经济系统
    EconomySystem ecoSystem = new EconomySystem();
    //分数系统
    ScoreSystem scoreSystem = new ScoreSystem();
    //当前玩家

    //初始化 生成僵尸/植物等等 目前测试用
    public void initial() {
        map.mapInitial();
        controller.drawMap(this);
        judger = new JudgeMent(this);
        judger.bindListener(new DeadEventListener());
        //新建清洁工对象
        cleaner = new Cleaner(this);
        generator = new FallingObjectsGenerator(this);
    }

    //游戏主循环逻辑
    public void playGame(int group, int missionnum) {
        //动画控制类
        AnimeControl animeControl = new AnimeControl();
        //播放背景音乐XXX 背景音乐在选择关卡后被重新加载
        SoundControl.playBackgoundMusic("Faster.mp3");
        //效果控制类，用来展示悬浮字等内容
        effectControl = new EffectControl(this);
        //游戏关卡控制器，玩家在这里选择阵营，关卡，出场植物
        MissionControl missionControl = new MissionControl(this);
        Mission mission = missionControl.loadMission(group, missionnum);
        //游戏初始化
        System.out.println("游戲初始化");
        initial();
        //游戏的主循环，为了游戏的菜单，选择关卡，重新开始游戏等功能，外层应该还有一个循环
        while (true) {
            //生成包含了所有实体的列表
            List<Entity> entities = new ArrayList<>();
            entities.addAll(getAllZombieList());
            entities.addAll(getAllPlantList());
            entities.addAll(getAllBuletList());
            mission.processControl();
            //依据阵营不同天上掉的东西也不一样
            generator.generateFallingObj(getPlayerGroup());
            for (int i = 0; i < entities.size(); i++) {
                Entity z = entities.get(i);
                z.notifyListener(new MoveEvent(z)); //先进行移动，之后进行攻击判断，如果可以攻击，攻击动作会覆盖移动动作
                //调用动画控制类的方法播放动画
                animeControl.animePlayer(z);
                //到达指定位置后死亡  ---测试用，之后写到Judgement类中判断
                judger.judgeBomb(z);
                judger.judgeDead(z);
                //死亡之后尸体清除的计时
            }

            for (int i = 0; i < CardView.getUpgrids().size(); i++) {
                int status = CardView.getUpgrids().get(i).clickstatus;
                if (status == 0) {
                    CardView.getUpgrids().get(i).time += 50;
                    //System.out.println(CardView.getUpgrids().get(i).time);
                }
                if (status == 0 && CardView.getUpgrids().get(i).getCool() == CardView.getUpgrids().get(i).time) {
                    CardView.getUpgrids().get(i).changeCardPic(graphics);
                    System.out.println("1");
                    CardView.getUpgrids().get(i).time = 0;
                }
                }
            cleaner.clearDeadZombie();
            cleaner.clearDeadPlant();
            cleaner.cleanBreakBullets();
            //更新地图状况
            map.updateMapStatus(this);
            //攻击判断
            judger.attackJudge();//攻击时应该停止移动。。\
            //命中判断
            judger.judgeHit();
            //更新效果
            effectControl.moveEffect();
            //更新视图
            controller.drawMap(this);
            //睡眠
            try {
                Thread.sleep(70);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            moveEntities(curMoveDirection);
        }
    }
    //数据处理完之后通过controller调用canvas进行绘图

    //绑定controller
    public void setController(GameController controller) {
        this.controller = controller;
    }

    public GameController getController() {
        return controller;
    }

    public List<Zombie> getAllZombieList() {
        return allZombieList;
    }

    public List<Entity> getAllEntity() {
        List<Entity> entities = new ArrayList<>();
        entities.addAll(allPlantList);
        entities.addAll(allZombieList);
        entities.addAll(allBuletList);
        return entities;
    }

    public List<Plant> getAllPlantList() {
        return allPlantList;
    }

    public Map getMap() {
        return map;
    }

    public List<Bullet> getAllBuletList() {
        return allBuletList;
    }

    public void setAllBuletList(List<Bullet> allBuletList) {
        this.allBuletList = allBuletList;
    }

    public List<Getable> getAllGetablesList() {
        return allGetablesList;
    }

    public void setAllGetablesList(List<Getable> allGetablesList) {
        this.allGetablesList = allGetablesList;
    }

    public void setAllZombieList(List<Zombie> allZombieList) {
        this.allZombieList = allZombieList;
    }

    public void setAllPlantList(List<Plant> allPlantList) {
        this.allPlantList = allPlantList;
    }

    public List<Effect> getAllEffectList() {
        return allEffectList;
    }

    public void setAllEffectList(List<Effect> allEffectList) {
        this.allEffectList = allEffectList;
    }

    public ZombieFactory getZombieFactory() {
        return zombieFactory;
    }

    public PlantFactory getPlantFactory() {
        return plantFactory;
    }

    public EffectControl getEffectControl() {
        return effectControl;
    }


    //在地图移动时，向背景图片移动的相反方向移动所有的实体 -1左移 0不动
    public void moveEntities(int direction) {
        this.curMoveDirection = direction;
        //指令向左
        if (direction == -1) {
            //移动后子弹射程不能变
            cleaner.setBulletDeadLine(cleaner.getBulletDeadLine() - moveDistance);
            curMove += direction * moveDistance;
            //向左移动至边缘
            if (curMove <= -1 * entityMoveLeftMax) {
                curMove = -1 * entityMoveLeftMax;
                this.curMoveDirection = 0;
            }
        }
        //右移回去
        else if (direction == 1) {
            cleaner.setBulletDeadLine(cleaner.getBulletDeadLine() + moveDistance);
            curMove += direction * moveDistance;
            if (curMove >= 0) {
                curMove = 0;
                this.curMoveDirection = 0;
            }
        }

        //对所有的实体进行移动
        for (Entity e : getAllEntity()) {
            e.positionX += curMoveDirection * moveDistance;
        }

        //对所有的单元格进行移动  终点线 这些都要移动
        for (int i = 0; i < map.getTotalRow(); i++) {
            for (int j = 0; j < map.getTotalColumn(); j++) {
                MapBlock mapBlock = map.getMapBlockList().get(i).get(j);
                mapBlock.setPostionX(mapBlock.getPostionX() + curMoveDirection * moveDistance);
            }
        }

    }


    public Mission getMission() {
        return mission;
    }

    public static int getPlayerGroup() {
        return playerGroup;
    }

    public static void setPlayerGroup(int j) {
        playerGroup = j;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public EconomySystem getEcoSystem() {
        return ecoSystem;
    }

    public ScoreSystem getScoreSystem() {
        return scoreSystem;
    }
}

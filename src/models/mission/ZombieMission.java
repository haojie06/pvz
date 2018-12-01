package models.mission;

import models.entities.zombie.ZombieFactory;
import models.events.GameOverEvent;
import models.gamesystem.SoundControl;
import models.gamesystem.Stage;
import models.entities.plant.PlantFactory;

//玩家选择僵尸阵营时...，有哪些僵尸直接在这里确定（手写三关generate）,在游戏的JUDGE中，如果所有植物被消灭或者有僵尸到达最左边的话，这一关胜利
//选择僵尸阵营时，破坏植物或者放置坟墓可以获得骷髅，使用骷髅召唤僵尸 （经济系统 + 传送门）
//请根据阵营设置天上的掉落物，以及玩家持有的卡牌 ，卡牌可放置区域
public class ZombieMission extends Mission {
    //关卡编号
    int ifGen = 0;
    int missionNum = 1;
    PlantFactory plantFactory;
    ZombieFactory zombieFactory;
    //僵尸关卡的倒计时，时间用尽还没过关则失败
    long curTime, maxTime;
    Stage stage;
    public ZombieMission(String missionName, Stage stage) {
        super(missionName);
        curTime = System.currentTimeMillis();
        //计时十分钟
        maxTime = curTime += 600000;
        plantFactory = stage.getPlantFactory();
        zombieFactory = stage.getZombieFactory();
        this.stage = stage;
        this.setGroup(2);
    }

    @Override
    public void processControl() {
        curTime = System.currentTimeMillis();
        long c = (maxTime - curTime) / 1000;
        String s = "倒计时" + c + "秒";
        stage.getController().setCount(s);
        if (curTime >= maxTime) {
            //僵尸失败了
            notifyListener(new GameOverEvent(stage, 2));
        }
        if (ifGen == 0) {
            ifGen = 1;
            //似乎..僵尸的关卡只用把植物放置好就行了
            if (missionNum == 1) {
                this.setTotalRow(5);
                SoundControl.playBackgoundMusic("Faster.mp3");
                //关卡背景图片
                this.setBackgroundPicSrc("src/resources/background/background1.png");
                //如果需要改变默认值
            /*
            this.setBackgroundMusicName();
            this.setBackgroundPicSrc();
            this.setTotalRow();
            */

                zombieFactory.genZombie("墓碑", 0, 14);
                zombieFactory.genZombie("墓碑", 1, 14);
                zombieFactory.genZombie("墓碑", 2, 14);
                for (int i = 0; i < getTotalRow(); i++) {
                    plantFactory.genPlant("机枪射手", i, 1);
                    plantFactory.genPlant("机枪射手", i, 2);
                    plantFactory.genPlant("卷心菜投手", i, 3);
                    plantFactory.genPlant("高坚果", i, 5);
                    plantFactory.genPlant("地刺", i, 6);
                    plantFactory.genPlant("土豆雷", i, 7);
                    plantFactory.genPlant("坚果墙", i, 8);
                }
            } else if (missionNum == 2) {
                this.setTotalRow(5);
                this.setBackgroundMusicName("Loonboon.mp3");
                //夜晚地图
                this.setBackgroundPicSrc("src/resources/background/background2.png");
                //如果需要改变默认值

                for (int i = 0; i < getTotalRow(); i++) {
                    plantFactory.genPlant("机枪射手", i, 1);
                    plantFactory.genPlant("机枪射手", i, 2);
                    plantFactory.genPlant("卷心菜投手", i, 3);
                    plantFactory.genPlant("高坚果", i, 5);
                    plantFactory.genPlant("食人花", i, 7);
                    plantFactory.genPlant("坚果墙", i, 8);
                }
            } else if (missionNum == 3) {
                //有六行的地圖
                this.setTotalRow(6);
                SoundControl.playBackgoundMusic("Mountains.mp3");
                //关卡背景图片
                this.setBackgroundPicSrc("src/resources/background/background3.png");
                //如果需要改变默认值

                for (int i = 0; i < getTotalRow(); i++) {
                    plantFactory.genPlant("卷心菜投手", i, 1);
                    plantFactory.genPlant("食人花", i, 2);
                    plantFactory.genPlant("食人花", i, 3);
                    plantFactory.genPlant("高坚果", i, 5);
                    plantFactory.genPlant("食人花", i, 7);
                    plantFactory.genPlant("高坚果", i, 8);
                }
            }
        }
    }

    public void generateMission(int missionNum) {
        //第一关
        if (missionNum == 1) {
            this.setTotalRow(5);

            SoundControl.playBackgoundMusic("Faster.mp3");
            //关卡背景图片
            this.setBackgroundPicSrc("src/resources/background/background1.png");
            //如果需要改变默认值
        } else if (missionNum == 2) {
            this.setTotalRow(5);
            this.setBackgroundMusicName("Loonboon.mp3");
            //夜晚地图
            this.setBackgroundPicSrc("src/resources/background/background2.png");
            //如果需要改变默认值
        } else if (missionNum == 3) {
            //有六行的地圖
            this.setTotalRow(6);
            SoundControl.playBackgoundMusic("Mountains.mp3");
            //关卡背景图片
            this.setBackgroundPicSrc("src/resources/background/background3.png");
            //如果需要改变默认值
        }
    }
}

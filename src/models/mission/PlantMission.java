package models.mission;

import models.events.Event;
import models.events.EventListener;
import models.events.MissionCompleteEvent;
import models.gamesystem.SoundControl;
import models.gamesystem.Stage;

import java.util.Random;
//植物的关卡对象包含内容 僵尸总数量，生成频率，僵尸种类，僵尸血量，尸潮波数以及出现时机
//植物阵营的流程，每一关有目的击杀僵尸数量，击杀僵尸会推进进度，进度每到达一定程度会触发一大波僵尸，最后还会
//出发“Final wave”，当进度到达一百或击杀数量达到MAX则关卡胜利，（提示玩家重新开始或者返回菜单或者退出游戏（
// 在这里还可以展示玩家名，玩家分数）当然这个界面不同阵营是不同的了）
//当僵尸到达最左边，出现“僵尸吃掉了你的脑子” Game Over,依旧出现相同的选择界面
public class PlantMission extends Mission {
    private Stage gameStage;
    //准备到开始的倒计时 以及僵尸总计数器
    int count = 150, zombieCount = 0;
    //每一轮循环生成僵尸的可能性，逐渐增加
    private int generatePossibility = 2, tempPossibility;
    //关卡中一共会出现多少僵尸,当前已经击杀了多少僵尸,”一大波僵尸有多少‘
    private double maxZombies = 100, killZombie = 0, largeWaveZombies = 10;
    //在进度条多少的时候出现“一大波僵尸” 以及递增多少 一大波僵尸出现多少，现在已经生成了多少, 是否还在生成这一波僵尸
    private int waveLine = 20, waveLineAdd = 30, waveZNum = 10, curWaveNum = 0, ifPrepareWave = 0, ifPrepareShowed = 0;
    //在进度多少的时候出现“最后一波”,最后一搏时候来了  最后一波要生成多少个
    private int finalWave = 90, ifFinalWaveComing = 0, finalWaveNum, ifFinalWaveShowed = 0;
    //进度条 以及进度事件是否处理 1--已经处理 0，没有处理
    private double maxProcess = 100, curProcess = 0, processEventHandle = 1, tempProcess = 0;
    //当前地图路径
    //生成强力僵尸的系数
    private double genetateCoefficient;
    private String mapImgSrc;
    Random flagPos = new Random();
    public PlantMission(String missionName, Stage stage) {
        super(missionName);
        this.gameStage = stage;
    }

    //生成僵尸的方法，生成所需的参数会随着关卡进度推进而改变。

    //ProcessControl 进度管理，依据当前进度做出不同的相应
    public void processControl() {
        //更新进度
        curProcess = (killZombie / maxZombies) * 100.0;
        if (curProcess != tempProcess) {
            System.out.println("进度改变: " + curProcess);
        }
        tempProcess = curProcess;
        if (curProcess == 0 && ifPrepareShowed == 0) {
            ifPrepareShowed = 1;
            //游戏开始,播放音效 Coming
            gameStage.getEffectControl().showPrepareGrow();
            processEventHandle = 0;
        }

        //游戏开始倒计时
        if (count >= 0) {
            count--;
        } else {
            if (processEventHandle == 0) {
                SoundControl.playSound("zombiecoming.mp3");
                gameStage.getZombieFactory().genZombie("旗手僵尸", flagPos.nextInt(6), 12);
                processEventHandle = 1;
            }
            if (curProcess >= waveLine) {
                //播放效果
                gameStage.getEffectControl().showLargeWave();
                System.out.println("一大波僵尸正在靠近中");
                //生成1-3个旗手僵尸
                gameStage.getZombieFactory().genZombie("旗手僵尸", flagPos.nextInt(6), 12);
                gameStage.getZombieFactory().genZombie("旗手僵尸", flagPos.nextInt(6), 12);
                gameStage.getZombieFactory().genZombie("旗手僵尸", flagPos.nextInt(6), 12);
                waveLine += waveLineAdd;
                generateALargeWaveZombies();
            }

            if (curProcess >= finalWave) {
                //最后一波，特效
                if (ifFinalWaveShowed == 0) {

                    System.out.println("最后一波");
                    gameStage.getEffectControl().showFinalWave();
                    gameStage.getZombieFactory().genZombie("旗手僵尸", flagPos.nextInt(6), 12);
                    gameStage.getZombieFactory().genZombie("旗手僵尸", flagPos.nextInt(6), 12);
                    gameStage.getZombieFactory().genZombie("旗手僵尸", flagPos.nextInt(6), 12);
                    ifFinalWaveShowed = 1;
                }
                if (ifFinalWaveComing == 0) {
                    generateFinalWaveZombies();
                    ifFinalWaveComing = 1;
                } else if (zombieCount >= maxZombies) {
                    generatePossibility = tempPossibility;
                    //通知时间监听器处理事件
                    notifyListener(new MissionCompleteEvent(gameStage));
                    System.out.println("过关！！！！！");
                    //需要添加事件处理（再来一次/下一关/退出游戏）
                }
            }

            generateZombies();
        }
    }

    public void generatePlantMission(int missionNum) {
        if (missionNum == 1) {
            //植物第一关 应该是最简单的一关
            //5行地圖
            this.setTotalRow(5);
            //关卡背景音乐
            SoundControl.playBackgoundMusic("Faster.mp3");
            //关卡背景图片
            this.setBackgroundPicSrc("src/resources/background/background1.png");
            //一共需要击杀多少僵尸
            this.setMaxZombies(100);
            //第一波僵尸在进度多少的时候出现以及出现的条件每出现一次递增多少
            //进度达到20（在总共一百只僵尸的情况下击杀20只出现第一波僵尸）
            this.setWaveLine(20);
            //下一波出现僵尸的时机 进度增加25
            this.setWaveLineAdd(25);
            //一波僵尸有多少只
            this.setWaveZNum(15);
            //进度达到多少的时候出现最后一波
            this.setFinalWave(90);
            //生成僵尸的几率,百分比,每次刷新会产生一个 0-100的随机数，小于这个概率就会生成新僵尸
            this.setGeneratePossibility(5);
            //生成僵尸的强度系数,系数越大，生成强力僵尸的可能性越高
            this.setGenetateCoefficient(1); //1--正常 确定是否生成的逻辑还需要完善 可能概率要换成千分之？
        } else if (missionNum == 2) {
            this.setTotalRow(5);
            SoundControl.playBackgoundMusic("Ultimate Battle.mp3");
            //夜间地图，当然使用的是白天的植物
            this.setBackgroundMusicName("Loonboon.mp3");
            //夜晚地图
            this.setBackgroundPicSrc("src/resources/background/background2.png");
            this.setMaxZombies(200);
            //第一波僵尸在进度多少的时候出现以及出现的条件每出现一次递增多少
            //进度达到20（在总共一百只僵尸的情况下击杀20只出现第一波僵尸）
            this.setWaveLine(20);
            //下一波出现僵尸的时机 进度增加25
            this.setWaveLineAdd(40);
            //一波僵尸有多少只
            this.setWaveZNum(20);
            //进度达到多少的时候出现最后一波
            this.setFinalWave(90);
            //生成僵尸的几率,百分比,每次刷新会产生一个 0-100的随机数，小于这个概率就会生成新僵尸
            this.setGeneratePossibility(10);
            this.setGenetateCoefficient(1.5);
        } else if (missionNum == 3) {
            this.setTotalRow(6);
            SoundControl.playBackgoundMusic("Mountains.mp3");
            //关卡背景图片
            this.setBackgroundPicSrc("src/resources/background/background3.png");
            //一共需要击杀多少僵尸
            this.setMaxZombies(100);
            //第一波僵尸在进度多少的时候出现以及出现的条件每出现一次递增多少
            //进度达到20（在总共一百只僵尸的情况下击杀20只出现第一波僵尸）
            this.setWaveLine(40);
            //下一波出现僵尸的时机 进度增加25
            this.setWaveLineAdd(40);
            //一波僵尸有多少只
            this.setWaveZNum(35);
            //进度达到多少的时候出现最后一波
            this.setFinalWave(90);
            //生成僵尸的几率,百分比,每次刷新会产生一个 0-100的随机数，小于这个概率就会生成新僵尸
            this.setGeneratePossibility(10);
            //生成僵尸的强度系数,系数越大，生成强力僵尸的可能性越高
            this.setGenetateCoefficient(2); //1--正常 确定是否生成的逻辑还需要完善 可能概率要换成千分之？
        }
    }

    public void generateZombies() {
        //一个循环判定一次
        Random random = new Random();
        int ifGenerateZombie = random.nextInt(100);
        // 5%的几率生成僵尸
        if (ifGenerateZombie < generatePossibility) {
            //生成一大波僵尸时进行计数
            zombieCount++;
            if (ifPrepareWave == 1) {
                curWaveNum++;
                //如果生成的僵尸已经满足
                if (curWaveNum == waveZNum) {
                    ifPrepareWave = 0;
                    curWaveNum = 0;
                    generatePossibility = tempPossibility;
                }
            }


            //生成的僵尸的坐标,只用确定行，列已经知道（在玩家控制植物的时候是否让僵尸从传送门中出来?）
            int col = 12, row;
            //注意！不是所有地图都是6行，确切来说只有含水池的地图达到了6行
            row = random.nextInt(this.getTotalRow());
            //僵尸随机生成的方法还有待修改
            //生成不同僵尸的概率，越强的僵尸生成概率越低(不同的关卡影响这个概率，后面的关卡强力僵尸概率变高/或者采取僵尸具有权重的方法来生成)
            int difZombiePosbillity = random.nextInt(100);
            if (difZombiePosbillity < 15){
                gameStage.getZombieFactory().genZombie("爆炸僵尸",row,col);
            }
            if (difZombiePosbillity >= 15 && difZombiePosbillity < 40 * genetateCoefficient) {
                gameStage.getZombieFactory().genZombie("运动员僵尸", row, col);
            } else if (difZombiePosbillity >= 60 && difZombiePosbillity < 100 * genetateCoefficient) {
                //生成普通僵尸
                gameStage.getZombieFactory().genZombie("普通僵尸", row, col);
            }
            if (difZombiePosbillity >= 50 && difZombiePosbillity <= 60 * genetateCoefficient) {
                gameStage.getZombieFactory().genZombie("小鬼僵尸", row, col);
            }
            if (difZombiePosbillity > 50 && difZombiePosbillity < 65 * genetateCoefficient) {
                gameStage.getZombieFactory().genZombie("铁桶僵尸", row, col - 1);
            }
            if (difZombiePosbillity > 65 && difZombiePosbillity < 75 * genetateCoefficient) {
                gameStage.getZombieFactory().genZombie("路障僵尸", row, col - 1);
            }
            if (difZombiePosbillity > 75 && difZombiePosbillity < 90 * genetateCoefficient) {
                gameStage.getZombieFactory().genZombie("撑杆跳僵尸", row, col);
            }

        }
    }


    //生成一大波僵尸,其实就是生成概率变高,达到一波的数量之后概率复原
    public void generateALargeWaveZombies() {
        //如果还没开始生成
        if (ifPrepareWave == 0) {
            ifPrepareWave = 1;
            tempPossibility = generatePossibility;
            generatePossibility = 50;

        }
    }

    //生成最后一波僵尸
    public void generateFinalWaveZombies() {
        System.out.println("生成最后一波僵尸");
        finalWaveNum = (int) (maxZombies - zombieCount);
        curWaveNum = 0;
        tempPossibility = generatePossibility;
        //百分百生成僵尸
        generatePossibility = 100;
    }

    public void addKillNum() {
        killZombie++;
        System.out.println(killZombie);
    }

    public int getGeneratePossibility() {
        return generatePossibility;
    }


    public void setGeneratePossibility(int generatePossibility) {
        this.generatePossibility = generatePossibility;
    }

    public int getTempPossibility() {
        return tempPossibility;
    }

    public void setTempPossibility(int tempPossibility) {
        this.tempPossibility = tempPossibility;
    }

    public double getMaxZombies() {
        return maxZombies;
    }

    public void setMaxZombies(double maxZombies) {
        this.maxZombies = maxZombies;
    }

    public double getKillZombie() {
        return killZombie;
    }

    public void setKillZombie(double killZombie) {
        this.killZombie = killZombie;
    }

    public double getLargeWaveZombies() {
        return largeWaveZombies;
    }

    public void setLargeWaveZombies(double largeWaveZombies) {
        this.largeWaveZombies = largeWaveZombies;
    }

    public int getWaveLine() {
        return waveLine;
    }

    public void setWaveLine(int waveLine) {
        this.waveLine = waveLine;
    }

    public int getWaveLineAdd() {
        return waveLineAdd;
    }

    public void setWaveLineAdd(int waveLineAdd) {
        this.waveLineAdd = waveLineAdd;
    }

    public int getWaveZNum() {
        return waveZNum;
    }

    public void setWaveZNum(int waveZNum) {
        this.waveZNum = waveZNum;
    }

    public int getCurWaveNum() {
        return curWaveNum;
    }

    public void setCurWaveNum(int curWaveNum) {
        this.curWaveNum = curWaveNum;
    }

    public int getFinalWave() {
        return finalWave;
    }

    public void setFinalWave(int finalWave) {
        this.finalWave = finalWave;
    }

    public int getFinalWaveNum() {
        return finalWaveNum;
    }

    public void setFinalWaveNum(int finalWaveNum) {
        this.finalWaveNum = finalWaveNum;
    }

    public String getMapImgSrc() {
        return mapImgSrc;
    }

    public void setMapImgSrc(String mapImgSrc) {
        this.mapImgSrc = mapImgSrc;
    }

    public double getGenetateCoefficient() {
        return genetateCoefficient;
    }

    public void setGenetateCoefficient(double genetateCoefficient) {
        this.genetateCoefficient = genetateCoefficient;
    }

}

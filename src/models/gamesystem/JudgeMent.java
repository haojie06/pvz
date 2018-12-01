package models.gamesystem;

import Interface.*;
import models.entities.Entity;
import models.events.*;
import models.entities.plant.Plant;
import models.entities.weapon.CurveLaucher;
import models.entities.weapon.Weapon;
import models.entities.weapon.bullets.Bullet;
import models.entities.weapon.bullets.LawnCleaner;
import models.entities.weapon.bullets.Skull;
import models.entities.weapon.bullets.Sunshine;
import models.entities.zombie.JackinTheBoxZombie;
import models.entities.zombie.Zombie;

import java.util.ArrayList;
import java.util.List;


//判断器，用来判断所有对象的状态，如击中，死亡...,会产生事件,地图以水平格分划
//所有的判断都写到一起了...
public class JudgeMent implements EventSource {
    private List<EventListener> listeners = new ArrayList<>();
    private Stage judgeStage;

    public JudgeMent(Stage judgeStage) {
        this.judgeStage = judgeStage;
        //绑定各个监听器
        this.bindListener(new DeadEventListener());
        this.bindListener(new AttackEventListener());
        this.bindListener(new HitEventListener());
        this.bindListener(new BombEventListener());
        this.bindListener(new ClickGetableEventListener());
        this.bindListener(new GameOverEventListener());
    }


    //测试 单元格内事件判断 点击了哪一个单元格 并返回该单元格
    public MapBlock clickWhich(int x, int y) {
        List<List<MapBlock>> blocks = judgeStage.getMap().getMapBlockList();
        //遍历单元格
        for (int i = 0; i < blocks.size(); i++) {
            List<MapBlock> blockList = blocks.get(i);
            for (int j = 0; j < blockList.size(); j++) {
                MapBlock block = blockList.get(j);
                if (x > block.getPostionX() && x < block.getPostionX() + 80)
                    if (y > block.getPostionY() && y < block.getPostionY() + 90) {
                        System.out.println("你点击了" + block.getRow() + "行" + block.getColumn() + "列");
                        //判断这个格子是否包含阳光金币之类的，Judgement内进行处理，传入鼠标坐标
                        return block;
                    }
            }

        }
        return null;
    }

    //攻击判定
    public void attackJudge() {
        //遍历单元格，找出位于同一个单元格中的僵尸和植物，判断攻击范围，若在范围内则产生攻击事件，部分植物（豌豆射手）攻击范围无限
        List<List<MapBlock>> blockLists = judgeStage.getMap().getMapBlockList();
        for (int i = 0; i < blockLists.size(); i++) {
            List<MapBlock> rowBlocks = blockLists.get(i);
            for (int j = 0; j < rowBlocks.size(); j++) {
                MapBlock mapBlock = rowBlocks.get(j);
                if (mapBlock.containsBothZombieNPlant()) {
                    //具体判断是否可以攻击 产生攻击事件，停止移动。。。
                    // 僵尸和植物都会产生攻击事件，攻击单元格中列表的第一个就好了，如果是范围伤害（土豆雷）则 整个列表都会受到伤害
                    //先遍历当前单元格中的僵尸  !!需要改进，因为还有植物，障碍等多种实体
                    for (Entity e : mapBlock.zombies) {
                        Zombie z = (Zombie) e;
                        List<Entity> attackTar = new ArrayList<>();
                        //僵尸活着才进行攻击
                        if (z.getIfAlive() == 2) {
                            if (z.getWeapon().getCurCoolTime() == 0) {
                                //用来存放攻击范围内的实体
                                for (Plant p : mapBlock.plants) {
                                    int attackField1 = z.getPositionX() + z.getWeapon().getAttackField();
                                    //调转方向做准备？
                                    int attackField2 = z.getBoxFirPositionX() - z.getWeapon().getAttackField();
                                    //僵尸从右向左触碰到植物右边的碰撞框
                                    if (attackField2 <= (p.getBoxSecPositionX()) && (!p.getName().equals("Spikeweed"))) {
                                        attackTar.add(p);
                                        if (z.getName().equals("JackinTheBoxZombie")){
                                            ((JackinTheBoxZombie)z).addCount();
                                        }
                                    }
                                }
                            } else {
                                z.getWeapon().coolDown();
                            }
                            if (attackTar.size() != 0) {
                                //将攻击通知给Listener
                                AttackEvent attackEvent = new AttackEvent(z, z.getWeapon(), attackTar);
                                this.notifyListener(attackEvent);
                            }
                        }
                    }
                }
            }
        }

        //僵尸攻击的执行（与上面的不同/尝试取代） 暂时只用于墓碑 以及吃掉脑子的判断
        for (int i = 0; i < judgeStage.getAllZombieList().size(); i++) {
            Zombie z = judgeStage.getAllZombieList().get(i);
            if (z.getWeapon().getAttackField() == -1) {
                z.getWeapon().attack(z, judgeStage);
            }

            if (z.getBoxFirPositionX() <= 0) {
                //！！需要判断游戏模式，只有植物关卡才会失败
                System.out.println("僵尸吃掉了你的脑子");
                //同样的事件在不同的阵营选择下是不同的结局
                Event gameEvent;
                if (judgeStage.getMission().getGroup() == 1) {
                    //植物阵营失败
                    gameEvent = new GameOverEvent(judgeStage, 1);
                } else {
                    //僵尸阵营过关（另外写一个事件类）
                    gameEvent = new MissionCompleteEvent(judgeStage);
                }
                notifyListener(gameEvent);
                z.setIfAlive(0);
                judgeStage.getAllZombieList().remove(z);
                judgeStage.getMap().getMapBlockList().get(z.getRow()).get(z.getCol()).removeEntity(z);
            }
        }

        //并非遍历单元格的形式
        //持有攻击距离为-1 (无限)的植物攻击判定（只判定攻击冷却与否） 豆子发射之类的
        for (Plant p : judgeStage.getAllPlantList()) {
            if (p.getWeapon() != null) {
                p.getWeapon().coolDown();
                //植物持有武器的攻击不需要判断周围是否有僵尸时
                if (p.getWeapon().getAttackField() == -1) {
                    //冷却完毕
                    if (p.getWeapon().getCurCoolTime() <= 0) {
                        p.getWeapon().attack(p, judgeStage);
                    }
                }
                //若攻击距离并不是无限
                else {
                    //如果植物持有的武器是激发型的 爆炸物或者近战
                    if (p instanceof Trigger && p.getIfAlive() == 2) {
                        //激发型攻击植物 且被激活
                        if (((Trigger) p).ifActivated()) {
                            Weapon w = p.getWeapon();
                            //冷却
                            //范围内有僵尸
                            MapBlock mapBlock = judgeStage.getMap().getMapBlockList().get(p.getRow()).get(p.getCol());
                            if (mapBlock.containsBothZombieNPlant())
                                if (p.getWeapon().getCurCoolTime() <= 0) {
                                    p.getWeapon().attack(mapBlock.getZombieEntity());
                                    if (p instanceof Explosive) {
                                        this.notifyListener(new BombEvent(p, judgeStage));
                                    } else {
                                        //激发的不是爆炸物
                                    }
                                }
                        }
                    } else if (p instanceof RemoteShooter) {
                        //当前游戏植物暂时只能攻击一行，远程射手（非无限攻击距离从当前行当前列开始向最后一列搜索攻击最先找到的僵尸
                        if (p.getWeapon().getCurCoolTime() <= 0) {
                            List<MapBlock> blocks = judgeStage.getMap().getMapBlockList().get(p.getRow());
                            int startIndex = p.getCol(), endIndex;
                            endIndex = startIndex + p.getWeapon().getAttackField();
                            if (endIndex > judgeStage.getMap().getTotalColumn()) {
                                endIndex = judgeStage.getMap().getTotalColumn();
                            }
                            for (; startIndex < endIndex; startIndex++) {
                                if (blocks.get(startIndex).zombies.size() != 0) {
                                    //攻击该单元格中的僵尸，退出循环
                                    ((CurveLaucher) p.getWeapon()).attack(blocks.get(startIndex).zombieEntity, judgeStage);
                                    break;
                                }
                            }
                        } else {
                            p.getWeapon().coolDown();
                        }
                    }
                    //植物是其它的射程并非无限的远程攻击植物
                }
            }
        }
    }

    //命中判定
    public void judgeHit() {
        List<List<MapBlock>> blockLists = judgeStage.getMap().mapBlockList;
        for (int row = 0; row < blockLists.size(); row++) {
            List<MapBlock> blocks = blockLists.get(row);
            for (int col = 0; col < blocks.size(); col++) {
                MapBlock block = blocks.get(col);
                if (block.containsBothZombieNBullet()) {
                    //一次判断两个格子（只要不是最后一格）
                    int maxBlock = judgeStage.getMap().getTotalColumn();
                    List<Bullet> bullets = new ArrayList<>();
                    List<Zombie> zombies = new ArrayList<>();
                    zombies.addAll(block.getZombies());
                    bullets.addAll(block.getBullets());
                    //用于处理植物和僵尸同单元格而子弹直接发射到僵尸背后的情况
                    if (block.getColumn() != (maxBlock - 1)) {
                        MapBlock secondBlock = blocks.get(col + 1);
                        zombies.addAll(secondBlock.getZombies());
                        bullets.addAll(secondBlock.getBullets());
                    }
                    //单元格中既有子弹也有僵尸 判断当前格子以及下一格 !!骷髅头非常特殊，不进行判断
                    //攻击范围
                    for (Bullet b : bullets) {
                        if ((!b.getName().equals("Skull")) && (!b.getName().equals("Sunshine"))) {
                            List<Entity> hitList = new ArrayList<>();
                            for (Zombie z : zombies) {
                                int bulletRightX = b.getBoxSecPositionX();
                                int zombieLeftX = z.getBoxFirPositionX();
                                int zombieRightX = z.getBoxSecPositionX();
                                if ((bulletRightX >= (zombieLeftX) && b.getIfAlive() == 2 && z.getIfAlive() == 2)) {
                                    if (b.getName().equals("LawnCleaner") && ((LawnCleaner) b).getIfActivated() == 0) {
                                        ((LawnCleaner) b).activate();
                                    }
                                    hitList.add(z);//满足击中条件
                                }
                            }
                            //System.out.println("外部" + hitList.size());
                            this.notifyListener(new HitEvent(b, hitList));
                        }
                    }
                }
            }
        }
    }

    //植物死亡后恢复同一单元格内僵尸的移动
    public void judgeDead(Entity e) {
        //血量为0死亡
        if (e.getHealth() <= 0 && e instanceof Plant) {
            this.notifyListener(new DeadEvent(e, "植物死亡", judgeStage));
        } else if (e.getHealth() <= 0 && e instanceof Zombie) {
            this.notifyListener(new DeadEvent(e, "僵尸死亡", judgeStage));
        }
    }

    //判断是否爆炸
    public void judgeBomb(Entity e) {
        //是樱桃炸弹
        if (e instanceof Explosive) {
            //定时爆炸的
            if (e instanceof Timer) {
                if (!e.getName().equals("JackinTheBoxZombie"))
                {e.setHealth(e.getHealth() - 1);}
                if (e.getHealth() <= 0 && e.getIfAlive() == 2) {
                    //播放爆炸音效
                    this.notifyListener(new BombEvent(e, judgeStage));
                }
            } else if (e instanceof Trigger) {
                //调用trigger方法 如土豆雷埋下一段时间后才激活，该类的攻击直接在攻击判断中进行
            }
        }

    }

    @Override
    public void bindListener(EventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void notifyListener(Event event) {
        for (EventListener l : listeners) {
            l.eventHandle(event);
        }
    }

    //点击单元格杀死僵尸---测试用
    public void clickKillZombie(int row, int col) {
        MapBlock mapBlock = judgeStage.getMap().getMapBlockList().get(row).get(col);
        System.out.println("开始消灭" + mapBlock.zombies.size() + " " + row + col);
        for (Zombie z : mapBlock.zombies) {
            DeadEvent deadEvent = new DeadEvent(z, "僵尸死亡", judgeStage);
            this.notifyListener(deadEvent);
        }
    }

    public void clickGetJudge(int x, int y, MapBlock mapBlock) {
        List<Getable> getables = mapBlock.getableList;
        for (Getable g : getables) {
            //阳光的处理
            if (g instanceof Sunshine) {
                Sunshine sunshine = (Sunshine) g;
                if (x >= sunshine.getBoxFirPositionX() && x <= sunshine.getBoxSecPositionX() && y >= sunshine.getBoxFirPositionY() && y <= sunshine.getBoxSecPositionY()) {
                    //太阳移动到 381，36 图标位置 无论在什么位置都20次移动完毕
                    this.notifyListener(new ClickGetableEvent(sunshine));
                }
            } else if (g instanceof Skull) {
                Skull skull = (Skull) g;
                if (x >= skull.getBoxFirPositionX() && x <= skull.getBoxSecPositionX() && y >= skull.getBoxFirPositionY() && y <= skull.getBoxSecPositionY()) {
                    this.notifyListener(new ClickGetableEvent(skull));
                }

            }
        }
    }


}

package models.gamesystem;

import Interface.Explosive;
import models.entities.plant.Plant;
import models.entities.weapon.bullets.Bullet;
import models.entities.zombie.Zombie;

import java.util.List;

public class Cleaner {
    //清洁工，清除不需要的东西（当对象ifAlive==0时彻底清除）
    //清除僵尸实体
    private Stage cleanStage;
    private int bulletDeadLine = 1000;
    public Cleaner(Stage cleanStage) {
        this.cleanStage = cleanStage;
    }

    public void clearDeadZombie() {
        List<Zombie> zombies = cleanStage.getAllZombieList();
        for (int i = 0; i < zombies.size(); i++) {
            Zombie z = zombies.get(i);
                if (z.getIfAlive() == 1) {
                    z.addDeadTime();
                    if (z.getDeadTime() >= z.getMaxDeadTime()) {
                        z.setIfAlive(0);
                    }
                }
                if (z.getIfAlive() == 0) {
                    cleanStage.getAllZombieList().remove(z);
                    cleanStage.getMap().getMapBlockList().get(z.getRow()).get(z.getCol()).removeEntity(z);
                    //System.out.println("清除僵尸");
                }
        }
    }

    public void clearDeadPlant() {
        List<Plant> plants = cleanStage.getAllPlantList();
        for (int i = 0; i < plants.size(); i++) {
            //植物直接清除了一些爆炸物
            Plant p = plants.get(i);
            //触发死亡事件
            if (p.getIfAlive() == 1) {
                if (!(p instanceof Explosive)) {
                    p.setIfAlive(0);
                } else {
                    p.addDeadTime();
                    if (p.getDeadTime() >= p.getMaxDeadTime()) {
                        p.setIfAlive(0);
                    }
                }

                if (p.getIfAlive() == 0) {
                    cleanStage.getMap().getMapBlockList().get(p.getRow()).get(p.getCol()).removeEntity(p);
                    cleanStage.getAllPlantList().remove(p);
                }
            }
        }
    }

    public void cleanBreakBullets() {
        List<Bullet> bullets = cleanStage.getAllBuletList();
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            //已经破碎
            if (bullet.getIfAlive() == 1) {
                bullet.addDeadTime();
            }
            if (bullet.getDeadTime() >= bullet.getMaxDeadTime() || bullet.getIfAlive() == 0) {
                bullets.remove(bullet);
                cleanStage.getMap().getMapBlockList().get(bullet.getRow()).get(bullet.getCol()).removeEntity(bullet);
            }

            //飞出边界的bullet也要销毁 这个值要根据地图移动修改
            if (bullet.getPositionX() >= bulletDeadLine) {
                if (!bullet.getName().equals("Skull")) {
                    bullets.remove(bullet);
                    cleanStage.getMap().getMapBlockList().get(bullet.getRow()).get(10).removeEntity(bullet);
                    cleanStage.getMap().getMapBlockList().get(bullet.getRow()).get(9).removeEntity(bullet);

                }
            }
        }
    }

    public int getBulletDeadLine() {
        return bulletDeadLine;
    }

    public void setBulletDeadLine(int bulletDeadLine) {
        this.bulletDeadLine = bulletDeadLine;
    }
}

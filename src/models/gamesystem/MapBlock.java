package models.gamesystem;

import Interface.Getable;
import models.entities.Entity;
import models.entities.plant.Plant;
import models.entities.weapon.bullets.Bullet;
import models.entities.zombie.Zombie;

import java.util.ArrayList;
import java.util.List;

//坐标格类，单元格
//作为Map的成员存在,二维List中储存,分别代表行/列
//在地图中,横向十个格子 包括推车和草地,纵向6行 横向从图片的 176像素开始画线,纵向从 90像素开始
public class MapBlock {
    //单元格宽度-80像素 高度 90
    private int width = 80, height = 90;
    //xy代表单元格左上角点的坐标, row代表行数 1,2,3,4,5,6 col代表列数 1--10
    private int postionX, postionY, row, column;
    //单元格内的僵尸
    boolean hasPlant;
    List<Zombie> zombies = new ArrayList<>();
    List<Entity> zombieEntity = new ArrayList<>();
    //单元格内的子弹(豌豆之类的东西)
    List<Bullet> bullets = new ArrayList<>();
    //单元格内的植物
    List<Plant> plants = new ArrayList<>();
    List<Entity> plantEntity = new ArrayList<>();
    //单元格内可获得物品列表
    List<Getable> getableList = new ArrayList<>();

//单元格内的障碍


    public MapBlock(int postionX, int postionY, int row, int col) {
        this.postionX = postionX;
        this.postionY = postionY;
        this.column = col;
        this.row = row;
        hasPlant=false;
    }

    //单元格内判断方法 同一个单元格内是否有子弹和僵尸
    public Boolean containsBothZombieNBullet() {
        if (zombies.size() > 0 && bullets.size() > 0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    //同一个单元格是否有植物和僵尸
    public Boolean containsBothZombieNPlant() {
        if (zombies.size() > 0 && plants.size() > 0) {
            return Boolean.TRUE;
        }
        else
            return Boolean.FALSE;
    }

    //是否包含可以获得的物品
    public Boolean containGetable() {
        if (this.getableList.size() != 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    public void changeStatus(){
        if(this.plants.size()!=0)
            setHasPlant(true);
        else
            setHasPlant(false);
    }

    //注意,是否会重复添加?
    public void addEntity(Entity entity) {
        if (entity instanceof Getable) {
            {
                Getable getableEntity = (Getable) entity;
                if (!getableList.contains(getableEntity)) {
                    this.getableList.add((Getable) entity);
                }
            }
        }
        if (entity instanceof Zombie && (!zombies.contains((Zombie) entity))) {
            //System.out.println("添加僵尸" + row + column + "坐标" + entity.getPositionX());
            zombies.add((Zombie) entity);
            zombieEntity.add(entity);
        } else if (entity instanceof Plant && (!plants.contains((Plant) entity))) {
            plants.add((Plant) entity);
            //  System.out.println("添加植物" + row + column + "坐标" + entity.getPositionX());
        } else if (entity instanceof Bullet && (!bullets.contains((Bullet) entity))) {
            bullets.add((Bullet) entity);
        }
    }

    //从单元格中移除实体的方法
    public void removeEntity(Entity entity) {
        if (entity instanceof Getable && getableList.contains((Getable) entity)) {
            //System.out.println("移除可拾取对象");
            getableList.remove((Getable) entity);
        }

        if (entity instanceof Zombie && zombies.contains((Zombie) entity)) {
            //System.out.println("移除僵尸" + row + column + "坐标" + entity.getPositionX());
            zombies.remove((Zombie) entity);
        } else if (entity instanceof Plant && plants.contains((Plant) entity)) {
            plants.remove((Plant) entity);
        } else if (entity instanceof Bullet) {
            bullets.remove((Bullet) entity);
        }
    }

    public int getPostionX() {
        return postionX;
    }

    public void setPostionX(int postionX) {
        this.postionX = postionX;
    }

    public int getPostionY() {
        return postionY;
    }

    public void setPostionY(int postionY) {
        this.postionY = postionY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getWidth() {
        return width;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public List<Entity> getZombieEntity() {
        return zombieEntity;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Plant> getPlants() {
        return plants;
    }
    public boolean isHasPlant() {
        return hasPlant;
    }

    public void setHasPlant(boolean hasPlant) {
        this.hasPlant = hasPlant;
    }

    public List<Entity> getPlantEntity() {
        for (Plant p : plants){
            plantEntity.add((Entity)p);
        }
        return plantEntity;
    }

    public List<Getable> getGetableList() {
        return getableList;
    }
}

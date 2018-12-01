package models.events;

import models.gamesystem.AnimeControl;
import models.gamesystem.SoundControl;
import models.entities.weapon.bullets.Bullet;
import models.entities.Entity;

import java.util.List;

//子弹命中后的处理，除草车被当作特殊子弹处理了，不会破碎，而是在超出坐标后被清除
public class HitEventListener extends EventListener {
    @Override
    public void eventHandle(Event event) {
        if (event.getEventName().equals("命中事件")) {
            //销毁子弹，造成伤害
            HitEvent hitEvent = (HitEvent) event;
            Bullet bullet = hitEvent.getBullet();
            if (bullet.getIfAlive() == 2) {//子弹还没被破坏攻击才有效
                List<Entity> entities = hitEvent.getEntities();
                if (entities.size() != 0) {
                    //除草机不会被破坏
                    if (!bullet.getName().equals("LawnCleaner")) {
                        bullet.setIfAlive(1);
                        AnimeControl animeControl = new AnimeControl();
                        animeControl.animeChange(bullet, "break");
                        bullet.stopMove();

                    }
                    //对实体造成伤害
                    int times = (bullet.getAttackNum() > entities.size()) ? bullet.getAttackNum() : entities.size();
                    for (int j = 0; j < times; j++) {
                        Entity e = entities.get(j);
                        //播放击中音效-根据子弹不同声音不同--有时间再找合适的了.. 子弹的声音和击中的声音 目前只有被击中的声音
                        if (bullet.getName().equals("Beans")) {
                            SoundControl.playSound(e.getHitSoundName());
                            SoundControl.playSound(bullet.getHitSoundName());
                        } else if (bullet.getName().equals("CabbageBullet")) {
                            SoundControl.playSound(e.getHitSoundName());
                            SoundControl.playSound(bullet.getHitSoundName());
                        }
                        entities.get(j).setHealth(entities.get(j).getHealth() - bullet.getDamage());
                    }
                }
            }
        }
    }
}



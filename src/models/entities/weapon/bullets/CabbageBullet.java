package models.entities.weapon.bullets;

public class CabbageBullet extends CurveBullets {
    public CabbageBullet(String name, int speed, int damage, int positionX, int positionY, int launchX, int targetX, int targetSpeed, int directionX, int row) {
        super(name, 0, positionX, positionY, launchX, targetX, targetSpeed, damage, directionX, row);
        loadMotion(1, 1);
        this.setBoxPadding(10, 10, 60, 60);
    }

    @Override
    public void stopMove() {
        xSpeed = 0;
        ySpeed = 0;
    }
}

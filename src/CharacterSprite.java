import java.awt.*;

public class CharacterSprite extends DynamicSprite {

    private int lifePoints;

    private boolean immunity;
    private long lastStartOfImmunity;

    public long getLastStartOfImmunity() {
        return lastStartOfImmunity;
    }

    public void setLastStartOfImmunity(long lastStartOfImmunity) {
        this.lastStartOfImmunity = lastStartOfImmunity;
    }

    public boolean getImmunity() {
        return immunity;
    }

    public void setImmunity(boolean immunity) {
        this.immunity = immunity;
    }

    public CharacterSprite(double x, double y, Image image, double width, double height, double speed, boolean isIntangible, int lifePoints) {
        super(x, y, image, width, height, speed, isIntangible);
        this.lifePoints = lifePoints;

    }

    public int getLifePoints() {
        return lifePoints;
    }

    private void death() {

    }

    public void setLifePoints(int lifePoints) {
        if (!immunity) {
            if (lifePoints <= 0) {
                this.lifePoints = 0;
                this.death();
            } else {
                this.lifePoints = lifePoints;
            }
        }
    }
}

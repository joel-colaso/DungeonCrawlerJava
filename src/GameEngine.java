import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    CharacterSprite hero;
    ArrayList<CharacterSprite> enemies = new ArrayList<CharacterSprite>();

    long immunityDurationInMilliseconds = 2000;
    long lastStartOfImmunity;

    int currentLevel;

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void addToEnemiesList(CharacterSprite enemy){
        if (!this.enemies.contains(enemy)){
            this.enemies.add(enemy);
        }
    }

    public void addToEnemiesList(ArrayList<CharacterSprite> newEnemies){
        if (!this.enemies.contains(newEnemies)){
            this.enemies.addAll(newEnemies);
        }
    }

    public GameEngine(CharacterSprite hero) {
        this.hero = hero;
    }


    private void moveEnemyTowardsHero(CharacterSprite enemy) {
        if (Math.abs(hero.x - enemy.x) > Math.abs(hero.y - enemy.y)) {
            if (hero.x > enemy.x) {
                enemy.setDirection(Direction.EAST);
            } else {
                enemy.setDirection(Direction.WEST);
            }
        } else {
            if (hero.y > enemy.y) {
                enemy.setDirection(Direction.SOUTH);
            } else {
                enemy.setDirection(Direction.NORTH);
            }
        }
        ArrayList<Sprite> environment = new ArrayList<>();
    }

    @Override
    public void update() {
        //System.out.println(hero.getImmunity());
        //System.out.println(hero.getLifePoints());

        long now = System.currentTimeMillis();
        if ((now - lastStartOfImmunity) > immunityDurationInMilliseconds) {
            hero.setImmunity(false);
        }

        for (CharacterSprite enemy : enemies) {
            moveEnemyTowardsHero(enemy);

            if (
                    (enemy.x + enemy.width > hero.x) && // horizontal
                            (enemy.x < hero.x + hero.width) &&
                            (enemy.y + enemy.height > hero.y) && // vertical
                            (enemy.y < hero.y + hero.height)
            ) {
                // Collision detected

                hero.setLifePoints(hero.getLifePoints() - 1);
                if (hero.getLifePoints() <= 0) {
                    currentLevel = -1;
                }

                if(!hero.getImmunity()){
                    this.lastStartOfImmunity = System.currentTimeMillis();
                    hero.setImmunity(true);
                }


            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

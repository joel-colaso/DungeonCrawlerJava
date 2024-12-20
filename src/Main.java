import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(800,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        CharacterSprite hero = new CharacterSprite(100,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),
                48,50, 7.5, false, 5);

        CharacterSprite enemy = new CharacterSprite(650,100,
                ImageIO.read(new File("./img/redEnemyTileSheetLowRes.png")),
                48,50, 5, false, 5);

        CharacterSprite enemy2 = new CharacterSprite(650,375,
                ImageIO.read(new File("./img/redEnemyTileSheetLowRes.png")),
                48,50, 5, false, 5);

        CharacterSprite ghost = new CharacterSprite(200,200,
                ImageIO.read(new File("./img/ghostEnemyTileSheet.png")),
                48,50, 3, true, 5);

        gameEngine = new GameEngine(hero);
        gameEngine.setCurrentLevel(1);
        renderEngine = new RenderEngine(displayZoneFrame, gameEngine);
        physicEngine = new PhysicEngine();

        gameEngine.addToEnemiesList(enemy);
        gameEngine.addToEnemiesList(enemy2);
        gameEngine.addToEnemiesList(ghost);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level1 = new Playground("./data/level1.txt");
        Playground level2 = new Playground("./data/level2.txt");

        renderEngine.addToRenderList(level2.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);

        renderEngine.addToRenderList(enemy);
        physicEngine.addToMovingSpriteList(enemy);

        renderEngine.addToRenderList(enemy2);
        physicEngine.addToMovingSpriteList(enemy2);

        renderEngine.addToRenderList(ghost);
        physicEngine.addToMovingSpriteList(ghost);

        physicEngine.setEnvironment(level2.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }
}

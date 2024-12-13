import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;
    private final GameEngine gameEngine;

    public RenderEngine(JFrame jFrame, GameEngine gameEngine) {
        renderList = new ArrayList<>();
        this.gameEngine = gameEngine;
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    public void removeFromRenderList(Displayable displayable){
        if (renderList.contains(displayable)){
            renderList.remove(displayable);
        }
    }

    public void removeFromRenderList(ArrayList<Displayable> displayable){
        for (Displayable d : renderList){
            if (renderList.contains(d)){
                renderList.remove(d);
            }
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (gameEngine.getCurrentLevel() >= 0) {


            // game objects
            for (Displayable renderObject : renderList) {
                renderObject.draw(g);
            }

            for (int i = 0; i < gameEngine.hero.getLifePoints(); i++) {
                try {
                    // too hardcoded but works for this application
                    g.drawImage(ImageIO.read(new File("./img/heart.png")), 20 + i * 40, 20, null);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (gameEngine.getCurrentLevel() == -1) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            String message = "GAME OVER";
            FontMetrics metrics = g.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(message)) / 2;
            int y = getHeight() / 2;
            g.drawString(message, x, y);

            g.setColor(Color.ORANGE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String message2 = "Thanks for playing!";
            FontMetrics metrics2 = g.getFontMetrics();
            int x2 = (getWidth() - metrics2.stringWidth(message2)) / 2;
            int y2 = (getHeight() / 10)*6;
            g.drawString(message2, x2, y2);

        } else if (gameEngine.getCurrentLevel() == -2) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            String message = "YOU WIN";
            FontMetrics metrics = g.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(message)) / 2;
            int y = getHeight() / 2;

            g.drawString(message, x, y);
            g.setColor(Color.ORANGE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String message2 = "Thanks for playing!";
            FontMetrics metrics2 = g.getFontMetrics();
            int x2 = (getWidth() - metrics2.stringWidth(message2)) / 2;
            int y2 = (getHeight() / 10)*6;
            g.drawString(message2, x2, y2);
        }
    }

    @Override
    public void update(){
        this.repaint();
    }
}

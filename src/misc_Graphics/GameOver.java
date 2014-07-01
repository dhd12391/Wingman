/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc_Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
/**
 *
 * @author Chidi
 */
public class GameOver {
    Image gameOverImg;
    
    public GameOver(Image gameOverImg){
        this.gameOverImg = gameOverImg;
    }
    
    public void draw(Graphics g, int x, int y, ImageObserver obs){
        g.drawImage(gameOverImg, x, y, obs);
    }
    
}

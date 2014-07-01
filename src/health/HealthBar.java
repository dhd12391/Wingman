/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author Chidi
 */
public class HealthBar {
    Image health[] = new Image[4];
    public HealthBar(Image health[]){
        for(int i = 0; i<4; i++){
            this.health[i] = health[i];
        }
    }
    
    public void draw(int healthPoints, Graphics g, int x, int y, ImageObserver obs){
        if(healthPoints != 0){
            g.drawImage(health[healthPoints-1], x, y, obs);
        }
    }
    
}

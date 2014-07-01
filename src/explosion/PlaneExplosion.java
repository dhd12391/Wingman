/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explosion;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import wingman.gm1942;

/**
 *
 * @author Chidi
 */
public class PlaneExplosion {
    Image explosion[] = new Image[6];
    
    public PlaneExplosion(Image explosionImg[]){
        for(int i = 0; i <= 5; i++){
            explosion[i] = explosionImg[i];
        }
    }
    
    public void playSound(){
        gm1942.playSound("Resources/snd_explosion2.wav", false);        
    }
    
    public void draw(Graphics g, int boomCount, int x, int y, ImageObserver obs){
        if(boomCount == 0){
            playSound();
        }
        
        if(boomCount <= 5){
            g.drawImage(explosion[boomCount], x, y, obs);
        }
    }    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
/**
 *
 * @author Chidi
 */
public class Life {
    Image lives[] = new Image[5];   //max 5 lives
    
    public Life(Image lifeImg){
        for(int i = 0; i< 5; i++){  
            lives[i] = lifeImg;
        }
    }
    
    public void draw(int numberLivesLeft, Graphics g, int x, int y, ImageObserver obs){
        if(numberLivesLeft > 5){
            numberLivesLeft = 5;        //default 3 lives
        }
        for(int i = 0; i < numberLivesLeft; i++){
            g.drawImage(lives[i], x + (i*lives[i].getWidth(obs)+ 8), y, obs);
        }
    }
    
}

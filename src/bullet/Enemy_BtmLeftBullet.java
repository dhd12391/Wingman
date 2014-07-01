/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bullet;

import java.awt.Image;

/**
 *
 * @author Chidi
 */
public class Enemy_BtmLeftBullet extends Enemy_Bullet{
  
    public Enemy_BtmLeftBullet(Image img, int x, int y, int speed){
        super(img,x,y,speed); //speed of bullet will be faster than enemy
    }    
    
        public void update(int w, int h) {
            if(show){
                x -= speed; y += speed;
                checkCollision();
                if(x <= 0 || y >= 480){
                    //delete object...
                    show = false;        
                }
            } 
            else{
                if(!objectGone){
                    x = 900;
                    y = 900;
                }
            }  
        }
}

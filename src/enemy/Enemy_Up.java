/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enemy;

import java.awt.Image;

/**
 *
 * @author Chidi
 */
public class Enemy_Up extends Enemy_Basic {

    public Enemy_Up(Image img, int x, int y, int speed) {
            super(img,x,y,speed);
    }
  
        public void update(int w, int h) {
            if(isShow()){
                y -= speed;
                checkCollision();
                if(y <= 0-h){
                    //delete object...
                    doNotShow();        
                }
            }  
            else{
                checkBoom();
            }
        }
    
}

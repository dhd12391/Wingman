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
public class Enemy_Right extends Enemy_Basic{

    public Enemy_Right(Image img, int x, int y, int speed) {
            super(img,x,y,speed);
    }  
    
        public void update(int w, int h) {
            if(isShow()){
                x += speed;
                checkCollision();
                if(x >= 640){
                    //delete object...
                    doNotShow();        
                }
            }    
            else{
                checkBoom();
            }    
        }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observable;
import wingman.*;

/**
 *
 * @author Chidi
 */
public class Enemy_Basic extends Enemy_Parent{

        private boolean show, boom, hasExploded;
        
        public Enemy_Basic(Image img, int x, int y, int speed) {
            super(img,x,y,speed);
            
            this.show = true; //destroy object if false...
            boom = false;
            hasExploded = false;
       }
        
        public void checkCollision(){
            if(gm1942.p1.collision(x, y, sizeX, sizeY) && show == true && boom == false) {
                show = false; //enemy dies
                boom = true;
                gm1942.gameEvents.setValue("p1 damaged"); //damage to plane1
            }
            else if(gm1942.p2.collision(x, y, sizeX, sizeY) && show == true && boom == false) {
                show = false; //enemy dies
                boom = true;   
                gm1942.gameEvents.setValue("p2 damaged"); //damage to plane2
            }            
        }
        
        public int getX(){
            return x;
        }
        
        public int getY(){
            return y;
        }

        public boolean isBoom(){
            return boom;
        }
        
        public void setBoom(){
            boom = true;
        }
        
        public boolean isShow(){
            return show;
        }
        
        public void doNotShow(){
            show = false;
        }
        
        public boolean hasExploded(){
            return hasExploded;
        }
        
        public void hasNotExploded(){
            hasExploded = false;
        }
        
        public void setHasExploded(){
            hasExploded = true;
        }
        
        public void update(int w, int h) {
            if(isShow()){
                y += speed;
                checkCollision();            
                if(y >= 480){
                   //delete object...
                  doNotShow();        
                }
            }
            else{
                checkBoom();
            }
        }
        
        public void checkBoom(){
                if(isBoom()){
                    if(hasExploded && !objectGone){
                        System.out.println("Enemy killed");
                        x = 0;
                        y = 800;
                        boom = false;
                    }
                }            
        }

        public void draw(Graphics g, ImageObserver obs) {
            if (show) {
                g.drawImage(img, x, y, obs);
            }
            else{
                if(boom && !hasExploded){
                    hasExploded = true;
                }
                else if (!boom && hasExploded && !objectGone){
                    g.drawImage(img,x,y,obs);
                    objectGone = true;
                }
            }
           //(!show) -> garbage collection deletes object

        }   
}

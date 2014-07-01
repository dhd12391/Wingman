/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import wingman.gm1942;

/**
 *
 * @author Chidi
 */
public abstract class Enemy_Parent extends wingman.GameObject{
    
    public boolean show, objectGone;
    
        public Enemy_Parent(Image img, int x, int y, int speed) {
            super(img,x,y,speed);
            
            this.show = true; //destroy object if false...   
            objectGone = false;
        }
        
         public void checkCollision(){
            if(gm1942.p1.collision(x, y, sizeX, sizeY) && show == true) {
                show = false; //enemy dies
                gm1942.gameEvents.setValue("p1 damaged"); //damage to plane1
            }
            else if(gm1942.p2.collision(x, y, sizeX, sizeY) && show == true) {
                show = false; //enemy dies
                gm1942.gameEvents.setValue("p2 damaged"); //damage to plane2
            }            
        } 
         
        public void update(int w, int h) {
            if(show){
                   y += speed;
                   checkCollision();
                   if(y >= 480){
                       show = false;
                   }
            }
            else{
                if(!objectGone){
                    x = 0;
                    y = 800;
                }
            }      
       }
        
        public void draw(Graphics g, ImageObserver obs) {
            if (show) {
                g.drawImage(img, x, y, obs);
            }
            else if(!objectGone){
                g.drawImage(img,x,y,obs);
                objectGone = true;
            }
        }
        
}

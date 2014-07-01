/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Observable;

public abstract class GameObject extends Observable {
        public Image img;
        public int x, y, sizeX, sizeY, speed;
        
        public GameObject(Image img, int x, int y, int speed){
            this.img = img;
            this.x = x;
            this.y = y;
            this.speed = speed; 
            
            sizeX = img.getWidth(null);
            sizeY = img.getHeight(null);
             
            //System.out.println("w:" + sizeX + " y:" + sizeY);
        }
        
        public abstract void draw(Graphics g, ImageObserver obs);
        
        public boolean collision(int x, int y, int w, int h) {
            Rectangle hittee = new Rectangle (this.x, this.y, this.sizeX, this.sizeY);
            Rectangle hitter = new Rectangle (x,y,w,h);
            if(hittee.intersects(hitter)) { //if hittee collides with hitter
                return true;
            }
           return false;
        }
}

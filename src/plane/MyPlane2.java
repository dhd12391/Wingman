/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plane;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import wingman.*;
import bullet.*;

public class MyPlane2 extends MyPlane_Parent implements Observer{
    
    public MyPlane2(Image img, Image bullets[], int x, int y, int speed) {
            super(img, bullets, x, y, speed);
    }
    
        public void insertBullet(Image img, int x, int y, int speed){
            gm1942.p2BulletList.add(new MyPlane2_Bullet(img, x, y, speed));
        }
        public void insertLeftBullet(Image img, int x, int y, int speed){
            gm1942.p2BulletList.add(new MyPlane2_TopLeftBullet(img, x, y, speed));
        }
        public void insertRightBullet(Image img, int x, int y, int speed){
            gm1942.p2BulletList.add(new MyPlane2_TopRightBullet(img, x, y, speed));
        }        
    
    
    public void update(Observable obj, Object arg) {
            System.out.print("p2 ");
            GameEvents ge = (GameEvents) arg;
            if(ge.type == 1) {
                KeyEvent e = (KeyEvent) ge.event;
                switch (e.getKeyCode()) {    
                    case KeyEvent.VK_A:
                        System.out.println("Left");
                        if (x == 0) {
                            x=x;
                        }
                        else {
                            x -= speed;
                        }
	        	break; 
                    case KeyEvent.VK_D:
                        System.out.println("Right");
                        if (x+sizeX> 640) {
                            x=x;
                        }
                        else {
                            x += speed;
                        }
	        	break;
                    case KeyEvent.VK_W:
                        System.out.println("Up");
                        if(y == 0) {
                            y=y;
                        }
                        else {
                            y -= speed;
                        }
                        break;
                    case KeyEvent.VK_S:
                        System.out.println("Down");
                        if(y+sizeY> 430) {
                            y = y;
                        }
                        else {
                            y += speed;
                        }
                        break;
                    default:
                    if(e.getKeyChar() == ' ') {
                        System.out.println("Fire");  
                        doShoot();//spawn new bullet
                  }
                }
            }
            else if(ge.type == 2) {
                String msg = (String)ge.event;
                if(msg.equals("p2 damaged")){
                    if(hasBeenDamaged() == 0){
                        setDamaged();
                        doDamage = true;
                    }
                }
                else if(msg.equals("p2 hits")){
                    if(hasHit() == 0){
                        hitEnemy = true;
                        setHit();
                    }
                }
                else if (msg.equals("p2 powerup")){
                    powerUp = true;
                }
                else if (msg.equals("p2 1up")){
                    oneUp = true;
                }                
                else if (msg.equals("p2 boss kill")){
                    if(!bossKill){
                        hitEnemy = true;
                        bossKill = true;
                    }
                }
            }
    }
    
}

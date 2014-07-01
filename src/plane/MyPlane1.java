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

public class MyPlane1 extends MyPlane_Parent implements Observer{
    
    public MyPlane1(Image img, Image bullets[], int x, int y, int speed) {
            super(img, bullets, x, y, speed);
    }
    
    public void update(Observable obj, Object arg) {
            System.out.print("p1 ");
            GameEvents ge = (GameEvents) arg;
            System.out.println(ge.type);
            if(ge.type == 1) {
                KeyEvent e = (KeyEvent) ge.event;
                switch (e.getKeyCode()) {    
                    case KeyEvent.VK_LEFT:
                        System.out.println("Left");
                        if (x == 0) {
                            x=x;
                        }
                        else {
                            x -= speed;
                        }
	        	break; 
                    case KeyEvent.VK_RIGHT:
                        System.out.println("Right");
                        if (x+sizeX> 640) {
                            x=x;
                        }
                        else {
                            x += speed;
                        }
	        	break;
                    case KeyEvent.VK_UP:
                        System.out.println("Up");
                        if(y == 0) {
                            y=y;
                        }
                        else {
                            y -= speed;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("Down");
                        if(y+sizeY > 430) {
                            y = y;
                        }
                        else {
                            y += speed;
                        }
                        break;
                    default:
                  if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                        System.out.println("Fire");  
                        doShoot();//spawn new bullet
                  }
                }
            }
            
            else if(ge.type == 2) {
                String msg = (String)ge.event;
                if(msg.equals("p1 damaged")){
                    if(hasBeenDamaged() == 0){
                        setDamaged();
                        doDamage = true;
                    }
                    else{
                        doDamage = false;
                    }
                }
                else if (msg.equals("p1 hits")){
                    if(hasHit() == 0){
                        hitEnemy = true;
                        setHit();
                    }
                }
                else if (msg.equals("p1 powerup")){
                    powerUp = true;
                }
                else if (msg.equals("p1 1up")){
                    oneUp = true;
                }
                else if (msg.equals("p1 boss kill")){
                    if(!bossKill){
                        hitEnemy = true;
                        bossKill = true;
                    }
                }
            }
        }    
}



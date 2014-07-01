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
public class MyPlane1_TopLeftBullet extends MyPlane1_Bullet{

    public MyPlane1_TopLeftBullet (Image img, int x, int y, int speed){
        super(img,x,y,speed);
    }    
    
    public void update(int w, int h){
        if(show){
            x -= speed; y -= speed;
            checkCollision();
            if(y<=0-h || x<=0-w){
                show = false;
            }
        }
        else{
            if(!objectGone){
                x = 1000;
                y = 1000;
            }
        }
    } 
}

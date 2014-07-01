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
public class MyPlane2_TopRightBullet extends MyPlane2_Bullet{

    public MyPlane2_TopRightBullet (Image img, int x, int y, int speed){
        super(img,x,y,speed);
    }
    
    public void update(int w, int h){
        if(show){
            x += speed; y -= speed;
            checkCollision();
            if(y<=0-h || x>=640){
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

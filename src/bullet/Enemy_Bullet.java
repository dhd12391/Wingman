/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bullet;

import enemy.Enemy_Parent;
import java.awt.Image;

/**
 *
 * @author Chidi
 */
public class Enemy_Bullet extends enemy.Enemy_Parent{
    
    public Enemy_Bullet(Image img, int x, int y, int speed){
        super(img,x,y,speed); //speed of bullet will be faster than enemy
    }
    
    public void update(int w, int h){
        if(show){
            y += speed;
            checkCollision();
            if(y >= 480){
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

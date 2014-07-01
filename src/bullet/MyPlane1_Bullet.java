/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bullet;

import enemy.Enemy_Parent;
import java.awt.Image;
import wingman.*;

/**
 *
 * @author Chidi
 */
public class MyPlane1_Bullet extends enemy.Enemy_Parent{
    
    public MyPlane1_Bullet (Image img, int x, int y, int speed){
        super(img,x,y,speed);
    }
    
    public void checkCollision(){
        if(gm1942.bossArrived()){
            if(gm1942.boss1.collision(x, y, sizeX, sizeY)&& show == true){
                show = false;
                gm1942.boss1.isHit();
                if(gm1942.boss1.isKilled()){
                    gm1942.boss1.doNotShow();
                    gm1942.boss1.setBoom();
                    gm1942.boss1.hasNotExploded();
                    gm1942.gameEvents.setValue("p1 boss kill");
                }
            }
        }
        for(int i = 0; i < gm1942.enemyList.size(); i++){
            if(gm1942.enemyList.get(i).collision(x,y,sizeX,sizeY) && show == true){
                show = false;
                gm1942.enemyList.get(i).doNotShow();;
                gm1942.enemyList.get(i).setBoom();
                gm1942.enemyList.get(i).hasNotExploded();                
                gm1942.gameEvents.setValue("p1 hits");
                break;
            }
        }
        
    }
    public void update(int w, int h){
        if(show){
            y -= speed;
            checkCollision();
            if(y<=0){
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

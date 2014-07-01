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
public class MyPlane2_Bullet extends MyPlane1_Bullet{
    
    public MyPlane2_Bullet (Image img, int x, int y, int speed){
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
                    gm1942.gameEvents.setValue("p2 boss kill");
                }
            }
        }
            
        for(int i = 0; i < gm1942.enemyList.size(); i++){
            if(gm1942.enemyList.get(i).collision(x,y,sizeX,sizeY) && show == true){
                show = false;
                gm1942.enemyList.get(i).doNotShow();
                gm1942.enemyList.get(i).setBoom();
                gm1942.enemyList.get(i).hasNotExploded();
                gm1942.gameEvents.setValue("p2 hits");
                break;
            }
        }
    }    
    
}
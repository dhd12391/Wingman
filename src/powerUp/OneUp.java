/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUp;

import java.awt.Image;
import wingman.gm1942;

/**
 *
 * @author Chidi
 */
public class OneUp extends PowerUp{
    public OneUp(Image powerUpImg, int x, int y, int speed){
        super(powerUpImg,x,y,speed);
    }
    
        public void checkCollision(){
            if(gm1942.p1.collision(x, y, sizeX, sizeY) && show == true) {
                show = false; //powerup used
                gm1942.gameEvents.setValue("p1 1up");
            }
          else if(gm1942.p2.collision(x, y, sizeX, sizeY)) {
                show = false; //powerup used
                gm1942.gameEvents.setValue("p2 1up"); 
            }            
        }
}

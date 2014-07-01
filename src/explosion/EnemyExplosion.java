/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package explosion;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import wingman.gm1942;
/**
 *
 * @author Chidi
 */
public class EnemyExplosion extends PlaneExplosion{
    
    public EnemyExplosion (Image explosionImg[]){
        super(explosionImg);
    }

    public void playSound(){
        gm1942.playSound("Resources/snd_explosion1.wav", false);
    }
}

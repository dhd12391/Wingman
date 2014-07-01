/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boss;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author Chidi
 */
public class Boss extends enemy.Enemy_Basic{
    boolean leftMove;
    private int bossHP;
    
    public Boss(Image img, int x, int y, int speed){
        super(img,x,y,speed);
        leftMove = true;
        bossHP = 25;
    }
    
    public void update(int w, int h){
        if(isShow()){
            checkLeftMove();
            if(leftMove){
                x -= speed;
            }
            else{
                x += speed;
            }
        }
        else{
            checkBoom();
        }
    }
    
    public void isHit(){
        bossHP--;
    }
    
    public boolean isKilled(){
        return (bossHP <= 0);
    }
    
    private void checkLeftMove(){
        if(x <= 0){
            leftMove = false;
        }
        if(x >= 640-sizeX){
            leftMove = true;
        }
    }
    
    public int getWidth(){
        return sizeX;
    }
    
    public int getHeight(){
        return sizeY;
    }
}

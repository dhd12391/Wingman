/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;
import wingman.*;
import bullet.*;

/**
 *
 * @author Chidi
 */
public abstract class MyPlane_Parent extends wingman.GameObject implements Observer{
    
        private int hit, damaged, shoot;
        private int score, healthPoints, life, boom;
        final int maxHP = 4;
        Image bullets[] = new Image[3];
        
        boolean doDamage,hitEnemy,powerUp, oneUp, bossKill, hasExploded;

        public MyPlane_Parent(Image img, Image bulletsImg[], int x, int y, int speed) {
            super(img,x,y,speed);
            this.bullets[0] = bulletsImg[0];
            this.bullets[1] = bulletsImg[1];
            this.bullets[2] = bulletsImg[2];
                    
            powerUp = false;
            oneUp = false;
            score = 0;
            healthPoints = maxHP;
            life = 2;
            hit = 0;
            damaged = 0;
            doDamage = false;
            shoot = 0;
            boom = 0;
            hitEnemy = false;
            bossKill = false;
            hasExploded = false;
        }
        
        public void insertBullet(Image img, int x, int y, int speed){
            gm1942.p1BulletList.add(new MyPlane1_Bullet(img, x, y, speed));
        }
        public void insertLeftBullet(Image img, int x, int y, int speed){
            gm1942.p1BulletList.add(new MyPlane1_TopLeftBullet(img, x, y, speed));
        }
        public void insertRightBullet(Image img, int x, int y, int speed){
            gm1942.p1BulletList.add(new MyPlane1_TopRightBullet(img, x, y, speed));
        }           
        
        public abstract void update(Observable obj, Object arg);
        
        /******************TO BE DISPLAYED IN GAME*************/
        public int getScore(){
            return score;
        }
        
        public int getHP(){
            return healthPoints;
        }
        
        public int getLife(){
            return life;
        }
        
        /*******************************************************/
        public int hasBeenDamaged(){
            return damaged;
        }
        
        public void setDamaged(){
            damaged = 1;
        }
        
        public int hasHit(){
            return hit;
        }
        
        public void setHit(){
            hit = 1;
        }
        
        public void doShoot(){
            shoot = 1;
        }
        
        public int getX(){
            return x;
        }
        
        public int getY(){
            return y;
        }
        
        public int isBoom(){
            return boom;
        }
        
        public boolean hasExploded(){
            return hasExploded;
        }
        
        public void setHasExploded(){
            hasExploded = true;
        }
        
        public void draw(Graphics g, ImageObserver obs) {
            if(oneUp){
                life++;
                oneUp = false;
            }
            /*******************PLANE DAMAGED********************/
            if (damaged == 1){ //damaged == 1
                damaged = 0;
                if (doDamage == true){
                    doDamage = false;
                    
                //1: life bar / health points (hp) decrease
                //2: destroy plane if hp == 0, activate EXPLOSION, use up 1 life
                //3: check for life, if life>0, gameover
                //4: respawn...
                    
                
                    //1:
                    System.out.println("damaged");
                    healthPoints -= 1; 
                
                    //2:
                    
                    if(hasExploded){
                        boom = 0;
                        hasExploded = false;
                    }
                    
                    if(healthPoints == 0){
                        powerUp = false;
                       
                        if(!hasExploded){
                            boom = 1;
                        }
                        
            /*******************PLANE EXPLODED********************/
                        //explosion animation done in applet
                    
                        //using up one life to respawn
                        life -= 1; 
                        System.out.println("LOSES LIFE");
                   
                    //3: no more lives to use, game over
                        if(life < 0){
                            //game over! show scoreboard
                            gm1942.setGameOver();
                            System.out.println("GAME OVER");
                        }
                    //4: respawn after using a life up    
                        else{ //if(life >= 0)
                            healthPoints = maxHP;
                        }
                    }
                }
                else{
                    g.drawImage(img,x,y,obs);
                }
                
            }
            
            /*********************PLANE SHOOTING******************/
            
            else if (shoot == 1){
                System.out.println("SHOOTING");
                //create bullet in front of myplane's y
                insertBullet(bullets[0],(x + (sizeX/2)-14), y, speed);
                if(powerUp == true){   //powerUp -> diagonal shooting
                    insertLeftBullet(bullets[1],x-3, y+4, speed);
                    insertRightBullet(bullets[2],(x + 16), y, speed);
                }
                shoot = 0;       
            }            
            /********************PLANE HITS ENEMY******************/
            else{
                if (hit == 1){
                    if (hitEnemy == true){
                        hitEnemy = false;
                        score++;
                        hit = 0;
                    }
                }
                else if(bossKill = true){
                    if(hitEnemy == true){
                        hitEnemy = false;
                        bossKill = false;
                        score += 10;
                    }
                }
                g.drawImage(img,x,y,obs);
            }
            
            
        }
          
    }
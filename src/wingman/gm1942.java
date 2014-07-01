/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;
import javax.sound.sampled.*;
import enemy.*;
import explosion.*;
import island.*;
import plane.*;
import bullet.*;
import powerUp.*;
import boss.*;
import health.*;
import misc_Graphics.*;

public class gm1942 extends JApplet implements Runnable {

    private Thread thread;
   
    private int k = 0, move = 0;
    public static BufferedImage bimg;
    
    int speed = 2, planeSpeed = 10, bulletSpeed = 4, score = 0, hp = 4, life = 2; 
    Random generator = new Random(1234567);
    Island I1, I2, I3;
    PowerUp pwr1, pwr2;
    OneUp oneUp1, oneUp2;
    public static PlaneExplosion planeExplosion;
    public static EnemyExplosion enemyExplosion;
    
    public static MyPlane1 p1; 
    int p1Score;
    HealthBar p1Health;
    Life p1Life;
    
    public static MyPlane2 p2; 
    int p2Score;
    HealthBar p2Health;
    Life p2Life;
    
    public static LinkedList <Enemy_Basic> enemyList = new LinkedList<Enemy_Basic>();
    public static LinkedList <Enemy_Bullet> enemyBulletList = new LinkedList<Enemy_Bullet>();
    public static LinkedList <MyPlane1_Bullet> p1BulletList = new LinkedList <MyPlane1_Bullet>();
    public static LinkedList <MyPlane2_Bullet> p2BulletList = new LinkedList <MyPlane2_Bullet>();
    public static LinkedList <Enemy_Bullet> bossBulletList = new LinkedList<Enemy_Bullet>();
    public static Boss boss1;
    
    int currentFrame = 0, finalFrame, explosionFrameP1, f1, explosionFrameP2, f2;
    int explosionFrameBoss, fB;
    int explosionFrameEnemy [] = new int[50];
    int fE [] = new int [50];
    
    //scoreboard & extra graphics
    YouWin youWin; GameOver gameOverScreen; Scoreboard scoreboard;
    
    public static GameEvents gameEvents;

    static boolean gameOver, bossArrived, setFinalFrame, setScoreboard,
                    setExplosionFrameP1, setExplosionFrameP2,
                    setExplosionFrameBoss;
    
    static boolean setExplosionFrameEnemy[] = new boolean[50];
    
    ImageObserver observer;

    public void init() {
        setBackground(Color.white);
        playSound("Resources/background.mid", true);
        
        Image myPlane, island1, island2, island3, lifeImg, oneUpImg,
                enemyS, enemyW, enemyE, enemyN, powerUpImg, boss, enemyBullet, 
                youWinImg, gameOverImg, scoreboardImg,
                planeBulletImg[] = new Image[3],
              planeExplosionImg[] = new Image[6],
              enemyExplosionImg[] = new Image[6],
              planeHealthBar[] = new Image[4];
              
        planeHealthBar[0] = getSprite("Resources/health3.png");
        planeHealthBar[1] = getSprite("Resources/health2.png");
        planeHealthBar[2] = getSprite("Resources/health1.png");
        planeHealthBar[3] = getSprite("Resources/health.png");
        
        lifeImg = getSprite("Resources/life.png");
        boss = getSprite("Resources/boss.png");
        
        powerUpImg = getSprite("Resources/powerup.png");
        oneUpImg = getSprite("Resources/1Up.png");
        
        island1 = getSprite("Resources/island1.png");
        island2 = getSprite("Resources/island2.png");
        island3 = getSprite("Resources/island3.png");
        
        enemyS = getSprite("Resources/enemy1_1.png"); //facing south       
        enemyW = getSprite("Resources/enemy2_1.png"); //facing west       
        enemyE = getSprite("Resources/enemy3_1.png"); //facing east
        enemyN = getSprite("Resources/enemy4_1.png"); //facing north
        
        enemyBullet = getSprite("Resources/enemybullet1.png"); 
        
        planeBulletImg[0] = getSprite("Resources/bullet.png");
        planeBulletImg[1] = getSprite("Resources/bulletLeft.png");
        planeBulletImg[2] = getSprite("Resources/bulletRight.png");
        
        myPlane = getSprite("Resources/myplane_1.png");

        planeExplosionImg[0] = getSprite("Resources/explosion2_1.png");
        planeExplosionImg[1] = getSprite("Resources/explosion2_2.png");
        planeExplosionImg[2] = getSprite("Resources/explosion2_3.png");
        planeExplosionImg[3] = getSprite("Resources/explosion2_4.png");
        planeExplosionImg[4] = getSprite("Resources/explosion2_5.png");
        planeExplosionImg[5] = getSprite("Resources/explosion2_6.png");

        enemyExplosionImg[0] = getSprite("Resources/explosion1_1.png");
        enemyExplosionImg[1] = getSprite("Resources/explosion1_2.png");
        enemyExplosionImg[2] = getSprite("Resources/explosion1_3.png");
        enemyExplosionImg[3] = getSprite("Resources/explosion1_4.png");
        enemyExplosionImg[4] = getSprite("Resources/explosion1_5.png");
        enemyExplosionImg[5] = getSprite("Resources/explosion1_6.png");
        
        youWinImg = getSprite("Resources/youWin.png");
        gameOverImg = getSprite("Resources/gameOver.png");
        scoreboardImg = getSprite("Resources/score.png");
               
        planeExplosion = new PlaneExplosion(planeExplosionImg);
        enemyExplosion = new EnemyExplosion(enemyExplosionImg);
        setExplosionFrameP1 = false;
        f1 = 0;
        setExplosionFrameP2 = false;
        f2 = 0;
        setExplosionFrameBoss = false;
        fB = 0;
        for(int i = 0; i < 50; i++){
            setExplosionFrameEnemy[i] = false;
            fE[i] = 0;
        }
        
        p1Health = new HealthBar(planeHealthBar);
        p2Health = new HealthBar(planeHealthBar);
        
        p1Life = new Life(lifeImg);
        p2Life = new Life(lifeImg);
        
        youWin = new YouWin(youWinImg);
        gameOverScreen = new GameOver(gameOverImg);
        scoreboard = new Scoreboard(scoreboardImg);
        
        bossArrived = false;
        gameOver = false;
        setFinalFrame = false;
        
        observer = this;
        
        I1 = new Island(island1, 100, 100, speed, generator);
        I2 = new Island(island2, 200, 400, speed, generator);
        I3 = new Island(island3, 300, 200, speed, generator);
        
        pwr1 = new PowerUp(powerUpImg, 482, -70, speed);
        pwr2 = new PowerUp(powerUpImg, 111, -100, speed);
        
        oneUp1 = new OneUp(oneUpImg, 333, -100, speed);
        oneUp2 = new OneUp(oneUpImg, 500, -100, speed);
        
        //**********SET UP TIMELINE FOR ENEMIES & BULLETS****************/
        
        //Enemy: 0~2, Bullet: 0~2
        enemyList.add(new Enemy_Basic(enemyS, 320, -50, speed));
        enemyBulletList.add(new Enemy_Bullet(enemyBullet, 320, -50, bulletSpeed));                
                
        enemyList.add(new Enemy_Basic(enemyS, 233, -50, speed));
        enemyBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 233, -50, bulletSpeed));          

        enemyList.add(new Enemy_Basic(enemyS, 407, -50, speed));
        enemyBulletList.add(new Enemy_BtmRightBullet(enemyBullet, 407, -50, bulletSpeed));          
        
        //Enemy: 3~5, Bullet: 3~5
        
        enemyList.add(new Enemy_Basic(enemyS, 160, -50, speed));
        enemyBulletList.add(new Enemy_BtmRightBullet(enemyBullet, 160, -50, bulletSpeed));
       
        enemyList.add(new Enemy_Basic(enemyS, 320, -50, speed));
        enemyBulletList.add(new Enemy_Bullet(enemyBullet, 320, -50, bulletSpeed));
        
        enemyList.add(new Enemy_Basic(enemyS, 480, -50, speed));
        enemyBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 480, -50, bulletSpeed));
        
        //Enemy: 6~11 , Bullet: 6~11
        enemyList.add(new Enemy_Basic(enemyS, 120, -100, speed));
        enemyList.add(new Enemy_Basic(enemyS, 160, -30, speed));
        enemyList.add(new Enemy_Basic(enemyS, 200, -100, speed));
        enemyList.add(new Enemy_Basic(enemyS, 440, -100, speed));
        enemyList.add(new Enemy_Basic(enemyS, 480, -30, speed));
        enemyList.add(new Enemy_Basic(enemyS, 520, -100, speed));
        
        enemyBulletList.add(new Enemy_BtmRightBullet(enemyBullet, 120, -100, bulletSpeed));
        enemyBulletList.add(new Enemy_BtmRightBullet(enemyBullet, 160, -30, bulletSpeed));
        enemyBulletList.add(new Enemy_BtmRightBullet(enemyBullet, 200, -100, bulletSpeed));
        enemyBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 440, -100, bulletSpeed));
        enemyBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 480, -30, bulletSpeed));
        enemyBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 520, -100, bulletSpeed));
        
        //Enemy: 12~15, Bullet: 12~13
        enemyList.add(new Enemy_Up(enemyN, 100, 550, speed));
        enemyList.add(new Enemy_Up(enemyN, 180, 550, speed));
        enemyList.add(new Enemy_Left(enemyW, 640, 120, speed));
        enemyList.add(new Enemy_Left(enemyW, 640, 180, speed));        
        enemyBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 640, 120, bulletSpeed));
        enemyBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 640, 180, bulletSpeed));        
        
        //Enemy: 16~23 (SPIRAL)
        
        enemyList.add(new Enemy_Up(enemyN, 500, 480, speed));
        enemyList.add(new Enemy_Left(enemyW, 640, 40, speed));
        enemyList.add(new Enemy_Left(enemyW, 640, 100, speed));
        enemyList.add(new Enemy_Basic(enemyS, 100, -32, speed));        
        enemyList.add(new Enemy_Basic(enemyS, 160, -32, speed));
        enemyList.add(new Enemy_Right(enemyE, -32, 380, speed));  
        enemyList.add(new Enemy_Right(enemyE, -32, 330, speed));
        enemyList.add(new Enemy_Up(enemyN, 540, 480, speed));   
        
        //Enemy: 24~27 (CROSS), Bullet: 14-16
 
        enemyList.add(new Enemy_Left(enemyW, 640, 240, speed));
        enemyList.add(new Enemy_Basic(enemyS, 320, -32, speed));        
        enemyList.add(new Enemy_Right(enemyE, -32, 240, speed));
        enemyList.add(new Enemy_Up(enemyN, 320, 480, speed));
        enemyBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 320, 0, bulletSpeed));
        enemyBulletList.add(new Enemy_Bullet(enemyBullet, 320, 0, bulletSpeed));
        enemyBulletList.add(new Enemy_BtmRightBullet(enemyBullet, 320, 0, bulletSpeed));
        
        //Enemy: 28~46 
        enemyList.add(new Enemy_Right(enemyE, -32, 32, speed));
        enemyList.add(new Enemy_Left(enemyW, 640, 396, speed));        
        enemyList.add(new Enemy_Left(enemyW, 640, 84, speed));
        enemyList.add(new Enemy_Right(enemyE, -32, 344, speed));
        enemyList.add(new Enemy_Right(enemyE, -32, 136, speed));
        enemyList.add(new Enemy_Left(enemyW, 640, 292, speed));
        enemyList.add(new Enemy_Right(enemyE, -32, 240, speed));
        enemyList.add(new Enemy_Left(enemyW, 640, 188, speed));        
        
        enemyList.add(new Enemy_Basic(enemyS, 258, -32, speed));
        enemyList.add(new Enemy_Up(enemyN, 330, 480, speed));
        enemyList.add(new Enemy_Up(enemyN, 474, 480, speed));
        enemyList.add(new Enemy_Basic(enemyS, 160, -32, speed));
        enemyList.add(new Enemy_Basic(enemyS, 480, -32, speed));
        enemyList.add(new Enemy_Basic(enemyS, 402, -32, speed));
        enemyList.add(new Enemy_Basic(enemyS, 114, -32, speed)); 
        enemyList.add(new Enemy_Up(enemyN, 42, 480, speed));
        enemyList.add(new Enemy_Up(enemyN, 186, 480, speed));
        enemyList.add(new Enemy_Up(enemyN, 330, 480, speed));
        
        //(Boss's bullets: 0~24
        
        for(int i = 0; i < 30; i++){
            bossBulletList.add(new Enemy_BtmLeftBullet(enemyBullet, 240, boss.getHeight(this), bulletSpeed));
            bossBulletList.add(new Enemy_Bullet(enemyBullet, 320, boss.getHeight(this), bulletSpeed));
            bossBulletList.add(new Enemy_BtmRightBullet(enemyBullet, 400, boss.getHeight(this), bulletSpeed));
        }
        
        boss1 = new Boss(boss, (640/2)-(boss.getWidth(this)/2), 20, speed); 
        
        //**********SET UP TIMELINE FOR ENEMIES & BULLETS FINISHED****************/   
        
        KeyControl key = new KeyControl();
        addKeyListener(key);
        gameEvents = new GameEvents();
        p1 = new MyPlane1(myPlane, planeBulletImg, 150, 360, planeSpeed);
        p2 = new MyPlane2(myPlane, planeBulletImg, 450, 360, planeSpeed);
        gameEvents.addObserver(p1);
        gameEvents.addObserver(p2);
        
    }
    
    public static void setGameOver(){
        gameOver = true;
    }
    
    public Image getSprite(String name) {
        URL url = gm1942.class.getResource(name);
        Image img = getToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {
        }
        return img;
    }

    // generates a new color with the specified hue
    public void drawBackGroundWithTileImage(int w, int h, Graphics2D g2) {
        Image sea;
        sea = getSprite("Resources/water.png");
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        Image Buffer = createImage(NumberX * TileWidth, NumberY * TileHeight);
        //Graphics BufferG = Buffer.getGraphics();

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(sea, j * TileWidth, i * TileHeight + 
                        (move % TileHeight), TileWidth, TileHeight, this);
            }
        }
        move += speed;
    }

    public void drawDemo(int w, int h, Graphics2D g2) {

        if (!gameOver) {
            
            drawBackGroundWithTileImage(w, h, g2);
            I1.update(w, h);
            I1.draw(g2, this);

            I2.update(w, h);
            I2.draw(g2, this);

            I3.update(w, h);
            I3.draw(g2, this);

            if(p1.isBoom()==1 && (!p1.hasExploded())){
                
                if(!setExplosionFrameP1){
                    explosionFrameP1 = currentFrame;
                    setExplosionFrameP1 = true;
                }
                
                if(currentFrame <= explosionFrameP1 + f1*5){               
                    planeExplosion.draw(g2,f1,p1.getX(),p1.getY(), this);
                }
                if(currentFrame == explosionFrameP1 + f1*5){
                    f1++;
                        
                    if(f1>5){
                        p1.setHasExploded();
                        f1 = 0;
                        setExplosionFrameP1 = false;
                    }
                }
            }
            else{
                p1.draw(g2, this);
            }
            p1Health.draw(p1.getHP(),g2, 20, 390, this);
            p1Life.draw(p1.getLife(), g2, 5, 430, this);
            drawDemoPlane1BulletUpdate(p1BulletList.size()-1,w,h,g2);

            if(p2.isBoom()==1 && (!p2.hasExploded())){
                
                if(!setExplosionFrameP2){
                    explosionFrameP2 = currentFrame;
                    setExplosionFrameP2 = true;
                }
                
                if(currentFrame <= explosionFrameP2 + f2*5){               
                    planeExplosion.draw(g2,f2,p2.getX(),p2.getY(), this);
                }
                if(currentFrame == explosionFrameP2 + f2*5){
                    f2++;
                        
                    if(f2>5){
                        p2.setHasExploded();
                        f2 = 0;
                        setExplosionFrameP2 = false;
                    }
                }
            }          
            else{
                p2.draw(g2, this);
            }
            p2Health.draw(p2.getHP(),g2,500, 390, this);
            p2Life.draw(p2.getLife(),g2,485,430,this);
            drawDemoPlane2BulletUpdate(p2BulletList.size()-1,w,h,g2);


    //************timeline, LAUNCHING OF ENEMIES & BULLETS***********/
            if(currentFrame <= 100){
                drawDemoEnemyUpdate(0,w,h,g2);
                drawDemoEnemyBulletUpdate(0,w,h,g2);
            }
            else if(currentFrame <= 170){
                drawDemoEnemyUpdate(2,w,h,g2);
                drawDemoEnemyBulletUpdate(2,w,h,g2);                
            }  
            else if(currentFrame <= 400){
                drawDemoEnemyUpdate(5,w,h,g2);
                drawDemoEnemyBulletUpdate(5,w,h,g2);                 
            }
            else if(currentFrame <= 700){
                drawDemoEnemyUpdate(8,w,h,g2);
                drawDemoEnemyBulletUpdate(8,w,h,g2);                
            }  
            
            else if(currentFrame <= 950){
                pwr1.update(w, h);
                pwr1.draw(g2, this);
                drawDemoEnemyUpdate(11,w,h,g2);
                drawDemoEnemyBulletUpdate(11,w,h,g2);  
            }  
            
            else if(currentFrame <= 1200){
                drawDemoEnemyUpdate(15,w,h,g2);
                drawDemoEnemyBulletUpdate(13,w,h,g2);  
            }  
            
            //SPIRAL
            else if(currentFrame <= 1625){
                
                if(currentFrame <= 1250){
                    drawDemoEnemyUpdate(17,w,h,g2); 
                }
                else if(currentFrame <= 1325){
                    oneUp1.update(w, h);
                    oneUp1.draw(g2, this);
                    drawDemoEnemyUpdate(19,w,h,g2);
                }
                else if(currentFrame <= 1400){
                    oneUp1.update(w, h);
                    oneUp1.draw(g2, this);
                    drawDemoEnemyUpdate(21,w,h,g2);
                }
                else{
                    oneUp1.update(w, h);
                    oneUp1.draw(g2, this);
                    drawDemoEnemyUpdate(23,w,h,g2);
                }
            }
            
            //CROSS
            else if(currentFrame < 2000){
                pwr2.update(w, h);
                pwr2.draw(g2, this);
                drawDemoEnemyUpdate(27,w,h,g2);
                drawDemoEnemyBulletUpdate(16,w,h,g2);
            }
           
            else if(currentFrame == 2000){
                    k=1;
            }
            
            //*********************WAVE******************/
            else if(currentFrame < 3500){
                
                if(currentFrame <= 2000 + k*70){
                    drawDemoEnemyUpdate(27+k,w,h,g2);
                }
                else if (k<8){
                    k++;
                    drawDemoEnemyUpdate(27+k,w,h,g2);        
                }
                else if(currentFrame <= 2000 + k*70){
                    drawDemoEnemyUpdate(27+k,w,h,g2);
                }
                else if (k<16){
                    k++;
                    drawDemoEnemyUpdate(27+k,w,h,g2); 
                }
                else if (currentFrame < 3500){
                    drawDemoEnemyUpdate(27+k+2,w,h,g2); 
                }
            }
            
            //****************BOSS*********************/
            else{
                if(currentFrame == 3500){
                    bossArrived = true;
                    k=0;
                }
                boss1.update(w, h);
                if(boss1.isBoom() && (!boss1.hasExploded())){
                
                    if(!setExplosionFrameBoss){
                        explosionFrameBoss = currentFrame;
                        setExplosionFrameBoss = true;
                    }
                
                    if(currentFrame <= explosionFrameBoss + fB*5){               
                        planeExplosion.draw(g2,fB,boss1.getX(),boss1.getY(), this);
                    }
                    if(currentFrame == explosionFrameBoss + fB*5){
                        fB++;
                        
                        if(fB>5){
                            boss1.setHasExploded();
                            fB = 0;
                            setExplosionFrameBoss = false;
                        }
                    }
                }      
                
                else{
                    boss1.draw(g2, this);
                }   
                
                oneUp2.update(w, h);
                oneUp2.draw(g2, this);
                
                if(!boss1.isKilled()){
                    if(currentFrame <= 3500 + k*70){
                        drawDemoBossBulletUpdate(k,w,h,g2);
                    }
                    else{
                        k++;
                    }
                }
                else if(boss1.isKilled() && setFinalFrame == false){
                    finalFrame = currentFrame;
                    setFinalFrame = true;
                }
                else{
                    if(currentFrame <= finalFrame + 150){
                        youWin.draw(g2,200,150,this);
                    }
                    else{
                        setGameOver();
                        setFinalFrame = false;
                    }
                }
            }
            currentFrame++;
         }
  
        
        else{ //gameOver == true
            //show scoreboard
            if(!setFinalFrame){
                setFinalFrame = true;
                finalFrame = currentFrame;
            }
  
            drawBackGroundWithTileImage(w, h, g2);
            
            if(currentFrame <= finalFrame + 150){
                gameOverScreen.draw(g2, 200, 50, this);
            }
            
            else{
                if(!setScoreboard){
                    p1Score = p1.getScore();
                    p2Score = p2.getScore();  
                    scoreboard.insertScores(p1Score,p2Score);
                    System.out.println("p1's score: " + p1Score + " p2's score: " + p2Score);
                    setScoreboard = true;
                }
                scoreboard.draw(g2, 140, 20, this);
            }
            

            currentFrame++;
        }
    }
    
    public static boolean bossArrived(){
        return bossArrived;
    }
    
    public void drawDemoPlane1BulletUpdate(int n, int w, int h, Graphics g2){
        for(int i = 0; i <= n; i++){
            p1BulletList.get(i).update(w, h);
            p1BulletList.get(i).draw(g2, this);
        }
    }
    
    public void drawDemoPlane2BulletUpdate(int n, int w, int h, Graphics g2){
        for(int i = 0; i <= n; i++){
            p2BulletList.get(i).update(w, h);
            p2BulletList.get(i).draw(g2, this);
        }
    }    
    
    public void drawDemoEnemyUpdate(int n, int w, int h, Graphics g2){
        for(int i = 0; i <= n; i++){
           enemyList.get(i).update(w, h);
           
           if(enemyList.get(i).isBoom() && !enemyList.get(i).hasExploded()){
               if(setExplosionFrameEnemy[i] == false){
                   setExplosionFrameEnemy[i] = true;
                   explosionFrameEnemy[i] = currentFrame;
               }
                if(currentFrame <= explosionFrameEnemy[i] + fE[i]*5){               
                    enemyExplosion.draw(g2,fE[i],enemyList.get(i).getX(),enemyList.get(i).getY(), this);
                }
                if(currentFrame == explosionFrameEnemy[i] + fE[i]*3){
                    fE[i]++;
                        
                    if(fE[i]>5){
                        enemyList.get(i).setHasExploded();
                        fE[i] = 0;
                        setExplosionFrameEnemy[i] = false;
                    }
                }
           }
           else{
               enemyList.get(i).draw(g2, this);
           } 
        }
    }
    
    public void drawDemoEnemyBulletUpdate(int n, int w, int h, Graphics g2){
        for(int i = 0; i <= n; i++){
           enemyBulletList.get(i).update(w,h);
           enemyBulletList.get(i).draw(g2, this);
        }
    }   

    public void drawDemoBossBulletUpdate(int n, int w, int h, Graphics g2){
        for(int i = 0; i <= 2 + n*3; i++){
           bossBulletList.get(i).update(w,h);
           bossBulletList.get(i).draw(g2, this);
        }
    }      

    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);
        return g2;
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        Graphics2D g2 = createGraphics2D(d.width, d.height);
        drawDemo(d.width, d.height, g2);
        g2.dispose();
        g.drawImage(bimg, 0, 0, this);
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
          
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
        }
       // thread = null;
    }

    public static void playSound(String filename, boolean activateLoop) {   //playing .wav files

        try{
          URL url = gm1942.class.getResource(filename); //"Resources/..."
          Clip clip = AudioSystem.getClip();
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
          clip.open(audioInputStream);
          if (activateLoop){
              clip.loop(Clip.LOOP_CONTINUOUSLY);
          }
          
          clip.start();
          
          if(!activateLoop){
              if(clip.getFramePosition() == clip.getFrameLength()){
                  audioInputStream.close();
             }
          }
          
        }
        catch(Exception e){
            System.out.println(filename + " not found");
        }
     
    }

    public static void main(String argv[]) {
        final gm1942 demo = new gm1942();
        demo.init();
        JFrame f = new JFrame("Scrolling Shooter");
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        demo.start();
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

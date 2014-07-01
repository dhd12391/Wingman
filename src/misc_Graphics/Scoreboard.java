/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc_Graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 *
 * @author Chidi
 */
public class Scoreboard {
    Image scoreboard;
    String p1Score, p2Score, combinedScore;
    
    public Scoreboard(Image scoreboard){
        this.scoreboard = scoreboard;
        this.p1Score = "0";
        this.p2Score = "0";
        this.combinedScore = "0";
    }
    
    public void insertScores(int p1Score, int p2Score){
        this.p1Score = Integer.toString(p1Score);
        this.p2Score = Integer.toString(p2Score);
        this.combinedScore = Integer.toString(p1Score + p2Score);
    }
    
    public void draw(Graphics g, int x, int y, ImageObserver obs){
        g.drawImage(scoreboard, x, y, obs);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("Player 1: ", x + 70, y + 100);
        g.drawString(p1Score, x + 230, y + 100);
        g.drawString("Player 2: ", x + 70, y + 200);
        g.drawString(p2Score, x + 230, y + 200);
        g.drawString("Total: ", x + 70, y + 300);
        g.drawString(combinedScore, x + 230, y + 300);
        
    }
}

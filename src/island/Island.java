/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package island;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

    public class Island {

        Image img;
        int x, y, speed;
        Random gen;

        public Island(Image img, int x, int y, int speed, Random gen) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.gen = gen;
        }

        public void update(int w, int h) {
            y += speed;
            if (y >= h) {
                y = -100;
                x = Math.abs(gen.nextInt() % (w - 30));
            }
        }

        public void draw(Graphics g, ImageObserver obs) {
            g.drawImage(img, x, y, obs);
        }
    }
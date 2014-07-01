/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

    public class KeyControl extends KeyAdapter {
        
        public void keyPressed(KeyEvent e) {
            gm1942.gameEvents.setValue(e);
        }
    }
        


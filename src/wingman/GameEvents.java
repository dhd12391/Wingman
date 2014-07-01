/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import java.awt.event.KeyEvent;
import java.util.Observable;

   public class GameEvents extends Observable {
       public int type;
       public Object event; 
       
       public void setValue(KeyEvent e) {
          type = 1; //key input event
          event = e;
          setChanged();
         // trigger notification
         notifyObservers(this);  
       }

       public void setValue(String msg) {
          type = 2; // message input event
          event = msg;
          setChanged();
         // trigger notification
         notifyObservers(this);  
        }
    }
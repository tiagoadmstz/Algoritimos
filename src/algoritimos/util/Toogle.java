/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.util;

import java.awt.Component;
import javax.swing.JComponent;

/**
 *
 * @author tiago.teixeira
 */
public class Toogle {
    
    public static void toogleEnable(Component component){
        component.setEnabled(!component.isEnabled());
    }
    
    public static void toogleEnable(JComponent component){
        component.setEnabled(!component.isEnabled());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.controle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author tiago.teixeira
 */
public class ControleListasGlobais implements Runnable{

    private int velho = 0;
    private int novo = 0;
    private static final Map<Class, List<?>> mapListas = new HashMap();
    private static final Map<Class, List<JComboBox>> mapCombo = new HashMap();
    private Thread atualizaListas;

    public static List<?> getLista(Class classe) {
        return mapListas.get(classe) != null ? mapListas.get(classe) : null;
    }
    
    public static void addLista(Class classe, List<Object> lista, JComboBox comboBox){
        mapListas.put(classe, lista);
        if(mapCombo.get(classe) == null){
            mapCombo.put(classe, Arrays.asList(new JComboBox[]{comboBox}));
        } else {
            List<JComboBox> cbl = mapCombo.get(classe);
            cbl.add(comboBox);
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(5000);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ControleListasGlobais.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

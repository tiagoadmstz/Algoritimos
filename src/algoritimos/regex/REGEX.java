/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.regex;

/**
 *
 * @author Tiago
 */
public enum REGEX {
    
    COMECACOM(0), CONTEM(1);
    
    private int value;

    private REGEX(int value) {
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
}

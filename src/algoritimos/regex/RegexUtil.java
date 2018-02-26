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
public class RegexUtil {

    private static String[] listaRegex = new String[]{
        "^(?i:(<>.*))$",
        "^(?i:.*<>.*)$",
        "^(?i:<>)$",
        "^(?!(?i:<>)$)",
        "^(?i:.*<>)$",
        "^(?!(?i:.*<>.*)$)"
    };
    
    public static String getRegex(REGEX regex, String texto){
        texto = regex == REGEX.CONTEM || regex == REGEX.NAOCONTEM ? texto.replace(" ", ".*") : texto;
        return listaRegex[regex.getValue()].replace("<>", texto);
    }
    
}

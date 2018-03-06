/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.treetable;

/**
 *
 * @author tiago.teixeira
 */
public class ParentNode extends Node{
    
    public ParentNode(Object[] parentNodes) {
        super(parentNodes);
    }
    
    public ParentNode(String parentNode){
        this(new String[]{parentNode});
    }
    
}

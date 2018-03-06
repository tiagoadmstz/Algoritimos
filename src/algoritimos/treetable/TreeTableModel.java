/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.treetable;

import java.util.Arrays;
import java.util.List;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

/**
 *
 * @author tiago.teixeira
 */
public final class TreeTableModel {

    private RootNode root;
    private final ParentNode[] parents;
    private final List<ChildNode> childs;
    private DefaultTreeTableModel model;
    private final String[] columnNames;

    public TreeTableModel(ParentNode[] parents, List<ChildNode> childs, String[] columnNames) {
        this.parents = parents;
        this.childs = childs;
        this.columnNames = columnNames;
        inicializeContent();
    }

    public DefaultTreeTableModel getTreeTableModel(){
        return model;
    }
    
    private void inicializeContent() {
        //cria a raiz da estrutura da árvore
        root = new RootNode("root");
        int cont = 0;
        //passa em todos os nós pais
        for(ParentNode p:parents){
            p.add(childs.get(cont++));
            root.add(p);
        }

        model = new DefaultTreeTableModel(root, Arrays.asList(columnNames));
    }

}

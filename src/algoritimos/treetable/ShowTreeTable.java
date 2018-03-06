/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.treetable;

import algoritimos.entidades.Usuario;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jdesktop.swingx.JXTreeTable;

/**
 *
 * @author tiago.teixeira
 */
public class ShowTreeTable {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        List<String[]> content = new ArrayList();

        ParentNode[] parents = new ParentNode[]{
            new ParentNode("Teste"),
            new ParentNode("Teste2"),
            new ParentNode("Teste3")
        };

        Usuario user = new Usuario("Tiago Daniel Teixeira", "tiago", "123456", false);
        
        List<ChildNode> childs = new ArrayList();
        childs.add(new ChildNode(new String[]{user.getNomeUsuario(),user.getUsuario(),user.getSenha()}));
        childs.add(new ChildNode(new String[]{"Teste4", "Teste5", "Teste6"}));
        childs.add(new ChildNode(new String[]{"Teste7", "Teste8", "Teste9"}));
        
        String[] columnNames = new String[]{"Coluna1", "Coluna2", "Coluna3"};
        
        TreeTableModel model = new TreeTableModel(parents, childs, columnNames);
        
        JXTreeTable table = new JXTreeTable(model.getTreeTableModel());
        
        table.setShowGrid(true, true);
        
        frame.setSize(500,500);
        
        frame.setLayout(new BorderLayout());
        
        frame.add(new JScrollPane(table), BorderLayout.CENTER); 
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
    }

}

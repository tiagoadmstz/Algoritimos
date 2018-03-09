package algoritimos.tabelas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

/**
 *
 * @author Tiago D. Teixeira
 */
public class TableModelCBIAdapter extends TableModelCBI{

    protected List<Object> lista;

    public TableModelCBIAdapter() {
    }

    public TableModelCBIAdapter(List<?> lista) {
        this.lista = (List<Object>) lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }
    
    public void setLista(List<?> lista){
        this.lista = (List<Object>) lista;
    }
    
    @Override
    public void addObject(Object Object) {
        lista.add(Object);
        fireTableDataChanged();
    }
    
    @Override
    public void removeObject(int rowIndex) {
        lista.remove(rowIndex);
        fireTableDataChanged();
    }

    @Override
    public Object getObject(int rowIndex) {
        return lista.get(rowIndex);
    }

    @Override
    public void deletarLista() {
        lista.clear();
        fireTableDataChanged();
    }

    @Override
    public void addLista(List<?> lista) {
        this.lista.addAll(lista);
        fireTableDataChanged();
    }

    @Override
    public List<?> getLista() {
        return lista;
    }

    @Override
    public void resetarLista() {
        
    }

    @Override
    public void atualizarItem(Object object) {
        
    }

    @Override
    public void aplicarFiltro(int column, String filtro) {
        
    }

    @Override
    public void aplicarFiltroLetra(String column, String filtro) {
        
    }

    @Override
    public void atualizarItem(Object object, int rowIndex) {
        
    }

    @Override
    public void setSelectedRow(int row) {
        
    }
    
}

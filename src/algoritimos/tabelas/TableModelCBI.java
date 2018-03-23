package algoritimos.tabelas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tiago D. Teixeira
 */
public abstract class TableModelCBI extends AbstractTableModel {

    private String[] columnNames;

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setColmunName(String... columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    public abstract void addObject(Object Object);

    public abstract void removeObject(int rowIndex);

    public abstract Object getObject(int rowIndex);

    public abstract void deletarLista();

    public abstract void addLista(List<?> lista);

    public abstract List<?> getLista();

    public abstract void resetarLista();

    public abstract void atualizarItem(Object object);

    public abstract void atualizarItem(Object object, int rowIndex);

    public abstract void setSelectedRow(int row);

    public abstract void aplicarFiltro(int column, String filtro);

    public abstract void aplicarFiltroLetra(String column, String filtro);

    public abstract List<?> clonar();
}

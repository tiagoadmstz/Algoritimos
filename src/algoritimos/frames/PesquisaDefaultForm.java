/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.frames;

import algoritimos.listener.ListenerCBI;
import algoritimos.listener.ListenerCBIAdapter;
import algoritimos.regex.REGEX;
import algoritimos.regex.RegexUtil;
import algoritimos.tabelas.TableModelCBI;
import algoritimos.util.OPERACAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.CaretEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tiago
 */
public final class PesquisaDefaultForm extends javax.swing.JFrame {

    private final PesquisaDefaultListener listener;

    /**
     * Construtor que inicia todos os paramentros necessários para utilização do
     * formulário de pesquisa padrão
     *
     * @param legenda Legenda do formulário de pesquisa
     * @param model Modelo que será utilizado no formulário de pesquisa
     * @param listenerSolicitante Listener que solicitou a pesquisa, caso for
     * popular o form com a pesquisa
     * @param modelSolicitante Model que solicitou a pesquisa, caso for inserir
     * dados em outra tabela
     * @param tamanho Array com o tamanho de cada coluna da pesquisa
     */
    public PesquisaDefaultForm(String legenda, TableModelCBI model, ListenerCBI listenerSolicitante, TableModelCBI modelSolicitante, int... tamanho) {
        initComponents();
        listener = new PesquisaDefaultListener(this);
        this.setLegenda(legenda);
        this.setTableModel(model);
        this.setTableModelSolicitante(modelSolicitante);
        this.setListenerSolicitante(listenerSolicitante);
        this.setColumnSize(tamanho);
    }

    public void setTableModel(TableModelCBI tableModel) {
        listener.addModel(tableModel);
    }

    public void setTableModelSolicitante(TableModelCBI tableModelSolicitante) {
        listener.setModelSolicitante(tableModelSolicitante);
    }

    public void setColumnSize(int... tamanho) {
        listener.setColumnSize(tamanho);
    }

    public void setListenerSolicitante(ListenerCBI listenerSolicitante) {
        listener.setListenerSolicitante(listenerSolicitante);
    }

    public void setLegenda(String legenda) {
        this.setTitle(legenda);
    }

    public JComboBox getCbPesqusia() {
        return cbPesqusia;
    }

    public void setCbPesqusia(JComboBox cbPesqusia) {
        this.cbPesqusia = cbPesqusia;
    }

    public JTextField getTxtPesquisa() {
        return txtPesquisa;
    }

    public void setTxtPesquisa(JTextField txtPesquisa) {
        this.txtPesquisa = txtPesquisa;
    }

    public JTable getTbPesquisa() {
        return tbPesquisa;
    }

    public void setTbPesquisa(JTable tbPesquisa) {
        this.tbPesquisa = tbPesquisa;
    }

    public JMenuItem getItemFechar() {
        return itemFechar;
    }

    public void setItemFechar(JMenuItem itemFechar) {
        this.itemFechar = itemFechar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelMain = new javax.swing.JPanel();
        cbPesqusia = new javax.swing.JComboBox();
        txtPesquisa = new javax.swing.JTextField();
        scPesquisa = new javax.swing.JScrollPane();
        tbPesquisa = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        itemFechar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa");

        tbPesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPesquisa.setAutoscrolls(false);
        scPesquisa.setViewportView(tbPesquisa);

        javax.swing.GroupLayout painelMainLayout = new javax.swing.GroupLayout(painelMain);
        painelMain.setLayout(painelMainLayout);
        painelMainLayout.setHorizontalGroup(
            painelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelMainLayout.createSequentialGroup()
                        .addComponent(cbPesqusia, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa))
                    .addComponent(scPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelMainLayout.setVerticalGroup(
            painelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPesqusia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuBar.setPreferredSize(new java.awt.Dimension(0, 0));

        itemFechar.setText("Fechar");
        itemFechar.setActionCommand("fechar");
        menuArquivo.add(itemFechar);

        menuBar.add(menuArquivo);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbPesqusia;
    private javax.swing.JMenuItem itemFechar;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel painelMain;
    private javax.swing.JScrollPane scPesquisa;
    private javax.swing.JTable tbPesquisa;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables

    private final class PesquisaDefaultListener extends ListenerCBIAdapter {

        private final PesquisaDefaultForm form;
        private TableModelCBI model = null;
        private TableModelCBI modelSolicitante = null;
        private ListenerCBI listenerSolicitante = null;
        private final PesquisaRenderer renderer;
        private TableRowSorter sorter;

        public PesquisaDefaultListener(PesquisaDefaultForm form) {
            this.form = form;
            renderer = new PesquisaRenderer();
            form.getTxtPesquisa().requestFocus();
            attachListener();
        }

        @Override
        public void attachListener() {
            form.getTbPesquisa().addMouseListener(this);
            form.getTxtPesquisa().addCaretListener(this);
            form.getCbPesqusia().addItemListener(this);
            form.getItemFechar().addActionListener(this);
            fecharESC(form.getItemFechar());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "fechar":
                    fechar(form);
                    break;
            }
        }

        public void addModel(TableModelCBI model) {
            this.model = model;
            form.getTbPesquisa().setModel(model);
            sorter = new TableRowSorter(model);
            setColumnFilter(tbPesquisa, sorter);
            form.getTbPesquisa().setRowSorter(sorter);
            for (int i = 0; i < model.getColumnCount(); i++) {
                form.getCbPesqusia().addItem(model.getColumnName(i));
            }
            setColumnDesign(tbPesquisa, renderer);
        }

        public void setColumnSize(int... tamanho) {
            if (tamanho != null && tamanho.length > 0) {
                tbPesquisa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                this.setColumnSize(form.getTbPesquisa(), tamanho);
            }
        }

        /**
         * Adiciona o tablemodel do formulário solicitante para fazer as adições
         * da lista
         *
         * @param modelSolicitante modelo da tabela solicitante
         */
        public void setModelSolicitante(TableModelCBI modelSolicitante) {
            this.modelSolicitante = modelSolicitante;
        }

        /**
         * Adiciona um listener do formulário solicitante para fazer adições de
         * objetos
         *
         * @param listenerSolicitante listener do formulário solicitante
         */
        public void setListenerSolicitante(ListenerCBI listenerSolicitante) {
            this.listenerSolicitante = listenerSolicitante;
        }

        /**
         * Campo utilizado para realizar a pesquisa de texto dinamico e ser
         * utilizado para fazer o link de dados ao receber um duplo clique
         *
         * @param campoDescricao nome do campo de descrição da tabela
         * @param column número da coluna onde fica o campo de descrição
         */
        public void setCampoDescricao(int column) {
            renderer.setColumnLink(column);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                if (listenerSolicitante != null) {
                    listenerSolicitante.setDados(model.getObject(sorter.convertRowIndexToModel(form.getTbPesquisa().getSelectedRow())));
                    listenerSolicitante.getDados();
                    listenerSolicitante.setEnableButtons(OPERACAO.SALVAR);
                    form.dispose();
                }

                if (modelSolicitante != null) {
                    modelSolicitante.addObject(model.getObject(sorter.convertRowIndexToModel(form.getTbPesquisa().getSelectedRow())));
                    form.dispose();
                }
            } else if (e.getClickCount() == 1) {
                form.getCbPesqusia().setSelectedIndex(form.getTbPesquisa().getSelectedColumn());
            }
        }

        @Override
        public void caretUpdate(CaretEvent e) {
            sorter.setRowFilter(RowFilter.regexFilter(RegexUtil.getRegex(REGEX.CONTEM, form.getTxtPesquisa().getText()), form.getCbPesqusia().getSelectedIndex()));
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED || e.getStateChange() == ItemEvent.DESELECTED) {
                form.getTxtPesquisa().requestFocus();
            }
        }

        private class PesquisaRenderer extends DefaultTableCellRenderer {

            private final Color gray = new Color(225, 225, 225);
            private final Color white = new Color(255, 255, 255);
            private int column = 0;

            public void setColumnLink(int column) {
                this.column = column;
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    renderer.setBackground(Color.blue);
                    renderer.setForeground(white);
                } else if (row % 2 != 0 && column != this.column) {
                    renderer.setForeground(Color.black);
                    renderer.setBackground(gray);
                } else if (row % 2 == 0 && column != this.column) {
                    renderer.setForeground(Color.black);
                    renderer.setBackground(white);
                } else if (row % 2 != 0 && column == this.column) {
                    renderer.setForeground(Color.blue);
                    renderer.setBackground(gray);
                } else if (row % 2 == 0 && column == this.column) {
                    renderer.setForeground(Color.blue);
                    renderer.setBackground(white);
                }

                return renderer;
            }

        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.listener;

import algoritimos.annotations.MapFrameField;
import algoritimos.beans.JTextFieldCBI;
import algoritimos.calculos.Datas;
import algoritimos.cast.CastFactory;
import algoritimos.controle.ControleInstancias;
import algoritimos.dao.EntityManagerHelper;
import algoritimos.dao.EntityManagerHelper.PERSISTENCE_UNIT;
import algoritimos.dao.JPAHelper;
import algoritimos.frames.ConsultaForm;
import algoritimos.frames.PesquisaDefaultForm;
import algoritimos.tabelas.DefaultCBIHeaderRenderer;
import algoritimos.tabelas.TableModelCBI;
import algoritimos.util.ManipulaFrames;
import algoritimos.util.OPERACAO;
import algoritimos.util.ScrollPaneUtil;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tiago D. Teixeira
 */
public abstract class ListenerCBI implements ActionListener, ListSelectionListener,
        KeyListener, MouseListener, FocusListener, ItemListener, WindowListener, CaretListener {

    public ListenerCBI() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //form.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        switch (e.getActionCommand()) {
            case "novo":
                break;
            case "cancelar":
                break;
            case "salvar":
                break;
            case "alterar":
                break;
            case "editar":
                break;
            case "fechar":
                break;
            case "imprimir":
                break;
            case "excluir":
                break;
            case "pesquisar":
                break;
        }
        //form.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * @param textField campo de identificação separado em painel próprio
     * @param jPanel lista de paines que serão manipulados pelo método
     * @param buttons iista de botões que serão manipulados pelo método
     */
    public void novo(JTextField textField, List<JPanel> jPanel, List<JComponent> buttons) {
        if (textField != null) {
            textField.setText("");
        }
        this.enableOrDisabelComponentsPanel(jPanel, OPERACAO.NOVO);
        this.setEnableButtons(OPERACAO.NOVO, buttons);
    }

    public void enableOrDisabelComponentsPanel(List<JPanel> jPanel, OPERACAO operation) {
        jPanel.stream().forEach((jp) -> {
            ManipulaFrames.enableDisableComponentJFrame(operation, jp.getComponents());
        });
    }

    private void apagarDadosPaineis(List<JPanel> jPanel) {
        jPanel.stream().forEach((jp) -> {
            for (Component cpt : jp.getComponents()) {
                if (cpt instanceof JTextField) {
                    JTextField txt = (JTextField) cpt;
                    txt.setText("");
                } else if (cpt instanceof JScrollPane) {
                    JScrollPane scp = (JScrollPane) cpt;
                    for (Component s : scp.getViewport().getComponents()) {
                        if (s instanceof JTable) {
                            JTable tb = (JTable) s;
                            TableModelCBI model = (TableModelCBI) tb.getModel();
                            model.deletarLista();
                        }
                    }
                }
            }
        });
    }

    public void cancelar(List<JPanel> jPanel, List<JComponent> buttons) {
        //this.apagarDadosPaineis(jPanel);
        this.enableOrDisabelComponentsPanel(jPanel, OPERACAO.CANCELAR);
        this.setEnableButtons(OPERACAO.CANCELAR, buttons);
    }

    public void editar(List<JPanel> jPanel, List<JComponent> buttons) {
        this.enableOrDisabelComponentsPanel(jPanel, OPERACAO.EDITAR);
        this.setEnableButtons(OPERACAO.EDITAR, buttons);
    }

    public void fechar(JFrame jFrame) {
        if ((JOptionPane.showConfirmDialog(jFrame, "Deseja realmente fechar o formulário?", "Fechar Formulário", JOptionPane.YES_NO_OPTION)) == 0) {
            jFrame.dispose();
        }
    }

    public void fecharSistema(JFrame jFrame) {
        if ((JOptionPane.showConfirmDialog(jFrame, "Deseja realmente encerrar o sistema?", "Encerrar Sistema", JOptionPane.YES_NO_OPTION)) == 0) {
            System.exit(0);
        }
    }

    public void fecharESC(JMenuItem menuItem) {
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        menuItem.setAccelerator(escape);
    }

    public abstract void attachListener();

    public abstract JPAHelper getJPAHelper();

    public abstract void addModel();

    public abstract void carregarListeners();

    public abstract void carregarPaineis();

    /**
     * Pega um objeto enviado de outro formulário e inclui no objeto local
     *
     * @param object = Entidade controlada pelo painel
     */
    public abstract void setDados(Object object);

    /**
     * Pega os dados do formulário e coloca no modelo de objeto EX:
     * produto.setQuantidade(form.getTxtQuantidade.getText());
     */
    public abstract void setDados();

    /**
     * Este método utiliza recursos de refletion com annotations para recuperar
     * dados dos formulários dinâmicamente
     *
     * @param form Formulário que será trabalhado
     * @param ob Objeto que é representado pelo formulário
     */
    public void setDados(JFrame form, Object ob) {
        for (Method mt : form.getClass().getMethods()) { //pega todos os metodos do formulário
            if (mt.isAnnotationPresent(MapFrameField.class)) { //verifica se são do tipo MapFrameFields
                MapFrameField map = mt.getAnnotation(MapFrameField.class); //recupera referencia da anotação
                try {
                    Method setMethod;
                    String referenceField;
                    String subClassReference = map.subClassReference();
                    try {
                        referenceField = map.referenceField().replaceFirst("^(\\w{1})", map.referenceField().substring(0, 1).toUpperCase());
                    } catch (Exception ex) {
                        break;
                    }

                    //pega o método get do objeto de referencia
                    if (!Objects.equals("", subClassReference)) {
                        subClassReference = !Objects.equals("", map.subClassReference()) ? map.subClassReference().replaceFirst("^(\\w{1})", map.subClassReference().substring(0, 1).toUpperCase()) : null;
                        Method getMethod = ob.getClass().getMethod("get".concat(subClassReference));
                        ob = getMethod.invoke(ob);
                        setMethod = ob.getClass().getMethod("set".concat(referenceField), map.typeReference());
                    } else {
                        try {
                            setMethod = ob.getClass().getMethod("set".concat(referenceField), map.typeReference());
                        } catch (Exception ex) {
                            setMethod = ob.getClass().getMethod("set".concat(referenceField), CastFactory.castReference(map.typeReference()));
                        }
                    }

                    //verifica o tipo de retorno do getter
                    if (mt.getReturnType() == JTextFieldCBI.class | mt.getReturnType() == JTextField.class | mt.getReturnType() == JTextArea.class) {
                        //pega o método set referenciado na variável sm e invoca o método getText do TextField
                        setMethod.invoke(ob, CastFactory.cast((mt.invoke(form).getClass().getMethod("getText")).invoke(mt.invoke(form)), map.typeReference()));
                    } else if (mt.getReturnType() == JComboBox.class) {
                        String metodo = null;
                        if (map.typeReference() == String.class) {
                            metodo = "getSelectedItem";
                        } else if (map.typeReference() == Integer.class) {
                            metodo = "getSelectedIndex";
                        }
                        setMethod.invoke(ob, (mt.invoke(form).getClass().getMethod(metodo)).invoke(mt.invoke(form)));
                    } else if (mt.getReturnType() == JCheckBox.class) {
                        setMethod.invoke(ob, (mt.invoke(form).getClass().getMethod("isSelected")).invoke(mt.invoke(form)));
                    } else if (mt.getReturnType() == ButtonGroup.class) {
                        Enumeration<AbstractButton> e = (Enumeration<AbstractButton>) (mt.invoke(form).getClass().getMethod("getElements").invoke(mt.invoke(form)));
                        while (e.hasMoreElements()) {
                            AbstractButton ab = e.nextElement();
                            if (ab.isSelected()) {
                                setMethod.invoke(ob, ab.getText());
                                break;
                            }
                        }
                    } else if (mt.getReturnType() == JTable.class) {
                        TableModelCBI model = (TableModelCBI) mt.invoke(form).getClass().getMethod("getModel").invoke(mt.invoke(form));
                        setMethod.invoke(ob, model.getLista());
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(ListenerCBI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Pega os dados de um objeto e mostra nos campos do formulário EX:
     * form.getTxtQuantidade().setText(produto.getQuantidade());
     */
    public abstract void getDados();

    /**
     * Este método popula o formulário de forma dinamica utilizando recursos de
     * refletion com annotations
     *
     * @param form Formulário que será trabalhado
     * @param ob Objeto representado pelo formulário
     */
    public void getDados(JFrame form, Object ob) {
        for (Method mt : form.getClass().getMethods()) { //pega todos os metodos do formulário
            if (mt.isAnnotationPresent(MapFrameField.class)) { //verifica se são do tipo SETTER
                MapFrameField map = mt.getAnnotation(MapFrameField.class); //recupera referencia da anotação
                try {
                    Method getMethod;
                    String referenceField;
                    String subClassReference = map.subClassReference();
                    try {
                        referenceField = map.referenceField().replaceFirst("^(\\w{1})", map.referenceField().substring(0, 1).toUpperCase());
                    } catch (Exception ex) {
                        break;
                    }

                    //pega o método get do objeto de referencia
                    if (!Objects.equals("", subClassReference)) {
                        subClassReference = !Objects.equals("", map.subClassReference()) ? map.subClassReference().replaceFirst("^(\\w{1})", map.subClassReference().substring(0, 1).toUpperCase()) : null;
                        getMethod = ob.getClass().getMethod("get".concat(subClassReference));
                        ob = getMethod.invoke(ob);
                        getMethod = ob.getClass().getMethod("get".concat(referenceField));
                    } else {
                        if (Objects.equals(Boolean.class, map.typeReference()) | Objects.equals(boolean.class, map.typeReference())) {
                            getMethod = ob.getClass().getMethod("is".concat(referenceField));
                        } else {
                            getMethod = ob.getClass().getMethod("get".concat(referenceField));
                        }
                    }
                    //pega o método set referenciado na variável sm e invoca o método getText do TextField
                    if (mt.getReturnType() == JTextFieldCBI.class | mt.getReturnType() == JTextField.class | mt.getReturnType() == JTextArea.class) {
                        mt.invoke(form).getClass().getMethod("setText", String.class).invoke(mt.invoke(form), CastFactory.cast(getMethod.invoke(ob), String.class));
                    } else if (mt.getReturnType() == JComboBox.class) {
                        if (map.typeReference() == String.class) {
                            mt.invoke(form).getClass().getMethod("setSelectedItem", Object.class).invoke(mt.invoke(form), CastFactory.cast(getMethod.invoke(ob), Object.class));
                        } else if (map.typeReference() == Integer.class) {
                            mt.invoke(form).getClass().getMethod("setSelectedIndex", int.class).invoke(mt.invoke(form), getMethod.invoke(ob));
                        }
                    } else if (mt.getReturnType() == JCheckBox.class) {
                        mt.invoke(form).getClass().getMethod("setSelected", map.typeReference()).invoke(mt.invoke(form), getMethod.invoke(ob));
                    } else if (mt.getReturnType() == ButtonGroup.class) {
                        Enumeration<AbstractButton> e = (Enumeration<AbstractButton>) (mt.invoke(form).getClass().getMethod("getElements").invoke(mt.invoke(form)));
                        while (e.hasMoreElements()) {
                            AbstractButton ab = e.nextElement();
                            if (Objects.equals(getMethod.invoke(ob), ab.getText())) {
                                ab.setSelected(true);
                            } else {
                                ab.setSelected(false);
                            }
                        }
                    } else if (mt.getReturnType() == JTable.class) {
                        TableModelCBI model = (TableModelCBI) mt.invoke(form).getClass().getMethod("getModel").invoke(mt.invoke(form));
                        model.addLista((List<?>) getMethod.invoke(ob));
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(ListenerCBI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public abstract void setMaxLegthTextFields();

    public abstract void setEnableButtons(OPERACAO codFunction);

    public abstract void salvar(int tipo);

    /**
     * Salva um registro e faz operações básicas de controle, atualizar o
     * formulário ou tabelas fica por conta do solicitante.
     *
     * @param tipo 0 = salvar, 1 = alterar
     * @param jpaHelper uma instância de JPAHelper
     * @param object o objeto a ser salvo na base de dados
     * @param form o formulário que solicitou a ação
     * @param paineis lista de paineis a serem manipulados
     * @param botoes lista de botoes a serem manipulados
     * @return retorna se a transação foi bem sucedida
     */
    public boolean salvar(int tipo, JPAHelper jpaHelper, Object object, JFrame form, List<JPanel> paineis, List<JComponent> botoes) {
        switch (tipo) {
            case 0: //salvar
                if (JOptionPane.showConfirmDialog(form, "Deseja salvar o registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
                    this.setDados();
                    jpaHelper.getOperation(jpaHelper.INSERT, object);
                    this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
                    this.setEnableButtons(OPERACAO.SALVAR, botoes);
                    this.getDados();
                    return true;
                }
                return false;
            case 1: //alterar
                if (JOptionPane.showConfirmDialog(form, "Deseja salvar as alterações feitas no registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
                    this.setDados();
                    jpaHelper.getOperation(jpaHelper.UPDATE, object);
                    this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
                    this.setEnableButtons(OPERACAO.SALVAR, botoes);
                    return true;
                }
                return false;
            case 3: //save or update
                if (JOptionPane.showConfirmDialog(form, "Deseja salvar as alterações feitas no registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
                    this.setDados();
                    jpaHelper.getOperation(jpaHelper.SAVEORUPDATE, object);
                    this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
                    this.setEnableButtons(OPERACAO.SALVAR, botoes);
                    return true;
                }
                return false;
        }
        return false;
    }

    /**
     * Salva um registro e faz operações básicas de controle, atualizar o
     * formulário ou tabelas fica por conta do solicitante.
     *
     * @param tipo 0 = salvar, 1 = alterar
     * @param emh uma instância de EntityManangerHelper
     * @param persistence_unit unidade de persistencia a acessar
     * @param object o objeto a ser salvo na base de dados
     * @param form o formulário que solicitou a ação
     * @param paineis lista de paineis a serem manipulados
     * @param botoes lista de botoes a serem manipulados
     * @return retorna se a transação foi bem sucedida
     */
    public boolean salvar(int tipo, EntityManagerHelper emh, PERSISTENCE_UNIT persistence_unit, Object object, JFrame form, List<JPanel> paineis, List<JComponent> botoes) {
        switch (tipo) {
            case 0: //salvar
                if (JOptionPane.showConfirmDialog(form, "Deseja salvar o registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
                    this.setDados();
                    emh.getOperation(EntityManagerHelper.OPERATION_TYPE.SAVE, object, persistence_unit);
                    this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
                    this.setEnableButtons(OPERACAO.SALVAR, botoes);
                    //this.getDados();
                    return true;
                }
                return false;
            case 1: //alterar
                if (JOptionPane.showConfirmDialog(form, "Deseja salvar as alterações feitas no registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
                    this.setDados();
                    emh.getOperation(EntityManagerHelper.OPERATION_TYPE.UPDATE, object, persistence_unit);
                    this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
                    this.setEnableButtons(OPERACAO.SALVAR, botoes);
                    return true;
                }
                return false;
        }
        return false;
    }

    public abstract void alterar();

    /**
     * Este método faz a manipulação do formulário e botões apenas, caso precise
     * fazer mais coisas sobreescreva o método alterar() e utilize este como
     * auxiliar com a chamada de super.
     *
     * @param paineis lista de painéis
     * @param botoes lista de botões
     */
    public void alterar(List<JPanel> paineis, List<JComponent> botoes) {
        this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
        this.setEnableButtons(OPERACAO.SALVAR, botoes);
    }

    public abstract void editar();

    public abstract void imprimir();

    public abstract void pesquisar();

    public ConsultaForm pesquisar(ListenerCBI listenerSolicitante) {
        /*ConsultaForm consulta = (ConsultaForm) ControleInstancias.getInstance(ConsultaForm.class.getName());
        if (consulta == null) {
        consulta = new ConsultaForm();
        ControleInstancias.setControleInstancias(ConsultaForm.class.getName(), consulta);
        }
        consulta.setListenerSolicitante(listenerSolicitante);
        return consulta;*/
        return null;
    }

    /**
     * Este método retorna um formulário de pesquisa padrão simplificado para
     * pesquisa rápidas
     *
     * @param titulo Título do formulário que será mostrado para o usuário
     * @param model Modelo da tabela contendo os dados de pesquisa
     * @param modelSolicitante Modelo da tabela onde os dados serão inseridos
     * caso exista, caso não informe 'null'
     * @param listenerSolicitante Listener do formulário solicitante onde serão
     * inseridos os dados da pesquisa, caso não informe 'null'
     * @return Retorna um formulário de pesquisa simplificada. Será necessário
     * fazer o setVisible
     */
    public PesquisaDefaultForm pesquisar(String titulo, TableModelCBI model, TableModelCBI modelSolicitante, ListenerCBI listenerSolicitante) {
        PesquisaDefaultForm pesquisa = (PesquisaDefaultForm) ControleInstancias.getInstance(PesquisaDefaultForm.class.getName());
        if (pesquisa == null) {
            pesquisa = new PesquisaDefaultForm(titulo, model, listenerSolicitante, modelSolicitante);
            ControleInstancias.setControleInstancias(PesquisaDefaultForm.class.getName(), pesquisa);
        }
        return pesquisa;
    }

    public abstract void deletar();

    /**
     * Está é um método básico para deletar registros simples e fazer a
     * manipulação do formulário e botões. Para itens complexos sobreescreva o
     * método deletar().
     *
     * @param object item a ser deletado
     * @param form formulário solicitante
     * @param jpaHelper instância de JPAHelper
     * @param paineis lista de paineis
     * @param botoes lista de botoes
     * @return
     */
    public boolean deletar(Object object, JFrame form, JPAHelper jpaHelper, List<JPanel> paineis, List<JComponent> botoes) {
        if (JOptionPane.showConfirmDialog(form, "Deseja deletar o registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
            this.setDados();
            jpaHelper.getOperation(jpaHelper.DELETE, object);
            enableOrDisabelComponentsPanel(paineis, OPERACAO.CANCELAR);
            setEnableButtons(OPERACAO.CANCELAR, botoes);
            return true;
        }
        return false;
    }

    public boolean deletar(Object object, JFrame form, EntityManagerHelper emh, PERSISTENCE_UNIT persistence_unit, List<JPanel> paineis, List<JComponent> botoes) {
        if (JOptionPane.showConfirmDialog(form, "Deseja deletar o registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
            this.setDados();
            emh.getOperation(EntityManagerHelper.OPERATION_TYPE.DELETE, object, persistence_unit);
            enableOrDisabelComponentsPanel(paineis, OPERACAO.CANCELAR);
            setEnableButtons(OPERACAO.CANCELAR, botoes);
            return true;
        }
        return false;
    }

    public void setEnableButtons(OPERACAO codigoOperacao, List<JComponent> componentes) {
        ManipulaFrames.operacaoEnableOrder(codigoOperacao, componentes);
    }

    public void setScrollPanelConfig(JScrollPane scrollPane) {
        ScrollPaneUtil.scrollPanelConfigurator(scrollPane);
    }

    public void addItens(String tipo) {

    }

    public void removeItens(String tipo) {

    }

    public abstract void carregarListas();

    public void apagarTabelas() {

    }

    public void setColumnDesign(JTable table, DefaultTableCellRenderer tableRenderer) {
        //rederização das colunas
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnClass(i) != Boolean.class) {
                table.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
            }
        }
    }

    public void setColumnFilter(JTable table, TableRowSorter rowSorter) {
        int columns = table.getModel().getColumnCount();
        for (int i = 0; i < columns; i++) {
            rowSorter.setSortable(i, false);
            table.getColumnModel().getColumn(i).setHeaderRenderer(new DefaultCBIHeaderRenderer(rowSorter));
        }
    }

    public void addColumnComboBox(JTable table, int colunmIndex, JComboBox comboBox) {
        table.getColumnModel().getColumn(colunmIndex).setCellEditor(new DefaultCellEditor(comboBox));
    }

    public void setColumnSize(JTable table, int... tamanho) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(tamanho[i]);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        OnChangeListener.EventListener(e);
    }

    /**
     * Este método é utilizado como padrão para campos de data e hora protegendo
     * o campo contra entradas inválidas e formatando a data e a hora informados
     * pelo usuário para um formato padrão, para utilizar este método basta
     * colocar o nome do campo de data ou hora e adicionar um FocusListener
     * passar super.focusLost(e) na sobreescrita do método se existir, caso
     * contrário será utilizado o método padrão.
     *
     * @param e FocusEvent passado pelo campo
     */
    @Override
    public void focusLost(FocusEvent e) {
        OnChangeListener.EventListener(e);
        if (OnChangeListener.getChangeEvent() == true) {
            if (e.getSource() instanceof JTextField || e.getSource() instanceof JTextFieldCBI) {
                JTextField textField = (JTextField) e.getComponent();
                if (!"".equals(textField.getText())) {
                    switch (textField.getName()) {
                        case "data":
                            textField.setText(Datas.getDateString(textField.getText(), 1));
                            break;
                        case "hora":
                            textField.setText(Datas.getHour(textField.getText(), 1));
                            break;
                    }
                }
            }

            /*if (e.getSource() instanceof JComboBox){
             JComboBox comboBox = (JComboBox) e.getComponent();
             if(!"".equals(comboBox.getSelectedItem().toString())){
             //escrever a ação aqui
             }
             }*/
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        //ControleInstancias.removeInstance(null);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}

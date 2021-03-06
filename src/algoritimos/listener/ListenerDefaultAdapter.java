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
import algoritimos.frames.ConsultaForm;
import algoritimos.frames.PesquisaDefaultForm;
import algoritimos.tabelas.DefaultCBIHeaderRenderer;
import algoritimos.tabelas.TableModelDefaultAdapter;
import algoritimos.util.ManipulaFrames;
import algoritimos.util.MessageFactory;
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
import java.io.Serializable;
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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Tiago D. Teixeira
 * @param <T>
 */
public abstract class ListenerDefaultAdapter<T> implements Serializable, ActionListener, ListSelectionListener,
        KeyListener, MouseListener, FocusListener, ItemListener, WindowListener, CaretListener {
    
    private static final long serialVersionUID = -7885949837839388251L;
    protected final T form;

    public ListenerDefaultAdapter(T form) {
        this.form = form;
    }

    protected abstract void initComponents();

    protected abstract void attachListener();

    protected void addModel() {
    }

    protected void carregarPaineis() {
    }

    protected void carregarListas(){}

    public void setEnableButtons(OPERACAO codFunction) {
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

    //métodos de manipulação de frames
    protected void novo() {
    }

    protected void cancelar() {
    }

    protected void imprimir() {
    }

    protected void pesquisar() {
    }

    /**
     * @param textField campo de identificação separado em painel próprio
     * @param jPanel lista de paines que serão manipulados pelo método
     * @param buttons iista de botões que serão manipulados pelo método
     */
    protected void novo(JTextField textField, List<JPanel> jPanel, List<JComponent> buttons) {
        if (textField != null) {
            textField.setText("");
        }
        this.enableOrDisabelComponentsPanel(jPanel, OPERACAO.NOVO);
        this.setEnableButtons(OPERACAO.NOVO, buttons);
    }

    protected void cancelar(List<JPanel> jPanel, List<JComponent> buttons) {
        //this.apagarDadosPaineis(jPanel);
        this.enableOrDisabelComponentsPanel(jPanel, OPERACAO.CANCELAR);
        this.setEnableButtons(OPERACAO.CANCELAR, buttons);
    }

    protected void fechar() {
        JFrame frame = (JFrame) form;
        if (MessageFactory.getMsgApp(MessageFactory.FECHAR_FRAME, frame)) {
            frame.dispose();
        }
    }

    protected void fecharSistema() {
        JFrame frame = (JFrame) form;
        if (MessageFactory.getMsgApp(MessageFactory.FECHAR_SISTEMA, frame)) {
            System.exit(0);
        }
    }

    protected void fecharESC(JMenuItem menuItem) {
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        menuItem.setAccelerator(escape);
    }

    public void setDados(Object object){}
    public void setDados(){}
    public void getDados(){}
    
    /**
     * Este método utiliza recursos de refletion com annotations para recuperar
     * dados dos formulários dinâmicamente
     *
     * @param form Formulário que será trabalhado
     * @param objetos Objeto que é representado pelo formulário
     */
    protected void setDados(JFrame form, Object... objetos) {
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

                    Object ob = objetos[0];

                    //começo da instrução
                    if (objetos.length > 1 && !Objects.equals("", map.controlClassType())) {
                        for (Object obj : objetos) {
                            if (obj.getClass().getName().contains(map.controlClassType())) {
                                ob = obj;
                            }
                        }
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
                        TableModelDefaultAdapter model = (TableModelDefaultAdapter) mt.invoke(form).getClass().getMethod("getModel").invoke(mt.invoke(form));
                        setMethod.invoke(ob, model.clonar());
                    }

                    //fim da instrução
                } catch (NullPointerException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(ListenerDefaultAdapter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Este método popula o formulário de forma dinamica utilizando recursos de
     * refletion com annotations
     *
     * @param form Formulário que será trabalhado
     * @param objetos Objeto representado pelo formulário
     */
    protected void getDados(JFrame form, Object... objetos) {
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

                    Object ob = objetos[0];

                    //começo da instrução
                    if (objetos.length > 1 && !Objects.equals("", map.controlClassType())) {
                        for (Object obj : objetos) {
                            if (obj.getClass().getName().contains(map.controlClassType())) {
                                ob = obj;
                            }
                        }
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
                        TableModelDefaultAdapter model = (TableModelDefaultAdapter) mt.invoke(form).getClass().getMethod("getModel").invoke(mt.invoke(form));
                        model.deletarLista();
                        model.addLista((List<?>) getMethod.invoke(ob));
                    }

                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(ListenerDefaultAdapter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    protected void setEnableButtons(OPERACAO codigoOperacao, List<JComponent> componentes) {
        ManipulaFrames.operacaoEnableOrder(codigoOperacao, componentes);
    }

    protected void setScrollPanelConfig(JScrollPane scrollPane) {
        ScrollPaneUtil.scrollPanelConfigurator(scrollPane);
    }

    protected void enableOrDisabelComponentsPanel(List<JPanel> jPanel, OPERACAO operation) {
        jPanel.stream().forEach((jp) -> {
            ManipulaFrames.enableDisableComponentJFrame(operation, jp.getComponents());
        });
    }

    protected void apagarDadosPaineis(List<JPanel> jPanel) {
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
                            TableModelDefaultAdapter model = (TableModelDefaultAdapter) tb.getModel();
                            model.deletarLista();
                        }
                    }
                }
            }
        });
    }

    //métodos para persistencia de objetos
    protected void salvar() {
    }

    protected void deletar() {
    }

    protected void alterar() {
    }

    protected void editar() {
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
    protected boolean salvar(int tipo, EntityManagerHelper emh, String persistence_unit, JFrame form, List<JPanel> paineis, List<JComponent> botoes, Object... object) {
        switch (tipo) {
            case 0: //salvar
                if (JOptionPane.showConfirmDialog(form, "Deseja salvar o registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
                    try {
                        this.setDados(form, object);
                    } catch (Exception ex) {
                        //this.setDados();
                    }
                    for (Object ob : object) {
                        emh.getOperation(EntityManagerHelper.SAVE, ob, persistence_unit);
                    }
                    this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
                    this.setEnableButtons(OPERACAO.SALVAR, botoes);
                    try {
                        this.getDados(form, object);
                    } catch (Exception ex) {
                        //this.getDados();
                    }
                    return true;
                }
                return false;
            case 1: //alterar
                if (JOptionPane.showConfirmDialog(form, "Deseja salvar as alterações feitas no registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
                    try {
                        this.setDados(form, object);
                    } catch (Exception ex) {
                        //this.setDados();
                    }
                    for (Object ob : object) {
                        emh.getOperation(EntityManagerHelper.UPDATE, ob, persistence_unit);
                    }
                    this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
                    this.setEnableButtons(OPERACAO.SALVAR, botoes);
                    return true;
                }
                return false;
        }
        return false;
    }

    /**
     * Este método faz a manipulação do formulário e botões apenas, caso precise
     * fazer mais coisas sobreescreva o método alterar() e utilize este como
     * auxiliar com a chamada de super.
     *
     * @param paineis lista de painéis
     * @param botoes lista de botões
     */
    protected void alterar(List<JPanel> paineis, List<JComponent> botoes) {
        this.enableOrDisabelComponentsPanel(paineis, OPERACAO.SALVAR);
        this.setEnableButtons(OPERACAO.SALVAR, botoes);
    }

    protected void editar(List<JPanel> jPanel, List<JComponent> buttons) {
        this.enableOrDisabelComponentsPanel(jPanel, OPERACAO.EDITAR);
        this.setEnableButtons(OPERACAO.EDITAR, buttons);
    }

    protected ConsultaForm pesquisar(ListenerDefaultAdapter listenerSolicitante) {
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
    protected PesquisaDefaultForm pesquisar(String titulo, TableModelDefaultAdapter model, TableModelDefaultAdapter modelSolicitante, ListenerDefaultAdapter listenerSolicitante, int... tamanho) {
        PesquisaDefaultForm pesquisa = (PesquisaDefaultForm) ControleInstancias.getInstance(PesquisaDefaultForm.class.getName());
        if (pesquisa == null) {
            pesquisa = new PesquisaDefaultForm(titulo, model, listenerSolicitante, modelSolicitante, tamanho);
            ControleInstancias.setControleInstancias(PesquisaDefaultForm.class.getName(), pesquisa);
        }
        return pesquisa;
    }

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
    protected boolean deletar(Object object, JFrame form, EntityManagerHelper emh, String persistence_unit, List<JPanel> paineis, List<JComponent> botoes) {
        if (JOptionPane.showConfirmDialog(form, "Deseja deletar o registro?", "Salvar Registro", JOptionPane.YES_NO_OPTION) == 0) {
            //this.setDados();
            emh.getOperation(EntityManagerHelper.DELETE, object, persistence_unit);
            enableOrDisabelComponentsPanel(paineis, OPERACAO.CANCELAR);
            setEnableButtons(OPERACAO.CANCELAR, botoes);
            return true;
        }
        return false;
    }
    
    //métodos de Table Model
    protected void setColumnDesign(JTable table, DefaultTableCellRenderer tableRenderer) {
        //rederização das colunas
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnClass(i) != Boolean.class) {
                table.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
            }
        }
    }

    protected void setColumnFilter(JTable table, TableRowSorter rowSorter) {
        int columns = table.getModel().getColumnCount();
        for (int i = 0; i < columns; i++) {
            rowSorter.setSortable(i, false);
            table.getColumnModel().getColumn(i).setHeaderRenderer(new DefaultCBIHeaderRenderer(rowSorter));
        }
    }

    protected void addColumnComboBox(JTable table, int colunmIndex, JComboBox comboBox) {
        table.getColumnModel().getColumn(colunmIndex).setCellEditor(new DefaultCellEditor(comboBox));
    }

    protected void setColumnSize(JTable table, int... tamanho) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(tamanho[i]);
        }
    }

    //métodos de listeners
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
                        case "valor":
                            MaskFormatter mask = new MaskFormatter();
                            String text = textField.getText().replace(",", ".");
                            if (!text.contains(".")) {
                                text = text.concat(".00");
                            } else if (text.contains(".")) {
                                String sufixo = text.substring(text.indexOf(".") + 1, text.length());
                                if (sufixo.length() < 2) {
                                    text = text.concat(sufixo.length() == 1 ? "0" : "00");
                                } else if (sufixo.length() > 2) {
                                    text = text.replace("." + sufixo, "." + sufixo.substring(0, 2));
                                }
                            }
                            text = text.contains("R$") ? text : "R$ ".concat(text);
                            textField.setText(text);
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

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void caretUpdate(CaretEvent e) {
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.util;

import algoritimos.beans.JTextFieldCBI;
import algoritimos.tabelas.TableModelDefaultAdapter;
import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author tiago.teixeira
 */
public class ManipulaFrames {

    public static void setFildsEnableDisable(JFrame form, boolean enable) {

        for (Component comp : form.getComponents()) {
            comp.setEnabled(enable);
        }

    }

    /**
     * Método de manipulação de formulários para ativar e desativar componentes
     * de interface
     *
     * @param codigoOperacao constante indicando a operacao
     * @param components lista de componentes a ser manipulados
     */
    public static void enableDisableComponentJFrame(OPERACAO codigoOperacao, Component[] components) {

        Map<Class, Integer> map = new HashMap();
        Boolean[][] opcoes = new Boolean[][]{
            {true, false, false, false, true}
        };

        map.put(JTextField.class, 0);
        map.put(JTextFieldCBI.class, 0);
        map.put(JComboBox.class, 0);
        map.put(JScrollPane.class, 0);
        map.put(JTextArea.class, 0);
        map.put(JTable.class, 0);
        map.put(JButton.class, 0);
        map.put(JCheckBox.class, 0);
        map.put(JRadioButton.class, 0);
        map.put(JSlider.class, 0);
        map.put(JPasswordField.class, 0);

        for (Component cp : components) {
            if (map.get(cp.getClass()) != null) {
                if (cp instanceof JScrollPane) {
                    JScrollPane sp = (JScrollPane) cp;
                    Component cp2 = sp.getViewport().getComponent(0);
                    cp2.setEnabled(opcoes[map.get(cp2.getClass())][codigoOperacao.valorOperacao]);
                } else {
                    cp.setEnabled(opcoes[map.get(cp.getClass())][codigoOperacao.valorOperacao]);
                }
            }
        }

        limparCampos(codigoOperacao, components);
    }

    /**
     * Este método faz a ativação ou inativação de botões ou itens de menu para
     * cada tipo de operacao do sistema.
     *
     * @param codigoOperacao É o código da operação do enumerador OPERACAO
     * @param componentes lista de botoes ou itens de menu
     */
    public static void operacaoEnableOrder(OPERACAO codigoOperacao, JComponent... componentes) {
        Map<String, Integer> mapEnable = new HashMap();
        mapEnable.put("Novo", 0);
        mapEnable.put("Cancelar", 1);
        mapEnable.put("Alterar", 2);
        mapEnable.put("Salvar", 2);
        mapEnable.put("Editar", 3);
        mapEnable.put("Fechar", 4);
        mapEnable.put("Imprimir", 5);
        mapEnable.put("Deletar", 6);
        mapEnable.put("Pesquisar", 7);
        //"Novo", "Cancelar", "Alterar", "Salvar", "Editar", "Fechar", "Imprimir", "Deletar"
        Boolean[][] opcoes = new Boolean[][]{
            {false, true, true, false, false, false, false, false}, //novo
            {true, false, false, false, false, true, true, true}, //cancelar e deletar
            {true, false, false, true, true, true, true, true}, //alterar
            {true, false, false, true, true, true, true, true}, //salvar
            {false, true, true, false, false, false, false, false} //editar
        };

        for (JComponent cp : componentes) {
            if (cp instanceof JMenuItem) {
                JMenuItem item = (JMenuItem) cp;
                item.setEnabled(opcoes[codigoOperacao.valorOperacao][mapEnable.get(item.getText())]);
                if (Objects.equals("Alterar", item.getText()) && (codigoOperacao.valorOperacao == 0 || codigoOperacao.valorOperacao == 2)) {
                    item.setText("Salvar");
                    item.setActionCommand("salvar");
                } else if (Objects.equals("Salvar", item.getText()) && (codigoOperacao.valorOperacao == 3 || codigoOperacao.valorOperacao == 4)) {
                    item.setText("Alterar");
                    item.setActionCommand("alterar");
                }
            } else if (cp instanceof JButton) {
                JButton bt = (JButton) cp;
                bt.setEnabled(opcoes[codigoOperacao.valorOperacao][mapEnable.get(bt.getText())]);
                if (Objects.equals("Alterar", bt.getText()) && (codigoOperacao.valorOperacao == 0 || codigoOperacao.valorOperacao == 2)) {
                    bt.setText("Salvar");
                    bt.setActionCommand("salvar");
                } else if (Objects.equals("Salvar", bt.getText()) && (codigoOperacao.valorOperacao == 3 || codigoOperacao.valorOperacao == 4)) {
                    bt.setText("Alterar");
                    bt.setActionCommand("alterar");
                }
            }
        }

    }

    /**
     * Este método faz a ativação ou inativação de botões ou itens de menu para
     * cada tipo de operacao do sistema.
     *
     * @param codigoOperacao É o código da operação do enumerador OPERACAO
     * @param componentes lista de botoes ou itens de menu
     */
    public static void operacaoEnableOrder(OPERACAO codigoOperacao, List<JComponent> componentes) {
        if (componentes != null) {
            operacaoEnableOrder(codigoOperacao, componentes.toArray(new JComponent[componentes.size()]));
        }
    }

    public static void enable(Container container, String... exclusoes) {

        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField) {
                comp.setEnabled(!comp.isEnabled());
            } else if (comp instanceof JComboBox) {
                comp.setEnabled(!comp.isEnabled());
            } else if (comp instanceof JButton) {
                comp.setEnabled(!comp.isEnabled());
            } else if (comp instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) comp;
                for (Component comp2 : scroll.getViewport().getComponents()) {
                    if (comp2 instanceof JTextArea) {
                        JTextArea textArea = (JTextArea) comp2;
                        textArea.setEditable(!textArea.isEditable());
                        textArea.setEnabled(!textArea.isEnabled());
                    }
                }
            }
        }
    }

    public static void limparCampos(OPERACAO codigoOperacao, Component[] componentes) {

        if (codigoOperacao != OPERACAO.EDITAR && codigoOperacao != OPERACAO.SALVAR && codigoOperacao != OPERACAO.ALTERAR) {
            for (Component component : componentes) {
                if (component instanceof JFormattedTextField) {
                    JFormattedTextField field = (JFormattedTextField) component;
                    field.setText("");
                } else if (component instanceof JTextField) {
                    JTextField field = (JTextField) component;
                    field.setText("");
                } else if (component instanceof JTextArea) {
                    JTextArea area = (JTextArea) component;
                    area.setText("");
                } else if (component instanceof JScrollPane) {
                    JScrollPane scroll = (JScrollPane) component;
                    for (Component comp : scroll.getViewport().getComponents()) {
                        if (comp instanceof JTextArea) {
                            JTextArea areaTemp = (JTextArea) comp;
                            areaTemp.setText("");
                        } else if (comp instanceof JTable) {
                            JTable tb = (JTable) comp;
                            TableModelDefaultAdapter model = (TableModelDefaultAdapter) tb.getModel();
                            model.deletarLista();
                        }
                    }
                } else if (component instanceof JComboBox) {
                    JComboBox combo = (JComboBox) component;
                    if (combo.getItemCount() > 0) {
                        combo.setSelectedIndex(0);
                    }
                } else if (component instanceof JCheckBox) {
                    JCheckBox ck = (JCheckBox) component;
                    ck.setSelected(false);
                } else if (component instanceof JSlider) {
                    JSlider sd = (JSlider) component;
                    sd.setValue(0);
                }
            }
        }
    }

    public void setDinamicFormFields(JPanel panel, Object name, Object valor) {
        for (Component comp : panel.getComponents()) {
            if (name.equals(comp.getName())) {
                if (comp instanceof JTextField) {
                    JTextField txtField = (JTextField) comp;
                    txtField.setText((String) valor);
                } else if (comp instanceof JFormattedTextField) {
                    JFormattedTextField formatTextField = (JFormattedTextField) comp;
                    formatTextField.setText((String) valor);
                } else if (comp instanceof JComboBox) {
                    JComboBox comboBox = (JComboBox) comp;
                    comboBox.setSelectedItem(valor);
                } else if (comp instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) comp;
                    for (Component comp2 : scrollPane.getViewport().getComponents()) {
                        if (comp2 instanceof JTextArea) {
                            JTextArea textArea = (JTextArea) comp2;
                            textArea.setText((String) valor);
                        }
                    }
                }
            }
        }
    }

    public Object getDinamicFormFields(JPanel panel, Object name) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JTextField) {
                if (comp.getName().equals(name)) {
                    JTextField txtField = (JTextField) comp;
                    return txtField.getText();
                }
            } else if (comp instanceof JFormattedTextField) {
                if (comp.getName().equals(name)) {
                    JFormattedTextField formattedTextField = (JFormattedTextField) comp;
                    return formattedTextField.getText();
                }
            } else if (comp instanceof JComboBox) {
                if (comp.getName().equals(name)) {
                    JComboBox comboBox = (JComboBox) comp;
                    return comboBox.getSelectedItem();
                }
            } else if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                for (Component comp2 : scrollPane.getViewport().getComponents()) {
                    if (comp2 instanceof JTextArea) {
                        if (comp.getName().equals(name)) {
                            JTextArea textArea = (JTextArea) comp2;
                            return textArea.getText();
                        }
                    }
                }
            }
        }
        return null;
    }

}

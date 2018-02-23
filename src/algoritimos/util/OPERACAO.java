/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.util;

/**
 *
 * @author tiago.teixeira
 */
public enum OPERACAO {
    NOVO(0), CANCELAR(1), SALVAR(3), EDITAR(4), FECHAR(1), IMPRIMIR(5), DELETAR(1), ALTERAR(2);
    
    int valorOperacao;
    
    OPERACAO(int valor){
        valorOperacao = valor;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.regex;

/**
 *
 * @author Tiago
 */
public class RegexUtil {

    private static String[] listaRegex = new String[]{
        "^(?i:(<>.*))$",
        "^(?i:.*<>.*)$",
        "^(?i:<>)$",
        "^(?!(?i:<>)$)",
        "^(?i:.*<>)$",
        "^(?!(?i:.*<>.*)$)"
    };

    /**
     * Método estático que devolve uma expressão regular incensitive case
     * conforme solicitado para realização de pesquisa ou filtros especiais
     *
     * @param regex Constante contendo regex solicitado
     * <ul>
     * <li>COMECACOM - Pesquisa somente inicio da string</li>
     * <li>CONTEM - Pesquisa toda a string obdecendo a ordem em que os
     * caracteres aparecem</li>
     * <li>IGUAL - Pesquisa exatamente o que contem a string</li>
     * <li>DIFERENTE - Pesquisa somente o que a string não contem</li>
     * <li>TERMINACOM - Pesquisa somente o fim a string</li>
     * <li>NAOCONTEM - Pesquisa o que a string não contem obedecendo a ordem em
     * que os caracteres aparecem</li>
     * </ul>
     * @param texto Texto que utilizará a expressão regular
     * @return Texto modificado contendo a expressão regular
     */
    public static String getRegex(REGEX regex, String texto) {
        texto = regex == REGEX.CONTEM || regex == REGEX.NAOCONTEM ? texto.replaceAll(" ", ".*") : texto;
        return listaRegex[regex.getValue()].replace("<>", texto);
    }

    /**
     * Metacaracteres
     * . -> busca qualquer caracter
     * \d -> busca qualquer número [0-9]
     * \D -> busca qualquer caracter que não seja número [^0-9]
     * \w -> busca qualquer caracter de letras e números [a-zA-Z_0-9]
     * \W -> busca qualquer caracter que não sejam letras e números [^\w]
     * \s -> busca qualquer caracter de espaço em branco, tabulações [\t\n\x0B\f\r]
     * \S -> busca qualquer caracter sem espaço em branco [^\s]
     * Modificadores
     * (?i:) -> ignora maisculas e minusculas
     * (?m:) -> trabalha multilinhas
     * (?s:) -> faz com que o caracter encontre novas linhas
     * (?x:) -> permite a inclusão de espaços e comentários
     *Quantificadores
     * X{n} -> X procura a ocorrencia de caracteres n vezes
     * X{n,} -> X pelo menos n vezes
     * X{n,m} -> X pelo menos n vezes não mais que m vezes
     * X? -> 0 ou 1 vezes
     * X* -> 0 ou mais vezes
     * X+ -> 1 ou mais vezes
     * Metacaracteres de fronteira
     * ^ -> inicia
     * $ -> finaliza
     * | -> ou (condição)
     * Agrupadores
     * [...] -> Agrupamento
     * [a-z] -> alcance
     * [a-e][i-u] -> união
     * [a-z&&[aeiou]] -> interseção
     * [^abc] -> exeção
     * [a-z&&^[m-p]] -> subtração
     * \x -> fuga literal (usa um caracter propriamente dito sem interromper a expressão)
     * 
     * [a-z] -> representa qualquer letra minúscula do alfabeto [A-Z] ->
     * representa qualquer letra maiúscula do alfabeto [a-zA-Z] -> representa
     * qualquer letra do alfabeto, seja maiúscula ou minúscula | -> representa o
     * 'ou'. "a|b" casa com 'a' ou com 'b', ou com os dois
     *
     * Vejamos alguns quantificadores . -> substitui qualquer caractere -> o
     * caractere anterior aparece nenhuma ou mais vezes + -> o caractere
     * anterior aparece uma ou mais vezes {a} -> o caractere anterior se repete
     * 'a' vezes {a,} -> o caractere anterior se repete pelo menos 'a' vezes
     * {a,b} -> o caractere anterior se repete entre 'a' e 'b' vezes
     *
     * A representação de dígito é '\d', mas dentro de strings, para '\' valer,
     * temos que escapá-la. Logo, podemos representar um CEP como:
     * "\\d\\d\\d\\d\\d-\\d\\d\\d" Ou "\\d{5}-\\d{3}" O método ficaria:
     *
     * meu_cep.matches("\\d{5}-\\d{3}") -> retorna 'true' se o CEP for digitado
     * correto, e 'false' caso errado
     *
     * Para validar um nome, sabemos que o primeiro caractere é maiúsculo:
     * nome.matches("[A-Z][a-zA-Z]*") -> retorna 'true' se a primeira letra do
     * nome for maiúscula, e 'false' caso errado
     *
     * O Java provém alguns métodos para o uso de regex e substituições: std =
     * std.replaceAll("a", "b") -> substitui todas as ocorrências de 'a' por 'b'
     *
     * Também podemos usar expressões regulares no método 'replaceAll': std =
     * std.replaceAll("\\w+","a") -> substitui todas as palavras por 'a' std =
     * std.replaceFirst("\\d","a") -> substitui a primeira ocorrência de um
     * dígito por 'a' String[] pedacos = std.split("a") -> divide a string 'std'
     * em partes, cujo separador é 'a' e armazena no vetor de strings 'pedacos'.
     * Por exemplo, para separarmos as palavras de uma string, usamos "," ou
     * ",\\s*" como separador
     */
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author tiago.teixeira
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapFrameField {
    
    /**
     * Indica o campo de referencia dentro do bean, deve-se informar o nome do campo sempre com a primeira letra maiscula
     * 
     * Exemplo: Usuario.getId()
     * referenceField = "Id"
     * 
     * @return nome do campo de referencia
     */
    public String referenceField();
    /**
     * Tipo do campo de referencia do bean, sempre deve-se informar com o .class
     * 
     * Exemplo: Usuario{ private Long id; getId()}
     * typeReference = Long.class
     * 
     * @return tipo do campo de referencia
     */
    public Class typeReference();
    /**
     * Quando existir referencia de outro objeto dentro do bean deve-se informar o nome dessa classe sempre com a primeira letra maiuscula
     * 
     * Exemplo: Departamento{ private Colaborador colaborador; getColadorador }
     * subClasseReference = "Colaborador"
     * 
     * @return nome da subClasse
     */
    public String subClassReference() default "";
    
}

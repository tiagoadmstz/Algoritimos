/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimos.cast;

import java.util.Objects;

/**
 *
 * @author tiago.teixeira
 */
public class CastFactory {

    public static Object cast(Object object, Class<?> classe) {
        try {
            if (Objects.equals(Long.class, classe) | Objects.equals(long.class, classe)) {
                object = Long.parseLong(object.toString());
            } else if (Objects.equals(Integer.class, classe) | Objects.equals(int.class, classe)) {
                object = Integer.parseInt(object.toString());
            } else if (Objects.equals(Boolean.class, classe) | Objects.equals(boolean.class, classe)) {
                object = Boolean.parseBoolean(object.toString());
            } else if (Objects.equals(String.class, classe)) {
                try {
                    object = String.valueOf(object);
                } catch (Exception ex) {
                    object = object.toString();
                }
            }
            return object;
        } catch (Exception e) {
            return object;
        }
    }

    public static Class castReference(Class classe) {
        if (Objects.equals(Integer.class, classe)) {
            return int.class;
        } else if (Objects.equals(Long.class, classe)) {
            return long.class;
        } else if (Objects.equals(Boolean.class, classe)) {
            return boolean.class;
        }
        return classe;
    }

}

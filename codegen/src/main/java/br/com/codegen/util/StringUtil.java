package br.com.codegen.util;

public class StringUtil {

    /**
     * Converte o primeiro caracter de uma String para maiusculo.
     * <p>
     * 
     * @param string
     *            String a ser convertida
     * @return String
     * @since 1.0
     */
    public String firstToUpperCase(String string) {
        String post = string.substring(1, string.length());
        String first = ("" + string.charAt(0)).toUpperCase();
        return first + post;
    }
}

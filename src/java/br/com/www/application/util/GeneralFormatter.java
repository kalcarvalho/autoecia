/*
 * GeneralFormatter.java
 *
 * Created on 15 de Fevereiro de 2007, 20:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.util;

import java.text.ParseException;
import java.text.*;
import java.util.Date;
import java.util.Locale;


/**
 *
 * @author Administrador
 */
public abstract class GeneralFormatter {
    
    /**
     * Creates a new instance of GeneralFormatter
     */
    
    public static String formatCurrency(double value) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(value);
    }
    
    public static String formatDate(Date date)  {
        DateFormat dfmt = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
        return dfmt.format(date);
    }
    
}

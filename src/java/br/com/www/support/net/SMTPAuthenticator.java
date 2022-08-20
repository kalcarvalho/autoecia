/*
 * SMTPAuthenticator.java
 *
 * Created on 22 de Fevereiro de 2007, 09:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.support.net;

import br.com.www.application.util.SendMailConstants;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author kalcarvalho
 */
public class SMTPAuthenticator extends Authenticator implements SendMailConstants {
    
    /** Creates a new instance of SMTPAuthenticator */
    public SMTPAuthenticator() {
        
    }
    
    
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(SendMailConstants.SEND_MAIL_USERNAME, SendMailConstants.SEND_MAIL_PASSWORD);
    }
    
}

/*
 * SendMail.java
 *
 * Created on 21 de Fevereiro de 2007, 00:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.net;

import br.com.www.application.util.SendMailConstants;
import br.com.www.support.net.SMTPAuthenticator;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;



public class SendMail implements SendMailConstants {
    
    private MimeMessage mimemessage;
    private String from;
    private String to;
    private String server;
    private String subject;
    private String mensagem;
    private Properties properties = new Properties();
    private Session session;
    private String cc;
    private String cco;
    
    public SendMail() {
        
    }
    
    public SendMail(String server, String from, String to, String subject, String mensagem) {
        
        this.setServer(server);
        this.setFrom(from);
        this.setTo(to);
        this.setSubject(subject);
        this.setMensagem(mensagem);
    }
    
    
    public void sendMail() throws AddressException, MessagingException  {
        InternetAddress[] replyTo = new InternetAddress[1];
        Authenticator auth = new SMTPAuthenticator();
        
        properties.put("mail.smtp.host", server);
        properties.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(properties, auth);
        replyTo[0] = new InternetAddress(from);
                
        mimemessage = new MimeMessage(session);
        mimemessage.setFrom(new InternetAddress(SendMailConstants.SEND_MAIL_FROM));
        mimemessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mimemessage.setRecipient(Message.RecipientType.BCC, new InternetAddress(cco));
        mimemessage.setReplyTo(replyTo);
        mimemessage.setSentDate(new Date());
        mimemessage.setSubject(subject);
        mimemessage.setContent(mensagem, "text/html");
        
        Transport.send(mimemessage);
        
    }

    public MimeMessage getMimemessage() {
        return mimemessage;
    }

    public void setMimemessage(MimeMessage mimemessage) {
        this.mimemessage = mimemessage;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCco() {
        return cco;
    }

    public void setCco(String cco) {
        this.cco = cco;
    }
    
}
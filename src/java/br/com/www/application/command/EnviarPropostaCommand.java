/*
 * EnviarPropostaCommand.java
 *
 * Created on 21 de Fevereiro de 2007, 00:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.command;

import br.com.www.application.domain.Carro;
import br.com.www.application.facade.CarroFacade;
import br.com.www.application.handler.ExceptionHandler;
import br.com.www.application.net.SendMail;
import br.com.www.application.util.MessageConstants;
import br.com.www.application.util.PageConstants;
import br.com.www.application.util.SendMailConstants;
import br.com.www.application.util.ServletUtil;
import br.com.www.application.util.Validator;
import br.com.www.support.exception.BusinessException;
import javax.mail.AuthenticationFailedException;
import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
public class EnviarPropostaCommand extends MainCommand {
    
    /** Creates a new instance of EnviarPropostaCommand */
    public EnviarPropostaCommand() {
    }
    
    public void preProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (Validator.isStringEmpty(request.getParameter("mail")) == true) {
            throw new BusinessException(MessageConstants.MESSAGE_CARRO_EMAIL_FIELD_IS_BLANK);
        }
        
        if (Validator.isStringEmpty(request.getParameter("mensagem")) == true) {
            throw new BusinessException(MessageConstants.MESSAGE_CARRO_MENSAGEM_FIELD_IS_BLANK);
        }
        
        if (Validator.isStringEmpty(request.getParameter("nome")) == true) {
            throw new BusinessException(MessageConstants.MESSAGE_CARRO_NOME_FIELD_IS_BLANK);
        }
        
    }
    
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int codigo = Integer.valueOf(request.getParameter(REQUEST_CARRO_CODIGO));
        
        String mail = request.getParameter("mail");
        String msg = "Nome: " + request.getParameter("nome") + "<br>";
        msg = msg + "Mail: " +  request.getParameter("mail") + "<br>";
        msg = msg + "Telefone: " +  request.getParameter("telefone") + "<br>" ;
        msg = msg + "Mensagem: " +  request.getParameter("mensagem") + "<br>";
        
        CarroFacade facade = new CarroFacade();
        
        Carro carro = facade.detalhar(new Carro(codigo));
        
        
        String subject = SendMail.SEND_MAIL_SUBJECT_PROPOSTA 
                       + " " + carro.getVersao().getModelo().getMarca().getDescricao() 
                       + " " + carro.getVersao().getModelo().getDescricao()
                       + " " + carro.getVersao().getDescricao();
        
        
        
        SendMail sendmail = new SendMail(
                SendMail.SEND_MAIL_SERVER,  
                mail,
                request.getParameter("usermail"),
                subject,
                msg
                );
        
        try {
            sendmail.setCco(SendMailConstants.SEND_MAIL_PROPOSTAS);
            sendmail.sendMail();
        } catch(SendFailedException ex ) {
            ex.printStackTrace();
        } catch(AuthenticationFailedException ex) {
            ex.printStackTrace();
            
        } catch(Exception ex) {
            ExceptionHandler handler = new ExceptionHandler();
            handler.doException(request, response, ex);
        }
    }
    
    public void postProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int codigo = Integer.valueOf(request.getParameter(REQUEST_CARRO_CODIGO));

        new CarroFacade().updateProposta(new Carro(codigo));
        
        response.sendRedirect(PAGE_NAME_OBRIGADO);
    }
    
}

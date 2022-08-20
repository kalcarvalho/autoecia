/*
 * VisualizarCarroCommand.java
 *
 * Created on 16 de Fevereiro de 2007, 17:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.command;

import br.com.www.application.domain.Carro;
import br.com.www.application.facade.CarroFacade;
import br.com.www.application.util.ServletUtil;
import br.com.www.application.util.GeneralFormatter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kalcarvalho
 */
public class VisualizarCarroCommand extends MainCommand {
    
    /** Creates a new instance of VisualizarCarroCommand */
    public VisualizarCarroCommand() { super(); }
    
    public void preProcess(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.setAttribute(REQUEST_PARAMETER_PAGE_TITLE, PAGE_TITLE_VISUALIZAR_CARRO);
        
    }
    
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = ServletUtil.loadParameter(REQUEST_PARAMETER_ACTION, request);
        if (action.equalsIgnoreCase(REQUEST_ACTION_PERFORM)) {
            performAction(request, response);
        }
        if (action.equalsIgnoreCase(REQUEST_ACTION_BACK)) {
            backAction(request, response);
        }
    }
    
    private void backAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* String textToFind = ServletUtil.loadParameter( REQUEST_ACTION_BACK , request);
        if (textToFind.trim().length() == 0) {
            textToFind = (String)request.getSession().getAttribute(USUARIO_TEXT_TO_FIND_SESSION);
            textToFind = (textToFind == null) ? "" : textToFind.trim();
        }
         
        UsuarioFacade facade = new UsuarioFacade();
        Collection result = facade.consultarUsuarios(textToFind);
         
        request.getSession().setAttribute(USUARIO_TEXT_TO_FIND_SESSION, textToFind.trim());
        request.setAttribute(REQUEST_PARAMETER_SEARCH_RESULT, result);
        ServletUtil.forward(PAGE_NAME_LISTAR_USUARIOS, PAGE_TITLE_LISTAR_USUARIOS, request, response); */
    }
    
    private void performAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Carro carro = new Carro();
        CarroFacade facade = new CarroFacade();
        
        carro.setCodigo(Integer.parseInt(request.getParameter(REQUEST_CARRO_CODIGO)));
        
        carro = (Carro) facade.detalhar(carro);
        
        request.setAttribute(REQUEST_CARRO_CODIGO, carro.getCodigo());
        request.setAttribute(REQUEST_CARRO_MARCA, carro.getVersao().getModelo().getMarca().getDescricao());
        request.setAttribute(REQUEST_CARRO_MODELO, carro.getVersao().getModelo().getDescricao());
        request.setAttribute(REQUEST_CARRO_ANO, carro.getVersao().getAno());
        request.setAttribute(REQUEST_CARRO_PRECO, GeneralFormatter.formatCurrency(carro.getValor()));
        request.setAttribute(REQUEST_CARRO_PROPOSTAS, carro.getPropostas());
        request.setAttribute(REQUEST_CARRO_VISITAS, carro.getVisitas());
        request.setAttribute(REQUEST_CARRO_VERSAO, carro.getVersao().getDescricao());
        request.setAttribute(REQUEST_CARRO_LOCALIZACAO, "");
        request.setAttribute(REQUEST_CARRO_COMBUSTIVEL, carro.getCombustivel());
        request.setAttribute(REQUEST_CARRO_COR, carro.getCor());
        request.setAttribute(REQUEST_CARRO_PORTAS, carro.getPortas());
        request.setAttribute(REQUEST_CARRO_CAMBIO, carro.getCambio().getDescricao());
        request.setAttribute(REQUEST_CARRO_PLACA, carro.getPlaca());
        request.setAttribute(REQUEST_CARRO_OBSERVACOES, carro.getObservacoes());
        request.setAttribute(REQUEST_CARRO_KM, carro.getQuilometragem());
        request.setAttribute(REQUEST_CARRO_EMAIL, carro.getAnunciante().getUsuario().getEmail());
        request.setAttribute(REQUEST_CARRO_LOCALIZACAO, carro.getAnunciante().getUsuario().getCidade() + " - " +
                                                        carro.getAnunciante().getUsuario().getUf());
        request.setAttribute(REQUEST_CARRO_TELEFONE, "(" + carro.getAnunciante().getUsuario().getDdd() + ")" +
                                                           carro.getAnunciante().getUsuario().getTelefone());
        
        if (carro.getFotos().size() != 0)
            request.setAttribute(REQUEST_CARRO_FOTOS, carro.getFotos());
        else {
            Collection coll = new ArrayList();
            coll.add("imagens/semfoto.png");
            coll.add("imagens/semfoto.png");
            coll.add("imagens/semfoto.png");
            coll.add("imagens/semfoto.png");
            request.setAttribute(REQUEST_CARRO_FOTOS, coll);
        }
        
        if (carro.getOpcional().size() != 0)
            request.setAttribute(REQUEST_CARRO_OPCIONAIS, carro.getOpcional());
        else {
            Collection coll = new ArrayList();
            request.setAttribute(REQUEST_CARRO_OPCIONAIS, coll);
        }
        
        
        
        
        if (carro.isUnicoDono().booleanValue() == true)
            request.setAttribute(REQUEST_CARRO_UNICODONO, "Sim");
        else
            request.setAttribute(REQUEST_CARRO_UNICODONO, "Não");
        
        if (carro.isNovo().booleanValue() == false) {
            
            request.setAttribute(REQUEST_CARRO_TIPOESTADO, "Usado");
            
        } else {
            request.setAttribute(REQUEST_CARRO_KM, carro.getQuilometragem());
            request.setAttribute(REQUEST_CARRO_TIPOESTADO, "Novo");
        }
        
        
        
        
        ServletUtil.forward(PAGE_NAME_VISUALIZAR_CARRO, PAGE_TITLE_VISUALIZAR_CARRO, request, response);
    }
    
}

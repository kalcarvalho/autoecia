<%@ page import="br.com.www.application.util.*"   %>
<%@ page import="br.com.www.application.domain.*" %>
<%@ page import="br.com.www.application.facade.*" %>
<%@ page import="br.com.www.application.io.*"     %>
<%@ page import="br.com.www.support.exception.BusinessException" %>


<%@ page import="java.util.*" %>

<% 

String menu = request.getParameter("menu");


if (menu == null || menu.equals("frame")) {
    String busca = request.getParameter("busca");
    String textToFind = ServletUtil.loadParameter(RequestConstants.REQUEST_PARAMETER_TEXT_TO_FIND, request);
    if (textToFind.trim().length() == 0) {
        textToFind = (String)session.getAttribute(SessionConstants.USUARIO_TEXT_TO_FIND_SESSION);
        textToFind = (textToFind == null) ? "" : textToFind.trim();
    }
    Collection coll = (Collection)request.getAttribute(RequestConstants.REQUEST_PARAMETER_SEARCH_RESULT);
    if (coll == null) {
        coll = new ArrayList();
    }
    
    if (busca.equals("destaques")) {
        
        CarroFacade facade = new CarroFacade();
        coll = facade.listDestaques();
        
        Iterator it = coll.iterator();
        
        out.print("<div align=\"center\">");
        out.print("<img src=\"imagens/destaques.png\">");
        out.print("<br><br>");
        out.print("</div>");
        out.print("<table align=\"center\" bordercolor=\"#FFFFFF\" border=\"1\" cellpadding=\"0\"  cellspacing=\"0\" style=\"font-size: 12px;\" >");
        out.print("<tbody>");
        
        int i = 0;
        while(it.hasNext()) {
            
            Carro carro = (Carro) it.next();
            
            if(i++ % 2 == 0) out.print("<tr bgcolor=\"#FFEEEE\">");
            else out.print("<tr bgcolor=\"#FFCCCC\">");
            
            out.print("<td width=\"60\" bgcolor=white>");
            out.print("<div align=\"center\">");
            out.print("<img src=\"" + carro.getVersao().getModelo().getMarca().getLogo() + "\">");
            out.print("</div>");
            out.print("</td>");
            out.print("<td width=\"300\" >");
            out.print(carro.getDescricao());
            out.print("</td>");
            out.print("<td width=\"65\" >");
            out.print("<div align=\"center\">");
            
            out.print(carro.getVersao().getAno());
            out.print("</div>");
            out.print("</td>");
            out.print("<td width=\"161\" >");
            out.print("<div class=\"preco\">");
            
            out.print(GeneralFormatter.formatCurrency(carro.getValor()));
            out.print("</div>");
            out.print("</td>");
            out.print("<td bgcolor=\"#FFFFFF\" >");
            out.print("<div align=\"left\">");
            out.print("<a href=\"controller?" + RequestConstants.REQUEST_PARAMETER_COMMAND + "=" + RequestConstants.REQUEST_COMMAND_VISUALIZAR_CARRO + "&" + RequestConstants.REQUEST_PARAMETER_ACTION + "=" + RequestConstants.REQUEST_ACTION_PERFORM + "&" + RequestConstants.REQUEST_CARRO_CODIGO + "=" + carro.getCodigo() + "\">");
            
            if (carro.getPossuiFoto().booleanValue() == true) out.print("<img src=\"imagens/fotos.gif\" border=\"0\">");
            else out.print("<img src=\"imagens/view.gif\" border=\"0\">");
            
            out.print("</a>");
            out.print("</div>");
            out.print("</td>");
            out.print("</tr>");
        }
        
        out.print("</tbody>");
        out.print("</table>");
    }
}  else if (menu.equals("acessorios")) {
%><jsp:include flush="true" page="acessorios.jsp"></jsp:include><%
}

%>
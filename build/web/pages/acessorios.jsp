<%@ page import="br.com.www.application.util.*" %> 
<%@ page import="br.com.www.application.domain.*" %> 
<%@ page import="br.com.www.application.facade.*" %> 
<%@ page import="java.util.*" %>


<% 

    out.print("<div align=\"center\" class=\"acessorios\">");
    out.print("<img src=\"imagens/destaques.png\">");
    out.print("<br><br>");
    out.print("</div>");
    out.print("<table align=\"center\" bordercolor=\"#FFFFFF\" border=\"1\" cellpadding=\"0\"  cellspacing=\"0\" style=\"font-size: 12px;\" >");
    out.print("<tbody>");


    out.print("<tr class=\"title\">");
    out.print("<th scope=\"col\" >Descrição do Produto</th>");
    out.print("<th scope=\"col\" >Preço</th>");
    out.print("<th scope=\"col\">Finaliza em:<th>");
    out.print("</tr>");

    AutoPecaFacade facade = new AutoPecaFacade();
    Collection coll = facade.listAtivos();

    Iterator it = coll.iterator();
    AutoPeca autopeca = new AutoPeca();

    int i = 0;

    while(it.hasNext()) {

        autopeca = (AutoPeca) it.next();

        if(i++ % 2 == 0) out.print("<tr class=\"detail\" bgcolor=\"#FFEEEE\">");
        else out.print("<tr class=\"detail\" bgcolor=\"#FFCCCC\">");

        out.print("<td width=\"300\" class=\"unique\">");
        out.print("<a href=" +  autopeca.getUrl() + " target=\"_blank\" >" + autopeca.getDescricao() + "</a>");
        out.print("</td>");
        out.print("<td width=\"100\" align=\"center\"class=\"price\">");
        out.print(GeneralFormatter.formatCurrency(autopeca.getValor()));
        out.print("</td>");
        out.print("<td align=\"center\" class=\"unique\">");
        out.print(GeneralFormatter.formatDate(autopeca.getFinaliza()));
        out.print("</td>");
        out.print("</tr>");
    }

    out.print("</div>");
    out.print("</tbody>");
    out.print("</table>");
%>

<%@ page import="br.com.www.application.util.*" %> 
<%@ page import="br.com.www.application.domain.*" %> 
<%@ page import="br.com.www.application.facade.*" %> 
<%@ page import="java.util.*" %>
<%
if  (request.getParameter(RequestConstants.REQUEST_PARAMETER_COMMAND).equalsIgnoreCase(RequestConstants.REQUEST_COMMAND_VISUALIZAR_CARRO)) {
    
    Integer codigo = (Integer) request.getAttribute(RequestConstants.REQUEST_CARRO_CODIGO);
    String marca = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_MARCA);
    String modelo = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_MODELO);
    Integer ano = ((Integer) request.getAttribute(RequestConstants.REQUEST_CARRO_ANO));
    Long km = ((Long) request.getAttribute(RequestConstants.REQUEST_CARRO_KM));
    String tipoestado = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_TIPOESTADO);
    String preco = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_PRECO);
    Long visitas = ((Long) request.getAttribute(RequestConstants.REQUEST_CARRO_VISITAS));
    Long propostas = ((Long) request.getAttribute(RequestConstants.REQUEST_CARRO_PROPOSTAS));
    String versao = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_VERSAO);
    String localizacao = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_LOCALIZACAO);
    String combustivel = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_COMBUSTIVEL);
    String unicoDono = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_UNICODONO);
    String cor = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_COR);
    Integer portas = ((Integer) request.getAttribute(RequestConstants.REQUEST_CARRO_PORTAS));
    String cambio = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_CAMBIO);
    Integer placa = ((Integer) request.getAttribute(RequestConstants.REQUEST_CARRO_PLACA));
    String obs = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_OBSERVACOES);
    Collection colfotos = (Collection) request.getAttribute(RequestConstants.REQUEST_CARRO_FOTOS);
    Collection opcionais = (Collection) request.getAttribute(RequestConstants.REQUEST_CARRO_OPCIONAIS);
    String telefone = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_TELEFONE);
    String email = (String) request.getAttribute(RequestConstants.REQUEST_CARRO_EMAIL);
    
    String foto = "imagens/semfoto.png";
    int fotos = 1;
    
    if (request.getParameter("foto") != null) {
        
        fotos = Integer.parseInt((String) request.getParameter("foto"));
        
        if (fotos == 0) fotos = 4;
        else if(fotos == 5) fotos = 1;
        
        ArrayList coll = (ArrayList) colfotos;
        
        foto = coll.get(fotos-1).toString();
        
    } else {
        foto = (String) colfotos.iterator().next();
    }



%>	

<table width="800" cellSpacing=0 cellPadding=0 align=center border=0 class="destaque">
<tr>
    <th colspan="2" scope="col" class="titulo"  bgcolor=#FFCCCC><%=marca %> | <%=modelo %> | <%=ano.intValue() %> | <% if(tipoestado == "Usado" && km.longValue() == 0)  out.print("N/I"); else out.print(km.longValue() + " Km"); %> | <%=tipoestado %></th>
</tr>

<tr>
<th rowspan="5" scope="col" width="50%" class="fotos">  
    <br>
    <img border="1" src= "<%=foto%>"></img>
    <br> 
    
    <table class="destaque">
        <tr class="fotos">
            <th scope="col" class="anterior"><% out.print("<a href=\"controller?" + RequestConstants.REQUEST_PARAMETER_COMMAND + "=" + RequestConstants.REQUEST_COMMAND_VISUALIZAR_CARRO + "&" + RequestConstants.REQUEST_PARAMETER_ACTION + "=" + RequestConstants.REQUEST_ACTION_PERFORM + "&" + RequestConstants.REQUEST_CARRO_CODIGO + "=" + (codigo.toString()) + "&foto=" + (Integer.toString(fotos - 1)) + "\">"); %>                   
            << anterior </a> </th>
            <th scope="col" class="centro"><%=colfotos.size()%> Fotos  </th>
            <th scope="col" class="proxima"><% out.print("<a href=\"controller?" + RequestConstants.REQUEST_PARAMETER_COMMAND + "=" + RequestConstants.REQUEST_COMMAND_VISUALIZAR_CARRO + "&" + RequestConstants.REQUEST_PARAMETER_ACTION + "=" + RequestConstants.REQUEST_ACTION_PERFORM + "&" + RequestConstants.REQUEST_CARRO_CODIGO + "=" + (codigo.toString()) + "&foto=" + (Integer.toString(fotos + 1)) + "\">"); %>
                próxima >>
            </a></th>
        </tr>
    </table>
    
</th>
<th valign="top" scope="col"><div class="detalhe">PRE&Ccedil;O: <%=preco%>
    <br>LOCALIZA&Ccedil;&Atilde;O: <%=localizacao %> 
    <br>PROPOSTAS: <%=propostas.longValue() %> 
    <br>VISITAS: <%=visitas.longValue()+1%>
    
    <span class="item" align="center">
        <form>
            <input type ="button" class="button" value="Enviar Proposta" onClick="open('proposta.jsp?<%=RequestConstants.REQUEST_CARRO_TELEFONE%>=<%=telefone%>&<%=RequestConstants.REQUEST_CARRO_EMAIL%>=<%=email%>&<%=RequestConstants.REQUEST_CARRO_CODIGO%>=<%=codigo%>', 'new', 'width=450,height=350,top=100,left=100,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no')">
        </form>
        
    </span>
</th>
</tr>

</table>
<table width="800" cellSpacing=0 cellPadding=0 align=center border=0 class="destaque">
    <tr>
        <th colspan="4" class="titulo" scope="col" bgcolor=#FFCCCC>CARACTER&Iacute;STICAS</th>
    </tr>
    
    <tr class="caracteristicas">
        <th scope="col"><%=(marca + " " + modelo)%></th>
        <th scope="col">Versão: <%=versao%> </th>
        <th scope="col">Preço: <%=preco%></th>
        <th scope="col">Localização: <%=localizacao%> </th>
    </tr>
    <tr class="caracteristicas">
        <th scope="col">Ano: <%=ano%></th>
        <th scope="col">Qulimetragem: <% if(tipoestado == "Usado" && km.longValue() == 0)  out.print("N/I"); else out.print(km.longValue() + " Km"); %></th>
        <th scope="col">Combustivel: <%=combustivel%></th>
        <th scope="col">Único dono? <%=unicoDono%></th>
    </tr>
    <tr class="caracteristicas">
        <th scope="col">Cor: <%=cor%> </th>
        <th scope="col">Portas: <%=portas.intValue()%> </th>
        <th scope="col">Câmbio: <%=cambio%></th>
        <th scope="col">Placa Final: <%=placa.intValue()%> </th>
    </tr>
    
</table>			
<% if(opcionais.size() != 0) {
                Iterator it = opcionais.iterator();
%>
<table width="800" cellSpacing=0 cellPadding=0 align=center border=0 class="destaque">
    <tr>
        <th colspan="4" class="titulo" scope="col" bgcolor=#FFCCCC>OPCIONAIS</th>
    </tr>
    <tr>
        <th colspan="4" class="detalhe" scope="col" >
            <% 
            int i = 0;
            while(it.hasNext()) {
                
                if (++i % 4 == 0)
                    out.print("<br>");
                
                Opcional opcional = (Opcional) it.next();
            %>
            <li><%=opcional.getDescricao() %>
            <% } %>
            <br><br>
        </th>
    </tr>
</table>
<% } %>
<table width="800" cellSpacing=0 cellPadding=0 align=center border=0 class="destaque">
    <tr>
        <th colspan="4" class="titulo" scope="col" bgcolor=#FFCCCC>OBSERVAÇÕES</th>
    </tr>
    <tr class="caracteristicas">
        <th colspan="4" scope="col"><%=obs%></th>
    </tr>
</table>


<% 
    if (request.getParameter("foto") == null) {
                
        CarroFacade facade = new CarroFacade();
                
        facade.updateVisitas(new Carro(codigo.intValue()));
                
    }
            
} %>  

<%@ page language = "java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="br.com.www.application.util.*" %> 

<%

    String pageName = ServletUtil.getPageName(request);
    String pageTitle = ServletUtil.getPageTitle(request);
    String successMessage = ServletUtil.loadParameter(RequestConstants.REQUEST_PARAMETER_SUCCESS_MESSAGE, request);
    String errorMessage = ServletUtil.loadParameter(RequestConstants.REQUEST_PARAMETER_ERROR_MESSAGE, request);

%>

<html>
    <head>
        <meta name="keywords" content="carros, tudo para o seu carro, autopeças, ar condicionado, trava elétrica, tunning, seguro de carro, volkswagem, vw, gm, chevrolet, fiat, gol, golf. ford, corsa, astra, pneu, anuncie grátis">
        <meta name="description" content="Tudo para o seu automóvel. Autopeças, seguros de carro, tunning, carros novos e usados. ">


        <title>Autom&oacute;veis e Cia - Tudo para o seu autom&oacute;vel</title>
        <link href="estilo/stylesheet.css" rel="stylesheet" type="text/css">
       
    </head>
    
    <body>
        
        <table width="926" border="0" align="center" cellpadding="0" cellspacing="0" >
            <tr bgcolor="#000000">
                <td width="167" height="90" rowspan="3" bgcolor="#FFFFFF" scope="col"><div align="center"><img src="imagens/autoecia.png" width="147" height="112"></div></td>
                <th colspan="2" bgcolor="#FFFFFF" class="menutop" scope="col"> <iframe src="http://pmssrv.mercadolivre.com.br/jm/PmsSrv?tool=4671436&creativity=41601&new=Y&ovr=Y" 
                    width="397" height="72" scrolling="no" frameborder="0" marginheight="0" marginwidth="0">
                </iframe><br><br> </th>
            </tr>
            <tr bgcolor="#000000">
            
            <td height="39" colspan="2" bgcolor="#FFFFFF" class="busca" scope="col">
                <table>
                    <tr>
                    <jsp:include flush="true" page="busca.jsp"></jsp:include>
                    <tr>
                </table>
            </td>
            
            </tr>
            <tr bgcolor="#000000">
                <th colspan="2" bgcolor="#FFFFFF" scope="col">&nbsp;</th>
            </tr>
            <tr class="style1">
                <td>&nbsp;</td>
                <td class="busca">&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr class="menu">
                <td valign="top" class="menulateral"> <div align="center" class="menu"></div>      
                <div align="left" class="titulo">Categorias</div><jsp:include flush="true" page="menu.jsp"></jsp:include><br>    </td>
                <td width="579" valign="top" class="busca"><div align="center" valign="top" class="destaque"><jsp:include flush="true" page="<%=pageName%>"></jsp:include></div></td>
                <td width="180" class="lateral"><div align="center" class="lateral">
                    <% if(pageName == PageConstants.PAGE_NAME_PAGE) { %>
                    <iframe src="http://smartad.mercadolivre.com.br/jm/SmartAd?tool=4675934&creativity=40002&new=N&ovr=N&bgcol=FFFFFF&brdcol=FFFFFF&txtcol=ff0000&lnkcol=0099cc&hvrcol=0000ff&prccol=009900&word=seguro&word=carro&word=usado&site=MLB" 
                        width="120" height="240" scrolling="no" frameborder="0" marginheight="0" marginwidth="0">
                    </iframe>
                    <% } %>
                </div></td>
            </tr>
            <tr>
                <td height="48" colspan="3"><div align="center" class="rodape">
                    (C) 2006 Todos os direitos reservados - Kal Carvalho - autoecia@autoecia.net 
                    <br>Todas as marcas comerciais, nomes de marcas, marcas de serviço e logotipos aqui mencionados são de propriedade de suas respectivas empresas. 

                </div></td>
            </tr>
        </table>
    </body>
</html>

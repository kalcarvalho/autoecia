<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title>Autom&oacute;veis e Cia - Tudo para o seu autom&oacute;vel</title>
        <link href="estilo/stylesheet.css" rel="stylesheet" type="text/css">
        
    </head>

    <body>
        <div class="proposta">
            Ao ligar, informar ter visto o anúncio na www.autoecia.net.<br>
        </div>
        <br>
        Telefone: <%=request.getParameter("telefone") %>
        
        <form name="form1" method="post" action="controller?command=enviarProposta">
            <input type="hidden" name="usermail" value="<%=request.getParameter("email") %>"> 
            <input type="hidden" name="codigo" value="<%=request.getParameter("codigo") %>"> 
            <table align="center" cellspacing="5">
                <tr>
                    <th scope="col"><div align="right">Nome:</div></th>
                    <th scope="col"><input type="text" class="field" name="nome"></th>
                </tr>
                <tr>
                    <th scope="col"><div align="right">E-mail:</div></th>
                    <th scope="col"><input type="text" name="mail"></th>
                </tr>
                <tr>
                    <th scope="col"><div align="right">Telefone:</div></th>
                    <th scope="col"><input type="text" name="telefone"></th>
                </tr>
                <tr>
                    <th valign="top" scope="col"><div align="right">Mensagem:</div></th>
                    <th scope="col"><textarea name="mensagem"></textarea></th>
                </tr>
                <tr>
                    <th colspan="2" valign="top" scope="col"><input type="submit" class="button" name="Submit" value="Enviar"></th>
                </tr>
            </table>
        </form>

    </body>
</html>

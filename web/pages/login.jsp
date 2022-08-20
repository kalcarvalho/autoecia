<%@ page import="br.com.sistac.www.application.util.*" %>
    <br>
    <table style="width: 550px; height: 213px;" border="0" valign="top" cellpadding="0" cellspacing="0">
        <tbody>
            <tr>
                <td>
                    <div style="margin-left: 10px;" align="left">
                        <img src="imagens/area.png">
                    </div>
                </td>
            </tr>
            <tr>
                <td style="font-family: Verdana; font-size: 12px; vertical-align: top; ">
                    <div style="margin-left: 20px;" align="justify">

                        <form action="controller" method="POST">
                            <input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>" value="<%=RequestConstants.REQUEST_COMMAND_EFETUAR_LOGIN%>"/>
                            <input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_ACTION%>" value="<%=RequestConstants.REQUEST_ACTION_PERFORM%>"/>
                    
                            <table align="center" valign="middle" width=50 border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="right" valign="middle" width="100%" >
                                        <span class="fieldTitle">
                                            Login:
                                        </span>
                                    </td>
                                    <td align="left" valign="middle" width="100%" >
                                        <input type="text" name="<%=RequestConstants.REQUEST_USUARIO_CHAVE%>" class="field">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" valign="middle" width="100%" >
                                        <span class="fieldTitle">
                                            Senha:
                                        </span>
                                    </td>
                                    <td align="left" valign="middle" width="100%" >
                                        <input type="password" name="<%=RequestConstants.REQUEST_USUARIO_SENHA%>" class="field">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    </td>
                                    <td align="right" valign="middle">
                                        <input type="submit" value="Enviar" class="button">
                                    </td>
                                </tr>
                            </table>
                        </form>

                    </div>
                </td>
            </tr>
        </tbody>
    </table>
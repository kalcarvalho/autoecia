<%@ page import="br.com.sistac.www.application.util.*" %> 
<%@ page import="br.com.sistac.www.application.domain.*" %> 
<%@ page import="java.util.*" %>
<%
	String textToFind = ServletUtil.loadParameter(RequestConstants.REQUEST_PARAMETER_TEXT_TO_FIND, request);
	if (textToFind.trim().length() == 0) {
		textToFind = (String)session.getAttribute(SessionConstants.USUARIO_TEXT_TO_FIND_SESSION);
		textToFind = (textToFind == null) ? "" : textToFind.trim();
	}
	Collection coll = (Collection)request.getAttribute(RequestConstants.REQUEST_PARAMETER_SEARCH_RESULT);
	if (coll == null) {
		coll = new ArrayList();
	}
%>
<table align="left" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" height="1%" style="border-bottom: 1px solid #333333; padding-bottom:10px;">
			<form name="listarUsuariosForm" method="POST" action="controller">
				<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>" value="<%=RequestConstants.REQUEST_COMMAND_LISTAR_USUARIOS%>"/>
				<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_ACTION%>" value="<%=RequestConstants.REQUEST_ACTION_PERFORM%>"/>
				<span class="fieldTitle">Pesquisar: </span><input type="text" name="<%=RequestConstants.REQUEST_PARAMETER_TEXT_TO_FIND%>" value="<%=textToFind%>" size="20" class="field"/>
				<input type="submit" value="Pesquisar" class="button"/>
			</form>
			</td>
		<td align="right" valign="middle" height="1%" style="border-bottom: 1px solid #333333; padding-bottom:10px;">
			<form name="adicionarUsuarioForm" method="POST" action="controller">
				<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>" value="<%=RequestConstants.REQUEST_COMMAND_ADICIONAR_USUARIO%>"/>
				<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_ACTION%>" value="<%=RequestConstants.REQUEST_ACTION_SHOW_FORM%>"/>
				<input type="submit" value="Criar Novo Usu&aacute;rio" class="button"/>
			</form>
			</td> 			
	</tr>
	<%
		if (coll.size() > 0) {
	%>
	<tr>
		<td align="left" valign="top" height="99%" colspan="2">
			<table align="center" cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr class="resultSearchHeader">
					<td align="center" valign="middle" colspan="3" width="3%" nowrap="nowrap">
						Ações
					</td>
					<td align="left" valign="middle" width="1%" nowrap="nowrap">
						Chave
					</td>
					<td align="left" valign="middle" width="94%" nowrap="nowrap">
						Nome
					</td>
					<td align="left" valign="middle" width="1%" nowrap="nowrap">
						Status
					</td>
					<td align="left" valign="middle" width="1%" nowrap="nowrap">
						Perfil
					</td>
				</tr>
				<%
					Iterator it = coll.iterator();
					Usuario usuarioFromSession = ServletUtil.getUsuarioFromSession(request);
					String estilo = "resultSearchPar";
					while (it.hasNext()) {
						Usuario usuarioResult = (Usuario)it.next();
				%>
				<tr class="<%=estilo%>">
					<td align="center" valign="middle" width="1%" nowrap="nowrap">
						<a href="controller?<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>=<%=RequestConstants.REQUEST_COMMAND_VISUALIZAR_USUARIO%>&<%=RequestConstants.REQUEST_PARAMETER_ACTION%>=<%=RequestConstants.REQUEST_ACTION_PERFORM%>&<%=RequestConstants.REQUEST_USUARIO_CHAVE%>=<%=usuarioResult.getChave()%>">
							<img src="../imagens/eye.gif" border="0" alt="Detalhar usuário"/>
						</a>
					</td>
					<td align="center" valign="middle" width="1%" nowrap="nowrap">
					<%
						if (!usuarioResult.getChave().equalsIgnoreCase(usuarioFromSession.getChave())) {
					%>
							<a href="controller?<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>=<%=RequestConstants.REQUEST_COMMAND_EDITAR_USUARIO%>&<%=RequestConstants.REQUEST_PARAMETER_ACTION%>=<%=RequestConstants.REQUEST_ACTION_SHOW_FORM%>&<%=RequestConstants.REQUEST_USUARIO_CHAVE%>=<%=usuarioResult.getChave()%>">
								<img src="../imagens/pencil.gif" border="0" alt="Editar usuário"/>
							</a>
					<%
						} else {
							out.println("&nbsp;");
						} // fim if
					%>
					</td>
					<td align="center" valign="middle" width="1%" nowrap="nowrap">
					<%
						if (!usuarioResult.getChave().equalsIgnoreCase(usuarioFromSession.getChave())) {
					%>
							<a href="controller?<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>=<%=RequestConstants.REQUEST_COMMAND_REMOVER_USUARIO%>&<%=RequestConstants.REQUEST_PARAMETER_ACTION%>=<%=RequestConstants.REQUEST_ACTION_PERFORM%>&<%=RequestConstants.REQUEST_USUARIO_CHAVE%>=<%=usuarioResult.getChave()%>">
								<img src="../imagens/trash.gif" border="0" alt="Excluir usuário"/>
							</a>
					<%
						} else {
							out.println("&nbsp;");
						} // fim if
					%>
					</td>
					<td align="left" valign="middle" width="1%" nowrap="nowrap">
						<%=usuarioResult.getChave()%>
					</td>
					<td align="left" valign="middle" width="96%" nowrap="nowrap">
						<%=usuarioResult.getNome()%>
					</td>
					<td align="left" valign="middle" width="1%" nowrap="nowrap">
						<%=(usuarioResult.isAtivado().booleanValue() == true)? "Ativado" : "Desativado"%>
					</td>
					<td align="left" valign="middle" width="1%" nowrap="nowrap">
						<%=usuarioResult.getPerfil().getDescricao()%>
					</td>
				</tr>
				<%
						if (estilo.equalsIgnoreCase("resultSearchPar")) {
							estilo = "resultSearchImpar";
						} else {
							estilo = "resultSearchPar";
						}
					} // fim while
				%>
			</table>
			</td>
	</tr>
	<%
		} else {
	%>
	<tr>
		<td align="left" valign="top" height="99%" colspan="2">
			&nbsp;
		</td>
	</tr>
	<%
		} // fim if
	%>
</table>
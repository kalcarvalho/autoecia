<%@ page import="br.com.sistac.www.application.util.*" %> 
<%@ page import="br.com.sistac.www.application.domain.*" %> 
<%@ page import="br.com.sistac.www.application.facade.*" %> 
<%@ page import="java.util.*" %>
<%
	if  (request.getParameter(RequestConstants.REQUEST_PARAMETER_COMMAND).equalsIgnoreCase(RequestConstants.REQUEST_COMMAND_VISUALIZAR_USUARIO)) {
		
%>	

	<table width="548" cellpadding="2">
				<p>
				<b> Chave: </b> <%= request.getAttribute(RequestConstants.REQUEST_USUARIO_CHAVE) %>
				</p>
				<p>
				<b> Nome: </b> <%= request.getAttribute(RequestConstants.REQUEST_USUARIO_NOME) %>		
				</p>
				<p>
				<b> E-Mail: </b> <%= request.getAttribute(RequestConstants.REQUEST_USUARIO_EMAIL) %>		
				</p>
				<p>
				<b> Telefone: </b> (<%= request.getAttribute(RequestConstants.REQUEST_USUARIO_DDD) %>) 
				<%=request.getAttribute(RequestConstants.REQUEST_USUARIO_TELEFONE)%>
				</p>
				<p>
				<b> Ramal: </b> <%= request.getAttribute(RequestConstants.REQUEST_USUARIO_RAMAL) %>		
				</p>
				<p>
				<b> Perfil: </b> <%= request.getAttribute(RequestConstants.REQUEST_USUARIO_PERFIL) %>		
				</p>
				<p>
				<b> Status: </b> <%= request.getAttribute(RequestConstants.REQUEST_USUARIO_STATUS) %>		
				</p>
				
				<form name="visualizarUsuariosForm" method="POST" action="controller">
					<td align=center>
						<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>" value="<%=RequestConstants.REQUEST_COMMAND_VISUALIZAR_USUARIO %>"/>
						<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_ACTION%>" value="<%=RequestConstants.REQUEST_ACTION_BACK%>"/>
						<input type="submit" value="Voltar" class="button"/>
						</form>
					</td>
				</form>

	</table>
<%
	}
	else  {
		Collection coll = new PerfilFacade().listAllAtivos() ;
		String value = "";
		
		if (coll == null) coll = new ArrayList();

%>
<%
	if  (request.getParameter(RequestConstants.REQUEST_PARAMETER_COMMAND).equalsIgnoreCase(RequestConstants.REQUEST_COMMAND_ADICIONAR_USUARIO)) {
%>
	<form name="incluirUsuariosForm" method="POST" action="controller">
		<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>" value="<%=RequestConstants.REQUEST_COMMAND_ADICIONAR_USUARIO %>"/>
		<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_ACTION%>" value="<%=RequestConstants.REQUEST_ACTION_PERFORM%>"/>
		<table width="548" cellpadding="2">
                  <tr>
                    <td width="190"><div align="right">Chave:</div></td>
                    <td width="124">
                    
                    <%
                    	value = (request.getAttribute(RequestConstants.REQUEST_USUARIO_CHAVE) == null) ? "" : "value=\"" + request.getAttribute(RequestConstants.REQUEST_USUARIO_CHAVE) + "\"";
                    	
                    %>
                    	<input name="<%=RequestConstants.REQUEST_USUARIO_CHAVE%>" type="text" <%= value %> size="20"></td>
                    <td width="57"><div align="right">Senha:</strong></div></td>
                    <td width="149">
                      <div align="left">
                        <input name="<%=RequestConstants.REQUEST_USUARIO_SENHA%>" type="password" size="15">
                      </div></td>
                  </tr>
                  <tr>
                    <td height="20"><div align="right">Nome:</strong></div></td>
                    <td colspan="3">
                    <%
                    	value = (request.getAttribute(RequestConstants.REQUEST_USUARIO_NOME) == null) ? "" : "value=\"" + request.getAttribute(RequestConstants.REQUEST_USUARIO_NOME) + "\"";
                    	
                    %>
                    <input name="<%=RequestConstants.REQUEST_USUARIO_NOME%>" type="text" <%= value %> size="50"></td>
                  </tr>
                  <tr>
                    <td><div align="right">E-Mail:</strong></div></td>
                    <td colspan="3">
                    <%
                    	value = (request.getAttribute(RequestConstants.REQUEST_USUARIO_EMAIL) == null) ? "" : "value=\"" + request.getAttribute(RequestConstants.REQUEST_USUARIO_EMAIL) + "\"";
                    	
                    %>
                    <input name="<%=RequestConstants.REQUEST_USUARIO_EMAIL%>" type="text" <%= value %> size="50"></td>
                  </tr>
                  <tr>
                    <td><div align="right">DDD:</strong></div></td>
                    <td colspan="3">
					<%
                    	value = (request.getAttribute(RequestConstants.REQUEST_USUARIO_DDD) == null) ? "" : "value=\"" + request.getAttribute(RequestConstants.REQUEST_USUARIO_DDD) + "\"";
                    	
                    %>
                    <input name="<%=RequestConstants.REQUEST_USUARIO_DDD%>" type="text" <%= value %> size="10"></td>
                  </tr>
                  <tr>
                    <td><div align="right">Telefone:</strong></div></td>
                    <td colspan="3">
                    <%
                    	value = (request.getAttribute(RequestConstants.REQUEST_USUARIO_TELEFONE) == null) ? "" : "value=\"" + request.getAttribute(RequestConstants.REQUEST_USUARIO_TELEFONE) + "\"";
                    	
                    %>
                    <input name="<%=RequestConstants.REQUEST_USUARIO_TELEFONE%>" type="text" <%= value %> size="20"></td>
                  </tr>
                  <tr>
                    <td><div align="right">Ramal:</strong></div></td>
                    <td colspan="3">
                    <%
                    	value = (request.getAttribute(RequestConstants.REQUEST_USUARIO_RAMAL) == null) ? "" : "value=\"" + request.getAttribute(RequestConstants.REQUEST_USUARIO_RAMAL) + "\"";
                    	
                    %>
                    <input name="<%=RequestConstants.REQUEST_USUARIO_RAMAL%>" type="text" <%= value %> size="15"></td>
                  </tr>
                  <tr>
                    <td><div align="right">Perfil:</strong></div></td>
                    <td colspan="3"><select name="<%=RequestConstants.REQUEST_USUARIO_CODIGO_PERFIL%>">
                    <%
                    
                    value = (request.getAttribute(RequestConstants.REQUEST_USUARIO_CODIGO_PERFIL) == null) ? "" : request.getAttribute(RequestConstants.REQUEST_USUARIO_CODIGO_PERFIL).toString();
                    
						Iterator it = coll.iterator();
	                    String option_perfil = "";
						String option_status = "1";
						
						while (it.hasNext()) {
							Perfil perfilResult = (Perfil) it.next() ;
							
							if (value.equalsIgnoreCase(perfilResult.getCodigo())) 
								option_perfil =  "<option value=\"" + perfilResult.getCodigo() + "\" selected> " + perfilResult.getDescricao() + "</option>";
							else
								option_perfil =  "<option value=\"" + perfilResult.getCodigo() + "\"> " + perfilResult.getDescricao() + "</option>";
						
					%>
					<%= option_perfil %>
					<% } %>
                    </select></td>
                  </tr>
                  <tr>
                    <td><div align="right">Status:</strong></div></td>
                    <%
	                    value = (request.getAttribute(RequestConstants.REQUEST_USUARIO_STATUS) == null) ? "" : request.getAttribute(RequestConstants.REQUEST_USUARIO_STATUS).toString();
                    %>
                    <td colspan="3"><select name="<%=RequestConstants.REQUEST_USUARIO_STATUS%>">
                      <% 

	                    if(option_status.equalsIgnoreCase((String) request.getAttribute(RequestConstants.REQUEST_USUARIO_STATUS))) {
	                    	option_status = "<option value=\"1\" selected>Ativado</option>" + 
										    "<option value=\"0\">Desativado</option>";
	                    }
	                    else {
	                    	option_status = "<option value=\"1\" >Ativado</option>" + 
										    "<option value=\"0\" selected>Desativado</option>";
	                    }
                	%>
                	<%= option_status %>
                                        </select></td>
                  </tr>
                  
                  <td></td>
                   <td align=right>
                   		<input type="submit" value="Confirmar" class="button"/>
                   		</form>
                   	<td align=left >
						<form name="cancelarUsuariosForm" method="POST" action="controller">
							<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>" value="<%=RequestConstants.REQUEST_COMMAND_EDITAR_USUARIO %>"/>
							<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_ACTION%>" value="<%=RequestConstants.REQUEST_ACTION_BACK%>"/>
							<td align=left><input type="submit" value="Cancelar" class="button"/>
						</form>
                  </td>
              </table>


		
	<% } else { %>
	
		<form name="editarUsuariosForm" method="POST" action="controller">
		<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>" value="<%=RequestConstants.REQUEST_COMMAND_EDITAR_USUARIO %>"/>
		<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_ACTION%>" value="<%=RequestConstants.REQUEST_ACTION_PERFORM%>"/>
		<input type="hidden" name="<%=RequestConstants.REQUEST_USUARIO_CHAVE%>" value="<%= request.getAttribute(RequestConstants.REQUEST_USUARIO_CHAVE) %>" />
		<table width="548" cellpadding="2">
                  <tr>
                    <td width="190"><div align="right">Chave:</div></td>
                    <td width="124">
                    	<%= request.getAttribute(RequestConstants.REQUEST_USUARIO_CHAVE) %></td>
                    <td width="57"><div align="right">Senha:</strong></div></td>
                    <td width="149">
                      <div align="left">
                        <input name="<%=RequestConstants.REQUEST_USUARIO_SENHA%>" type="password" size="15">
                      </div></td>
                  </tr>
                  <tr>
                    <td height="20"><div align="right">Nome:</strong></div></td>
                    <td colspan="3"><input name="<%=RequestConstants.REQUEST_USUARIO_NOME%>" type="text" value="<%= request.getAttribute(RequestConstants.REQUEST_USUARIO_NOME) %>" size="50"></td>
                  </tr>
                  <tr>
                    <td><div align="right">E-Mail:</strong></div></td>
                    <td colspan="3"><input name="<%=RequestConstants.REQUEST_USUARIO_EMAIL%>" type="text" value="<%= request.getAttribute(RequestConstants.REQUEST_USUARIO_EMAIL) %>" size="50"></td>
                  </tr>
                  <tr>
                    <td><div align="right">DDD:</strong></div></td>
                    <td colspan="3"><input name="<%=RequestConstants.REQUEST_USUARIO_DDD%>" type="text" value="<%= request.getAttribute(RequestConstants.REQUEST_USUARIO_DDD) %>" size="10"></td>
                  </tr>
                  <tr>
                    <td><div align="right">Telefone:</strong></div></td>
                    <td colspan="3"><input name="<%=RequestConstants.REQUEST_USUARIO_TELEFONE%>" type="text" value="<%= request.getAttribute(RequestConstants.REQUEST_USUARIO_TELEFONE) %>" size="20"></td>
                  </tr>
                  <tr>
                    <td><div align="right">Ramal:</strong></div></td>
                    <td colspan="3"><input name="<%=RequestConstants.REQUEST_USUARIO_RAMAL%>" type="text" value="<%= request.getAttribute(RequestConstants.REQUEST_USUARIO_RAMAL) %>" size="15"></td>
                  </tr>
                  <tr>
                    <td><div align="right">Perfil:</strong></div></td>
                    <td colspan="3"><select name="<%=RequestConstants.REQUEST_USUARIO_CODIGO_PERFIL%>">
                    <%
						Iterator it = coll.iterator();
						String option_perfil = "";
						String option_status = "1";
						
						while (it.hasNext()) {
							Perfil perfilResult = (Perfil) it.next() ;
							if(perfilResult.getDescricao().equalsIgnoreCase( (String) request.getAttribute(RequestConstants.REQUEST_USUARIO_PERFIL)))  {					 
								option_perfil = "<option value=" + perfilResult.getCodigo() + " selected>" + perfilResult.getDescricao() + "</option>";
							}
							else {
								option_perfil = "<option value=" + perfilResult.getCodigo() + ">" + perfilResult.getDescricao() + "</option>";
							}
							
							
					%>
						<%= option_perfil %>
					<%
						}
					%>
                    </select></td>
                  </tr>
                  <tr>
                    <td><div align="right">Status:</strong></div></td>
                    <td colspan="3"><select name="<%=RequestConstants.REQUEST_USUARIO_STATUS%>">
                    <% 

	                    if(option_status.equalsIgnoreCase((String) request.getAttribute(RequestConstants.REQUEST_USUARIO_STATUS))) {
	                    	option_status = "<option value=\"1\" selected>Ativado</option>" + 
										    "<option value=\"0\">Desativado</option>";
	                    }
	                    else {
	                    	option_status = "<option value=\"1\" >Ativado</option>" + 
										    "<option value=\"0\" selected>Desativado</option>";
	                    }
                	%>
                	<%= option_status %>
					</select></td>
                  </tr>
                  <td></td>
                   <td align=right>
                   		<input type="submit" value="Confirmar" class="button"/>
                   	</td>
                   	<td>
                   	</form>
                   		<form name="cancelarUsuariosForm" method="POST" action="controller">
							<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_COMMAND%>" value="<%=RequestConstants.REQUEST_COMMAND_ADICIONAR_USUARIO %>"/>
							<input type="hidden" name="<%=RequestConstants.REQUEST_PARAMETER_ACTION%>" value="<%=RequestConstants.REQUEST_ACTION_BACK %>"/>
							<input type="submit" value="Cancelar" class="button"/>
						</form>
                  </td>
                  
              </table>

			
	
	<% } %>

<% } %>

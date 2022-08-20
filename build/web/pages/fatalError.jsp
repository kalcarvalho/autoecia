<%@ page import="br.com.www.application.util.*" %>
<%
	Throwable exc = (Throwable)request.getAttribute(RequestConstants.REQUEST_PARAMETER_FATAL_ERROR_EXCEPTION);
	String exceptionMessage = "";
	String exceptionDetail = "";
	if (exc == null) {
		exceptionMessage = "Exceção desconhecida !";
		exceptionDetail = "";
	} else {
		exceptionMessage = exc.getMessage();
		exceptionDetail = exc.toString();
	}
%>
<link href="estilo/stylesheet.css" rel="stylesheet" type="text/css">
<table align="center" cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td align="center" valign="middle" class="fatalErrorMessage">
			<%=exceptionMessage%>
		</td>
	</tr>
	<tr>
		<td align="center" valign="middle" class="fatalErrorDetail">
			<%=exceptionDetail%>
		</td>
	</tr>
</table>
<div align="center">
    <a href="javascript:history.back(-1);">Voltar</a>
</div>
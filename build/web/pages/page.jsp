<%@ page import="br.com.www.application.util.*"   %>
<%@ page import="br.com.www.application.domain.*" %>
<%@ page import="br.com.www.application.facade.*" %>
<%@ page import="br.com.www.application.io.*"     %>
<%@ page import="br.com.www.support.exception.BusinessException" %>


<%@ page import="java.util.*" %>

<%

    String frame = request.getParameter("menu");
    Collection coll = new ArrayList();
    Usuario sessionUser = ServletUtil.getUsuarioOf(request);

    if (sessionUser == null) {

        UsuarioFacade facade = new UsuarioFacade();
        sessionUser = facade.efetuarLogin("visitante","123");

    }

    if (frame == null) {
        frame = "frame";
    }

    FuncaoFacade facade = new FuncaoFacade();
    Funcao funcao = facade.findByParameter(frame);
    
    
    if (funcao.getAcao().equals("frame")) {
            %><jsp:include flush="true" page="finder.jsp?busca=destaques"></jsp:include><%
    }
    
    
        
%>
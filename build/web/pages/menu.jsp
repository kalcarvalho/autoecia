<%@ page import="br.com.www.application.util.*" %>
<%@ page import="br.com.www.application.domain.*" %>
<%@ page import="br.com.www.application.facade.*" %>
<%@ page import="br.com.www.application.io.*" %>

<%@ page import="java.util.*" %>

<%

    Usuario sessionUser = ServletUtil.getUsuarioOf(request);
    boolean showMenu = false;



    if (sessionUser == null) {

        UsuarioFacade facade = new UsuarioFacade();
        try {
            sessionUser = facade.efetuarLogin("visitante","123");
            showMenu = true;
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }

    } else {
        showMenu = true;
    }

    if (showMenu == false) {

        out.print("&nbsp;");

    } else {

        Iterator it = sessionUser.getPerfil().listFuncoes().iterator();


%>
        <table width="160" border="0" cellspacing="0" cellpadding="0" class="menubar">
        
            <%   

                while (it.hasNext()) {
                Funcao funcaoMenu = (Funcao) it.next();
                if (funcaoMenu.isAtivado().booleanValue() == true &&
                        funcaoMenu.isMenuFunction().booleanValue() == true) {
            %>
            
            <tr>
                <td align="left" valign="middle" colspan="2" class="option">
                    <div align="center">
                        <% if(funcaoMenu.isMenuRestrito().booleanValue() == true) { %>
                        <a href="controller?command=<%=funcaoMenu.getAcao()%>" ><%=funcaoMenu.getDescricao()%></a>
                        <% } else { %>
                        <a href="<%=funcaoMenu.getAcao()%>.jsp?menu=<%=funcaoMenu.getParametro()%>" ><%=funcaoMenu.getDescricao()%></a>
                        <% } %>
                    </div>
                </td>
            </tr>
            
            <%

                    }
                    }    %>
            <tr>
                <td align="center">
                    <br>
                    <script type="text/javascript">
                        <!--
                        google_ad_client = "pub-4471805171066214";
                        google_ad_width = 120;
                        google_ad_height = 90;
                        google_ad_format = "120x90_0ads_al";
                        //2007-02-12: carros, carros usados
                        google_ad_channel = "5278678229+1255493025";
                        google_color_border = "FFcccc";
                        google_color_bg = "FFcccc";
                        google_color_link = "6131BD";
                        google_color_text = "FFFFFF";
                        google_color_url = "FFFF00";
                        
                        //-->
                    </script>
                    <script type="text/javascript"
                        src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
                    </script>
                    <br><br>
<%                  
                    String counter = request.getParameter("menu");
                        
                    if ((counter == null || PageConstants.PAGE_NAME_FRAME.contains(counter)) && request.getParameter("command") == null)  {
%>
                            <div id="eXTReMe"><a href="http://extremetracking.com/open?login=autoecia">
                            <img src="http://t1.extreme-dm.com/i.gif" style="border: 0;"
                            height="38" width="41" id="EXim" alt="eXTReMe Tracker" /></a>
                            <script type="text/javascript"><!--
                                var EXlogin='autoecia' // Login
                                var EXvsrv='s10' // VServer
                                EXs=screen;EXw=EXs.width;navigator.appName!="Netscape"?
                                EXb=EXs.colorDepth:EXb=EXs.pixelDepth;
                                navigator.javaEnabled()==1?EXjv="y":EXjv="n";
                                EXd=document;EXw?"":EXw="na";EXb?"":EXb="na";
                                EXd.write("<img src=http://e1.extreme-dm.com",
                                "/"+EXvsrv+".g?login="+EXlogin+"&amp;",
                                "jv="+EXjv+"&amp;j=y&amp;srw="+EXw+"&amp;srb="+EXb+"&amp;",
                                "l="+escape(EXd.referrer)+" height=1 width=1>");//-->
                            </script><noscript><div id="neXTReMe"><img height="1" width="1" alt=""
                                src="http://e1.extreme-dm.com/s10.g?login=autoecia&amp;j=n&amp;jv=n" />
                            </div></noscript></div>
<%                  
                  
                    }

%>

                </td>
            </tr>
        </table>
 <%

        
        }

 %>
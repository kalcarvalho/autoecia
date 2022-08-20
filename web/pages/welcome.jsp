<%@ page import="br.com.sistac.www.application.util.*"   %>
<%@ page import="br.com.sistac.www.application.domain.*" %>
<%@ page import="br.com.sistac.www.application.facade.*" %>
<%@ page import="br.com.sistac.www.application.io.*"     %>
<%@ page import="br.com.sistac.www.support.exception.BusinessException" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.io.File" %>

<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.imageio.ImageIO" %>



<table style="width: 550px; height: 213px;" border="0" valign="top" cellpadding="0" cellspacing="0">
    <tbody>
        <tr>
            <td>
                <div style="margin-left: 10px;" align="left">
                    <% try { 
                            
                            out.print("Bem vindo, " + ServletUtil.getUsuarioOf(request).getNome()); 
                            BufferedImage b1 = ImageIO.read(new File("c://t.gif"));
                            BufferedImage b2 = ImageIO.read(new File("c://t2.gif"));
                            
                            if (ServletUtil.compareImage(b1, b2)) {
                                out.print("Verdadeiro");
                            }
                            
                        } catch(NullPointerException ex) {
                            }
                            
                    %>
                </div>
            </td>
        </tr>
        <tr>
            <td style="font-family: Verdana; font-size: 13px; vertical-align: top; ">
                <div style="margin-left: 20px;" align="justify" >
                    <a href="controller?command=efetuarLogoff" >Efetuar Logoff</a>
                </td>
            </tr>
        </tbody>
    </table>



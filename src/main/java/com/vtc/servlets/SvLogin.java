package com.vtc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.vtc.modelo.Controladora;
import com.vtc.modelo.Driver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public SvLogin() {
        super();
    }

    // protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     response.setContentType("text/html;charset=UTF-8");
    //     request.getRequestDispatcher("login.jsp").forward(request, response);
    // }
    // ES PARA LOS DOS METODOS, GET Y POST, PERO NO SE USA

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("Nikc: " + request.getParameter("usuario") + "<br>");
        out.println("Password: " + request.getParameter("password") + "<br>");
        out.println("Método GET no implementado en SvLogin.<br>");
        out.println("</body></html>");

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
 
        Controladora control = new Controladora();
        Driver conductor = control.buscarDriverPorNickYPass(usuario, password);

        if (conductor != null) {
            request.getSession().setAttribute("usuario_logueado", conductor);
            //response.sendRedirect("inicioConductor.jsp"); ESTO CREO QUE REDIRIGE A LA PAGINA DE INICIO DEL CONDUCTOR SIN LOGUEAR
            request.getRequestDispatcher("inicioConductor.jsp").forward(request, response); 
        } else {
            response.sendRedirect("error.jsp");
        }
        System.out.println(
            "Usuario: " + usuario + ", Password: " + password);
        System.out.println(
            "Resultado búsqueda: " + 
            (conductor != null ? "ENCONTRADO" : "NO ENCONTRADO"));
    }
    

    
}

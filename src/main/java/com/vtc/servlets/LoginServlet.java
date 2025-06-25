package com.vtc.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String tipo = request.getParameter("tipo");

        // Aquí iría la lógica para autenticar al usuario (consultar base de datos, etc.)
        if ("admin".equals(usuario) && "1234".equals(password)) {
            response.sendRedirect("bienvenida.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}

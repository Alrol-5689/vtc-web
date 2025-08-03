package com.vtc.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.vtc.modelo.Driver;
import com.vtc.modelo.Controladora;

@WebServlet(name = "SvEliminarConductor", urlPatterns = {"/SvEliminarConductor"})
public class SvEliminarConductor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Driver conductor = (Driver) request.getSession().getAttribute("usuario_logueado");

        if (conductor != null) {
            Controladora control = new Controladora();
            control.eliminarConductor(conductor.getId());

            request.getSession().invalidate();
        }

        response.sendRedirect("index.jsp");

    }

}

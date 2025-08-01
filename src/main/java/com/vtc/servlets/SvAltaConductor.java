package com.vtc.servlets;

import java.io.IOException;

import com.vtc.excepciones.DniInvalidoException;
import com.vtc.excepciones.PinInvalidoException;
import com.vtc.modelo.Controladora;
import com.vtc.modelo.Driver;
import com.vtc.util.Validador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvAltaConductor")
public class SvAltaConductor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nick = request.getParameter("nick");
        String password = request.getParameter("password");
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");

        // Validación básica
        if (nick == null || password == null || dni == null || nombre == null || telefono == null || email == null ||
            nick.isBlank() || password.isBlank() || dni.isBlank() || nombre.isBlank() || telefono.isBlank() || email.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos obligatorios deben estar rellenados.");
            return;
        }

        Validador v = new Validador();
        try {
            //v.validarPin(password);
            v.validarDni_formato(dni);
        } catch (DniInvalidoException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        Driver conductor = new Driver();
        conductor.setNick(nick);
        conductor.setPassword(password);
        conductor.setDni(dni);
        conductor.setNombre(nombre);
        conductor.setApellido1(apellido1);
        conductor.setApellido2(apellido2);
        conductor.setTelefono(telefono);
        conductor.setEmail(email);

        Controladora control = new Controladora();
        control.crearDriver(conductor);


        response.sendRedirect("login.jsp");
    }

}

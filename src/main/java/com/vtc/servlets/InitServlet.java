package com.vtc.servlets;

import com.vtc.persistencia.JpaUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "InitServlet", urlPatterns = {"/InitServlet"}, loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        System.out.println("[InitServlet] Inicializando JPA...");
        // Aquí puedes forzar el acceso a EntityManager para que se cree el esquema
        try {
            JpaUtil.getEntityManager().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

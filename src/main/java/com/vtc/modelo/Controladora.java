package com.vtc.modelo;

import com.vtc.persistencia.ControladoraPersistencia;

public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void crearDriver(Driver driver) {
        controlPersis.crearDriver(driver);
    }

    public void eliminarDriver(Long id) {
        controlPersis.eliminarDriver(id);
    }

	public Driver buscarDriverPorNickYPass(String nick, String password) {
        Driver encontrado = new Driver();
		encontrado = controlPersis.buscaDriver(nick, password);
        return encontrado;
	}

}

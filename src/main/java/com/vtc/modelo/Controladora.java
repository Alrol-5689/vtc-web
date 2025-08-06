package com.vtc.modelo;

import java.util.List;

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
        Driver encontrado = controlPersis.buscaDriver(nick, password);
        return encontrado;
    }

    public void eliminarConductor(Long id) {
        controlPersis.eliminarDriver(id);
    }

    public List<DriverDay> buscarDiasPorConductor(Long idConductor) {
        return controlPersis.findByDriverId(idConductor);
    }

}

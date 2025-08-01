package com.vtc.persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.Driver;

public class ControladoraPersistencia {

    DriverJpaController driverJpa = new DriverJpaController();
    ConvenioJpaController convenioJpa = new ConvenioJpaController();

    //===>> MÉTODODS CONDUCTORES <<===//

    public void crearDriver(Driver driver) {
        driverJpa.create(driver);
    }

    public void eliminarDriver(Long id) {
        try {
            driverJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(
                Level.SEVERE, 
                "Error al eliminar el conductor con id: " + id, 
                ex);
        }      
    }

    public Driver buscaDriver(String nick, String password){
        Driver encontrado = new Driver();
        encontrado = driverJpa.findByNickAndPassword(nick, password);
        return encontrado;
    }

}

package com.vtc.persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.Driver;
import com.vtc.modelo.DriverDay;

public class ControladoraPersistencia {

    DriverJpaController driverJpa = new DriverJpaController();
    DriverDayJpaController driverDayJpa = new DriverDayJpaController();
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

    //===>> MÉTODOS DÍAS DE CONDUCTOR <<===//

    public List<DriverDay> findByDriverId(Long idConductor) {
        List<DriverDay> dias = null;
        try {
            dias = driverDayJpa.findByDriverId(idConductor);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(
                Level.SEVERE, 
                "Error al buscar los días del conductor con id: " + idConductor, 
                e);
        }
        return dias;
    }

}

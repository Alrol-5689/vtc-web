package com.vtc.util;

import com.vtc.excepciones.DniInvalidoException;
import com.vtc.excepciones.PinInvalidoException;

public class Validador {
	
	public void validarPin(String pin) throws PinInvalidoException {
		if (pin == null || !pin.matches("\\d{4}")) {
			throw new PinInvalidoException("el PIN debe tener 4 dígitos.");
		}
	}
	
	public void validarDni_formato(String dni) throws DniInvalidoException {
		if (!dni.matches("\\d{8}[A-Za-z]")) {
			throw new DniInvalidoException("formato incorrecto.");
		}
	}

}

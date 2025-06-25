package com.vtc.excepciones;

public class TelefonoInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public TelefonoInvalidoException(String mensaje) {
		super(mensaje);
	}

}
